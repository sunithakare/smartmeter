package com.envision.aims.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AimsImportMiDatumDto implements Serializable {
    private String id;
    private  String discom;
    private  String warehouse;
    private  String subcontractor;
    private  String connectionType;
    private  String quantity;
    private  String remark;
}
