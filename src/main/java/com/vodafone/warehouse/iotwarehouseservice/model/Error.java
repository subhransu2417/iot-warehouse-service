package com.vodafone.warehouse.iotwarehouseservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
@Data
@ApiModel("Error Structure")
public class Error implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("code")
    @ApiModelProperty("Unique Error Code")
    private String code;

    @JsonProperty("reasonCode")
    @ApiModelProperty("Reason Code")
    private String reasonCode;

    @JsonProperty("message")
    @ApiModelProperty("Error message")
    private String message;

    public Error code(String code) {
        this.code = code;
        return this;
    }

    public Error reasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
        return this;
    }

    public Error message(String message) {
        this.message = message;
        return this;
    }
}
