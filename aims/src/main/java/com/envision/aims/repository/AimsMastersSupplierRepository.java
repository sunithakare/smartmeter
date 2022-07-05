package com.envision.aims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.envision.aims.entity.AimsSupplier;
import com.envision.aims.model.AimsMasterSupplierNameVO;


@Repository
public interface AimsMastersSupplierRepository extends JpaRepository<AimsSupplier, Long>{


    @Query(value = "SELECT nextval('custom.aims_supplier_seq')", nativeQuery = true)
    Long getNextValSequenceName();

    List<AimsSupplier> findByStatus(String active);
    
    List<AimsMasterSupplierNameVO> findSupplierBy();
    
}
