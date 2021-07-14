package com.vodafone.warehouse.iotwarehouseservice.repository.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
@Table(name = "sim")
public class SimDto {

    @Id
    @Column(name = "sim_id", nullable = false, updatable = false)
    private String simId;

    @Column(name = "operator_code", nullable = false)
    private String operatorCode;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "sim_status", nullable = false)
    private String simStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;
}
