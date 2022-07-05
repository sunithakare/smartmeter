package com.envision.useraccessmanagement.modal;

import java.util.List;

import lombok.Data;

@Data
public class UAMCompleteRequest {
    private List<SpocDetail> spocdetails;
    private String spocRemarks;
}
