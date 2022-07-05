package com.envision.approvalprocess.database.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.approvalprocess.database.objects.ApproverDetails;
import com.envision.approvalprocess.modal.ApproverDetailsProjection;

@Repository
public interface ApproverDetailsDAO extends JpaRepository<ApproverDetails, Integer> {

	List<ApproverDetails> findByUserName(String userName);
	List<ApproverDetailsProjection> findApproverByUserName(String userName);
	Page<ApproverDetailsProjection> findByApproverIdentity(String approverIdentity,Pageable pageableObj);
	Page<ApproverDetailsProjection> findAllBy(Pageable pageableObj);
	Page<ApproverDetailsProjection> findAllByApproverIdentityAndUserNameContainingIgnoreCase( String approverIdentity,String userName,
			Pageable pageableObj);
	List<ApproverDetails>  findByUserNameAndApproverIdentity(String name, String requiredIdentity);
	List<ApproverDetails>  findByUserNameAndApproverIdentityAndApprovalForAndApproverFilter(String name, String requiredIdentity,
			String approverFor, String approverFilter);
	List<ApproverDetails> findByUserNameAndApproverIdentityIn(String userName, List<String> approverIdentity);
	List<ApproverDetails> findByApproverIdentity(String requiredIdentity);
	List<ApproverDetails> findByApproverIdentityAndApprovalForAndApproverFilter(String requiredIdentity,
			String approverFor, String approverFilter);
	

}
