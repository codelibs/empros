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
package org.codelibs.empros.server.response;

/**
 * EventResponse is a response of processed events.
 * 
 * @author shinsuke
 *
 */
public class EventResponse {
    private String status;

    private long received;

    private long processed;

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public long getProcessed() {
        return processed;
    }

    public void setProcessed(final long num) {
        processed = num;
    }

    public long getReceived() {
        return received;
    }

    public void setReceived(final long received) {
        this.received = received;
    }
}
