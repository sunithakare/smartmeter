package com.envision.useraccessmanagement.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.useraccessmanagement.database.objects.UserRegistrationApproverRemarks;

@Repository
public interface UserRegistrationApproverDAO extends JpaRepository<UserRegistrationApproverRemarks, Long> {

}
