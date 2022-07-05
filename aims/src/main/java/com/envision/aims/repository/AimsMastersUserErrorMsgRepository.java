package com.envision.aims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.aims.entity.AimsUserErrorMsg;

@Repository
public interface AimsMastersUserErrorMsgRepository extends JpaRepository<AimsUserErrorMsg, Long>{

}
