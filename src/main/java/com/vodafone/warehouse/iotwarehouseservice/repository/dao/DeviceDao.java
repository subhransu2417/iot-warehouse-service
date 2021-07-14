package com.vodafone.warehouse.iotwarehouseservice.repository.dao;

import com.vodafone.warehouse.iotwarehouseservice.repository.model.DeviceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDao extends JpaRepository<DeviceDto, String> {
}
