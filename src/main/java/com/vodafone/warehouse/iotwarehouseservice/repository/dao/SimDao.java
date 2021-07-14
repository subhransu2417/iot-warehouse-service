package com.vodafone.warehouse.iotwarehouseservice.repository.dao;

import com.vodafone.warehouse.iotwarehouseservice.repository.model.SimDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimDao extends JpaRepository<SimDto, String> {
}
