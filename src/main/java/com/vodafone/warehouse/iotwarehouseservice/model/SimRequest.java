package com.vodafone.warehouse.iotwarehouseservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vodafone.warehouse.iotwarehouseservice.constant.SimStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import validator.EnumConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Iot Device Associated SIM Request")
public class SimRequest implements Serializable {

    @JsonProperty("operatorCode")
    @NotBlank(message = "Operator Code must be present")
    @ApiModelProperty(value = "SIM Operator Code")
    private String operatorCode;

    @JsonProperty("countryName")
    @NotBlank(message = "Country Name must be present")
    @ApiModelProperty(value = "SIM Operator Code")
    private String countryName;

    @JsonProperty("simStatus")
    @NotNull(message = "Sim Status must be present")
    @ApiModelProperty(value = "SIM Operator Code", allowableValues = "A, W, B, D")
    @EnumConstraint(enumClass = SimStatus.class)
    private SimStatus simStatus;
}
