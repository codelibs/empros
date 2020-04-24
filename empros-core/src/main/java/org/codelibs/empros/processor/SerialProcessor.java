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

import java.util.ArrayList;
import java.util.List;

import org.codelibs.empros.util.ProcessorUtil;

/**
 * SerialProcessor is a serial event processor class.
 * 
 * @author shinsuke
 *
 */
public class SerialProcessor extends DispatchProcessor {

    public SerialProcessor() {
        super(new ArrayList<EventProcessor>());
    }

    public SerialProcessor(final List<EventProcessor> processorList) {
        super(processorList);
    }

    @Override
    protected void invokeNext(final ProcessContext context,
            final ProcessorListener listener) {
        final int size = nextProcessorList.size();
        try {
            if (size == 0) {
                ProcessorUtil.finish(context, this, listener);
            } else {
                ProcessorListener nextCallback = new ProcessorListener() {
                    @Override
                    public void onSuccess(final ProcessContext context) {
                        ProcessorUtil.finish(context, SerialProcessor.this,
                                listener);
                    }

                    @Override
                    public void onFailure(final Throwable t) {
                        ProcessorUtil.fail(context, SerialProcessor.this,
                                listener, t);
                    }
                };
                final ProcessContext currentContext = context;
                for (int i = size - 1; i >= 0; i--) {
                    final EventProcessor currentProcessor = nextProcessorList
                            .get(i);
                    final ProcessorListener currentCallback = nextCallback;
                    if (i == 0) {
                        currentProcessor.process(context, currentCallback);
                    } else {
                        nextCallback = new ProcessorListener() {
                            @Override
                            public void onSuccess(final ProcessContext context) {
                                currentProcessor.process(currentContext,
                                        currentCallback);
                            }

                            @Override
                            public void onFailure(final Throwable t) {
                                currentCallback.onFailure(t);
                            }
                        };
                    }
                }
            }
        } catch (final Throwable t) { // NOPMD
            ProcessorUtil.fail(context, this, listener, t);
        }
    }

}
