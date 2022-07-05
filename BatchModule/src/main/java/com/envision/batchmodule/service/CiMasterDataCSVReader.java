package com.envision.batchmodule.service;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.envision.cimodule.database.objects.CiMasterData;

@Component
public class CiMasterDataCSVReader extends FlatFileItemReader<CiMasterData> {


	   @Autowired
	   CIMasterDataFieldSetMapper ciMasterDataFieldSetMapper;
	   
	
	public CiMasterDataCSVReader() {
		super();
//		  FlatFileItemReader<CiMasterDataCsv> reader = new FlatFileItemReader<CiMasterDataCsv>();
		
	      this.setLineMapper(new DefaultLineMapper<CiMasterData>() {
	         {
	            setLineTokenizer(new DelimitedLineTokenizer() {
	               {
	                  setNames(headerFields);
	               }
	            });
//	            setFieldSetMapper(new BeanWrapperFieldSetMapper<CiMasterDataCsv>() {
//	               {
//	                  setTargetType(CiMasterDataCsv.class);
//	               }
//	            });
	            setFieldSetMapper(new CIMasterDataFieldSetMapper());
	         }
	      });
	      this.setLinesToSkip(1);
	      

//			DefaultLineMapper<CiMasterDataCsv> defaultLineMapper=new DefaultLineMapper<CiMasterDataCsv>() ;
//			defaultLineMapper.setLineTokenizer(new DelimitedLineTokenizer(",") {
//			               {
//			                  setNames(headerFields);
//			               }
//			            });
//			      defaultLineMapper.setFieldSetMapper(ciMasterDataFieldSetMapper);
//			
//		      this.setLineMapper(defaultLineMapper);
//		      this.setLinesToSkip(1);
	      
	}
		
	
	static String[] headerFields=new String[] {
		    "state",
		    "discom",
		    "divCode",
		    "sdoCode",
		    "acctId",
		    "kno",
		    "mobileNo",
		    "landlineNo",
		    "bookNo",
		    "scno",
		    "name",
		    "address",
		    "supplyType",
		    "load",
		    "loadUnit",
		    "doc",
		    "securityAmt",
		    "conStatus",
		    "serialNbr",
		    "multiplyFactor",
		    "meterStatus",
		    "lastBillDate",
		    "closeReading",
		    "mdi",
		    "billBasis",
		    "billTyp",
		    "consumptionCurrMnth",
		    "consumptionPrevMnth",
		    "consumptionPrevToPrevMnth",
		    "arrear",
		    "lpsc",
		    "currentAssessment",
		    "currentCycleLpsc",
		    "totalOutstanding",
		    "dueDateRebate",
		    "lastOkReading",
		    "lastOkReadStatus",
		    "meterReadFltyCnt",
		    "lastPayAmt",
		    "lastPayDate",
		    "substation",
		    "gisSubstation",
		    "feeder",
		    "gisFeeder",
		    "dt",
		    "gisDt",
		    "poleNo",
		    "gisPoleNo",
		    "oprFlg",
		    "billAfterDate",
		    "meterReadRemark",
		    "installationDate",
		    "sbmBillDate",
		    "sbmMachineId",
		    "billCycCd",
		    "town",
		    "serviceCycCd",
		    "ctRatio",
		    "ptRatio",
		    "ec",
		    "fc",
		    "rebates",
		    "minCharges",
		    "fuelSurcharge",
		    "demandAmt",
		    "ltmeteringCharges",
		    "capCharges",
		    "regSurcharge",
		    "regSurcharge2",
		    "electricityDuty",
		    "tariffAdjustments",
		    "provAdjustments",
		    "caAbr",
		    "infBill",
		    "mrSourceCd",
		    "healthyConsumerFlag",
		    "meterBadgeNo",
		    "mtrMake",
		    "connectionType",
		    "loomsGrtrthn60",
		    "loomsLessthn60",
		    "supplyVoltage",
		    "meterVoltage",
		    "mtrTypeCd",
		    "latitude",
		    "longitude",
		    "binderId"
	};
	
}
