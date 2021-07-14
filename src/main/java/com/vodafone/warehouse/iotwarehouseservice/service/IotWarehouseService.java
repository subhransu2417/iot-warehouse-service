package com.vodafone.warehouse.iotwarehouseservice.service;

import com.vodafone.warehouse.iotwarehouseservice.model.IotDeviceRequest;
import com.vodafone.warehouse.iotwarehouseservice.repository.model.DeviceDto;

import java.util.List;

public interface IotWarehouseService {
    List<DeviceDto> getAllActiveDevices(String deviceStatus);
    List<DeviceDto> getAllDevices();
    void addDevice(IotDeviceRequest deviceRequest);
    DeviceDto updateDevice(String deviceId, IotDeviceRequest deviceRequest);
    void deleteDevice(String deviceId);
}
