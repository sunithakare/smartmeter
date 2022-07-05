// Generated with g9.

package com.envision.cimodule.database.objects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name="ci_master_data")
@EntityListeners(AuditingEntityListener.class)
public class CiMasterData implements Serializable {

    /** Primary key. */
    protected static final String PK = "acctId";

    @Column(length=100)
    private String state;
    @Column(nullable=false, length=100)
    private String discom;
    @Column(name="div_code", length=100)
    private String divCode;
    @Column(name="sdo_code", length=100)
    private String sdoCode;
    @Id
    @Column(name="acct_id", unique=true, nullable=false, precision=19)
    private long acctId;
    @Column(length=100)
    private String kno;
    @Column(name="mobile_no", length=100)
    private String mobileNo;
    @Column(name="landline_no", length=100)
    private String landlineNo;
    @Column(name="book_no", length=100)
    private String bookNo;
    @Column(length=100)
    private String scno;
    @Column(length=1000)
    private String name;
    @Column(length=1000)
    private String address;
    @Column(name="supply_type", length=100)
    private String supplyType;
    @Column(length=100)
    private String load;
    @Column(name="load_unit", length=100)
    private String loadUnit;
    private LocalDateTime doc;
    @Column(name="security_amt", length=100)
    private String securityAmt;
    @Column(name="con_status", length=100)
    private String conStatus;
    @Column(name="serial_nbr", length=100)
    private String serialNbr;
    @Column(name="multiply_factor", length=100)
    private String multiplyFactor;
    @Column(name="meter_status", length=100)
    private String meterStatus;
    @Column(name="last_bill_date")
    private LocalDateTime lastBillDate;
    @Column(name="close_reading", length=100)
    private String closeReading;
    @Column(length=100)
    private String mdi;
    @Column(name="bill_basis", length=100)
    private String billBasis;
    @Column(name="bill_typ", length=100)
    private String billTyp;
    @Column(name="consumption_curr_mnth", length=100)
    private String consumptionCurrMnth;
    @Column(name="consumption_prev_mnth", length=100)
    private String consumptionPrevMnth;
    @Column(name="consumption_prev_to_prev_mnth", length=100)
    private String consumptionPrevToPrevMnth;
    @Column(length=100)
    private String arrear;
    @Column(length=100)
    private String lpsc;
    @Column(name="current_assessment", length=100)
    private String currentAssessment;
    @Column(name="current_cycle_lpsc", length=100)
    private String currentCycleLpsc;
    @Column(name="total_outstanding", length=100)
    private String totalOutstanding;
    @Column(name="due_date_rebate", length=100)
    private String dueDateRebate;
    @Column(name="last_ok_reading", length=100)
    private String lastOkReading;
    @Column(name="last_ok_read_status")
    private LocalDateTime lastOkReadStatus;
    @Column(name="meter_read_flty_cnt", length=100)
    private String meterReadFltyCnt;
    @Column(name="last_pay_amt", length=100)
    private String lastPayAmt;
    @Column(name="last_pay_date")
    private LocalDateTime lastPayDate;
    @Column(length=100)
    private String substation;
    @Column(name="gis_substation", length=100)
    private String gisSubstation;
    @Column(length=100)
    private String feeder;
    @Column(name="gis_feeder", length=100)
    private String gisFeeder;
    @Column(length=100)
    private String dt;
    @Column(name="gis_dt", length=100)
    private String gisDt;
    @Column(name="pole_no", length=100)
    private String poleNo;
    @Column(name="gis_pole_no", length=100)
    private String gisPoleNo;
    @Column(name="opr_flg", length=100)
    private String oprFlg;
    @Column(name="bill_after_date", length=100)
    private String billAfterDate;
    @Column(name="meter_read_remark", length=100)
    private String meterReadRemark;
    @Column(name="installation_date")
    private LocalDateTime installationDate;
    @Column(name="sbm_bill_date")
    private LocalDateTime sbmBillDate;
    @Column(name="sbm_machine_id", length=100)
    private String sbmMachineId;
    @Column(name="bill_cyc_cd", length=100)
    private String billCycCd;
    @Column(length=100)
    private String town;
    @Column(name="service_cyc_cd", length=100)
    private String serviceCycCd;
    @Column(name="ct_ratio", length=100)
    private String ctRatio;
    @Column(name="pt_ratio", length=100)
    private String ptRatio;
    @Column(length=100)
    private String ec;
    @Column(length=100)
    private String fc;
    @Column(length=100)
    private String rebates;
    @Column(name="min_charges", length=100)
    private String minCharges;
    @Column(name="fuel_surcharge", length=100)
    private String fuelSurcharge;
    @Column(name="demand_amt", length=100)
    private String demandAmt;
    @Column(name="ltmetering_charges", length=100)
    private String ltmeteringCharges;
    @Column(name="cap_charges", length=100)
    private String capCharges;
    @Column(name="reg_surcharge", length=100)
    private String regSurcharge;
    @Column(name="reg_surcharge_2", length=100)
    private String regSurcharge2;
    @Column(name="electricity_duty", length=100)
    private String electricityDuty;
    @Column(name="tariff_adjustments", length=100)
    private String tariffAdjustments;
    @Column(name="prov_adjustments", length=100)
    private String provAdjustments;
    @Column(name="ca_abr", length=100)
    private String caAbr;
    @Column(name="inf_bill", length=100)
    private String infBill;
    @Column(name="mr_source_cd", length=100)
    private String mrSourceCd;
    @Column(name="healthy_consumer_flag", length=100)
    private String healthyConsumerFlag;
    @Column(name="meter_badge_no", length=100)
    private String meterBadgeNo;
    @Column(name="mtr_make", length=100)
    private String mtrMake;
    @Column(name="connection_type", length=100)
    private String connectionType;
    @Column(name="looms_grtrthn_60", length=100)
    private String loomsGrtrthn60;
    @Column(name="looms_lessthn_60", length=100)
    private String loomsLessthn60;
    @Column(name="supply_voltage", length=100)
    private String supplyVoltage;
    @Column(name="meter_voltage", length=100)
    private String meterVoltage;
    @Column(name="mtr_type_cd", length=100)
    private String mtrTypeCd;
    @Column(length=100)
    private String latitude;
    @Column(length=100)
    private String longitude;
    @Column(name="binder_id", length=100)
    private String binderId;
    @CreatedBy
    @Column(name="created_by", length=100)
    private String createdBy;
    @LastModifiedBy
    @Column(name="modified_by", length=100)
    private String modifiedBy;
    @CreatedDate
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
    @Column(name="is_active")
    private Boolean isActive;
    @Column(name="signal_strength", length=50)
    private String signalStrength;
    @Column(name="network_operator", length=50)
    private String networkOperator;
    @Column(length=100)
    private String city;
    @Column(name="dtr_name", length=100)
    private String dtrName;

    /** Default constructor. */
    public CiMasterData() {
        super();
    }

    /**
     * Access method for state.
     *
     * @return the current value of state
     */
    public String getState() {
        return state;
    }

    /**
     * Setter method for state.
     *
     * @param aState the new value for state
     */
    public void setState(String aState) {
        state = aState;
    }

    /**
     * Access method for discom.
     *
     * @return the current value of discom
     */
    public String getDiscom() {
        return discom;
    }

    /**
     * Setter method for discom.
     *
     * @param aDiscom the new value for discom
     */
    public void setDiscom(String aDiscom) {
        discom = aDiscom;
    }

    /**
     * Access method for divCode.
     *
     * @return the current value of divCode
     */
    public String getDivCode() {
        return divCode;
    }

    /**
     * Setter method for divCode.
     *
     * @param aDivCode the new value for divCode
     */
    public void setDivCode(String aDivCode) {
        divCode = aDivCode;
    }

    /**
     * Access method for sdoCode.
     *
     * @return the current value of sdoCode
     */
    public String getSdoCode() {
        return sdoCode;
    }

    /**
     * Setter method for sdoCode.
     *
     * @param aSdoCode the new value for sdoCode
     */
    public void setSdoCode(String aSdoCode) {
        sdoCode = aSdoCode;
    }

    /**
     * Access method for acctId.
     *
     * @return the current value of acctId
     */
    public long getAcctId() {
        return acctId;
    }

    /**
     * Setter method for acctId.
     *
     * @param aAcctId the new value for acctId
     */
    public void setAcctId(long aAcctId) {
        acctId = aAcctId;
    }

    /**
     * Access method for kno.
     *
     * @return the current value of kno
     */
    public String getKno() {
        return kno;
    }

    /**
     * Setter method for kno.
     *
     * @param aKno the new value for kno
     */
    public void setKno(String aKno) {
        kno = aKno;
    }

    /**
     * Access method for mobileNo.
     *
     * @return the current value of mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * Setter method for mobileNo.
     *
     * @param aMobileNo the new value for mobileNo
     */
    public void setMobileNo(String aMobileNo) {
        mobileNo = aMobileNo;
    }

    /**
     * Access method for landlineNo.
     *
     * @return the current value of landlineNo
     */
    public String getLandlineNo() {
        return landlineNo;
    }

    /**
     * Setter method for landlineNo.
     *
     * @param aLandlineNo the new value for landlineNo
     */
    public void setLandlineNo(String aLandlineNo) {
        landlineNo = aLandlineNo;
    }

    /**
     * Access method for bookNo.
     *
     * @return the current value of bookNo
     */
    public String getBookNo() {
        return bookNo;
    }

    /**
     * Setter method for bookNo.
     *
     * @param aBookNo the new value for bookNo
     */
    public void setBookNo(String aBookNo) {
        bookNo = aBookNo;
    }

    /**
     * Access method for scno.
     *
     * @return the current value of scno
     */
    public String getScno() {
        return scno;
    }

    /**
     * Setter method for scno.
     *
     * @param aScno the new value for scno
     */
    public void setScno(String aScno) {
        scno = aScno;
    }

    /**
     * Access method for name.
     *
     * @return the current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param aName the new value for name
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Access method for address.
     *
     * @return the current value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for address.
     *
     * @param aAddress the new value for address
     */
    public void setAddress(String aAddress) {
        address = aAddress;
    }

    /**
     * Access method for supplyType.
     *
     * @return the current value of supplyType
     */
    public String getSupplyType() {
        return supplyType;
    }

    /**
     * Setter method for supplyType.
     *
     * @param aSupplyType the new value for supplyType
     */
    public void setSupplyType(String aSupplyType) {
        supplyType = aSupplyType;
    }

    /**
     * Access method for load.
     *
     * @return the current value of load
     */
    public String getLoad() {
        return load;
    }

    /**
     * Setter method for load.
     *
     * @param aLoad the new value for load
     */
    public void setLoad(String aLoad) {
        load = aLoad;
    }

    /**
     * Access method for loadUnit.
     *
     * @return the current value of loadUnit
     */
    public String getLoadUnit() {
        return loadUnit;
    }

    /**
     * Setter method for loadUnit.
     *
     * @param aLoadUnit the new value for loadUnit
     */
    public void setLoadUnit(String aLoadUnit) {
        loadUnit = aLoadUnit;
    }

    /**
     * Access method for doc.
     *
     * @return the current value of doc
     */
    public LocalDateTime getDoc() {
        return doc;
    }

    /**
     * Setter method for doc.
     *
     * @param aDoc the new value for doc
     */
    public void setDoc(LocalDateTime aDoc) {
        doc = aDoc;
    }

    /**
     * Access method for securityAmt.
     *
     * @return the current value of securityAmt
     */
    public String getSecurityAmt() {
        return securityAmt;
    }

    /**
     * Setter method for securityAmt.
     *
     * @param aSecurityAmt the new value for securityAmt
     */
    public void setSecurityAmt(String aSecurityAmt) {
        securityAmt = aSecurityAmt;
    }

    /**
     * Access method for conStatus.
     *
     * @return the current value of conStatus
     */
    public String getConStatus() {
        return conStatus;
    }

    /**
     * Setter method for conStatus.
     *
     * @param aConStatus the new value for conStatus
     */
    public void setConStatus(String aConStatus) {
        conStatus = aConStatus;
    }

    /**
     * Access method for serialNbr.
     *
     * @return the current value of serialNbr
     */
    public String getSerialNbr() {
        return serialNbr;
    }

    /**
     * Setter method for serialNbr.
     *
     * @param aSerialNbr the new value for serialNbr
     */
    public void setSerialNbr(String aSerialNbr) {
        serialNbr = aSerialNbr;
    }

    /**
     * Access method for multiplyFactor.
     *
     * @return the current value of multiplyFactor
     */
    public String getMultiplyFactor() {
        return multiplyFactor;
    }

    /**
     * Setter method for multiplyFactor.
     *
     * @param aMultiplyFactor the new value for multiplyFactor
     */
    public void setMultiplyFactor(String aMultiplyFactor) {
        multiplyFactor = aMultiplyFactor;
    }

    /**
     * Access method for meterStatus.
     *
     * @return the current value of meterStatus
     */
    public String getMeterStatus() {
        return meterStatus;
    }

    /**
     * Setter method for meterStatus.
     *
     * @param aMeterStatus the new value for meterStatus
     */
    public void setMeterStatus(String aMeterStatus) {
        meterStatus = aMeterStatus;
    }

    /**
     * Access method for lastBillDate.
     *
     * @return the current value of lastBillDate
     */
    public LocalDateTime getLastBillDate() {
        return lastBillDate;
    }

    /**
     * Setter method for lastBillDate.
     *
     * @param aLastBillDate the new value for lastBillDate
     */
    public void setLastBillDate(LocalDateTime aLastBillDate) {
        lastBillDate = aLastBillDate;
    }

    /**
     * Access method for closeReading.
     *
     * @return the current value of closeReading
     */
    public String getCloseReading() {
        return closeReading;
    }

    /**
     * Setter method for closeReading.
     *
     * @param aCloseReading the new value for closeReading
     */
    public void setCloseReading(String aCloseReading) {
        closeReading = aCloseReading;
    }

    /**
     * Access method for mdi.
     *
     * @return the current value of mdi
     */
    public String getMdi() {
        return mdi;
    }

    /**
     * Setter method for mdi.
     *
     * @param aMdi the new value for mdi
     */
    public void setMdi(String aMdi) {
        mdi = aMdi;
    }

    /**
     * Access method for billBasis.
     *
     * @return the current value of billBasis
     */
    public String getBillBasis() {
        return billBasis;
    }

    /**
     * Setter method for billBasis.
     *
     * @param aBillBasis the new value for billBasis
     */
    public void setBillBasis(String aBillBasis) {
        billBasis = aBillBasis;
    }

    /**
     * Access method for billTyp.
     *
     * @return the current value of billTyp
     */
    public String getBillTyp() {
        return billTyp;
    }

    /**
     * Setter method for billTyp.
     *
     * @param aBillTyp the new value for billTyp
     */
    public void setBillTyp(String aBillTyp) {
        billTyp = aBillTyp;
    }

    /**
     * Access method for consumptionCurrMnth.
     *
     * @return the current value of consumptionCurrMnth
     */
    public String getConsumptionCurrMnth() {
        return consumptionCurrMnth;
    }

    /**
     * Setter method for consumptionCurrMnth.
     *
     * @param aConsumptionCurrMnth the new value for consumptionCurrMnth
     */
    public void setConsumptionCurrMnth(String aConsumptionCurrMnth) {
        consumptionCurrMnth = aConsumptionCurrMnth;
    }

    /**
     * Access method for consumptionPrevMnth.
     *
     * @return the current value of consumptionPrevMnth
     */
    public String getConsumptionPrevMnth() {
        return consumptionPrevMnth;
    }

    /**
     * Setter method for consumptionPrevMnth.
     *
     * @param aConsumptionPrevMnth the new value for consumptionPrevMnth
     */
    public void setConsumptionPrevMnth(String aConsumptionPrevMnth) {
        consumptionPrevMnth = aConsumptionPrevMnth;
    }

    /**
     * Access method for consumptionPrevToPrevMnth.
     *
     * @return the current value of consumptionPrevToPrevMnth
     */
    public String getConsumptionPrevToPrevMnth() {
        return consumptionPrevToPrevMnth;
    }

    /**
     * Setter method for consumptionPrevToPrevMnth.
     *
     * @param aConsumptionPrevToPrevMnth the new value for consumptionPrevToPrevMnth
     */
    public void setConsumptionPrevToPrevMnth(String aConsumptionPrevToPrevMnth) {
        consumptionPrevToPrevMnth = aConsumptionPrevToPrevMnth;
    }

    /**
     * Access method for arrear.
     *
     * @return the current value of arrear
     */
    public String getArrear() {
        return arrear;
    }

    /**
     * Setter method for arrear.
     *
     * @param aArrear the new value for arrear
     */
    public void setArrear(String aArrear) {
        arrear = aArrear;
    }

    /**
     * Access method for lpsc.
     *
     * @return the current value of lpsc
     */
    public String getLpsc() {
        return lpsc;
    }

    /**
     * Setter method for lpsc.
     *
     * @param aLpsc the new value for lpsc
     */
    public void setLpsc(String aLpsc) {
        lpsc = aLpsc;
    }

    /**
     * Access method for currentAssessment.
     *
     * @return the current value of currentAssessment
     */
    public String getCurrentAssessment() {
        return currentAssessment;
    }

    /**
     * Setter method for currentAssessment.
     *
     * @param aCurrentAssessment the new value for currentAssessment
     */
    public void setCurrentAssessment(String aCurrentAssessment) {
        currentAssessment = aCurrentAssessment;
    }

    /**
     * Access method for currentCycleLpsc.
     *
     * @return the current value of currentCycleLpsc
     */
    public String getCurrentCycleLpsc() {
        return currentCycleLpsc;
    }

    /**
     * Setter method for currentCycleLpsc.
     *
     * @param aCurrentCycleLpsc the new value for currentCycleLpsc
     */
    public void setCurrentCycleLpsc(String aCurrentCycleLpsc) {
        currentCycleLpsc = aCurrentCycleLpsc;
    }

    /**
     * Access method for totalOutstanding.
     *
     * @return the current value of totalOutstanding
     */
    public String getTotalOutstanding() {
        return totalOutstanding;
    }

    /**
     * Setter method for totalOutstanding.
     *
     * @param aTotalOutstanding the new value for totalOutstanding
     */
    public void setTotalOutstanding(String aTotalOutstanding) {
        totalOutstanding = aTotalOutstanding;
    }

    /**
     * Access method for dueDateRebate.
     *
     * @return the current value of dueDateRebate
     */
    public String getDueDateRebate() {
        return dueDateRebate;
    }

    /**
     * Setter method for dueDateRebate.
     *
     * @param aDueDateRebate the new value for dueDateRebate
     */
    public void setDueDateRebate(String aDueDateRebate) {
        dueDateRebate = aDueDateRebate;
    }

    /**
     * Access method for lastOkReading.
     *
     * @return the current value of lastOkReading
     */
    public String getLastOkReading() {
        return lastOkReading;
    }

    /**
     * Setter method for lastOkReading.
     *
     * @param aLastOkReading the new value for lastOkReading
     */
    public void setLastOkReading(String aLastOkReading) {
        lastOkReading = aLastOkReading;
    }

    /**
     * Access method for lastOkReadStatus.
     *
     * @return the current value of lastOkReadStatus
     */
    public LocalDateTime getLastOkReadStatus() {
        return lastOkReadStatus;
    }

    /**
     * Setter method for lastOkReadStatus.
     *
     * @param aLastOkReadStatus the new value for lastOkReadStatus
     */
    public void setLastOkReadStatus(LocalDateTime aLastOkReadStatus) {
        lastOkReadStatus = aLastOkReadStatus;
    }

    /**
     * Access method for meterReadFltyCnt.
     *
     * @return the current value of meterReadFltyCnt
     */
    public String getMeterReadFltyCnt() {
        return meterReadFltyCnt;
    }

    /**
     * Setter method for meterReadFltyCnt.
     *
     * @param aMeterReadFltyCnt the new value for meterReadFltyCnt
     */
    public void setMeterReadFltyCnt(String aMeterReadFltyCnt) {
        meterReadFltyCnt = aMeterReadFltyCnt;
    }

    /**
     * Access method for lastPayAmt.
     *
     * @return the current value of lastPayAmt
     */
    public String getLastPayAmt() {
        return lastPayAmt;
    }

    /**
     * Setter method for lastPayAmt.
     *
     * @param aLastPayAmt the new value for lastPayAmt
     */
    public void setLastPayAmt(String aLastPayAmt) {
        lastPayAmt = aLastPayAmt;
    }

    /**
     * Access method for lastPayDate.
     *
     * @return the current value of lastPayDate
     */
    public LocalDateTime getLastPayDate() {
        return lastPayDate;
    }

    /**
     * Setter method for lastPayDate.
     *
     * @param aLastPayDate the new value for lastPayDate
     */
    public void setLastPayDate(LocalDateTime aLastPayDate) {
        lastPayDate = aLastPayDate;
    }

    /**
     * Access method for substation.
     *
     * @return the current value of substation
     */
    public String getSubstation() {
        return substation;
    }

    /**
     * Setter method for substation.
     *
     * @param aSubstation the new value for substation
     */
    public void setSubstation(String aSubstation) {
        substation = aSubstation;
    }

    /**
     * Access method for gisSubstation.
     *
     * @return the current value of gisSubstation
     */
    public String getGisSubstation() {
        return gisSubstation;
    }

    /**
     * Setter method for gisSubstation.
     *
     * @param aGisSubstation the new value for gisSubstation
     */
    public void setGisSubstation(String aGisSubstation) {
        gisSubstation = aGisSubstation;
    }

    /**
     * Access method for feeder.
     *
     * @return the current value of feeder
     */
    public String getFeeder() {
        return feeder;
    }

    /**
     * Setter method for feeder.
     *
     * @param aFeeder the new value for feeder
     */
    public void setFeeder(String aFeeder) {
        feeder = aFeeder;
    }

    /**
     * Access method for gisFeeder.
     *
     * @return the current value of gisFeeder
     */
    public String getGisFeeder() {
        return gisFeeder;
    }

    /**
     * Setter method for gisFeeder.
     *
     * @param aGisFeeder the new value for gisFeeder
     */
    public void setGisFeeder(String aGisFeeder) {
        gisFeeder = aGisFeeder;
    }

    /**
     * Access method for dt.
     *
     * @return the current value of dt
     */
    public String getDt() {
        return dt;
    }

    /**
     * Setter method for dt.
     *
     * @param aDt the new value for dt
     */
    public void setDt(String aDt) {
        dt = aDt;
    }

    /**
     * Access method for gisDt.
     *
     * @return the current value of gisDt
     */
    public String getGisDt() {
        return gisDt;
    }

    /**
     * Setter method for gisDt.
     *
     * @param aGisDt the new value for gisDt
     */
    public void setGisDt(String aGisDt) {
        gisDt = aGisDt;
    }

    /**
     * Access method for poleNo.
     *
     * @return the current value of poleNo
     */
    public String getPoleNo() {
        return poleNo;
    }

    /**
     * Setter method for poleNo.
     *
     * @param aPoleNo the new value for poleNo
     */
    public void setPoleNo(String aPoleNo) {
        poleNo = aPoleNo;
    }

    /**
     * Access method for gisPoleNo.
     *
     * @return the current value of gisPoleNo
     */
    public String getGisPoleNo() {
        return gisPoleNo;
    }

    /**
     * Setter method for gisPoleNo.
     *
     * @param aGisPoleNo the new value for gisPoleNo
     */
    public void setGisPoleNo(String aGisPoleNo) {
        gisPoleNo = aGisPoleNo;
    }

    /**
     * Access method for oprFlg.
     *
     * @return the current value of oprFlg
     */
    public String getOprFlg() {
        return oprFlg;
    }

    /**
     * Setter method for oprFlg.
     *
     * @param aOprFlg the new value for oprFlg
     */
    public void setOprFlg(String aOprFlg) {
        oprFlg = aOprFlg;
    }

    /**
     * Access method for billAfterDate.
     *
     * @return the current value of billAfterDate
     */
    public String getBillAfterDate() {
        return billAfterDate;
    }

    /**
     * Setter method for billAfterDate.
     *
     * @param aBillAfterDate the new value for billAfterDate
     */
    public void setBillAfterDate(String aBillAfterDate) {
        billAfterDate = aBillAfterDate;
    }

    /**
     * Access method for meterReadRemark.
     *
     * @return the current value of meterReadRemark
     */
    public String getMeterReadRemark() {
        return meterReadRemark;
    }

    /**
     * Setter method for meterReadRemark.
     *
     * @param aMeterReadRemark the new value for meterReadRemark
     */
    public void setMeterReadRemark(String aMeterReadRemark) {
        meterReadRemark = aMeterReadRemark;
    }

    /**
     * Access method for installationDate.
     *
     * @return the current value of installationDate
     */
    public LocalDateTime getInstallationDate() {
        return installationDate;
    }

    /**
     * Setter method for installationDate.
     *
     * @param aInstallationDate the new value for installationDate
     */
    public void setInstallationDate(LocalDateTime aInstallationDate) {
        installationDate = aInstallationDate;
    }

    /**
     * Access method for sbmBillDate.
     *
     * @return the current value of sbmBillDate
     */
    public LocalDateTime getSbmBillDate() {
        return sbmBillDate;
    }

    /**
     * Setter method for sbmBillDate.
     *
     * @param aSbmBillDate the new value for sbmBillDate
     */
    public void setSbmBillDate(LocalDateTime aSbmBillDate) {
        sbmBillDate = aSbmBillDate;
    }

    /**
     * Access method for sbmMachineId.
     *
     * @return the current value of sbmMachineId
     */
    public String getSbmMachineId() {
        return sbmMachineId;
    }

    /**
     * Setter method for sbmMachineId.
     *
     * @param aSbmMachineId the new value for sbmMachineId
     */
    public void setSbmMachineId(String aSbmMachineId) {
        sbmMachineId = aSbmMachineId;
    }

    /**
     * Access method for billCycCd.
     *
     * @return the current value of billCycCd
     */
    public String getBillCycCd() {
        return billCycCd;
    }

    /**
     * Setter method for billCycCd.
     *
     * @param aBillCycCd the new value for billCycCd
     */
    public void setBillCycCd(String aBillCycCd) {
        billCycCd = aBillCycCd;
    }

    /**
     * Access method for town.
     *
     * @return the current value of town
     */
    public String getTown() {
        return town;
    }

    /**
     * Setter method for town.
     *
     * @param aTown the new value for town
     */
    public void setTown(String aTown) {
        town = aTown;
    }

    /**
     * Access method for serviceCycCd.
     *
     * @return the current value of serviceCycCd
     */
    public String getServiceCycCd() {
        return serviceCycCd;
    }

    /**
     * Setter method for serviceCycCd.
     *
     * @param aServiceCycCd the new value for serviceCycCd
     */
    public void setServiceCycCd(String aServiceCycCd) {
        serviceCycCd = aServiceCycCd;
    }

    /**
     * Access method for ctRatio.
     *
     * @return the current value of ctRatio
     */
    public String getCtRatio() {
        return ctRatio;
    }

    /**
     * Setter method for ctRatio.
     *
     * @param aCtRatio the new value for ctRatio
     */
    public void setCtRatio(String aCtRatio) {
        ctRatio = aCtRatio;
    }

    /**
     * Access method for ptRatio.
     *
     * @return the current value of ptRatio
     */
    public String getPtRatio() {
        return ptRatio;
    }

    /**
     * Setter method for ptRatio.
     *
     * @param aPtRatio the new value for ptRatio
     */
    public void setPtRatio(String aPtRatio) {
        ptRatio = aPtRatio;
    }

    /**
     * Access method for ec.
     *
     * @return the current value of ec
     */
    public String getEc() {
        return ec;
    }

    /**
     * Setter method for ec.
     *
     * @param aEc the new value for ec
     */
    public void setEc(String aEc) {
        ec = aEc;
    }

    /**
     * Access method for fc.
     *
     * @return the current value of fc
     */
    public String getFc() {
        return fc;
    }

    /**
     * Setter method for fc.
     *
     * @param aFc the new value for fc
     */
    public void setFc(String aFc) {
        fc = aFc;
    }

    /**
     * Access method for rebates.
     *
     * @return the current value of rebates
     */
    public String getRebates() {
        return rebates;
    }

    /**
     * Setter method for rebates.
     *
     * @param aRebates the new value for rebates
     */
    public void setRebates(String aRebates) {
        rebates = aRebates;
    }

    /**
     * Access method for minCharges.
     *
     * @return the current value of minCharges
     */
    public String getMinCharges() {
        return minCharges;
    }

    /**
     * Setter method for minCharges.
     *
     * @param aMinCharges the new value for minCharges
     */
    public void setMinCharges(String aMinCharges) {
        minCharges = aMinCharges;
    }

    /**
     * Access method for fuelSurcharge.
     *
     * @return the current value of fuelSurcharge
     */
    public String getFuelSurcharge() {
        return fuelSurcharge;
    }

    /**
     * Setter method for fuelSurcharge.
     *
     * @param aFuelSurcharge the new value for fuelSurcharge
     */
    public void setFuelSurcharge(String aFuelSurcharge) {
        fuelSurcharge = aFuelSurcharge;
    }

    /**
     * Access method for demandAmt.
     *
     * @return the current value of demandAmt
     */
    public String getDemandAmt() {
        return demandAmt;
    }

    /**
     * Setter method for demandAmt.
     *
     * @param aDemandAmt the new value for demandAmt
     */
    public void setDemandAmt(String aDemandAmt) {
        demandAmt = aDemandAmt;
    }

    /**
     * Access method for ltmeteringCharges.
     *
     * @return the current value of ltmeteringCharges
     */
    public String getLtmeteringCharges() {
        return ltmeteringCharges;
    }

    /**
     * Setter method for ltmeteringCharges.
     *
     * @param aLtmeteringCharges the new value for ltmeteringCharges
     */
    public void setLtmeteringCharges(String aLtmeteringCharges) {
        ltmeteringCharges = aLtmeteringCharges;
    }

    /**
     * Access method for capCharges.
     *
     * @return the current value of capCharges
     */
    public String getCapCharges() {
        return capCharges;
    }

    /**
     * Setter method for capCharges.
     *
     * @param aCapCharges the new value for capCharges
     */
    public void setCapCharges(String aCapCharges) {
        capCharges = aCapCharges;
    }

    /**
     * Access method for regSurcharge.
     *
     * @return the current value of regSurcharge
     */
    public String getRegSurcharge() {
        return regSurcharge;
    }

    /**
     * Setter method for regSurcharge.
     *
     * @param aRegSurcharge the new value for regSurcharge
     */
    public void setRegSurcharge(String aRegSurcharge) {
        regSurcharge = aRegSurcharge;
    }

    /**
     * Access method for regSurcharge2.
     *
     * @return the current value of regSurcharge2
     */
    public String getRegSurcharge2() {
        return regSurcharge2;
    }

    /**
     * Setter method for regSurcharge2.
     *
     * @param aRegSurcharge2 the new value for regSurcharge2
     */
    public void setRegSurcharge2(String aRegSurcharge2) {
        regSurcharge2 = aRegSurcharge2;
    }

    /**
     * Access method for electricityDuty.
     *
     * @return the current value of electricityDuty
     */
    public String getElectricityDuty() {
        return electricityDuty;
    }

    /**
     * Setter method for electricityDuty.
     *
     * @param aElectricityDuty the new value for electricityDuty
     */
    public void setElectricityDuty(String aElectricityDuty) {
        electricityDuty = aElectricityDuty;
    }

    /**
     * Access method for tariffAdjustments.
     *
     * @return the current value of tariffAdjustments
     */
    public String getTariffAdjustments() {
        return tariffAdjustments;
    }

    /**
     * Setter method for tariffAdjustments.
     *
     * @param aTariffAdjustments the new value for tariffAdjustments
     */
    public void setTariffAdjustments(String aTariffAdjustments) {
        tariffAdjustments = aTariffAdjustments;
    }

    /**
     * Access method for provAdjustments.
     *
     * @return the current value of provAdjustments
     */
    public String getProvAdjustments() {
        return provAdjustments;
    }

    /**
     * Setter method for provAdjustments.
     *
     * @param aProvAdjustments the new value for provAdjustments
     */
    public void setProvAdjustments(String aProvAdjustments) {
        provAdjustments = aProvAdjustments;
    }

    /**
     * Access method for caAbr.
     *
     * @return the current value of caAbr
     */
    public String getCaAbr() {
        return caAbr;
    }

    /**
     * Setter method for caAbr.
     *
     * @param aCaAbr the new value for caAbr
     */
    public void setCaAbr(String aCaAbr) {
        caAbr = aCaAbr;
    }

    /**
     * Access method for infBill.
     *
     * @return the current value of infBill
     */
    public String getInfBill() {
        return infBill;
    }

    /**
     * Setter method for infBill.
     *
     * @param aInfBill the new value for infBill
     */
    public void setInfBill(String aInfBill) {
        infBill = aInfBill;
    }

    /**
     * Access method for mrSourceCd.
     *
     * @return the current value of mrSourceCd
     */
    public String getMrSourceCd() {
        return mrSourceCd;
    }

    /**
     * Setter method for mrSourceCd.
     *
     * @param aMrSourceCd the new value for mrSourceCd
     */
    public void setMrSourceCd(String aMrSourceCd) {
        mrSourceCd = aMrSourceCd;
    }

    /**
     * Access method for healthyConsumerFlag.
     *
     * @return the current value of healthyConsumerFlag
     */
    public String getHealthyConsumerFlag() {
        return healthyConsumerFlag;
    }

    /**
     * Setter method for healthyConsumerFlag.
     *
     * @param aHealthyConsumerFlag the new value for healthyConsumerFlag
     */
    public void setHealthyConsumerFlag(String aHealthyConsumerFlag) {
        healthyConsumerFlag = aHealthyConsumerFlag;
    }

    /**
     * Access method for meterBadgeNo.
     *
     * @return the current value of meterBadgeNo
     */
    public String getMeterBadgeNo() {
        return meterBadgeNo;
    }

    /**
     * Setter method for meterBadgeNo.
     *
     * @param aMeterBadgeNo the new value for meterBadgeNo
     */
    public void setMeterBadgeNo(String aMeterBadgeNo) {
        meterBadgeNo = aMeterBadgeNo;
    }

    /**
     * Access method for mtrMake.
     *
     * @return the current value of mtrMake
     */
    public String getMtrMake() {
        return mtrMake;
    }

    /**
     * Setter method for mtrMake.
     *
     * @param aMtrMake the new value for mtrMake
     */
    public void setMtrMake(String aMtrMake) {
        mtrMake = aMtrMake;
    }

    /**
     * Access method for connectionType.
     *
     * @return the current value of connectionType
     */
    public String getConnectionType() {
        return connectionType;
    }

    /**
     * Setter method for connectionType.
     *
     * @param aConnectionType the new value for connectionType
     */
    public void setConnectionType(String aConnectionType) {
        connectionType = aConnectionType;
    }

    /**
     * Access method for loomsGrtrthn60.
     *
     * @return the current value of loomsGrtrthn60
     */
    public String getLoomsGrtrthn60() {
        return loomsGrtrthn60;
    }

    /**
     * Setter method for loomsGrtrthn60.
     *
     * @param aLoomsGrtrthn60 the new value for loomsGrtrthn60
     */
    public void setLoomsGrtrthn60(String aLoomsGrtrthn60) {
        loomsGrtrthn60 = aLoomsGrtrthn60;
    }

    /**
     * Access method for loomsLessthn60.
     *
     * @return the current value of loomsLessthn60
     */
    public String getLoomsLessthn60() {
        return loomsLessthn60;
    }

    /**
     * Setter method for loomsLessthn60.
     *
     * @param aLoomsLessthn60 the new value for loomsLessthn60
     */
    public void setLoomsLessthn60(String aLoomsLessthn60) {
        loomsLessthn60 = aLoomsLessthn60;
    }

    /**
     * Access method for supplyVoltage.
     *
     * @return the current value of supplyVoltage
     */
    public String getSupplyVoltage() {
        return supplyVoltage;
    }

    /**
     * Setter method for supplyVoltage.
     *
     * @param aSupplyVoltage the new value for supplyVoltage
     */
    public void setSupplyVoltage(String aSupplyVoltage) {
        supplyVoltage = aSupplyVoltage;
    }

    /**
     * Access method for meterVoltage.
     *
     * @return the current value of meterVoltage
     */
    public String getMeterVoltage() {
        return meterVoltage;
    }

    /**
     * Setter method for meterVoltage.
     *
     * @param aMeterVoltage the new value for meterVoltage
     */
    public void setMeterVoltage(String aMeterVoltage) {
        meterVoltage = aMeterVoltage;
    }

    /**
     * Access method for mtrTypeCd.
     *
     * @return the current value of mtrTypeCd
     */
    public String getMtrTypeCd() {
        return mtrTypeCd;
    }

    /**
     * Setter method for mtrTypeCd.
     *
     * @param aMtrTypeCd the new value for mtrTypeCd
     */
    public void setMtrTypeCd(String aMtrTypeCd) {
        mtrTypeCd = aMtrTypeCd;
    }

    /**
     * Access method for latitude.
     *
     * @return the current value of latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Setter method for latitude.
     *
     * @param aLatitude the new value for latitude
     */
    public void setLatitude(String aLatitude) {
        latitude = aLatitude;
    }

    /**
     * Access method for longitude.
     *
     * @return the current value of longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Setter method for longitude.
     *
     * @param aLongitude the new value for longitude
     */
    public void setLongitude(String aLongitude) {
        longitude = aLongitude;
    }

    /**
     * Access method for binderId.
     *
     * @return the current value of binderId
     */
    public String getBinderId() {
        return binderId;
    }

    /**
     * Setter method for binderId.
     *
     * @param aBinderId the new value for binderId
     */
    public void setBinderId(String aBinderId) {
        binderId = aBinderId;
    }


    /**
     * Access method for createdBy.
     *
     * @return the current value of createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter method for createdBy.
     *
     * @param aCreatedBy the new value for createdBy
     */
    public void setCreatedBy(String aCreatedBy) {
        createdBy = aCreatedBy;
    }

    /**
     * Access method for modifiedBy.
     *
     * @return the current value of modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Setter method for modifiedBy.
     *
     * @param aModifiedBy the new value for modifiedBy
     */
    public void setModifiedBy(String aModifiedBy) {
        modifiedBy = aModifiedBy;
    }

    /**
     * Access method for createdDate.
     *
     * @return the current value of createdDate
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Setter method for createdDate.
     *
     * @param aCreatedDate the new value for createdDate
     */
    public void setCreatedDate(LocalDateTime aCreatedDate) {
        createdDate = aCreatedDate;
    }

    /**
     * Access method for modifiedDate.
     *
     * @return the current value of modifiedDate
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Setter method for modifiedDate.
     *
     * @param aModifiedDate the new value for modifiedDate
     */
    public void setModifiedDate(LocalDateTime aModifiedDate) {
        modifiedDate = aModifiedDate;
    }



    public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
     * Access method for signalStrength.
     *
     * @return the current value of signalStrength
     */
    public String getSignalStrength() {
        return signalStrength;
    }

    /**
     * Setter method for signalStrength.
     *
     * @param aSignalStrength the new value for signalStrength
     */
    public void setSignalStrength(String aSignalStrength) {
        signalStrength = aSignalStrength;
    }

    /**
     * Access method for networkOperator.
     *
     * @return the current value of networkOperator
     */
    public String getNetworkOperator() {
        return networkOperator;
    }

    /**
     * Setter method for networkOperator.
     *
     * @param aNetworkOperator the new value for networkOperator
     */
    public void setNetworkOperator(String aNetworkOperator) {
        networkOperator = aNetworkOperator;
    }

    /**
     * Access method for city.
     *
     * @return the current value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter method for city.
     *
     * @param aCity the new value for city
     */
    public void setCity(String aCity) {
        city = aCity;
    }

    /**
     * Access method for dtrName.
     *
     * @return the current value of dtrName
     */
    public String getDtrName() {
        return dtrName;
    }

    /**
     * Setter method for dtrName.
     *
     * @param aDtrName the new value for dtrName
     */
    public void setDtrName(String aDtrName) {
        dtrName = aDtrName;
    }

    /**
     * Compares the key for this instance with another CiMasterData.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class CiMasterData and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof CiMasterData)) {
            return false;
        }
        CiMasterData that = (CiMasterData) other;
        if (this.getAcctId() != that.getAcctId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another CiMasterData.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CiMasterData)) return false;
        return this.equalKeys(other) && ((CiMasterData)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = (int)(getAcctId() ^ (getAcctId()>>>32));
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[CiMasterData |");
        sb.append(" acctId=").append(getAcctId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("acctId", Long.valueOf(getAcctId()));
        return ret;
    }

}
