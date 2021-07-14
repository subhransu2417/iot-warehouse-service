package com.vodafone.warehouse.iotwarehouseservice.constant;

public enum SimStatus {
    ACTIVE("A"), WAITING_FOR_ACTIVATION("W"), BLOCKED("B"), DEACTIVATED("D");
    private String code;

    SimStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SimStatus fromCode(String code) {
        for (SimStatus simDeviceStatus : values()) {
            if (simDeviceStatus.getCode().equalsIgnoreCase(code)) {
                return simDeviceStatus;
            }
        }
        throw new IllegalArgumentException("Invalid Sim Status Code" + code);
    }
}
