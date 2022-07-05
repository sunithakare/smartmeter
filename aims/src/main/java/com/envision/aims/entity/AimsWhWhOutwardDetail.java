package com.envision.aims.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "aims_wh_wh_outward_details")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AimsWhWhOutwardDetail {
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


    @Column(name = "wh_wh_outward_id", nullable = false)
    private String whWhOutward;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
}