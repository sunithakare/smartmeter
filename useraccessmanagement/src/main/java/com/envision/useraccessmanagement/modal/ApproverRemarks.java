package com.envision.useraccessmanagement.modal;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApproverRemarks {
String remarks;
Integer approverLevel;
String approverName;
LocalDateTime approvedDate;
}
