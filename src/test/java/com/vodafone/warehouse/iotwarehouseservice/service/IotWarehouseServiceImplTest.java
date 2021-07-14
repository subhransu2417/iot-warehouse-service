package com.vodafone.warehouse.iotwarehouseservice.service;

import com.vodafone.warehouse.iotwarehouseservice.constant.DeviceStatus;
import com.vodafone.warehouse.iotwarehouseservice.constant.SimStatus;
import com.vodafone.warehouse.iotwarehouseservice.exception.IotWarehouseException;
import com.vodafone.warehouse.iotwarehouseservice.model.IotDeviceRequest;
import com.vodafone.warehouse.iotwarehouseservice.model.SimRequest;
import com.vodafone.warehouse.iotwarehouseservice.repository.dao.DeviceDao;
import com.vodafone.warehouse.iotwarehouseservice.repository.model.DeviceDto;
import com.vodafone.warehouse.iotwarehouseservice.repository.model.SimDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IotWarehouseServiceImplTest {

    @InjectMocks
    private IotWarehouseServiceImpl iotWarehouseService;

    @Mock
    private DeviceDao deviceDao;

    @Test
    public void testGetAllActiveDevices() {
        when(deviceDao.findAll()).thenReturn(List.of(createDevice()));
        List<DeviceDto> devices = iotWarehouseService.getAllActiveDevices("READY");
        assertNotNull(devices);
    }

    @Test
    public void testGetAllDevices() {
        when(deviceDao.findAll()).thenReturn(List.of(createDevice()));
        List<DeviceDto> devices = iotWarehouseService.getAllDevices();
        assertNotNull(devices);
    }

    @Test
    public void testAddDevice() {
        ArgumentCaptor<DeviceDto> deviceDtoArgumentCaptor = ArgumentCaptor.forClass(DeviceDto.class);
        iotWarehouseService.addDevice(createDeviceRequest());
        verify(deviceDao, times(1)).save(deviceDtoArgumentCaptor.capture());
        DeviceDto deviceDto = deviceDtoArgumentCaptor.getValue();
        assertEquals(deviceDto.getDeviceStatus(), DeviceStatus.READY.name());
    }

    @Test(expected = IotWarehouseException.class)
    public void updateDevice() {
        when(deviceDao.findById("5a33a443-87e5-427d-8eb0-c4b79e73d65e")).thenReturn(Optional.empty());
        ArgumentCaptor<DeviceDto> deviceDtoArgumentCaptor = ArgumentCaptor.forClass(DeviceDto.class);
        iotWarehouseService.updateDevice("5a33a443-87e5-427d-8eb0-c4b79e73d65e",
                createDeviceRequest());
        verify(deviceDao, times(1)).save(deviceDtoArgumentCaptor.capture());
        DeviceDto deviceDto = deviceDtoArgumentCaptor.getValue();
        assertEquals(deviceDto.getDeviceStatus(), DeviceStatus.READY.name());
    }

    @Test
    public void deleteDevice() {
        DeviceDto deviceDto = createDevice();
        when(deviceDao.findById(deviceDto.getDeviceId())).thenReturn(Optional.of(deviceDto));
        iotWarehouseService.deleteDevice(deviceDto.getDeviceId());
        verify(deviceDao, times(1)).delete(deviceDto);
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

    private DeviceDto createDevice() {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setDeviceId(UUID.randomUUID().toString());
        deviceDto.setDeviceStatus(DeviceStatus.READY.name());
        deviceDto.setDeviceTemp(20);
        deviceDto.setCreatedAt(new Date());
        deviceDto.setUpdatedAt(new Date());
        SimDto simDto = new SimDto();
        simDto.setSimId(UUID.randomUUID().toString());
        simDto.setOperatorCode("ABCD");
        simDto.setCountryName("England");
        simDto.setSimStatus(SimStatus.ACTIVE.getCode());
        simDto.setCreatedAt(new Date());
        simDto.setUpdatedAt(new Date());
        deviceDto.setSimDto(simDto);
        return deviceDto;
    }
}