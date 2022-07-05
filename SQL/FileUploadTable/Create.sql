
CREATE TABLE custom.file_upload_table (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	filepath varchar NULL,
	createdby varchar(150) NULL,
	createddate timestamp NULL,
	filename varchar NULL,
	CONSTRAINT file_upload_table_pk PRIMARY KEY (id)
);

