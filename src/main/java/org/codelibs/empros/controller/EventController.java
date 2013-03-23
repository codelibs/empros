package org.codelibs.empros.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public EventResponseDto create(@RequestBody final Object data) {
        if (logger.isInfoEnabled()) {
            logger.info("event request");
        }

        final List<Event> eventList = new ArrayList<>();
        if (data instanceof Map<?, ?>) {
            final Event event = new Event(
                    (Map<? extends String, ? extends Object>) data);
            eventList.add(event);
        } else if (data instanceof List<?>) {
            for (final Object obj : (List<?>) data) {
                if (obj instanceof Map<?, ?>) {
                    final Event event = new Event(
                            (Map<? extends String, ? extends Object>) obj);
                    eventList.add(event);
                } else {
                    throw new EmprosClientException("EEM0001",
                            new Object[] { obj });
                }
            }
        } else {
            throw new EmprosClientException("EEM0001", new Object[] { data });
        }

        if (!eventList.isEmpty()) {
            eventProcessor.process(eventList);
        }

        final EventResponseDto responseDto = new EventResponseDto();
        responseDto.setStatus("ok");
        responseDto.setProcessed(eventList.size());
        return responseDto;
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
        final ErrorDto errorDto = new ErrorResponseDto.ErrorDto();
        errorDto.setCode("SYSTEM");
        errorDto.setMessage(e.getMessage());
        final ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setStatus("error");
        responseDto.setError(errorDto);
        return responseDto;
    }

}
