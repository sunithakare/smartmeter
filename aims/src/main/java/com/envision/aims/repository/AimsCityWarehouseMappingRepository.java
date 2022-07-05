package com.envision.aims.repository;

import com.envision.aims.entity.AimsCityWarehouseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsCityWarehouseMappingRepository extends JpaRepository<AimsCityWarehouseMapping, Long> {
}
