package com.vodafone.warehouse.iotwarehouseservice.controller;

import com.vodafone.warehouse.iotwarehouseservice.constant.DeviceStatus;
import com.vodafone.warehouse.iotwarehouseservice.constant.SimStatus;
import com.vodafone.warehouse.iotwarehouseservice.repository.dao.DeviceDao;
import com.vodafone.warehouse.iotwarehouseservice.repository.model.DeviceDto;
import com.vodafone.warehouse.iotwarehouseservice.service.IotWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validator.EnumConstraint;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "Iot Warehouse Controller")
public class IotWarehouseController {

    private static final Logger LOG = LoggerFactory.getLogger(IotWarehouseController.class);

    @Autowired
    private IotWarehouseService iotWarehouseService;

    @ApiOperation(value = "View a list of active iot devices", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping(value = "/getActiveDevices",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeviceDto>> getActiveDevices(
            @RequestParam(name = "deviceStatus")
            @EnumConstraint(enumClass = DeviceStatus.class)
                    DeviceStatus deviceStatus) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(iotWarehouseService.getAllActiveDevices(deviceStatus.name()));
    }

    @ApiOperation(value = "View a list of all iot devices", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping(value = "/getAllDevices",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeviceDto>> getAllDevices() {
        return ResponseEntity.status(HttpStatus.OK).body(iotWarehouseService.getAllDevices());
    }
}
