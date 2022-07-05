package com.envision.aims.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "aims_old_meter_inward_details")
@Getter
@Setter
@NoArgsConstructor
public class AimsOldMeterInwardDetail {
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


    @Column(name = "old_meter_inward_id", nullable = false)
    private String oldMeterInward;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

}