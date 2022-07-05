package com.envision.aims.model;

import com.poiji.annotation.ExcelCell;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ImportFileDTO implements Serializable {
    @ExcelCell(0)
    private  String srNo;
    @ExcelCell(1)
    private  String boxNumber;
    @ExcelCell(2)
    private  String internalSrNo;
    @ExcelCell(3)
    private  String latestSrNo;
    @ExcelCell(4)
    private  String remarks;
}
