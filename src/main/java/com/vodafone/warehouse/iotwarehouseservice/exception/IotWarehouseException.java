package com.vodafone.warehouse.iotwarehouseservice.exception;

import com.vodafone.warehouse.iotwarehouseservice.model.Error;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;

public class IotWarehouseException extends RuntimeException {

    private final transient Error error;
    private final String logMessage;

    public IotWarehouseException(Error error, String logMessage) {
        super(logMessage);
        this.error = error;
        this.logMessage = logMessage;
    }

    public IotWarehouseException(Throwable cause, Error error, String logMessage) {
        super(logMessage, cause);
        this.error = error;
        this.logMessage = logMessage;
    }

    public String getLogMessage() {
        String message = this.logMessage != null ? ":" + this.logMessage : "";
        return NestedExceptionUtils.buildMessage(message, this.getCause());
    }

    public Error getError(){
        return this.error;
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.resolve(Integer.valueOf(error.getCode()));
    }
}
