package com.envision.batchmodule.service;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;

import com.envision.cimodule.database.objects.CiMasterData;

@Component
public class CIMasterDataFieldSetMapper extends BeanWrapperFieldSetMapper<CiMasterData> {
	public CIMasterDataFieldSetMapper() {
		super();
		this.setTargetType(CiMasterData.class);
		
	}
    @Override
    protected void initBinder(DataBinder binder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (StringUtils.isNotEmpty(text)) {
                    setValue(LocalDate.parse(text, formatter).atTime(LocalTime.MIN));
                } else {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() throws IllegalArgumentException {
                Object date = getValue();
                if (date != null) {
                    return formatter.format((LocalDateTime) date);
                } else {
                    return "";
                }
            }
        });
    }
    
//    @Override
//    public CiMasterDataCsv mapFieldSet(FieldSet fs)
//            throws BindException {
//    	
//    	System.out.println("CiMasterDataCsvCiMasterDataCsvCiMasterDataCsvCiMasterDataCsvCiMasterDataCsvCiMasterDataCsvCiMasterDataCsvCiMasterDataCsvCiMasterDataCsvCiMasterDataCsvCiMasterDataCsv");
//    	CiMasterDataCsv tmp= super.mapFieldSet(fs);
//        // your custom code here
//        return tmp;
//    }

    
}