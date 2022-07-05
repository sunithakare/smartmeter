package com.envision.aims.service;

import com.envision.aims.model.ImportFileDTO;
import com.envision.common.exception.DataNotFoundException;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AimsCommonService {

    public final static String EXCEL = "application/vnd.ms-excel";
    public final static String CSV = "text/csv";

    public List<ImportFileDTO> readDataFromExcelSheet(MultipartFile file) throws DataNotFoundException {
        if(ObjectUtils.isEmpty(file)){
            return new ArrayList<>();
        }
        try (InputStream input = file.getInputStream()){

            return Poiji.fromExcel(input, PoijiExcelType.XLSX , ImportFileDTO.class);

        } catch (IOException e) {
            throw  new DataNotFoundException("Data not matching: " + e.getMessage());
        }
    }
}
