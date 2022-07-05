package com.envision.useraccessmanagement.database.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.envision.useraccessmanagement.database.objects.UserRegistrationMasterTable;
import com.envision.useraccessmanagement.database.projection.MasterDataProjection;

@Repository
public interface UserRegistrationMasterDAO extends JpaRepository<UserRegistrationMasterTable, String>, JpaSpecificationExecutor<UserRegistrationMasterTable> {

	Optional<UserRegistrationMasterTable> findByUuid(UUID uuid);

//	Page<MasterDataProjection> findAll(Specification<MasterDataProjection> spec, Pageable pageable);

	Page<MasterDataProjection> findAllBy(Pageable pageableObj);

	Page<MasterDataProjection> findByStatus(String status, Pageable pageableObj);
	
	Page<MasterDataProjection> findByStatusIn(List<String> status, Pageable pageableObj);
	
	Page<MasterDataProjection> findByReferenceIdContaining(String refId, Pageable pageableObj);

	Page<MasterDataProjection> findByStatusAndReferenceIdContaining(String status, String refId,
			Pageable pageableObj);

	Page<MasterDataProjection> findByStatusInAndReferenceIdContaining(List<String> statusList, String refId,
			Pageable pageableObj);
}
