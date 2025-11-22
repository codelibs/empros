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
package org.codelibs.empros.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codelibs.empros.server.async.AsyncExecutor;
import org.codelibs.empros.event.Event;
import org.codelibs.empros.exception.EmprosClientException;
import org.codelibs.empros.exception.EmprosProcessException;
import org.codelibs.empros.exception.EmprosProcessTimeoutException;
import org.codelibs.empros.processor.EventProcessor;
import org.codelibs.empros.processor.ProcessContext;
import org.codelibs.empros.server.processor.ProcessorProvider;
import org.codelibs.empros.server.response.EventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Handles requests for events.
 * 
 * @author shinsuke
 */
@RestController
@RequestMapping(value = "/events")
public class EventController {

    private static final Logger logger = LoggerFactory
            .getLogger(EventController.class);

    private final EventProcessor eventProcessor;

    private final AsyncExecutor asyncExecutor;

    public EventController(final ProcessorProvider processorProvider, final AsyncExecutor asyncExecutor) {
        this.eventProcessor = processorProvider.getProcessor();
        this.asyncExecutor = asyncExecutor;
    }

    @RequestMapping(method = { RequestMethod.HEAD})
    public String head() {
        return "";
    }

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public DeferredResult<Object> create(@RequestBody(required = false) Object data) {
        if (logger.isInfoEnabled()) {
            logger.info("event request");
        }

        final List<Event> eventList = new ArrayList<>();
        if (data instanceof Map) {
            @SuppressWarnings("unchecked")
            final Map<?, ?> dataMap = (Map<?, ?>) data;
            final Event event = new Event(dataMap);
            event.setCreatedBy(getCreatedBy());
            event.setCreatedTime(new Date());
            eventList.add(event);
        } else if (data instanceof List) {
            final String createdBy = getCreatedBy();
            final Date now = new Date();
            @SuppressWarnings("unchecked")
            final List<?> dataList = (List<?>) data;
            for (final Object obj : dataList) {
                if (obj instanceof Map) {
                    @SuppressWarnings("unchecked")
                    final Map<?, ?> objMap = (Map<?, ?>) obj;
                    final Event event = new Event(objMap);
                    event.setCreatedBy(createdBy);
                    event.setCreatedTime(now);
                    eventList.add(event);
                } else {
                    throw new EmprosClientException("EEMW0001",
                            new Object[] { obj });
                }
            }
        } else {
            throw new EmprosClientException("EEMW0001", new Object[] { data });
        }

        final DeferredResult<Object> result = new DeferredResult<Object>();
        result.onTimeout( () -> {
                result.setErrorResult(new EmprosProcessTimeoutException(
                        "EEMW0002", new Object[0]));
            }
        );

        final Runnable task = () -> {
            try {
                if (eventList.isEmpty()) {
                    result.setErrorResult(new EmprosClientException("WEMW0001",
                            new Object[0]));
                } else {
                    final ProcessContext processContext = new ProcessContext(
                            eventList, (context) -> {
                            final ProcessContext originalContext = context
                                    .getOriginal();
                            final Throwable[] failures = originalContext
                                    .getFailures();
                            if (failures.length == 0) {
                                final Object response = originalContext
                                        .getResponse();
                                if (response != null) {
                                    result.setResult(response);
                                } else {
                                    final EventResponse eventResponse = new EventResponse();
                                    eventResponse.setStatus("ok");
                                    eventResponse.setReceived(eventList
                                            .size());
                                    eventResponse
                                            .setProcessed(originalContext
                                                    .getProcessed());
                                    result.setResult(eventResponse);
                                }
                            } else {
                                result.setErrorResult(new EmprosProcessException(
                                        failures));
                            }
                        });
                    eventProcessor.process(processContext, null);
                }
            } catch (final Throwable t) {
                logger.error("Unexpected error during event processing", t);
                result.setErrorResult(new EmprosProcessException(
                        new Throwable[] { t }));
            }
        };
        asyncExecutor.generic(task);

        return result;
    }

    protected String getCreatedBy() {
        final var request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest();

        // Check X-Forwarded-For header first (for proxy/load balancer scenarios)
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isEmpty()) {
            // X-Forwarded-For can contain multiple IPs, get the first one (original client)
            int commaIndex = forwardedFor.indexOf(',');
            if (commaIndex > 0) {
                return forwardedFor.substring(0, commaIndex).trim();
            }
            return forwardedFor.trim();
        }

        // Fall back to remote address
        return request.getRemoteAddr();
    }

}
