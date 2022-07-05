package com.envision.aims.model;

import com.envision.aims.entity.AimsWhWhOutward;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class WarehouseOutwardRequestDTO {

    AimsWhWhOutward data;
    MultipartFile dataFile;
}
