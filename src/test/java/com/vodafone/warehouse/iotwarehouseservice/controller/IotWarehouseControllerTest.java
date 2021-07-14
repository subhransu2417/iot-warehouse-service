package com.vodafone.warehouse.iotwarehouseservice.controller;

import com.vodafone.warehouse.iotwarehouseservice.constant.DeviceStatus;
import com.vodafone.warehouse.iotwarehouseservice.constant.SimStatus;
import com.vodafone.warehouse.iotwarehouseservice.repository.model.DeviceDto;
import com.vodafone.warehouse.iotwarehouseservice.repository.model.SimDto;
import com.vodafone.warehouse.iotwarehouseservice.service.IotWarehouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IotWarehouseControllerTest {

    @InjectMocks
    private IotWarehouseController iotWarehouseController;

    @Mock
    private IotWarehouseService iotWarehouseService;

    @Test
    public void getActiveDevices() {
        List<DeviceDto> devices = List.of(createDevice());
        when(iotWarehouseService.getAllActiveDevices(anyString())).thenReturn(List.of(createDevice()));
        ResponseEntity<List<DeviceDto>> listResponseEntity = iotWarehouseController
                .getActiveDevices(DeviceStatus.READY);
        assertEquals(listResponseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(listResponseEntity.getBody().size(), devices.size());
    }

    @Test
    public void getAllDevices() {
        List<DeviceDto> devices = List.of(createDevice());
        when(iotWarehouseService.getAllDevices()).thenReturn(List.of(createDevice()));
        ResponseEntity<List<DeviceDto>> listResponseEntity = iotWarehouseController
                .getAllDevices();
        assertEquals(listResponseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(listResponseEntity.getBody().size(), devices.size());
    }

    private DeviceDto createDevice() {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setDeviceId("d256ee62-e859-4191-a0f7-aa0613b918f4");
        deviceDto.setDeviceStatus(DeviceStatus.READY.name());
        deviceDto.setDeviceTemp(20);
        deviceDto.setCreatedAt(new Date());
        deviceDto.setUpdatedAt(new Date());
        SimDto simDto = new SimDto();
        simDto.setSimId("61d1fe2e-ebfc-432c-9d17-9196bdced7e8");
        simDto.setOperatorCode("ABCD");
        simDto.setCountryName("England");
        simDto.setSimStatus(SimStatus.ACTIVE.getCode());
        simDto.setCreatedAt(new Date());
        simDto.setUpdatedAt(new Date());
        deviceDto.setSimDto(simDto);
        return deviceDto;
    }
}