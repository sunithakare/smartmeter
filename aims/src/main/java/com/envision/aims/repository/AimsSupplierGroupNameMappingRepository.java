package com.envision.aims.repository;

import com.envision.aims.entity.AimsSupplierGroupNameMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsSupplierGroupNameMappingRepository extends JpaRepository<AimsSupplierGroupNameMapping, Long> {
}
