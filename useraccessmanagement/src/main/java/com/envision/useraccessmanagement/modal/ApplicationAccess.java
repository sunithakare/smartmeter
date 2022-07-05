package com.envision.useraccessmanagement.modal;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationAccess {
    private String applicationName;
    private String accesType;
    private String appType;
    private String remarks;
    private Boolean isRequired;
    private LocalDateTime start;
    private LocalDateTime end;
}