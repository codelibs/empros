package org.codelibs.empros.dto;

public class ErrorResponseDto {
    private String status;

    private ErrorDto error;

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public ErrorDto getError() {
        return error;
    }

    public void setError(final ErrorDto error) {
        this.error = error;
    }

    public static class ErrorDto {
        private String code;

        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(final String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(final String message) {
            this.message = message;
        }
    }
}
