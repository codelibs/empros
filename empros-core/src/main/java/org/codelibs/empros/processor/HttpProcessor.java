package org.codelibs.empros.processor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.codelibs.empros.event.Event;
import org.codelibs.empros.exception.EmprosConfigException;
import org.codelibs.empros.exception.EmprosHttpRequestException;
import org.codelibs.empros.util.ProcessorUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpProcessor implements EventProcessor {
    protected HttpAsyncClient httpClient;

    protected String defaultUrl;

    protected ObjectMapper objectMapper;

    protected String requestEncoding = "UTF-8";

    public HttpProcessor() {
        initHttpClient();
        initJson();
    }

    protected void initHttpClient() {
        try {
            httpClient = new DefaultHttpAsyncClient();
            final HttpParams params = httpClient.getParams();
            params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 3000)
                    .setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
                            3000)
                    .setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE,
                            8 * 1024)
                    .setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true);
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
            httpClient.shutdown();
        } catch (final InterruptedException e) {
            // ignore
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
        try {
            final HttpEntity entity = new StringEntity(
                    getBodyContent(eventList), requestEncoding);
            return entity;
        } catch (final UnsupportedEncodingException e) {
            throw new EmprosHttpRequestException("EEMC0008",
                    new Object[] { requestEncoding }, e);
        }
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
