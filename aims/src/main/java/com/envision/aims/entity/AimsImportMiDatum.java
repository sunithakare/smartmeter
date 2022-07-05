package com.envision.aims.entity;

import javax.persistence.*;

@Entity
@Table(name = "aims_import_mi_data")
public class AimsImportMiDatum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reference_id", nullable = false, length = 50)
    private String id;

    @Column(name = "discom", length = 20)
    private String discom;

    @Column(name = "warehouse", length = 50)
    private String warehouse;

    @Column(name = "subcontractor", length = 50)
    private String subcontractor;

    @Column(name = "connection_type", length = 50)
    private String connectionType;

    @Column(name = "quantity", length = 20)
    private String quantity;

    @Column(name = "remark", length = 500)
    private String remark;



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getSubcontractor() {
        return subcontractor;
    }

    public void setSubcontractor(String subcontractor) {
        this.subcontractor = subcontractor;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getDiscom() {
        return discom;
    }

    public void setDiscom(String discom) {
        this.discom = discom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}