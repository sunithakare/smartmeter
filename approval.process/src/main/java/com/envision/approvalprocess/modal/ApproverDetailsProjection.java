package com.envision.approvalprocess.modal;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApproverDetailsProjection {

    String approverIdentity;
    String approvalFor;
    String approverFilter;
    String userName;

}
