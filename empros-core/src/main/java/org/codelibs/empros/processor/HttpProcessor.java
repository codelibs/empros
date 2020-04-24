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
package org.codelibs.empros.processor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.codelibs.empros.event.Event;
import org.codelibs.empros.exception.EmprosClientException;
import org.codelibs.empros.exception.EmprosConfigException;
import org.codelibs.empros.exception.EmprosHttpRequestException;
import org.codelibs.empros.util.ProcessorUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpProcessor implements EventProcessor {
    protected CloseableHttpAsyncClient httpClient;

    protected String defaultUrl;

    protected ObjectMapper objectMapper;

    protected String requestEncoding = "UTF-8";

    public HttpProcessor() {
        initHttpClient();
        initJson();
    }

    protected void initHttpClient() {
        try {
            final IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                    .setSoTimeout(3000)
                    .setConnectTimeout(3000)
                    .build();
            httpClient = HttpAsyncClientBuilder.create()
                    .setDefaultIOReactorConfig(ioReactorConfig)
                    .build();
            httpClient.start();
        } catch (final Exception e) {
            throw new EmprosConfigException("EEMC0007", e);
        }
    }

    protected void initJson() {
        objectMapper = new ObjectMapper();
    }

    public void destroy() {
        try {
            httpClient.close();
        } catch (final IOException e) {
            // ignore
            // throw new EmprosClientException("Failed to close HttpClient", e);
        }
    }

    @Override
    public void process(final ProcessContext context,
            final ProcessorListener listener) {
        final List<Event> eventList = ProcessorUtil
                .getCurrentEventList(context);
        final Map<String, List<Event>> eventMap = aggregateEvents(eventList);
        final AtomicInteger count = new AtomicInteger(eventMap.size());
        try {
            for (final Map.Entry<String, List<Event>> entry : eventMap
                    .entrySet()) {
                final HttpPost request = getHttpPost(entry.getKey());
                final HttpEntity entity = getHttpEntity(entry.getValue());
                request.setEntity(entity);
                httpClient.execute(request, new FutureCallback<HttpResponse>() {

                    @Override
                    public void completed(final HttpResponse response) {
                        context.addNumOfProcessedEvents(eventList.size());
                        if (count.decrementAndGet() == 0) {
                            ProcessorUtil.finish(context, HttpProcessor.this,
                                    listener);
                        }
                    }

                    @Override
                    public void failed(final Exception e) {
                        EventProcessor processor = null;
                        if (count.decrementAndGet() == 0) {
                            processor = HttpProcessor.this;
                        }
                        ProcessorUtil.fail(context, processor, listener, e);
                    }

                    @Override
                    public void cancelled() {
                        ProcessorUtil.fail(context, HttpProcessor.this,
                                listener,
                                new EmprosHttpRequestException("WEMC0002",
                                        new Object[] { request.toString() }));
                    }

                });
            }
        } catch (final Throwable t) {
            ProcessorUtil.fail(context, this, listener, t);
        }
    }

    protected HttpEntity getHttpEntity(final List<Event> eventList) {
        return new StringEntity(getBodyContent(eventList), requestEncoding);
    }

    protected String getBodyContent(final List<Event> eventList) {
        try {
            return objectMapper.writeValueAsString(eventList);
        } catch (final JsonProcessingException e) {
            throw new EmprosHttpRequestException("EEMC0009",
                    new Object[] { eventList.toString() }, e);
        }
    }

    protected HttpPost getHttpPost(final String u) {
        return new HttpPost(u);
    }

    protected Map<String, List<Event>> aggregateEvents(
            final List<Event> eventList) {
        final Map<String, List<Event>> eventMap = new HashMap<String, List<Event>>();
        for (final Event event : eventList) {
            final String url = getUrl(event);
            List<Event> list = eventMap.get(url);
            if (list == null) {
                list = new ArrayList<Event>();
                eventMap.put(url, list);
            }
            list.add(event);
        }
        return eventMap;
    }

    protected String getUrl(final Event event) {
        return defaultUrl;
    }

}
