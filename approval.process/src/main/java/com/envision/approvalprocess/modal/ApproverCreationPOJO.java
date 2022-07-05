package com.envision.approvalprocess.modal;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApproverCreationPOJO {
    String userName;
    List<ApproverDataList> approverDataList;
}