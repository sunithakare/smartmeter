alter table custom.employee_creation alter column id add GENERATED ALWAYS AS IDENTITY
ALTER TABLE custom.employee_qualifications DROP CONSTRAINT report_user_qualifications_un;
ALTER TABLE custom.employee_creation DROP COLUMN experience;
ALTER TABLE custom.employee_creation ALTER COLUMN modified_by DROP DEFAULT;
ALTER TABLE custom.employee_creation ALTER COLUMN active TYPE bool USING active::bool;
ALTER TABLE custom.employee_creation DROP CONSTRAINT emp_creation_rolename_category_unq;
