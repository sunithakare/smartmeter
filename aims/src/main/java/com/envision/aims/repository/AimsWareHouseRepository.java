package com.envision.aims.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.envision.aims.entity.AimsWarehouse;
import com.envision.aims.model.AimsMasterWarehouseVO;
import com.envision.aims.model.AimsWareHouseFetchAllListDto;
import com.envision.aims.model.AimsWarehouseDto;
import com.envision.aims.model.AimsWarehouseFetchDto;

@Repository
public interface AimsWareHouseRepository extends JpaRepository<AimsWarehouse, Long>{

	@Query(value = "SELECT nextval('custom.aims_warehouse_creation_seq')", nativeQuery = true)
    Long getNextValSequenceName(); 
	
	List<AimsWarehouse> findByDiscomAndCity(String discom,String city);
	
	List<AimsWarehouse> findByWarehouseName(String warehouseName);

    List<AimsWarehouse> findByStatus(String active);
    
    List<AimsMasterWarehouseVO> findWarehouseNameBy();
}
