/*
 * Copyright 2013 the CodeLibs Project and the Others.
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
package org.codelibs.empros.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codelibs.empros.event.Event;
import org.codelibs.empros.exception.EmprosClientException;
import org.codelibs.empros.exception.EmprosProcessException;
import org.codelibs.empros.exception.EmprosProcessTimeoutException;
import org.codelibs.empros.processor.EventProcessor;
import org.codelibs.empros.processor.ProcessContext;
import org.codelibs.empros.processor.ProcessListener;
import org.codelibs.empros.response.ErrorResponse;
import org.codelibs.empros.response.ErrorResponse.ErrorDto;
import org.codelibs.empros.response.EventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Handles requests for events.
 * 
 * @author shinsuke
 */
@Controller(value = "/events")
public class EventController {

    private static final Logger logger = LoggerFactory
            .getLogger(EventController.class);

    @Resource(name = "eventProcessor")
    protected EventProcessor eventProcessor;

    @Resource(name = "taskExecutor")
    protected TaskExecutor taskExecutor;

    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public DeferredResult<Object> create(@RequestBody final Object data) {
        if (logger.isInfoEnabled()) {
            logger.info("event request");
        }

        final List<Event> eventList = new ArrayList<Event>();
        if (data instanceof Map<?, ?>) {
            final Event event = new Event((Map<?, ?>) data);
            event.setCreatedBy(getCreatedBy());
            event.setCreatedTime(new Date());
            eventList.add(event);
        } else if (data instanceof List<?>) {
            final String createdBy = getCreatedBy();
            final Date now = new Date();
            for (final Object obj : (List<?>) data) {
                if (obj instanceof Map<?, ?>) {
                    final Event event = new Event((Map<?, ?>) obj);
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
        result.onTimeout(new Runnable() {
            @Override
            public void run() {
                result.setErrorResult(new EmprosProcessTimeoutException(
                        "EEMW0002", new Object[0]));
            }
        });

        final Runnable task = new Runnable() {
            @Override
            public void run() {
                if (eventList.isEmpty()) {
                    result.setErrorResult(new EmprosClientException("WEMW0001",
                            new Object[0]));
                } else {
                    final ProcessContext context = new ProcessContext(
                            eventList, new ProcessListener() {
                                @Override
                                public void onFinish(
                                        final ProcessContext context) {
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
                                }
                            });
                    eventProcessor.process(context, null);
                }
            }
        };
        taskExecutor.execute(task);

        return result;
    }

    @RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public void index() {
        throw new EmprosClientException("EEMW0001", new Object[0]);
    }

    @ExceptionHandler(EmprosProcessTimeoutException.class)
    @ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
    @ResponseBody
    public ErrorResponse handlerEmprosProcessTimeoutException(
            final EmprosProcessTimeoutException e) {
        final ErrorDto errorDto = new ErrorResponse.ErrorDto();
        errorDto.setCode(e.getMessageCode());
        errorDto.setMessage(e.getMessage());
        final ErrorResponse responseDto = new ErrorResponse();
        responseDto.setStatus("error");
        responseDto.setError(errorDto);
        return responseDto;
    }

    @ExceptionHandler(EmprosClientException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlerEmprosClientException(
            final EmprosClientException e) {
        final ErrorDto errorDto = new ErrorResponse.ErrorDto();
        errorDto.setCode(e.getMessageCode());
        errorDto.setMessage(e.getMessage());
        final ErrorResponse responseDto = new ErrorResponse();
        responseDto.setStatus("error");
        responseDto.setError(errorDto);
        return responseDto;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handlerException(final Exception e) {
        logger.error("System Error occured.", e);

        final ErrorDto errorDto = new ErrorResponse.ErrorDto();
        errorDto.setCode("SYSTEM");
        errorDto.setMessage(e.getMessage());
        final ErrorResponse responseDto = new ErrorResponse();
        responseDto.setStatus("error");
        responseDto.setError(errorDto);
        return responseDto;
    }

    protected String getCreatedBy() {
        return ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest().getRemoteAddr();
    }

}
