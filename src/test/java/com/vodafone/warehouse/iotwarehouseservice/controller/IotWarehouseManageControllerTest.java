package com.vodafone.warehouse.iotwarehouseservice.controller;


import com.vodafone.warehouse.iotwarehouseservice.constant.DeviceStatus;
import com.vodafone.warehouse.iotwarehouseservice.constant.SimStatus;
import com.vodafone.warehouse.iotwarehouseservice.model.IotDeviceRequest;
import com.vodafone.warehouse.iotwarehouseservice.model.SimRequest;
import com.vodafone.warehouse.iotwarehouseservice.service.IotWarehouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IotWarehouseManageControllerTest {

    @InjectMocks
    private IotWarehouseManageController warehouseManageController;

    @Mock
    private IotWarehouseService iotWarehouseService;

    @Test
    public void testAddDevice() {
        warehouseManageController.addDevice(createDeviceRequest());
        iotWarehouseService.addDevice(createDeviceRequest());
    }

    @Test
    public void testUpdateDevice() {
    }

    @Test
    public void testDeleteDevice() {
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