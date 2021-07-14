package com.vodafone.warehouse.iotwarehouseservice.constant;

import com.vodafone.warehouse.iotwarehouseservice.model.Error;
import org.springframework.http.HttpStatus;

public interface IotWarehouseServiceConstant {

    Error INVALID_REQUEST = new Error()
            .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
            .message("Invalid Request")
            .reasonCode("Invalid Request");

    Error MEDIA_TYPE_NOT_SUPPORTED = new Error()
            .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
            .message("Unsupported Media Type")
            .reasonCode("Unsupported Media Type");

    Error METHOD_NOT_SUPPORTED = new Error()
            .code(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))
            .message("Method Not Allowed")
            .reasonCode("Method Not Allowed");

    Error NOT_FOUND = new Error()
            .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
            .message("Not Found")
            .reasonCode("Not Found");

    Error CONFLICT = new Error()
            .code(String.valueOf(HttpStatus.CONFLICT.value()))
            .message("Conflict")
            .reasonCode("Conflict");
}
