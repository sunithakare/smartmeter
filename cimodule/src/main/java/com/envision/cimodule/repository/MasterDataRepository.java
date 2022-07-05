package com.envision.cimodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.cimodule.database.objects.CiMasterData;

@Repository
public interface MasterDataRepository extends JpaRepository<CiMasterData, Long>{

}
