package com.envision.aims.entity;

import javax.persistence.*;

@Entity
@Table(name = "aims_import_mi_data_details")
public class AimsImportMiDataDetail {
    @Column(name = "sr_no", length = 50)
    private String srNo;

    @Column(name = "box_number", length = 50)
    private String boxNumber;

    @Column(name = "internal_sr_no", length = 50)
    private String internalSrNo;

    @Column(name = "latest_sr_no", length = 50)
    private String latestSrNo;

    @Column(name = "remarks", length = 4000)
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "import_mi_data_id", nullable = false)
    private AimsImportMiDatum importMiData;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AimsImportMiDatum getImportMiData() {
        return importMiData;
    }

    public void setImportMiData(AimsImportMiDatum importMiData) {
        this.importMiData = importMiData;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLatestSrNo() {
        return latestSrNo;
    }

    public void setLatestSrNo(String latestSrNo) {
        this.latestSrNo = latestSrNo;
    }

    public String getInternalSrNo() {
        return internalSrNo;
    }

    public void setInternalSrNo(String internalSrNo) {
        this.internalSrNo = internalSrNo;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }
}