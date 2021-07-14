package com.vodafone.warehouse.iotwarehouseservice.controller;


import com.vodafone.warehouse.iotwarehouseservice.constant.DeviceStatus;
import com.vodafone.warehouse.iotwarehouseservice.constant.SimStatus;
import com.vodafone.warehouse.iotwarehouseservice.model.IotDeviceRequest;
import com.vodafone.warehouse.iotwarehouseservice.model.SimRequest;
import com.vodafone.warehouse.iotwarehouseservice.repository.model.DeviceDto;
import com.vodafone.warehouse.iotwarehouseservice.service.IotWarehouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IotWarehouseManageControllerTest {

    @InjectMocks
    private IotWarehouseManageController warehouseManageController;

    @Mock
    private IotWarehouseService iotWarehouseService;

    @Test
    public void testAddDevice() {
        ResponseEntity<DeviceDto> addResponseEntity = warehouseManageController.addDevice(createDeviceRequest());
        verify(iotWarehouseService, times(1)).addDevice(any());
        assertEquals(addResponseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testUpdateDevice() {
        IotDeviceRequest deviceRequest = createDeviceRequest();
        ResponseEntity<DeviceDto> updateResponseEntity = warehouseManageController
                .updateDevice("1234", deviceRequest);
        verify(iotWarehouseService, times(1)).updateDevice(anyString(), any());
        assertEquals(updateResponseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testDeleteDevice() {
        ResponseEntity<Void> delResponseEntity = warehouseManageController.deleteDevice("1234");
        verify(iotWarehouseService, times(1)).deleteDevice(anyString());
        assertEquals(delResponseEntity.getStatusCode(), HttpStatus.ACCEPTED);
    }

    private IotDeviceRequest createDeviceRequest() {
        IotDeviceRequest request = new IotDeviceRequest();
        request.setDeviceStatus(DeviceStatus.READY);
        request.setDeviceTemperature(26);
        SimRequest simRequest = new SimRequest();
        simRequest.setOperatorCode("abcd");
        simRequest.setCountryName("England");
        simRequest.setSimStatus(SimStatus.ACTIVE);
        request.setSimRequest(simRequest);
        return request;
    }
}