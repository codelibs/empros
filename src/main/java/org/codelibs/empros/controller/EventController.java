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
package org.codelibs.empros.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.codelibs.empros.dto.ErrorResponseDto;
import org.codelibs.empros.dto.ErrorResponseDto.ErrorDto;
import org.codelibs.empros.dto.EventResponseDto;
import org.codelibs.empros.event.Event;
import org.codelibs.empros.exception.EmprosClientException;
import org.codelibs.empros.processor.EventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Callable<EventResponseDto> create(@RequestBody final Object data) {
        if (logger.isInfoEnabled()) {
            logger.info("event request");
        }

        return new Callable<EventResponseDto>() {
            @Override
            public EventResponseDto call() throws Exception {
                final List<Event> eventList = new ArrayList<>();
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
                            throw new EmprosClientException("EEM0001",
                                    new Object[] { obj });
                        }
                    }
                } else {
                    throw new EmprosClientException("EEM0001",
                            new Object[] { data });
                }

                int processed = 0;
                if (!eventList.isEmpty()) {
                    processed = eventProcessor.invoke(eventList);
                }

                final EventResponseDto responseDto = new EventResponseDto();
                responseDto.setStatus("ok");
                responseDto.setReceived(eventList.size());
                responseDto.setProcessed(processed);
                return responseDto;
            }
        };
    }

    @RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public void index() {
        throw new EmprosClientException("EEM0001", new Object[0]);
    }

    @ExceptionHandler(EmprosClientException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto handlerEmprosClientException(
            final EmprosClientException e) {
        final ErrorDto errorDto = new ErrorResponseDto.ErrorDto();
        errorDto.setCode(e.getMessageCode());
        errorDto.setMessage(e.getMessage());
        final ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setStatus("error");
        responseDto.setError(errorDto);
        return responseDto;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDto handlerException(final Exception e) {
        logger.error("System Error occured.", e);

        final ErrorDto errorDto = new ErrorResponseDto.ErrorDto();
        errorDto.setCode("SYSTEM");
        errorDto.setMessage(e.getMessage());
        final ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setStatus("error");
        responseDto.setError(errorDto);
        return responseDto;
    }

    protected String getCreatedBy() {
        return ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest().getRemoteAddr();
    }

}
