INSERT INTO custom.config_codes (code_id,code_type,short_code,long_code,short_description,long_description,code_group,numeric_value,remarks,valid_from,valid_to,is_active,created_by,created_at,modified_by,modified_at) VALUES
(301,'ROLETYPE','ADMIN','ADMIN ROLE TYPE','ADMIN ROLE TYPE','ADMIN ROLE TYPE','',NULL,'','2021-09-07 12:48:22.460456','2080-12-31 00:00:00',1,'DATALOAD','2021-09-07 12:48:22.460456','',NULL),
(302,'ROLETYPE','VIEWER','VIEWER ROLE TYPE','VIEWER ROLE TYPE','VIEWER ROLE TYPE','',NULL,'','2021-09-07 12:48:22.460456','2080-12-31 00:00:00',1,'DATALOAD','2021-09-07 12:48:22.460456','',NULL),
(303,'ROLETYPE','MANAGER','MANAGER ROLE TYPE','MANAGER ROLE TYPE','MANAGER ROLE TYPE','',NULL,'','2021-09-07 12:48:22.460456','2080-12-31 00:00:00',1,'DATALOAD','2021-09-07 12:48:22.460456','',NULL);




INSERT INTO custom.system_access_tbl (privilege_name,privilege_type,isactive,"desc","module") VALUES
('All SLA Link','UI',true,'All SLA Link','SLA Dashboard'),
('HES','UI',true,'HES','SLA Dashboard'),
('MDM-A','UI',true,'MDM-A','SLA Dashboard'),
('MDM-B','UI',true,'MDM-B','SLA Dashboard'),
('CSP','UI',true,'CSP','SLA Dashboard'),
('INVENTORY','UI',true,'INVENTORY','SLA Dashboard'),
('HELPDESK','UI',true,'HELPDESK','SLA Dashboard'),
('PROJECTS-A','UI',true,'PROJECTS-A','SLA Dashboard'),
('PROJECTS-B','UI',true,'PROJECTS-B','SLA Dashboard'),
('TSP','UI',true,'TSP','SLA Dashboard');




update custom.sla_reports set "module" = upper("module");

ALTER TABLE custom.adh_security_policy ALTER COLUMN id DROP DEFAULT;
ALTER TABLE custom.adh_security_policy DROP CONSTRAINT adh_security_chk1;
ALTER TABLE custom.adh_security_policy DROP COLUMN upload_from;
ALTER TABLE custom.adh_security_policy DROP COLUMN upload_to;
ALTER TABLE custom.adh_security_policy DROP COLUMN upload_option;
ALTER TABLE custom.adh_security_policy DROP COLUMN is_active;
ALTER TABLE custom.adh_security_policy DROP COLUMN log_upload;
ALTER TABLE custom.adh_security_policy DROP COLUMN uploadoption;
ALTER TABLE custom.adh_security_policy DROP COLUMN serial_no;
ALTER TABLE custom.adh_security_policy DROP COLUMN file_type;
ALTER TABLE custom.adh_security_policy DROP COLUMN "date";
ALTER TABLE custom.adh_security_policy DROP COLUMN from_date;
ALTER TABLE custom.adh_security_policy DROP COLUMN to_date;
ALTER TABLE custom.adh_security_policy ALTER COLUMN id TYPE int8 USING id::int8;
ALTER TABLE custom.adh_security_policy ALTER COLUMN created_by TYPE varchar(150) USING created_by::varchar;
ALTER TABLE custom.adh_security_policy ALTER COLUMN modified_by TYPE varchar(150) USING modified_by::varchar;
ALTER TABLE custom.adh_security_policy RENAME COLUMN quaterly TO quarterly;


alter table custom.adh_security_policy alter column id add  GENERATED ALWAYS AS IDENTITY






ALTER TABLE custom.imple_audit_recom DROP CONSTRAINT imple_audit_chk1;
ALTER TABLE custom.imple_audit_recom DROP COLUMN upload_from;
ALTER TABLE custom.imple_audit_recom DROP COLUMN upload_to;
ALTER TABLE custom.imple_audit_recom DROP COLUMN upload_option;
ALTER TABLE custom.imple_audit_recom DROP COLUMN is_active;
ALTER TABLE custom.imple_audit_recom DROP COLUMN file_type;
ALTER TABLE custom.imple_audit_recom DROP COLUMN serial_no;
ALTER TABLE custom.imple_audit_recom DROP COLUMN "date";
ALTER TABLE custom.imple_audit_recom DROP COLUMN "month";
ALTER TABLE custom.imple_audit_recom ALTER COLUMN id TYPE int8 USING id::int8;




alter table custom.imple_audit_recom alter column id add  GENERATED ALWAYS AS IDENTITY;


ALTER TABLE custom.imple_audit_recom ALTER COLUMN created_by TYPE varchar(150) USING created_by::varchar;
ALTER TABLE custom.imple_audit_recom ALTER COLUMN modified_by TYPE varchar(150) USING modified_by::varchar;




--UAM realted sql

ALTER TABLE custom.user_registration_master_table RENAME COLUMN laptop_desktop_mac_address TO lan_mac_address;
ALTER TABLE custom.user_registration_master_table ADD wifi_mac_address varchar(100) NULL;
ALTER TABLE custom.user_registration_master_table ADD discom varchar NULL;


--29-01
ALTER TABLE custom.user_registration_master_table ADD user_remarks varchar NULL;

ALTER TABLE custom.user_registration_master_table ADD spoc_remarks varchar NULL;


INSERT INTO custom.system_access_tbl (privilege_name,privilege_type,isactive,"desc","module") VALUES
	 ('APPUC','UI',true,'Approval User Creation','CONFIG'),
	 ('PROJECT-A','UI',true,'PROJECTS-A Menu Access','SLA Dashboard');
	 
	 ALTER TABLE custom.approver_details ALTER COLUMN approval_for DROP NOT NULL;
ALTER TABLE custom.approver_details ALTER COLUMN approver_filter DROP NOT NULL;


INSERT INTO custom.config_codes (code_id,code_type,short_code,long_code,short_description,long_description,code_group,numeric_value,remarks,valid_from,valid_to,is_active,created_by,created_at,modified_by,modified_at,sub_type) VALUES
	 (298,'MODULE','ADMIN','ADMIN','ADMIN','ADMIN',NULL,NULL,NULL,NULL,NULL,1,NULL,'2021-12-20 09:44:11.338',NULL,NULL,NULL),
	 (301,'ROLETYPE','ADMIN','ADMIN ROLE TYPE','ADMIN ROLE TYPE','ADMIN ROLE TYPE','',NULL,'','2021-09-07 12:48:22.460','2080-12-31 00:00:00.000',1,'DATALOAD','2021-09-07 12:48:22.460','',NULL,NULL),
	 (311,'DISCOM','HBVN',NULL,'HBVN',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,'2022-01-15 03:50:14.309',NULL,NULL,'HR'),
	 (312,'DISCOM','UHBVN',NULL,'UHBVN',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,'2022-01-15 03:50:14.309',NULL,NULL,NULL),
	 (313,'DISCOM','UPPCL',NULL,'UPPCL',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,'2022-01-15 03:50:14.309',NULL,NULL,'HR'),
	 (314,'DISCOM','DVVNL',NULL,'DVVNL',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,'2022-01-15 03:50:14.309',NULL,NULL,NULL);
	 
	 
	 INSERT INTO custom.approver_details (user_name,approver_identity,approval_for,approver_filter) VALUES
	 ('Approver1','DISCOM_MANAGER','Discom','PVVNL'),
	 ('Approver2','CLIENT_MANAGER','Discom','PVVNL'),
	 ('Approver3','SPOC','',''),
	 ('Approver4','PROJECT_DIRECTOR','',''),
	 ('Approver5','CLIENT_DIRECTOR','',''),
	 ('Approver3','SPOC','','');
	 
	 
	 
	 
	 Update custom.system_access_tbl set "desc"='9:Management of DISCOM EMS' where privilege_name='Management of DISCOM EMS';
Update custom.system_access_tbl set "desc"='4:Integration Services Uptime' where privilege_name='Integration Services Uptime';
Update custom.system_access_tbl set "desc"='5:Database administration services' where privilege_name='Database administration services';
Update custom.system_access_tbl set "desc"='Extra:GPRS , MPLS and Meter Level SLA Compliance Report' where privilege_name='GPRS , MPLS and Meter Level SLA Compliance Report';
Update custom.system_access_tbl set "desc"='1.7:Actions  for UTRNgeneration and transmission' where privilege_name='Actions  for UTRNgeneration and transmission';
Update custom.system_access_tbl set "desc"='18 logs:Adherence to Security Policy logs' where privilege_name='Adherence to Security Policy logs';
Update custom.system_access_tbl set "desc"='19 logs:Implementation of Audit Recommendations logs' where privilege_name='Implementation of Audit Recommendations logs';
Update custom.system_access_tbl set "desc"='20:Report and Dashboard' where privilege_name='Report and Dashboard';
Update custom.system_access_tbl set "desc"='11:Project Mangement' where privilege_name='Project Mangement';
Update custom.system_access_tbl set "desc"='3:Back-end Application Uptime' where privilege_name='Back-end Application Uptime';
Update custom.system_access_tbl set "desc"='2.2A:Data retention in MDMS system' where privilege_name='Data retention in MDMS system';
Update custom.system_access_tbl set "desc"='1.5:Recurring Data Delivery Actions' where privilege_name='Recurring Data Delivery Actions';
Update custom.system_access_tbl set "desc"='1.6:Alerts And Notifications' where privilege_name='Alerts And Notifications';
Update custom.system_access_tbl set "desc"='1.9:Performance level for remote load control commands for selected consumers' where privilege_name='Performance level for remote load control commands for selected consumers';
Update custom.system_access_tbl set "desc"='1.2:OnDemandActionHighPriority' where privilege_name='OnDemandActionHighPriority';
Update custom.system_access_tbl set "desc"='1.3:OnDemand Action Low Priority' where privilege_name='OnDemand Action Low Priority';
Update custom.system_access_tbl set "desc"='1.4:Actions carried out on predefined Schedule date and time' where privilege_name='Actions carried out on predefined Schedule date and time';
Update custom.system_access_tbl set "desc"='1.8A:Actions related to Firmware5 upgrade or rollback activity for asset population 10,000' where privilege_name='Actions related to Firmware5 upgrade or rollback activity for asset population 10,000';
Update custom.system_access_tbl set "desc"='1.8B:Actions related to Firmware5 upgrade or rollback activity for asset population 10,000 and 50000' where privilege_name='Actions related to Firmware5 upgrade or rollback activity for asset population 10,000 and 50000';
Update custom.system_access_tbl set "desc"='1.10:Performance level for Remote ConnectDisconnect for selected consumers' where privilege_name='Performance level for Remote ConnectDisconnect for selected consumers';
Update custom.system_access_tbl set "desc"='1.8C:Actions related to Firmware5 upgrade or rollback activity for asset population  50,000' where privilege_name='Actions related to Firmware5 upgrade or rollback activity for asset population  50,000';
Update custom.system_access_tbl set "desc"='12:AMI Helpdesk' where privilege_name='AMI Helpdesk';
Update custom.system_access_tbl set "desc"='14:Incident Management' where privilege_name='Incident Management';
Update custom.system_access_tbl set "desc"='15:Problem Management' where privilege_name='Problem Management';
Update custom.system_access_tbl set "desc"='16:Change Management' where privilege_name='Change Management';
Update custom.system_access_tbl set "desc"='17:Release Management' where privilege_name='Release Management';
Update custom.system_access_tbl set "desc"='33:Extra Defective Meter' where privilege_name='Extra Defective Meter';
Update custom.system_access_tbl set "desc"='10:Meter key Information availability' where privilege_name='Meter key Information availability';
Update custom.system_access_tbl set "desc"='2.2B:Data archival in MDMS system' where privilege_name='Data archival in MDMS system';
Update custom.system_access_tbl set "desc"='7:Meter Installation Performance' where privilege_name='Meter Installation Performance';
Update custom.system_access_tbl set "desc"='6.1:Monthly MIS Asset Inventory' where privilege_name='Monthly MIS Asset Inventory';
Update custom.system_access_tbl set "desc"='6.2:Annual Physical Asset Verification' where privilege_name='Annual Physical Asset Verification';
Update custom.system_access_tbl set "desc"='8:SIM Information Availability' where privilege_name='SIM Information Availability';
Update custom.system_access_tbl set "desc"='1.1:Offline1 actions between  HES and MDMS' where privilege_name='Offline1 actions between  HES and MDMS';
Update custom.system_access_tbl set "desc"='13:Deployment' where privilege_name='Deployment';
Update custom.system_access_tbl set "desc"='18:Adherence to Security Policy' where privilege_name='Adherence to Security Policy';
Update custom.system_access_tbl set "desc"='19:Implementation of Audit Recommendations' where privilege_name='Implementation of Audit Recommendations';
Update custom.system_access_tbl set "desc"='21 A:Resource Management-1' where privilege_name='Resource Management-1';
Update custom.system_access_tbl set "desc"='21 B:Resource Management-2' where privilege_name='Resource Management-2';
Update custom.system_access_tbl set "desc"='32:Extra Meter to Billing' where privilege_name='Extra Meter to Billing';
Update custom.system_access_tbl set "desc"='2.1:Update of individual consumer data on portal app after receiving the data in MDMS' where privilege_name='Update of individual consumer data on portal app after receiving the data in MDMS';


INSERT INTO custom.approval_steps (approval_process,step_no,record_stage,step_on_reject,step_on_approved,approved_status,reject_status,"desc",approver_identity) VALUES
	 ('UAM Approval Process',0,'Submited',1,2,'In Process','Rejected','Initial stage after application is submitted',NULL),
	 ('UAM Approval Process',1,'Rejected',NULL,NULL,'Rejected','Rejected','Reject Step',NULL),
	 ('UAM Approval Process',2,'In Progress',1,4,'Approved 1','Rejected','First Level Approver','DISCOM_MANAGER'),
	 ('UAM Approval Process',3,'Awaiting Approval',1,4,'Approved U1','Rejected','Multiple Discom Approver','UPPCL_DISCOM_MANAGER'),
	 ('UAM Approval Process',4,'Approved U1',1,4,'Approved 2','Rejected','Multiple Discom Approver','UPPCL_CLIENT_MANAGER'),
	 ('UAM Approval Process',5,'Approved 1',1,6,'Approved 2','Rejected','2nd Level Approver','CLIENT_MANAGER'),
	 ('UAM Approval Process',6,'Approved 2',1,7,'Approved 3','Rejected','3rd Level Approver','SPOC'),
	 ('UAM Approval Process',7,'Approved 3',1,8,'Approved 4','Rejected','4th Level Approver','PROJECT_DIRECTOR'),
	 ('UAM Approval Process',8,'Approved 4',1,9,'Approved','Rejected','5th Level Approver','CLIENT_DIRECTOR'),
	 ('UAM Approval Process',9,'Approved',1,NULL,'Completed','Rejected','SPOC Processing','SPOC');