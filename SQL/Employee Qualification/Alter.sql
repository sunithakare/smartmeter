ALTER TABLE custom.employee_qualifications RENAME COLUMN file_path TO file_doc_id;
ALTER TABLE custom.employee_qualifications ALTER COLUMN file_doc_id TYPE int8 USING file_doc_id::int8;
ALTER TABLE custom.employee_qualifications ALTER COLUMN id TYPE int8 USING id::int8;
alter table custom.employee_qualifications  alter column id add GENERATED ALWAYS AS IDENTITY