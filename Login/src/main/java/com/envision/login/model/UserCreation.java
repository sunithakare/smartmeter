package com.envision.login.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreation {
     String firstName;
     String lastName;
     String orgName;
     String employeeId;
     String email;
     String phonoNo;
     String userId;
     boolean active;
//     @JsonIgnore
     String password;
     List<UserDiscomList> discomDataList;
     List<UserRolesList> userRolesList;
}
