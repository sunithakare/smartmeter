package com.envision.useraccessmanagement.modal;

import java.util.List;

import lombok.Data;

@Data
public class UAMRequest {
    private UserDetails userDetails;
    private EmploymentDetails employmentDetails;
    private LocationDetails locationDetails;
    private ApplicationDetails applicationDetails;
    private List<ApplicationAccess> applicationAccess;
    private VPNdetails vpnDetails;
    private RemarksData remarksData;
}