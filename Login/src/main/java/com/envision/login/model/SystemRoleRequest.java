package com.envision.login.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SystemRoleRequest {
String roleName;
String roleType;
String roleDesc;
List<SystemAccessRequest> asignedPermission;
}
