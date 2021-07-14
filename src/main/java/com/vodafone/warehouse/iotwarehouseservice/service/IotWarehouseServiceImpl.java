package com.vodafone.warehouse.iotwarehouseservice.service;

import com.vodafone.warehouse.iotwarehouseservice.constant.SimStatus;
import com.vodafone.warehouse.iotwarehouseservice.exception.IotWarehouseException;
import com.vodafone.warehouse.iotwarehouseservice.model.IotDeviceRequest;
import com.vodafone.warehouse.iotwarehouseservice.repository.dao.DeviceDao;
import com.vodafone.warehouse.iotwarehouseservice.repository.model.DeviceDto;
import com.vodafone.warehouse.iotwarehouseservice.repository.model.SimDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.vodafone.warehouse.iotwarehouseservice.constant.IotWarehouseServiceConstant.NOT_FOUND;

/**
 * Iot Warehouse service to manage and display warehouse devices
 */
@Service
public class IotWarehouseServiceImpl implements IotWarehouseService {

    private static final Logger LOG = LoggerFactory.getLogger(IotWarehouseServiceImpl.class);

    @Autowired
    private DeviceDao deviceDao;

    /**
     * This method returns all the active Devices on WAITING or READY state
     *
     * @param deviceStatus
     * @return
     */
    @Override
    public List<DeviceDto> getAllActiveDevices(String deviceStatus) {
        return deviceDao.findAll().stream().filter(d -> {
            return (d.getDeviceStatus()
                    .equalsIgnoreCase(deviceStatus)
                    && d.getSimDto().getSimStatus().equals(SimStatus.ACTIVE.getCode()));
        })
                .collect(Collectors.toList());
    }

    /**
     * This method gets all the devices stored
     *
     * @return
     */
    @Override
    public List<DeviceDto> getAllDevices() {
        return deviceDao.findAll();
    }

    /**
     * This method stores device details with corresponding Sim Details
     *
     * @param deviceRequest
     */
    @Override
    @Transactional
    public DeviceDto addDevice(IotDeviceRequest deviceRequest) {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setDeviceId(UUID.randomUUID().toString());
        deviceDto.setDeviceTemp(deviceRequest.getDeviceTemperature());
        deviceDto.setDeviceStatus(deviceRequest.getDeviceStatus().name());
        deviceDto.setCreatedAt(new Date());
        deviceDto.setUpdatedAt(new Date());
        SimDto simDto = new SimDto();
        simDto.setSimId(UUID.randomUUID().toString());
        simDto.setOperatorCode(deviceRequest.getSimRequest().getOperatorCode());
        simDto.setCountryName(deviceRequest.getSimRequest().getCountryName());
        simDto.setSimStatus(deviceRequest.getSimRequest().getSimStatus().getCode());
        simDto.setCreatedAt(new Date());
        simDto.setUpdatedAt(new Date());
        deviceDto.setSimDto(simDto);
        return deviceDao.save(deviceDto);
    }

    /**
     * This method updates the device details
     *
     * @param deviceId
     * @param deviceRequest
     * @return
     */
    @Override
    @Transactional
    public DeviceDto updateDevice(String deviceId, IotDeviceRequest deviceRequest) {
        return deviceDao.findById(deviceId)
                .map(device -> {
                    device.setDeviceTemp(deviceRequest.getDeviceTemperature());
                    device.setDeviceStatus(deviceRequest.getDeviceStatus().name());
                    device.setUpdatedAt(new Date());
                    return deviceDao.save(device);
                }).orElseThrow(() -> new IotWarehouseException(NOT_FOUND, "Device not found with id " + deviceId));
    }

    /**
     * This method delete the device and associated sim details
     *
     * @param deviceId
     */
    @Override
    @Transactional
    public void deleteDevice(String deviceId) {
        Optional<DeviceDto> deviceDto = deviceDao.findById(deviceId);
        if (deviceDto.isPresent()) {
            deviceDao.delete(deviceDto.get());
        } else {
            throw new IotWarehouseException(NOT_FOUND, "Device not found with id " + deviceId);
        }
    }
}
