package com.vodafone.warehouse.iotwarehouseservice.controller;

import com.vodafone.warehouse.iotwarehouseservice.model.IotDeviceRequest;
import com.vodafone.warehouse.iotwarehouseservice.repository.model.DeviceDto;
import com.vodafone.warehouse.iotwarehouseservice.service.IotWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Iot Warehouse Manage Controller")
@RestController
@RequestMapping(value = "/manage", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class IotWarehouseManageController {

    @Autowired
    private IotWarehouseService iotWarehouseService;

    @ApiOperation(value = "Add Device and associated Sim Details", response = DeviceDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Added Device Details"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @PostMapping(value = "/addDevice",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceDto> addDevice(@Valid @RequestBody IotDeviceRequest deviceRequest) {
        DeviceDto deviceDto = iotWarehouseService.addDevice(deviceRequest);
        return new ResponseEntity<>(deviceDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update the Device Details", response = DeviceDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Update the device"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @PutMapping(value = "/updateDevice/{deviceId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceDto> updateDevice(@PathVariable String deviceId,
                                                  @RequestBody IotDeviceRequest deviceRequest) {
        return new ResponseEntity<>(iotWarehouseService
                .updateDevice(deviceId, deviceRequest),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete specified Device Details", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Successfully Deleted the Device"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @DeleteMapping(value = "/deleteDevice/{deviceId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteDevice(@PathVariable String deviceId) {
        iotWarehouseService.deleteDevice(deviceId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
