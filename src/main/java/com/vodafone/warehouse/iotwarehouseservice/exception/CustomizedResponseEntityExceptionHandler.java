package com.vodafone.warehouse.iotwarehouseservice.exception;

import com.vodafone.warehouse.iotwarehouseservice.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static com.vodafone.warehouse.iotwarehouseservice.constant.IotWarehouseServiceConstant.INVALID_REQUEST;
import static com.vodafone.warehouse.iotwarehouseservice.constant.IotWarehouseServiceConstant.MEDIA_TYPE_NOT_SUPPORTED;
import static com.vodafone.warehouse.iotwarehouseservice.constant.IotWarehouseServiceConstant.METHOD_NOT_SUPPORTED;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.info("ServletRequestBindingException [{}]", ex.getMessage());
        return new ResponseEntity<>(INVALID_REQUEST, headers, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.info("HttpRequestMethodNotSupported [{}]", ex.getMessage());
        return new ResponseEntity<>(METHOD_NOT_SUPPORTED, headers, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.info("HttpMessageNotReadable [{}]", ex.getMessage());
        return new ResponseEntity<>(INVALID_REQUEST, headers, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.info("MethodArgumentNotValid [{}]", ex.getMessage());
        return new ResponseEntity<>(INVALID_REQUEST, headers, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.info("HttpMediaTypeNotSupported [{}]", ex.getMessage());
        return new ResponseEntity<>(MEDIA_TYPE_NOT_SUPPORTED, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler({ConstraintViolationException.class, HttpMessageConversionException.class, MethodArgumentTypeMismatchException.class, DataIntegrityViolationException.class})
    public final ResponseEntity<Error> handleConstraintViolationException(Exception ex, WebRequest webRequest) {
        LOG.error("ConstraintViolationException [{}]", ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(INVALID_REQUEST, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IotWarehouseException.class)
    public final ResponseEntity<Error> handleIotWarehouseException(IotWarehouseException iwe, WebRequest webRequest) {
        LOG.error("IotWarehouseException [{}]", iwe.getLogMessage(), iwe);
        return new ResponseEntity<>(iwe.getError(), iwe.getHttpStatus());
    }
}
