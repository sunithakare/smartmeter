package com.envision.login.model;

import java.util.List;

import lombok.Data;

@Data
public class UserPojo {
 int id;
 String userId;
 String password;
 String email;
 List<String> rolesAndPermission;
 String isactive;


}
