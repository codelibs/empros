package org.codelibs.empros.dto;

public class EventResponseDto {
    private String status;

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
        this.processed = num;
    }
}
