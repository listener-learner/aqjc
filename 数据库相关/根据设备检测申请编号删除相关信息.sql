select * from nt_apply_device where apply_id='14687eb2-7641-40c2-8895-8cc7c0664f82';
select * from t_device_base_info where device_id in (select device_id from nt_apply_device where apply_id ='14687eb2-7641-40c2-8895-8cc7c0664f82');
select * from nt_device_test_apply where apply_id='14687eb2-7641-40c2-8895-8cc7c0664f82';
select * from nt_device_apply_audit where apply_id='14687eb2-7641-40c2-8895-8cc7c0664f82';
select * from nt_device_test_apply_attach where dev_apply_id = '14687eb2-7641-40c2-8895-8cc7c0664f82';
select * from nt_device_test_report where apply_id = '14687eb2-7641-40c2-8895-8cc7c0664f82';
select * from nt_test_report_pic where report_id='fff0f2f0-1493-4c31-8688-89748089f43f';

--����apply_id ɾ���������������Ϣ
--1��ɾ�������豸��Ӧ�豸������Ϣ
delete from t_device_base_info where device_id in (select device_id from nt_apply_device where apply_id ='14687eb2-7641-40c2-8895-8cc7c0664f82');
--2��ɾ�������豸��Ϣ
delete from nt_apply_device where apply_id='14687eb2-7641-40c2-8895-8cc7c0664f82';
--3��ɾ��������Ϣ
delete from nt_device_test_apply where apply_id='14687eb2-7641-40c2-8895-8cc7c0664f82';
--4 ɾ������������Ϣ
delete from nt_device_apply_audit where apply_id='14687eb2-7641-40c2-8895-8cc7c0664f82';
--5 ɾ���������
delete from nt_device_test_apply_attach where dev_apply_id='14687eb2-7641-40c2-8895-8cc7c0664f82';
--6 ɾ�������Ӧ�ļ�ⱨ���ͼƬ
delete from nt_test_report_pic where report_id='fff0f2f0-1493-4c31-8688-89748089f43f';
--7 ɾ�������Ӧ�ļ�ⱨ��
delete from nt_device_test_report where apply_id='14687eb2-7641-40c2-8895-8cc7c0664f82';