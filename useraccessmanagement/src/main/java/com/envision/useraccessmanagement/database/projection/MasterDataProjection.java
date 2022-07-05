package com.envision.useraccessmanagement.database.projection;

import java.time.LocalDateTime;

public interface MasterDataProjection {

    String getReferenceId();
    String getFirstName();
    String getEmployeeId();
    String getUserType();
    String getStatus();
    LocalDateTime getCreatedDate();
}
