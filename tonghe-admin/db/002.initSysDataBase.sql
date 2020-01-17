prompt PL/SQL Developer import file
prompt Created on 2019年12月4日 by Administrator
set feedback off
set define off
prompt Dropping SYS_CONFIG...
drop table SYS_CONFIG cascade constraints;
prompt Dropping SYS_DEPT...
drop table SYS_DEPT cascade constraints;
prompt Dropping SYS_DICT...
drop table SYS_DICT cascade constraints;
prompt Dropping SYS_MENU...
drop table SYS_MENU cascade constraints;
prompt Dropping SYS_OSS...
drop table SYS_OSS cascade constraints;
prompt Dropping SYS_ROLE...
drop table SYS_ROLE cascade constraints;
prompt Dropping SYS_ROLE_DEPT...
drop table SYS_ROLE_DEPT cascade constraints;
prompt Dropping SYS_ROLE_MENU...
drop table SYS_ROLE_MENU cascade constraints;
prompt Dropping SYS_USER...
drop table SYS_USER cascade constraints;
prompt Dropping SYS_USER_ROLE...
drop table SYS_USER_ROLE cascade constraints;
prompt Creating SYS_CONFIG...
create table SYS_CONFIG
(
  id          NUMBER(20) not null,
  param_key   VARCHAR2(50),
  param_value VARCHAR2(4000),
  status      NUMBER(2) default 1 not null,
  remark      VARCHAR2(500)
)
tablespace HZSIE_B1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create unique index INDEX_PARAM_KEY on SYS_CONFIG (PARAM_KEY)
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_CONFIG
  add primary key (ID)
  using index 
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_DEPT...
create table SYS_DEPT
(
  dept_id   NUMBER(20) not null,
  parent_id NUMBER(20) not null,
  name      VARCHAR2(50),
  order_num NUMBER(10) not null,
  del_flag  NUMBER(2) default 0 not null
)
tablespace HZSIE_B1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_DEPT
  add primary key (DEPT_ID)
  using index 
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_DICT...
create table SYS_DICT
(
  id        NUMBER(20) not null,
  name      VARCHAR2(100) not null,
  type      VARCHAR2(100) not null,
  code      VARCHAR2(100) not null,
  value     VARCHAR2(1000) not null,
  order_num NUMBER(10) default 0 not null,
  remark    VARCHAR2(255),
  del_flag  NUMBER(2) default 0 not null
)
tablespace HZSIE_B1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create unique index INDEX_TYPE_CODE on SYS_DICT (TYPE, CODE)
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_DICT
  add primary key (ID)
  using index 
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_MENU...
create table SYS_MENU
(
  menu_id   NUMBER(20) not null,
  parent_id NUMBER(20) not null,
  name      VARCHAR2(50),
  url       VARCHAR2(200),
  perms     VARCHAR2(500),
  type      NUMBER(2),
  icon      VARCHAR2(50),
  order_num INTEGER
)
tablespace HZSIE_B1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_MENU
  add primary key (MENU_ID)
  using index 
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_OSS...
create table SYS_OSS
(
  id          NUMBER(20) not null,
  url         VARCHAR2(200),
  create_date TIMESTAMP(6)
)
tablespace HZSIE_B1
  pctfree 10
  initrans 1
  maxtrans 255;
alter table SYS_OSS
  add primary key (ID)
  using index 
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255;

prompt Creating SYS_ROLE...
create table SYS_ROLE
(
  role_id     NUMBER(20) not null,
  role_name   VARCHAR2(100),
  remark      VARCHAR2(100),
  dept_id     NUMBER(20) not null,
  create_time TIMESTAMP(6)
)
tablespace HZSIE_B1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_ROLE
  add primary key (ROLE_ID)
  using index 
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_ROLE_DEPT...
create table SYS_ROLE_DEPT
(
  id      NUMBER(20) not null,
  role_id NUMBER(20) not null,
  dept_id NUMBER(20) not null
)
tablespace HZSIE_B1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_ROLE_DEPT
  add primary key (ID)
  using index 
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_ROLE_MENU...
create table SYS_ROLE_MENU
(
  id      NUMBER(20) not null,
  role_id NUMBER(20) not null,
  menu_id NUMBER(20) not null
)
tablespace HZSIE_B1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_ROLE_MENU
  add primary key (ID)
  using index 
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_USER...
create table SYS_USER
(
  user_id     NUMBER(20) not null,
  username    VARCHAR2(50) not null,
  password    VARCHAR2(100),
  salt        VARCHAR2(20),
  email       VARCHAR2(100),
  mobile      VARCHAR2(100),
  status      NUMBER(2) not null,
  dept_id     NUMBER(20),
  create_time TIMESTAMP(6)
)
tablespace HZSIE_B1
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create unique index INDEX_USERNAME on SYS_USER (USERNAME)
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_USER
  add primary key (USER_ID)
  using index 
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_USER_ROLE...
create table SYS_USER_ROLE
(
  id      NUMBER(20) not null,
  user_id NUMBER(20) not null,
  role_id NUMBER(20) not null
)
tablespace HZSIE_B1
  pctfree 10
  initrans 1
  maxtrans 255;
alter table SYS_USER_ROLE
  add primary key (ID)
  using index 
  tablespace HZSIE_B1
  pctfree 10
  initrans 2
  maxtrans 255;

prompt Disabling triggers for SYS_CONFIG...
alter table SYS_CONFIG disable all triggers;
prompt Disabling triggers for SYS_DEPT...
alter table SYS_DEPT disable all triggers;
prompt Disabling triggers for SYS_DICT...
alter table SYS_DICT disable all triggers;
prompt Disabling triggers for SYS_MENU...
alter table SYS_MENU disable all triggers;
prompt Disabling triggers for SYS_OSS...
alter table SYS_OSS disable all triggers;
prompt Disabling triggers for SYS_ROLE...
alter table SYS_ROLE disable all triggers;
prompt Disabling triggers for SYS_ROLE_DEPT...
alter table SYS_ROLE_DEPT disable all triggers;
prompt Disabling triggers for SYS_ROLE_MENU...
alter table SYS_ROLE_MENU disable all triggers;
prompt Disabling triggers for SYS_USER...
alter table SYS_USER disable all triggers;
prompt Disabling triggers for SYS_USER_ROLE...
alter table SYS_USER_ROLE disable all triggers;
prompt Loading SYS_CONFIG...
insert into SYS_CONFIG (id, param_key, param_value, status, remark)
values (1, 'CLOUD_STORAGE_CONFIG_KEY', '{"aliyunAccessKeyId":"","aliyunAccessKeySecret":"","aliyunBucketName":"","aliyunDomain":"","aliyunEndPoint":"","aliyunPrefix":"","qcloudBucketName":"","qcloudDomain":"","qcloudPrefix":"","qcloudSecretId":"","qcloudSecretKey":"","qiniuAccessKey":"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ","qiniuBucketName":"mall","qiniuDomain":"http://7xlij2.com1.z0.glb.clouddn.com","qiniuPrefix":"upload","qiniuSecretKey":"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV","type":1}', 0, '雲存儲配置信息');
commit;
prompt 1 records loaded
prompt Loading SYS_DEPT...
insert into SYS_DEPT (dept_id, parent_id, name, order_num, del_flag)
values (1199196637490216961, 0, '測試簡体中文21', 20, -1);
insert into SYS_DEPT (dept_id, parent_id, name, order_num, del_flag)
values (1, 0, '系統部門根', 0, 0);
insert into SYS_DEPT (dept_id, parent_id, name, order_num, del_flag)
values (2, 1, '統合電子公司', 1, 0);
insert into SYS_DEPT (dept_id, parent_id, name, order_num, del_flag)
values (3, 1, '蘇州華冠公司', 2, 0);
insert into SYS_DEPT (dept_id, parent_id, name, order_num, del_flag)
values (4, 3, '研發部', 0, 0);
insert into SYS_DEPT (dept_id, parent_id, name, order_num, del_flag)
values (5, 3, '銷售部', 1, -1);
insert into SYS_DEPT (dept_id, parent_id, name, order_num, del_flag)
values (1199196182475362305, 0, '測試', 10, -1);
insert into SYS_DEPT (dept_id, parent_id, name, order_num, del_flag)
values (1199199180790349825, 0, '測試簡体測試', 2, -1);
commit;
prompt 8 records loaded
prompt Loading SYS_DICT...
insert into SYS_DICT (id, name, type, code, value, order_num, remark, del_flag)
values (1, '性別', 'sex', '0', '女', 0, null, -1);
insert into SYS_DICT (id, name, type, code, value, order_num, remark, del_flag)
values (2, '性別', 'sex', '1', '男', 1, null, -1);
insert into SYS_DICT (id, name, type, code, value, order_num, remark, del_flag)
values (3, '性別', 'sex', '2', '未知', 3, null, -1);
commit;
prompt 3 records loaded
prompt Loading SYS_MENU...
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1201406937543204866, 1191645704061350000, '產線達成率維護', 'modules/sys/thratereddata.html', null, 1, 'fa fa-list', 8);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1201407615082684418, 1201406937543204866, '查看', null, 'sys:thratereddata:list,sys:thratereddata:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1201407674440474625, 1201406937543204866, '新增', null, 'sys:thratereddata:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1201407747467501570, 1201406937543204866, '修改', null, 'sys:thratereddata:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1201407792631767041, 1201406937543204866, '？除', null, 'sys:thratereddata:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1201407854615191554, 1201407200228270082, '查看', null, 'sys:thwipreddata:list,sys:thwipreddata:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1201407903092957186, 1201407200228270082, '新增', null, 'sys:thwipreddata:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1201407952359251969, 1201407200228270082, '修改', null, 'sys:thwipreddata:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1201408012040003586, 1201407200228270082, '？除', null, 'sys:thwipreddata:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191635627665440000, 1191635419221120000, '修改', null, 'sys:theuipmentconfig:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191220599019790000, 0, '看板管理', null, null, 0, 'fa fa-list', 1);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191635691498560000, 1191635419221120000, '刪除', null, 'sys:theuipmentconfig:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191637850357180000, 1191637796946920000, '查看', null, 'sys:thmaintenanceconfig:list,sys:thmaintenanceconfig:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191637903704540000, 1191637796946920000, '新增', null, 'sys:thmaintenanceconfig:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191640522871210000, 1191640318898020000, '刪除', null, 'sys:thqualityconfig:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191644391764290000, 1191606217184780000, '訂單看板配置', 'modules/sys/thorderconfig.html', null, 1, 'fa fa-list', 7);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191644450459380000, 1191644391764290000, '查看', null, 'sys:thorderconfig:list,sys:thorderconfig:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191644502003180000, 1191644391764290000, '新增', null, 'sys:thorderconfig:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191644555006600000, 1191644391764290000, '修改', null, 'sys:thorderconfig:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191644600804200000, 1191644391764290000, '刪除', null, 'sys:thorderconfig:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191646099814900000, 1191645987013290000, '新增', null, 'sys:thproductline:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191646201770040000, 1191645987013290000, '刪除', null, 'sys:thproductline:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191647796331180000, 1191645704061350000, '站點數據維護', 'modules/sys/thstation.html', null, 1, 'fa fa-list', 1);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191647856485880000, 1191647796331180000, '查看', null, 'sys:thstation:list,sys:thstation:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191647904196090000, 1191647796331180000, '新增', null, 'sys:thstation:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191647947770720000, 1191647796331180000, '修改', null, 'sys:thstation:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1193811193759980000, 1191645704061350000, '班別信息維護', 'modules/sys/thshift.html', null, 1, 'fa fa-list', 6);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1193811487776500000, 1193811193759980000, '查看', null, 'sys:thshift:list,sys:thshift:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1193811540662480000, 1193811193759980000, '新增', null, 'sys:thshift:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1193811599722470000, 1193811193759980000, '修改', null, 'sys:thshift:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1193811655863230000, 1193811193759980000, '刪除', null, 'sys:thshift:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191878710537650000, 1191645704061350000, '產線休息時間維護', 'modules/sys/thbreaktime.html', null, 1, 'fa fa-list', 2);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191879401784120000, 1191645704061350000, '節假日數據維護', 'modules/sys/thholiday.html', null, 1, 'fa fa-list', 3);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191879459384490000, 1191878710537650000, '查看', null, 'sys:thbreaktime:list,sys:thbreaktime:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191879662518830000, 1191878710537650000, '新增', null, 'sys:thbreaktime:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194144212236570000, 1191220599019790000, '達成率看板', 'webHtml/AchieveRateReport.html', null, 1, 'fa fa-list', 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194144367971070000, 1191220599019790000, '自動化看板', 'webHtml/AutoEquipmentReport.html', null, 1, 'fa fa-list', 1);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194144561655640000, 1191220599019790000, '溫濕度ESD看板', 'webHtml/HumitureReport.html', null, 1, 'fa fa-list', 2);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194144758855040000, 1191220599019790000, '維修看板', 'webHtml/MaintainReport.html', null, 1, 'fa fa-list', 3);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194145514005970000, 1191220599019790000, '工序狀態看板', 'webHtml/ProcessStateReport.html', null, 1, 'fa fa-list', 5);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194145641886100000, 1191220599019790000, '質量看板', 'webHtml/QualityReport.html', null, 1, 'fa fa-list', 6);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194145794810430000, 1191220599019790000, '物料看板', 'webHtml/ThrowRateReport.html', null, 1, 'fa fa-list', 7);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194145927551760000, 1191220599019790000, '監控看板', 'webHtml/VideoReport.html', null, 1, 'fa fa-list', 8);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194151743746930000, 1191220599019790000, '訂單看板', 'webHtml/OrderReport.html', null, 1, 'fa fa-list', 4);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194163387759780000, 1194161998669210000, '查看', null, 'sys:threpairstabdardtime:list,sys:threpairstabdardtime:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194163517497990000, 1194161998669210000, '新增', null, 'sys:threpairstabdardtime:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194163578948740000, 1194161998669210000, '修改', null, 'sys:threpairstabdardtime:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194163633751520000, 1194161998669210000, '刪除', null, 'sys:threpairstabdardtime:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191606217184780000, 0, '看板配置', null, null, 0, 'fa fa-list', 2);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191607028791000000, 1191606217184780000, '自動化看板配置', 'modules/sys/thautoconfig.html', null, 1, 'fa fa-list', 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191607245674260000, 1191607028791000000, '查看', null, 'sys:thautoconfig:list,sys:thautoconfig:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191607344731140000, 1191607028791000000, '新增', null, 'sys:thautoconfig:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191607408811720000, 1191607028791000000, '修改', null, 'sys:thautoconfig:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191607468236620000, 1191607028791000000, '刪除', null, 'sys:thautoconfig:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1192000079355130000, 1191999955027570000, '新增', null, 'sys:thqualityyield:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191999955027570000, 1191645704061350000, '產線標准良率配置', 'modules/sys/thqualityyield.html', null, 1, 'fa fa-list', 4);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1192000024212620000, 1191999955027570000, '查看', null, 'sys:thqualityyield:list,sys:thqualityyield:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1192000131129620000, 1191999955027570000, '修改', null, 'sys:thqualityyield:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1192000184313390000, 1191999955027570000, '刪除', null, 'sys:thqualityyield:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1192284107459200000, 1191645704061350000, '不良類別維護', 'modules/sys/thbadtype.html', null, 1, 'fa fa-list', 5);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1192284163830650000, 1192284107459200000, '查看', null, 'sys:thbadtype:list,sys:thbadtype:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1192284216209120000, 1192284107459200000, '新增', null, 'sys:thbadtype:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1192284262564560000, 1192284107459200000, '修改', null, 'sys:thbadtype:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1192284308496390000, 1192284107459200000, '刪除', null, 'sys:thbadtype:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1194161998669210000, 1191645704061350000, '維修標准工時維護', 'modules/sys/threpairstabdardtime.html', null, 1, 'fa fa-list', 7);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191635549445870000, 1191635419221120000, '新增', null, 'sys:theuipmentconfig:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191635419221120000, 1191606217184780000, '工序看板配置', 'modules/sys/theuipmentconfig.html', null, 1, 'fa fa-list', 1);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191635488527800000, 1191635419221120000, '查看', null, 'sys:theuipmentconfig:list,sys:theuipmentconfig:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191637796946920000, 1191606217184780000, '維修看板配置', 'modules/sys/thmaintenanceconfig.html', null, 1, 'fa fa-list', 2);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191637951100170000, 1191637796946920000, '修改', null, 'sys:thmaintenanceconfig:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191637996184740000, 1191637796946920000, '刪除', null, 'sys:thmaintenanceconfig:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191638988603200000, 1191606217184780000, '溫濕度ESD配置', 'modules/sys/thesdconfig.html', null, 1, 'fa fa-list', 3);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191639064289420000, 1191638988603200000, '查看', null, 'sys:thesdconfig:list,sys:thesdconfig:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191639106077270000, 1191638988603200000, '新增', null, 'sys:thesdconfig:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191639167863560000, 1191638988603200000, '修改', null, 'sys:thesdconfig:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191639213363370000, 1191638988603200000, '刪除', null, 'sys:thesdconfig:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191640318898020000, 1191606217184780000, '質量看板配置', 'modules/sys/thqualityconfig.html', null, 1, 'fa fa-list', 4);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191640371351980000, 1191640318898020000, '查看', null, 'sys:thqualityconfig:list,sys:thqualityconfig:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191640417904560000, 1191640318898020000, '新增', null, 'sys:thqualityconfig:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191640474833850000, 1191640318898020000, '修改', null, 'sys:thqualityconfig:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191641495387710000, 1191606217184780000, '物料看板配置', 'modules/sys/thmateeralconfig.html', null, 1, 'fa fa-list', 5);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191641553143270000, 1191641495387710000, '查看', null, 'sys:thmateeralconfig:list,sys:thmateeralconfig:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191641605639180000, 1191641495387710000, '新增', null, 'sys:thmateeralconfig:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191641657409470000, 1191641495387710000, '修改', null, 'sys:thmateeralconfig:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191641703945280000, 1191641495387710000, '刪除', null, 'sys:thmateeralconfig:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191643276331080000, 1191606217184780000, '達成率看板配置', 'modules/sys/thrateconfig.html', null, 1, 'fa fa-list', 5);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191643333541390000, 1191643276331080000, '查看', null, 'sys:thrateconfig:list,sys:thrateconfig:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191643381947850000, 1191643276331080000, '新增', null, 'sys:thrateconfig:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191643433260970000, 1191643276331080000, '修改', null, 'sys:thrateconfig:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191643481910700000, 1191643276331080000, '刪除', null, 'sys:thrateconfig:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191645704061350000, 0, '數據管理', null, null, 0, 'fa fa-list', 3);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191645987013290000, 1191645704061350000, '產線數據維護', 'modules/sys/thproductline.html', null, 1, 'fa fa-list', 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191646042730420000, 1191645987013290000, '查看', null, 'sys:thproductline:list,sys:thproductline:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191646153925620000, 1191645987013290000, '修改', null, 'sys:thproductline:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191647993446690000, 1191647796331180000, '刪除', null, 'sys:thstation:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191879712938560000, 1191878710537650000, '修改', null, 'sys:thbreaktime:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191879765296050000, 1191878710537650000, '刪除', null, 'sys:thbreaktime:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191880064723220000, 1191879401784120000, '查看', null, 'sys:thholiday:list,sys:thholiday:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191880132536730000, 1191879401784120000, '新增', null, 'sys:thholiday:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191880179089310000, 1191879401784120000, '修改', null, 'sys:thholiday:update', 2, null, 0);
commit;
prompt 100 records committed...
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1191880230217880000, 1191879401784120000, '刪除', null, 'sys:thholiday:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1201407200228270082, 1191645704061350000, '站點WIP警戒值維護', 'modules/sys/thwipreddata.html', null, 1, 'fa fa-list', 9);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (1, 0, '系統管理', null, null, 0, 'fa fa-cog', 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (2, 1, '管理員管理', 'modules/sys/user.html', null, 1, 'fa fa-user', 1);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (3, 1, '角色管理', 'modules/sys/role.html', null, 1, 'fa fa-user-secret', 2);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (4, 1, '菜單管理', 'modules/sys/menu.html', null, 1, 'fa fa-th-list', 3);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (5, 1, 'SQL監控', 'druid/sql.html', null, 1, 'fa fa-bug', 4);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (6, 1, '定時任務', 'modules/job/schedule.html', null, 1, 'fa fa-tasks', 5);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (7, 6, '查看', null, 'sys:schedule:list,sys:schedule:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (8, 6, '新增', null, 'sys:schedule:save', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (9, 6, '修改', null, 'sys:schedule:update', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (10, 6, '刪除', null, 'sys:schedule:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (11, 6, '暫停', null, 'sys:schedule:pause', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (12, 6, '恢復', null, 'sys:schedule:resume', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (13, 6, '立即執行', null, 'sys:schedule:run', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (14, 6, '日誌列表', null, 'sys:schedule:log', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (15, 2, '查看', null, 'sys:user:list,sys:user:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (16, 2, '新增', null, 'sys:user:save,sys:role:select', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (17, 2, '修改', null, 'sys:user:update,sys:role:select', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (18, 2, '刪除', null, 'sys:user:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (19, 3, '查看', null, 'sys:role:list,sys:role:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (20, 3, '新增', null, 'sys:role:save,sys:menu:perms', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (21, 3, '修改', null, 'sys:role:update,sys:menu:perms', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (22, 3, '刪除', null, 'sys:role:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (23, 4, '查看', null, 'sys:menu:list,sys:menu:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (24, 4, '新增', null, 'sys:menu:save,sys:menu:select', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (25, 4, '修改', null, 'sys:menu:update,sys:menu:select', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (26, 4, '刪除', null, 'sys:menu:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (27, 1, '參數管理', 'modules/sys/config.html', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'fa fa-sun-o', 6);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (29, 1, '系統日誌', 'modules/sys/log.html', 'sys:log:list', 1, 'fa fa-file-text-o', 7);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (31, 1, '部門管理', 'modules/sys/dept.html', null, 1, 'fa fa-file-code-o', 1);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (32, 31, '查看', null, 'sys:dept:list,sys:dept:info', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (33, 31, '新增', null, 'sys:dept:save,sys:dept:select', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (34, 31, '修改', null, 'sys:dept:update,sys:dept:select', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (35, 31, '刪除', null, 'sys:dept:delete', 2, null, 0);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (36, 1, '字典管理', 'modules/sys/dict.html', null, 1, 'fa fa-bookmark-o', 6);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (37, 36, '查看', null, 'sys:dict:list,sys:dict:info', 2, null, 6);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (38, 36, '新增', null, 'sys:dict:save', 2, null, 6);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (39, 36, '修改', null, 'sys:dict:update', 2, null, 6);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (40, 36, '刪除', null, 'sys:dict:delete', 2, null, 6);
insert into SYS_MENU (menu_id, parent_id, name, url, perms, type, icon, order_num)
values (30, 1, '文件上傳', 'modules/oss/oss.html', 'sys:oss:all', 1, 'fa fa-file-image-o', 6);
commit;
prompt 141 records loaded
prompt Loading SYS_OSS...
prompt Table is empty
prompt Loading SYS_ROLE...
insert into SYS_ROLE (role_id, role_name, remark, dept_id, create_time)
values (1199557774534696962, '研發管理員', '研發管理員', 3, to_timestamp('27-11-2019 13:17:14.780000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 1 records loaded
prompt Loading SYS_ROLE_DEPT...
insert into SYS_ROLE_DEPT (id, role_id, dept_id)
values (1199557775931400193, 1199557774534696962, 1);
insert into SYS_ROLE_DEPT (id, role_id, dept_id)
values (1199557775969148929, 1199557774534696962, 2);
insert into SYS_ROLE_DEPT (id, role_id, dept_id)
values (1199557775977537538, 1199557774534696962, 3);
insert into SYS_ROLE_DEPT (id, role_id, dept_id)
values (1199557775985926146, 1199557774534696962, 4);
commit;
prompt 4 records loaded
prompt Loading SYS_ROLE_MENU...
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774677303298, 1199557774534696962, 1191220599019790000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774706663425, 1199557774534696962, 1194144212236570000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774723440642, 1199557774534696962, 1194144367971070000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774727634946, 1199557774534696962, 1194144561655640000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774740217857, 1199557774534696962, 1194144758855040000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774748606466, 1199557774534696962, 1194145514005970000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774756995073, 1199557774534696962, 1194145641886100000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774765383682, 1199557774534696962, 1194145794810430000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774777966593, 1199557774534696962, 1194145927551760000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774786355201, 1199557774534696962, 1194151743746930000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774794743810, 1199557774534696962, 1191606217184780000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774803132417, 1199557774534696962, 1191644391764290000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774815715329, 1199557774534696962, 1191644450459380000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774824103938, 1199557774534696962, 1191644502003180000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774832492546, 1199557774534696962, 1191644555006600000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774840881153, 1199557774534696962, 1191644600804200000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774849269761, 1199557774534696962, 1191607028791000000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774857658369, 1199557774534696962, 1191607245674260000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774866046977, 1199557774534696962, 1191607344731140000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774874435586, 1199557774534696962, 1191607408811720000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774882824193, 1199557774534696962, 1191607468236620000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774895407106, 1199557774534696962, 1191635419221120000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774903795713, 1199557774534696962, 1191635627665440000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774912184322, 1199557774534696962, 1191635691498560000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774920572929, 1199557774534696962, 1191635549445870000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774928961538, 1199557774534696962, 1191635488527800000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774937350145, 1199557774534696962, 1191637796946920000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774945738753, 1199557774534696962, 1191637850357180000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774958321666, 1199557774534696962, 1191637903704540000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774966710273, 1199557774534696962, 1191637951100170000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774975098882, 1199557774534696962, 1191637996184740000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774983487490, 1199557774534696962, 1191638988603200000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557774991876097, 1199557774534696962, 1191639064289420000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775000264705, 1199557774534696962, 1191639106077270000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775008653314, 1199557774534696962, 1191639167863560000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775017041921, 1199557774534696962, 1191639213363370000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775025430530, 1199557774534696962, 1191640318898020000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775033819138, 1199557774534696962, 1191640522871210000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775042207745, 1199557774534696962, 1191640371351980000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775050596354, 1199557774534696962, 1191640417904560000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775058984962, 1199557774534696962, 1191640474833850000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775063179265, 1199557774534696962, 1191641495387710000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775075762177, 1199557774534696962, 1191641553143270000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775084150786, 1199557774534696962, 1191641605639180000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775092539394, 1199557774534696962, 1191641657409470000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775100928001, 1199557774534696962, 1191641703945280000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775109316609, 1199557774534696962, 1191643276331080000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775117705218, 1199557774534696962, 1191643333541390000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775126093826, 1199557774534696962, 1191643381947850000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775134482433, 1199557774534696962, 1191643433260970000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775142871042, 1199557774534696962, 1191643481910700000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775151259649, 1199557774534696962, 1191645704061350000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775159648258, 1199557774534696962, 1191647796331180000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775168036866, 1199557774534696962, 1191647856485880000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775176425474, 1199557774534696962, 1191647904196090000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775184814082, 1199557774534696962, 1191647947770720000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775193202689, 1199557774534696962, 1191647993446690000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775201591297, 1199557774534696962, 1193811193759980000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775209979905, 1199557774534696962, 1193811487776500000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775218368513, 1199557774534696962, 1193811540662480000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775226757121, 1199557774534696962, 1193811599722470000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775235145729, 1199557774534696962, 1193811655863230000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775243534338, 1199557774534696962, 1191878710537650000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775251922945, 1199557774534696962, 1191879459384490000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775264505857, 1199557774534696962, 1191879662518830000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775272894465, 1199557774534696962, 1191879712938560000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775281283074, 1199557774534696962, 1191879765296050000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775289671682, 1199557774534696962, 1191879401784120000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775293865985, 1199557774534696962, 1191880064723220000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775306448898, 1199557774534696962, 1191880132536730000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775314837506, 1199557774534696962, 1191880179089310000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775323226113, 1199557774534696962, 1191880230217880000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775331614721, 1199557774534696962, 1191999955027570000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775340003329, 1199557774534696962, 1192000079355130000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775348391937, 1199557774534696962, 1192000024212620000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775356780545, 1199557774534696962, 1192000131129620000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775365169153, 1199557774534696962, 1192000184313390000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775373557762, 1199557774534696962, 1192284107459200000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775381946369, 1199557774534696962, 1192284163830650000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775390334978, 1199557774534696962, 1192284216209120000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775394529281, 1199557774534696962, 1192284262564560000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775407112194, 1199557774534696962, 1192284308496390000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775411306498, 1199557774534696962, 1194161998669210000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775423889410, 1199557774534696962, 1194163387759780000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775432278018, 1199557774534696962, 1194163517497990000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775440666625, 1199557774534696962, 1194163578948740000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775449055234, 1199557774534696962, 1194163633751520000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775461638145, 1199557774534696962, 1191645987013290000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775470026753, 1199557774534696962, 1191646099814900000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775478415361, 1199557774534696962, 1191646201770040000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775486803969, 1199557774534696962, 1191646042730420000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775495192577, 1199557774534696962, 1191646153925620000);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775503581185, 1199557774534696962, 1);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775511969794, 1199557774534696962, 2);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775520358401, 1199557774534696962, 15);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775528747009, 1199557774534696962, 16);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775537135618, 1199557774534696962, 17);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775545524226, 1199557774534696962, 18);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775553912833, 1199557774534696962, 3);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775562301442, 1199557774534696962, 19);
commit;
prompt 100 records committed...
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775570690049, 1199557774534696962, 20);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775579078657, 1199557774534696962, 21);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775579078658, 1199557774534696962, 22);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775591661569, 1199557774534696962, 4);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775600050177, 1199557774534696962, 23);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775608438785, 1199557774534696962, 24);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775616827394, 1199557774534696962, 25);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775625216001, 1199557774534696962, 26);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775633604609, 1199557774534696962, 5);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775641993218, 1199557774534696962, 6);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775650381825, 1199557774534696962, 7);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775654576130, 1199557774534696962, 8);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775662964738, 1199557774534696962, 9);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775671353346, 1199557774534696962, 10);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775679741954, 1199557774534696962, 11);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775688130562, 1199557774534696962, 12);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775696519169, 1199557774534696962, 13);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775713296386, 1199557774534696962, 14);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775730073602, 1199557774534696962, 27);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775738462210, 1199557774534696962, 29);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775755239426, 1199557774534696962, 31);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775763628034, 1199557774534696962, 32);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775772016642, 1199557774534696962, 33);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775780405249, 1199557774534696962, 34);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775801376769, 1199557774534696962, 35);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775813959682, 1199557774534696962, 36);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775822348290, 1199557774534696962, 37);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775830736897, 1199557774534696962, 38);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775839125505, 1199557774534696962, 39);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775847514114, 1199557774534696962, 40);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1199557775847514115, 1199557774534696962, 30);
commit;
prompt 131 records loaded
prompt Loading SYS_USER...
insert into SYS_USER (user_id, username, password, salt, email, mobile, status, dept_id, create_time)
values (1, 'admin', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', 1, 1, to_timestamp('26-11-2019 09:37:43.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 1 records loaded
prompt Loading SYS_USER_ROLE...
prompt Table is empty
prompt Enabling triggers for SYS_CONFIG...
alter table SYS_CONFIG enable all triggers;
prompt Enabling triggers for SYS_DEPT...
alter table SYS_DEPT enable all triggers;
prompt Enabling triggers for SYS_DICT...
alter table SYS_DICT enable all triggers;
prompt Enabling triggers for SYS_MENU...
alter table SYS_MENU enable all triggers;
prompt Enabling triggers for SYS_OSS...
alter table SYS_OSS enable all triggers;
prompt Enabling triggers for SYS_ROLE...
alter table SYS_ROLE enable all triggers;
prompt Enabling triggers for SYS_ROLE_DEPT...
alter table SYS_ROLE_DEPT enable all triggers;
prompt Enabling triggers for SYS_ROLE_MENU...
alter table SYS_ROLE_MENU enable all triggers;
prompt Enabling triggers for SYS_USER...
alter table SYS_USER enable all triggers;
prompt Enabling triggers for SYS_USER_ROLE...
alter table SYS_USER_ROLE enable all triggers;
set feedback on
set define on
prompt Done.
