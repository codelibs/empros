/*
 * Copyright 2012-2020 CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.codelibs.empros.store;

import java.util.List;
import java.util.Map;

import org.codelibs.elasticsearch.client.HttpClient;
import org.codelibs.empros.event.Event;
import org.codelibs.empros.exception.EmprosDataStoreException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkItemResponse.Failure;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.seasar.util.lang.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticSearchStore implements DataStore {

    private static final Logger logger = LoggerFactory
            .getLogger(ElasticSearchStore.class);

    public static final String HTTP_ADDRESS = "empros.es.http_address";

    protected String address;

    protected Client client;

    protected int requestEventSize = 2;

    protected String index;

    protected String type;

    protected String idKey = "id";

    public ElasticSearchStore() {
        // nothing
        address = System.getProperty(HTTP_ADDRESS, "localhost:9200").trim();
    }

    public void init() {
        if (StringUtil.isBlank(index)) {
            throw new EmprosDataStoreException("EEME0001",
                    new Object[] { "index" });
        }
        if (StringUtil.isBlank(type)) {
            throw new EmprosDataStoreException("EEME0001",
                    new Object[] { "type" });
        }
        final Settings settings = Settings.builder().putList("http.hosts", address).build();
        client = new HttpClient(settings, null);
    }

    public void destroy() {
        if (client != null) {
            client.close();
        }
    }

    /* (non-Javadoc)
     * @see org.codelibs.empros.store.DataStore#store(java.util.List)
     */
    @Override
    public void store(final List<Event> eventList,
            final DataStoreListener listener) {
        partialStore(eventList, 0, listener);
    }

    protected void partialStore(final List<Event> eventList, final int pos,
            final DataStoreListener listener) {
        final int start = pos;
        int last = start + requestEventSize;
        final int size = eventList.size();
        if (last > size) {
            last = size;
        }
        try {
            final BulkRequestBuilder bulkRequst = client.prepareBulk();
            final List<Event> subEventList = eventList.subList(start, last);
            if (logger.isDebugEnabled()) {
                logger.debug("Sending to ElasticSearch: {}", subEventList);
            }
            for (final Event event : subEventList) {
                final XContentBuilder contentBuilder = XContentFactory
                        .jsonBuilder().startObject();
                for (final Map.Entry<String, Object> entry : event.entrySet()) {
                    contentBuilder.field(entry.getKey(), entry.getValue());
                }

                contentBuilder.endObject();
                final String id = event.getID(idKey);
                bulkRequst.add(client.prepareIndex(index, type, id).setSource(
                        contentBuilder));
            }

            final int nextPos = pos + requestEventSize;
            bulkRequst.execute(new ActionListener<BulkResponse>() {
                @Override
                public void onResponse(final BulkResponse response) {
                    if (logger.isDebugEnabled()) {
//                        logger.debug("Response: took: {}, header: {}", response
//                                .getTook().toString(), bulkRequst.getHeaders());
                        for (final BulkItemResponse item : response.getItems()) {
                            logger.debug(
                                    "Response Item: id: {}, index: {}, itemId: {}, opType: {}, type: {}, version: {}",
                                    item.getId(), item.getIndex(),
                                    item.getItemId(), item.getOpType(),
                                    item.getType(), item.getVersion());
                            final Failure failure = item.getFailure();
                            if (failure != null) {
                                logger.debug(
                                        "Response Failure: id: {}, index: {}, type: {}, message: {}",
                                        failure.getId(), failure.getIndex(),
                                        failure.getType(), failure.getMessage());
                            }
                        }
                    }
                    if (nextPos < size) {
                        partialStore(eventList, nextPos, listener);
                    } else {
                        listener.onSuccess(ElasticSearchStore.this, eventList);
                    }
                }

                @Override
                public void onFailure(final Exception t) {
                    listener.onFailure(t);
                }
            });
        } catch (final Exception e) {
            listener.onFailure(e);
        }
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public int getRequestEventSize() {
        return requestEventSize;
    }

    public void setRequestEventSize(final int requestEventSize) {
        this.requestEventSize = requestEventSize;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(final String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getIdKey() {
        return idKey;
    }

    public void setIdKey(final String idKey) {
        this.idKey = idKey;
    }
}
