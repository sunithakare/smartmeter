package com.envision.useraccessmanagement.modal;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationRequested {
    private String applicationName;
    private String accesType;
    private String appType;
    private Boolean isRequired;
    private LocalDateTime start;
    private LocalDateTime end;
    private String status;
    private String remarks;
}