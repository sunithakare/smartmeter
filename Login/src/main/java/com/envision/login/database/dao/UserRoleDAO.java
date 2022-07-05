package com.envision.login.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.envision.login.database.objects.UserRoleMapping;

public interface UserRoleDAO extends JpaRepository<UserRoleMapping, Long> { 

}
