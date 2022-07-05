package com.envision.useraccessmanagement.modal;

import java.util.List;

import com.envision.common.database.projections.ConfigCodeProjection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class ApplicationDetails {
    private String userType;
    private String requestFor;
    private String existingVPNName;
    private List<ConfigCodeProjection> discomList;
    private String state;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String status;
}