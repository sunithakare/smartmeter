package com.envision.cimodule.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterDataModel {
	
	private long acctId;
	private String signalStrength;
	private String networkOperator;
	private String name;
	private String address;
	private String mobileNo;
	private String dtrName;
	private String poleNo;
	private String gisPoleNo;

	private String multiplyFactor;
	private String mtrMake;

	private String meterStatus;

	private String latitude;
	private String longitude;
	private String meterReadRemark;

	private Boolean isActive;

}
