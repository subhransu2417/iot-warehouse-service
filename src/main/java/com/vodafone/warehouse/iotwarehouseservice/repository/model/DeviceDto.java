package com.vodafone.warehouse.iotwarehouseservice.repository.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
@Table(name = "iot_device")
public class DeviceDto {

    @Id
    @Column(name = "device_id", nullable = false, updatable = false)
    private String deviceId;

    @Column(name = "device_status", nullable = false)
    private String deviceStatus;

    @Column(name = "device_temp", nullable = false)
    private int deviceTemp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sim_id", referencedColumnName = "sim_id")
    private SimDto simDto;

}
