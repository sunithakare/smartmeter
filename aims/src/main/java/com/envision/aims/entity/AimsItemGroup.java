
package com.envision.aims.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="aims_item_group", indexes={@Index(name="aimsItemGroupAimsItemGrpGrpTransUn", columnList="item_group,transaction_mechnsm", unique=true)})
public class AimsItemGroup implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    @Column(name="item_group", nullable=false, length=1000)
    private String itemGroup;
//    @Column(nullable=false, length=10)
//    private String status;
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @Column(name="created_by", length=150)
    private String createdBy;
    @Column(name="modified_by", length=150)
    private String modifiedBy;
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
    @Column(name="deactivated_date")
    private LocalDateTime deactivatedDate;
    @Column(name="transaction_mechnsm", nullable=false, length=100)
    private String transactionMechnsm;

    @OneToMany( mappedBy = "group", fetch = FetchType.LAZY)
    private List<AimsUserErrorMsg> errors = new ArrayList<>();

}
