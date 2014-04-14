/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013-12-11 17:01:52                          */
/*==============================================================*/


drop table if exists BaseFile;

drop table if exists BaseNews;

drop table if exists SysButton;

drop table if exists SysDataPermission;

drop table if exists SysEmployee;

drop table if exists SysException;

drop table if exists SysIpBlacklist;

drop table if exists SysItem;

drop table if exists SysItemDetail;

drop table if exists SysLog;

drop table if exists SysLogDetail;

drop table if exists SysLoginLog;

drop table if exists SysMenu;

drop table if exists SysMenuButton;

drop table if exists SysOrganization;

drop table if exists SysRole;

drop table if exists SysRoleMenu;

drop table if exists SysRoleMenuButton;

drop table if exists SysShortcut;

drop table if exists SysStaffOrganize;

drop table if exists SysUser;

drop table if exists SysUserMenu;

drop table if exists SysUserMenuButton;

drop table if exists SysUserRole;

/*==============================================================*/
/* Table: BaseFile                                              */
/*==============================================================*/
create table BaseFile
(
   FileId               varchar(50) comment '文件表主键',
   ParentId             varchar(50) comment '父级主键',
   FileName             varchar(256) comment '文件名',
   FilePath             varchar(256) comment '文件位置',
   Introduction         varchar(256) comment '介绍',
   FileType             varchar(50) comment '文件类型',
   FileSize             varchar(50) comment '文件大小',
   FileImg              varchar(50) comment '文件图标',
   Extension            varchar(50) comment '后缀名',
   Enabled              int comment '是不有效：1--有效，0--无效',
   SortCode             int comment '排序码',
   DeleteMark           int comment '删除标记：1--正常，0--已删',
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50),
   ModifyDate           datetime,
   ModifyUserId         varchar(50),
   ModifyUserName       varchar(50)
);

alter table BaseFile comment '文件表';

/*==============================================================*/
/* Table: BaseNews                                              */
/*==============================================================*/
create table BaseNews
(
   NewsId               char(10) comment '新闻表主键',
   Introduction         char(10) comment '内容简介',
   Contents             char(10) comment '文章内容',
   Keywords             char(10) comment '关键字',
   AuditStatus          char(10) comment '审核状态',
   ReadCount            char(10) comment '被阅读次数',
   Enabled              char(10) comment '是否有效：1--有效，0--无效',
   SortCode             char(10) comment '排序码',
   DeleteMark           char(10) comment '删除标记：1--正常，0--已删',
   CreateDate           char(10),
   CreateUserId         char(10),
   CreateUserName       char(10),
   ModifyDate           char(10),
   ModifyUserId         char(10),
   ModifyUserName       char(10)
);

alter table BaseNews comment '新闻表';

/*==============================================================*/
/* Table: SysButton                                             */
/*==============================================================*/
create table SysButton
(
   ButtonId             varchar(50) comment '按钮主键',
   FullName             varchar(50) comment '按钮名称',
   Img                  varchar(50) comment '按钮图标',
   Event                varchar(50) comment '按钮事件',
   ControlId            varchar(50) comment '控件ID',
   Category             varchar(50) comment '分类',
   Split                int comment '是否分开',
   Description          varchar(50) comment '描述',
   Enabled              int comment '有效：1--有效，0--无效',
   SortCode             int comment '排序码',
   DeleteMark           int comment '删除标记：1--正常，0--已删',
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50),
   ModifyDate           datetime,
   ModifyUserId         varchar(50),
   ModifyUserName       varchar(50)
);

alter table SysButton comment '操作按钮';

/*==============================================================*/
/* Table: SysDataPermission                                     */
/*==============================================================*/
create table SysDataPermission
(
   DataPermissionId     char(10) comment '数据权限存储主键',
   RoleId               char(10) comment '角色主键',
   Code                 char(10) comment '编号',
   ResourceId           char(10) comment '对什么资源',
   ObjectId             char(10) comment '存储对象主键',
   CreateDate           char(10) comment '创建时间',
   CreateUserId         char(10) comment '创建用户主键',
   CreateUserName       char(10) comment '创建用户'
);

alter table SysDataPermission comment '数据权限存储表';

/*==============================================================*/
/* Table: SysEmployee                                           */
/*==============================================================*/
create table SysEmployee
(
   EmployeeId           varchar(50) comment '员工主键',
   UserId               varchar(50) comment '用户主键',
   Code                 varchar(50) comment '编号、工号',
   RealName             varchar(50) comment '姓名',
   Spell                varchar(50) comment '真实姓名拼音',
   Alias                varchar(50) comment '别名',
   Gender               varchar(50) comment '性别',
   SubCompanyId         varchar(50) comment '分支机构主键',
   CompanyId            varchar(50) comment '公司主键',
   DepartmentId         varchar(50) comment '部门主键',
   WorkgroupId          varchar(50) comment '工作组主键',
   DutyId               varchar(50) comment '职位主键',
   IdCard               varchar(50) comment '身份证号码',
   BankCode             varchar(50) comment '工资卡',
   Email                varchar(50) comment '电子邮件',
   Mobile               varchar(50) comment '手机',
   ShortNumber          varchar(50) comment '短号',
   Telephone            varchar(50) comment '电话',
   Oicq                 varchar(50) comment 'QQ号码',
   OfficePhone          varchar(50) comment '办公电话',
   OfficeZipCode        varchar(50) comment '办公邮编',
   OfficeAddress        varchar(200) comment '办公地址',
   OfficeFax            varchar(50) comment '办公传真',
   Age                  int comment '年龄',
   Birthday             datetime comment '出生日期',
   Education            varchar(50) comment '最高学历',
   School               varchar(50) comment '毕业院校',
   GraduationDate       datetime comment '毕业时间',
   Major                varchar(50) comment '所学专业',
   Degree               varchar(50) comment '最高学位',
   TitleId              varchar(50) comment '职称主键',
   TitleDate            datetime comment '职称评定日期',
   TitleLevel           varchar(50) comment '职称等级',
   WorkingDate          varchar(50) comment '工作时间',
   JoinInDate           datetime comment '加入本单位时间',
   HomeZipCode          varchar(50) comment '家庭信址邮编',
   HomeAddress          varchar(50) comment '家庭信址',
   HomePhone            varchar(50) comment '住宅电话',
   HomeFax              varchar(50) comment '家庭传真',
   Province             varchar(50) comment '籍贯省',
   City                 varchar(50) comment '籍贯市',
   Area                 varchar(50) comment '籍贯区',
   NativePlace          varchar(50) comment '籍贯',
   Party                varchar(50) comment '政治面貌',
   Nation               varchar(50) comment '国籍',
   Nationality          varchar(50) comment '民族',
   WorkingProperty      varchar(50) comment '用工性质',
   Competency           varchar(200) comment '职业资格',
   EmergencyContact     varchar(50) comment '紧急联系',
   IsDimission          int comment '离职',
   DimissionDate        datetime comment '离职日期',
   DimissionWhither     varchar(200) comment '离职去向',
   DimissionCause       varchar(200) comment '离职原因',
   Description          varchar(200) comment '描述',
   Enabled              int comment '有效：1--有效，0--无效',
   SortCode             int comment '排序码',
   DeleteMark           int comment '删除标记：1--正常，0--已删',
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50),
   ModifyDate           datetime,
   ModifyUserId         varchar(50),
   ModifyUserName       varchar(50)
);

alter table SysEmployee comment '员工、职员';

/*==============================================================*/
/* Table: SysException                                          */
/*==============================================================*/
create table SysException
(
   ExceptionId          varchar(50) comment '系统异常主键',
   Source               varchar(200) comment '异常信息来源',
   Exception            varchar(200) comment '异常信息',
   Description          varchar(200) comment '异常信息描述',
   OperationIp          varchar(50) comment '操作IP',
   OperationIpCity      varchar(200) comment '操作IP所在城市',
   CreateDate           datetime comment '发生时间'
);

alter table SysException comment '系统异常';

/*==============================================================*/
/* Table: SysIpBlacklist                                        */
/*==============================================================*/
create table SysIpBlacklist
(
   IpBlackListId        varchar(50),
   Category             varchar(50),
   StartIp              varchar(50),
   EndIp                varchar(50),
   FailureTime          datetime,
   Description          varchar(50),
   Enabled              int,
   SortCode             int,
   DeleteMark           int,
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50),
   ModifyDate           datetime,
   ModifyUserId         varchar(50),
   ModifyUserName       varchar(50)
);

alter table SysIpBlacklist comment 'IP黑名单';

/*==============================================================*/
/* Table: SysItem                                               */
/*==============================================================*/
create table SysItem
(
   ItemId               varchar(50),
   ParentId             varchar(50),
   Code                 varchar(50),
   FullName             varchar(50),
   Category             varchar(50),
   IsTree               int,
   AllowEdit            int,
   AllowDelete          int,
   Enabled              int,
   SortCode             int,
   DeleteMark           int,
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50),
   ModifyDate           datetime,
   ModifyUserId         varchar(50),
   ModifyUserName       varchar(50)
);

alter table SysItem comment '数据字典主表';

/*==============================================================*/
/* Table: SysItemDetail                                         */
/*==============================================================*/
create table SysItemDetail
(
   ItemDetailId         varchar(50),
   ItemId               varchar(50),
   ParentId             varchar(50),
   ItemName             varchar(50),
   ItemCode             varchar(50),
   Description          varchar(50),
   AllowEdit            int,
   AllowDelete          int,
   Enabled              int,
   SortCode             int,
   DeleteMark           int,
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50),
   ModifyDate           datetime,
   ModifyUserId         varchar(50),
   ModifyUserName       varchar(50)
);

alter table SysItemDetail comment '数据字典明细表';

/*==============================================================*/
/* Table: SysLog                                                */
/*==============================================================*/
create table SysLog
(
   logId                varchar(50) comment '操作日志主键',
   OperationType        int comment '操作类型',
   TableName            varchar(50) comment '数据库表',
   BussinessName        varchar(50) comment '业务名称',
   ObjectID             varchar(50) comment '对象主键',
   OperationIp          varchar(50) comment '操作IP',
   CreateDate           datetime comment '发生时间',
   CreateUserId         varchar(50) comment '创建用户主键',
   CreateUserName       varchar(50) comment '创建用户',
   Result               varchar(500) comment '结果数据'
);

alter table SysLog comment '系统操作日志';

/*==============================================================*/
/* Table: SysLogDetail                                          */
/*==============================================================*/
create table SysLogDetail
(
   LogDetailId          varchar(50) comment '操作日志明细主键',
   LogId                varchar(50) comment '操作日志主键',
   FieldName            varchar(50) comment '字段名称',
   FieldText            varchar(50) comment '字段值',
   NewValue             varchar(50) comment '新值',
   OldValue             varchar(50) comment '旧值',
   Remark               varchar(50) comment '备注'
);

alter table SysLogDetail comment '系统操作日志明细';

/*==============================================================*/
/* Table: SysLoginLog                                           */
/*==============================================================*/
create table SysLoginLog
(
   LoginLogId           varchar(50) comment '登录系统日志主键',
   CreateDate           datetime comment '发生时间',
   Account              varchar(50) comment '登录账户',
   Status               varchar(50) comment '登录状态',
   IpAddress            varchar(50) comment '登录IP地址',
   IpAddressName        varchar(50) comment '登录IP地址所在地址'
);

alter table SysLoginLog comment '登录系统日志';

/*==============================================================*/
/* Table: SysMenu                                               */
/*==============================================================*/
create table SysMenu
(
   MenuId               varchar(50) comment '菜单主键',
   ParentId             varchar(50) comment '父级主键',
   Code                 varchar(50) comment '编号',
   FullName             varchar(50) comment '名称',
   Description          varchar(50) comment '描述',
   Img                  varchar(50) comment '图标',
   Category             varchar(50) comment '菜单分类',
   NavigateUrl          varchar(50) comment '导航地址',
   FormName             varchar(50) comment '窗体名',
   Target               varchar(50) comment '目标',
   IsUnfold             int comment '是否展开',
   AllowEdit            int comment '允许编辑',
   AllowDelete          int comment '允许删除',
   Enabled              int comment '有效：1--有效，0--无效',
   SortCode             int comment '排序码',
   DeleteMark           int comment '删除标记：1--正常，0--删除',
   CreateDate           datetime comment '创建时间',
   CreateUserId         varchar(50) comment '创建用户主键',
   CreateUserName       varchar(50) comment '创建用户',
   ModifyDate           datetime comment '修改时间',
   ModifyUserId         varchar(50) comment '修改用户主键',
   ModifyUserName       varchar(50) comment '修改用户'
);

alter table SysMenu comment '模块（菜单导航）';

/*==============================================================*/
/* Table: SysMenuButton                                         */
/*==============================================================*/
create table SysMenuButton
(
   MenuButtonId         varchar(50),
   MenuId               varchar(50),
   ButtonId             varchar(50),
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50),
   SortCode             int
);

alter table SysMenuButton comment '菜单导航操作按钮关系表';

/*==============================================================*/
/* Table: SysOrganization                                       */
/*==============================================================*/
create table SysOrganization
(
   OrganizationId       varchar(50) comment '组织机构主键',
   ParentId             varchar(50) comment '父级主键',
   Code                 varchar(50) comment '编号',
   ShortName            varchar(50) comment '简称',
   FullName             varchar(50) comment '名称',
   Category             varchar(50) comment '分类',
   IsInnerOrganize      int comment '内部组织',
   Manager              varchar(50) comment '主负责人',
   AssistantManager     varchar(50) comment '副负责人',
   OuterPhone           varchar(50) comment '电话',
   InnerPhone           varchar(50) comment '内线',
   Fax                  varchar(50) comment '传真',
   PostalCode           varchar(50) comment '邮编',
   Address              varchar(50) comment '地址',
   Web                  varchar(50) comment '网址',
   Description          varchar(50),
   Enabled              int comment '有效：1--有效，0--无效',
   SortCode             int comment '排序码',
   DeleteMark           int comment '删除标记：1--正常，0--删除',
   CreateDate           datetime comment '创建时间',
   CreateUserId         varchar(50) comment '创建用户主键',
   CreateUserName       varchar(50) comment '创建用户',
   ModifyDate           datetime comment '修改时间',
   ModifyUserId         varchar(50) comment '修改用户主键',
   ModifyUserName       varchar(50) comment '修改用户'
);

alter table SysOrganization comment '组织机构、部门';

/*==============================================================*/
/* Table: SysRole                                               */
/*==============================================================*/
create table SysRole
(
   RoleId               varchar(50),
   OrganizationId       varchar(50) comment '组织机构主键',
   ParentId             varchar(50) comment '父级主键',
   Code                 varchar(50) comment '角色编号',
   FullName             varchar(50) comment '角色名称',
   Category             varchar(50) comment '角色分类',
   Description          varchar(50),
   AllowEdit            int comment '允许编辑',
   AllowDelete          int comment '允许删除',
   Enabled              int comment '有效：1--有效，0--无效',
   SortCode             int comment '排序码',
   DeleteMark           int comment '删除标记：1--正常，0--已删',
   CreateDate           datetime comment '创建时间',
   CreateUserId         varchar(50) comment '创建用户主键',
   CreateUserName       varchar(50) comment '创建用户',
   ModifyDate           datetime comment '修改时间',
   ModifyUserId         varchar(50) comment '修改用户主键',
   ModifyUserName       varchar(50) comment '修改用户'
);

/*==============================================================*/
/* Table: SysRoleMenu                                           */
/*==============================================================*/
create table SysRoleMenu
(
   RoleMenuId           varchar(50),
   RoleId               varchar(50),
   MenuId               varchar(50),
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50)
);

/*==============================================================*/
/* Table: SysRoleMenuButton                                     */
/*==============================================================*/
create table SysRoleMenuButton
(
   RoleMenuButtonId     varchar(50),
   RoleId               varchar(50),
   MenuId               varchar(50),
   ButtonId             varchar(50),
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50)
);

/*==============================================================*/
/* Table: SysShortcut                                           */
/*==============================================================*/
create table SysShortcut
(
   ShortcutId           varchar(50) comment '首页快捷功能主键',
   UserId               varchar(50) comment '用户主键',
   MenuId               varchar(50) comment '菜单主键',
   CreateDate           datetime comment '创建时间'
);

alter table SysShortcut comment '首页快捷功能';

/*==============================================================*/
/* Table: SysStaffOrganize                                      */
/*==============================================================*/
create table SysStaffOrganize
(
   StaffOrganizeId      varchar(50) comment '用户组织关系主键',
   OrganizeId           varchar(50) comment '组织机构',
   UserId               varchar(50) comment '用户主键',
   CreateDate           datetime comment '发生时间',
   CreateUserId         varchar(50) comment '创建用户主键',
   CreateUserName       varchar(50) comment '创建用户'
);

alter table SysStaffOrganize comment '用户账户部门关系';

/*==============================================================*/
/* Table: SysUser                                               */
/*==============================================================*/
create table SysUser
(
   UserId               varchar(50),
   Code                 varchar(50) comment '用户编码',
   Acount               varchar(50) comment '登录账户',
   Password             varchar(50),
   SecretKey            varchar(50),
   RealName             varchar(50) comment '真实姓名',
   Spell                varchar(50) comment '真实姓名拼音',
   Alias                varchar(50) comment '别名',
   RoleId               varchar(50) comment '默认角色',
   Gender               varchar(50) comment '性别',
   Mobile               varchar(50) comment '手机号码',
   Telephone            varchar(50) comment '固定电话',
   Birthday             datetime,
   Email                varchar(50),
   Oicq                 varchar(50) comment 'QQ号码',
   DutyId               varchar(50) comment '岗位',
   TitleId              varchar(50) comment '职称',
   CompanyId            varchar(50) comment '公司主键',
   DepartmentId         varchar(50) comment '部门主键',
   WorkgroupId          varchar(50) comment '工作组主键',
   Description          varchar(50),
   SecurityLevel        varchar(50) comment '安全级别',
   ChangePasswordDay    datetime comment '最后修改密码日期',
   OpenId               int comment '单点登录标识',
   IpAddress            varchar(50) comment 'IP地址',
   MacAddress           varchar(50) comment 'MA地址',
   logOnCount           bigint comment '登录次数',
   FirstVisit           datetime comment '第一次访问时间',
   PreviousVisit        datetime comment '上一次访问时间',
   LastVisit            datetime comment '最后次访问时间',
   Enabled              int comment '有效：1--有效， 0--无效',
   SortCode             int comment '排序码',
   DeleteMark           int comment '删除标记：1--正常， 0--已删',
   CreateDate           datetime comment '创建时间',
   CreateUserId         varchar(50) comment '创建用户主键',
   CreateUserName       varchar(50) comment '创建用户',
   ModifyDate           datetime comment '修改时间',
   ModifyUserId         varchar(50) comment '修改用户主键',
   ModifyUserName       varchar(50) comment '修改用户'
);

/*==============================================================*/
/* Table: SysUserMenu                                           */
/*==============================================================*/
create table SysUserMenu
(
   UserMenuId           varchar(50),
   UserId               varchar(50),
   MenuId               varchar(50),
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50)
);

alter table SysUserMenu comment '用户菜单关系';

/*==============================================================*/
/* Table: SysUserMenuButton                                     */
/*==============================================================*/
create table SysUserMenuButton
(
   UserMenuButtonId     varchar(50),
   UserId               varchar(50),
   MenuId               varchar(50),
   ButtonId             varchar(50),
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50)
);

alter table SysUserMenuButton comment '用户菜单按钮关系';

/*==============================================================*/
/* Table: SysUserRole                                           */
/*==============================================================*/
create table SysUserRole
(
   UserRoleId           varchar(50),
   UserId               varchar(50),
   RoleId               varchar(50),
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50)
);

alter table SysUserRole comment '用户帐户角色关系';

