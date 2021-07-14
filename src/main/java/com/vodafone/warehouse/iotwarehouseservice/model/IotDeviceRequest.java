package com.vodafone.warehouse.iotwarehouseservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vodafone.warehouse.iotwarehouseservice.constant.DeviceStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import validator.EnumConstraint;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Validated
@ApiModel(value = "Iot Device Request")
public class IotDeviceRequest {

    @JsonProperty("deviceStatus")
    @NotNull(message = "Device Status must be present")
    @ApiModelProperty(value = "Device Status", allowableValues = "READY, WAITING")
    @EnumConstraint(enumClass = DeviceStatus.class)
    private DeviceStatus deviceStatus;

    @JsonProperty("deviceTemperature")
    @NotBlank(message = "Device Temperature must be present")
    @ApiModelProperty(value = "Device Temperature")
    @Min(value = -25, message = "Temperature must be greater than -25")
    @Max(value = 50, message = "Temperature must be less than 85")
    private int deviceTemperature;

    @JsonProperty("sim")
    @NotNull
    @ApiModelProperty(value = "Device Associate Sim Request")
    private SimRequest simRequest;
}
