package com.envision.useraccessmanagement.modal;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UAMDetailsVO {

    private UserDetails userDetails;
    private EmploymentDetails employmentDetails;
    private LocationDetails locationDetails;
    private ApplicationDetails applicationDetails;
    private List<ApplicationRequested> applicationRequested;
    private VPNdetails vpnDetails;
    private List<ApproverRemarks> approverRemarks;
    private RemarksData remarksData;
    private List<SpocDetail> spocData;
}
