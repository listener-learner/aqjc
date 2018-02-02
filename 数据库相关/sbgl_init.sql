/*��ռ�*/
/*windows*/
create tablespace sbgl
datafile 'D:\oracle\product\10.2.0\oradata\orcl\sbgl_01.dbf'
size 200m
 autoextend on next 50m
force logging
logging
/
/*���ݿ��û�*/
-- USER SQL
CREATE USER sbgl IDENTIFIED BY sbgl 
DEFAULT TABLESPACE sbgl
TEMPORARY TABLESPACE TEMP
/

-- ROLES
GRANT "CONNECT" TO sbgl 
/
GRANT "RESOURCE" TO sbgl 
/

--���뵼��Ŀ¼
--CREATE OR REPLACE DIRECTORY pump_dir as 'D:\expdp'
--/
GRANT read,write ON DIRECTORY pump_dir TO sbgl
/