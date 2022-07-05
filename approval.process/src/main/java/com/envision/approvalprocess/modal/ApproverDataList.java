package com.envision.approvalprocess.modal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApproverDataList {
    String approverIdentity;
    String approvalFor;
    String approverFilter;
}
