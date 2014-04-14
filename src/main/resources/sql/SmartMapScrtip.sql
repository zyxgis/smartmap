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
   FileId               varchar(50) comment '�ļ�������',
   ParentId             varchar(50) comment '��������',
   FileName             varchar(256) comment '�ļ���',
   FilePath             varchar(256) comment '�ļ�λ��',
   Introduction         varchar(256) comment '����',
   FileType             varchar(50) comment '�ļ�����',
   FileSize             varchar(50) comment '�ļ���С',
   FileImg              varchar(50) comment '�ļ�ͼ��',
   Extension            varchar(50) comment '��׺��',
   Enabled              int comment '�ǲ���Ч��1--��Ч��0--��Ч',
   SortCode             int comment '������',
   DeleteMark           int comment 'ɾ����ǣ�1--������0--��ɾ',
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50),
   ModifyDate           datetime,
   ModifyUserId         varchar(50),
   ModifyUserName       varchar(50)
);

alter table BaseFile comment '�ļ���';

/*==============================================================*/
/* Table: BaseNews                                              */
/*==============================================================*/
create table BaseNews
(
   NewsId               char(10) comment '���ű�����',
   Introduction         char(10) comment '���ݼ��',
   Contents             char(10) comment '��������',
   Keywords             char(10) comment '�ؼ���',
   AuditStatus          char(10) comment '���״̬',
   ReadCount            char(10) comment '���Ķ�����',
   Enabled              char(10) comment '�Ƿ���Ч��1--��Ч��0--��Ч',
   SortCode             char(10) comment '������',
   DeleteMark           char(10) comment 'ɾ����ǣ�1--������0--��ɾ',
   CreateDate           char(10),
   CreateUserId         char(10),
   CreateUserName       char(10),
   ModifyDate           char(10),
   ModifyUserId         char(10),
   ModifyUserName       char(10)
);

alter table BaseNews comment '���ű�';

/*==============================================================*/
/* Table: SysButton                                             */
/*==============================================================*/
create table SysButton
(
   ButtonId             varchar(50) comment '��ť����',
   FullName             varchar(50) comment '��ť����',
   Img                  varchar(50) comment '��ťͼ��',
   Event                varchar(50) comment '��ť�¼�',
   ControlId            varchar(50) comment '�ؼ�ID',
   Category             varchar(50) comment '����',
   Split                int comment '�Ƿ�ֿ�',
   Description          varchar(50) comment '����',
   Enabled              int comment '��Ч��1--��Ч��0--��Ч',
   SortCode             int comment '������',
   DeleteMark           int comment 'ɾ����ǣ�1--������0--��ɾ',
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50),
   ModifyDate           datetime,
   ModifyUserId         varchar(50),
   ModifyUserName       varchar(50)
);

alter table SysButton comment '������ť';

/*==============================================================*/
/* Table: SysDataPermission                                     */
/*==============================================================*/
create table SysDataPermission
(
   DataPermissionId     char(10) comment '����Ȩ�޴洢����',
   RoleId               char(10) comment '��ɫ����',
   Code                 char(10) comment '���',
   ResourceId           char(10) comment '��ʲô��Դ',
   ObjectId             char(10) comment '�洢��������',
   CreateDate           char(10) comment '����ʱ��',
   CreateUserId         char(10) comment '�����û�����',
   CreateUserName       char(10) comment '�����û�'
);

alter table SysDataPermission comment '����Ȩ�޴洢��';

/*==============================================================*/
/* Table: SysEmployee                                           */
/*==============================================================*/
create table SysEmployee
(
   EmployeeId           varchar(50) comment 'Ա������',
   UserId               varchar(50) comment '�û�����',
   Code                 varchar(50) comment '��š�����',
   RealName             varchar(50) comment '����',
   Spell                varchar(50) comment '��ʵ����ƴ��',
   Alias                varchar(50) comment '����',
   Gender               varchar(50) comment '�Ա�',
   SubCompanyId         varchar(50) comment '��֧��������',
   CompanyId            varchar(50) comment '��˾����',
   DepartmentId         varchar(50) comment '��������',
   WorkgroupId          varchar(50) comment '����������',
   DutyId               varchar(50) comment 'ְλ����',
   IdCard               varchar(50) comment '���֤����',
   BankCode             varchar(50) comment '���ʿ�',
   Email                varchar(50) comment '�����ʼ�',
   Mobile               varchar(50) comment '�ֻ�',
   ShortNumber          varchar(50) comment '�̺�',
   Telephone            varchar(50) comment '�绰',
   Oicq                 varchar(50) comment 'QQ����',
   OfficePhone          varchar(50) comment '�칫�绰',
   OfficeZipCode        varchar(50) comment '�칫�ʱ�',
   OfficeAddress        varchar(200) comment '�칫��ַ',
   OfficeFax            varchar(50) comment '�칫����',
   Age                  int comment '����',
   Birthday             datetime comment '��������',
   Education            varchar(50) comment '���ѧ��',
   School               varchar(50) comment '��ҵԺУ',
   GraduationDate       datetime comment '��ҵʱ��',
   Major                varchar(50) comment '��ѧרҵ',
   Degree               varchar(50) comment '���ѧλ',
   TitleId              varchar(50) comment 'ְ������',
   TitleDate            datetime comment 'ְ����������',
   TitleLevel           varchar(50) comment 'ְ�Ƶȼ�',
   WorkingDate          varchar(50) comment '����ʱ��',
   JoinInDate           datetime comment '���뱾��λʱ��',
   HomeZipCode          varchar(50) comment '��ͥ��ַ�ʱ�',
   HomeAddress          varchar(50) comment '��ͥ��ַ',
   HomePhone            varchar(50) comment 'סլ�绰',
   HomeFax              varchar(50) comment '��ͥ����',
   Province             varchar(50) comment '����ʡ',
   City                 varchar(50) comment '������',
   Area                 varchar(50) comment '������',
   NativePlace          varchar(50) comment '����',
   Party                varchar(50) comment '������ò',
   Nation               varchar(50) comment '����',
   Nationality          varchar(50) comment '����',
   WorkingProperty      varchar(50) comment '�ù�����',
   Competency           varchar(200) comment 'ְҵ�ʸ�',
   EmergencyContact     varchar(50) comment '������ϵ',
   IsDimission          int comment '��ְ',
   DimissionDate        datetime comment '��ְ����',
   DimissionWhither     varchar(200) comment '��ְȥ��',
   DimissionCause       varchar(200) comment '��ְԭ��',
   Description          varchar(200) comment '����',
   Enabled              int comment '��Ч��1--��Ч��0--��Ч',
   SortCode             int comment '������',
   DeleteMark           int comment 'ɾ����ǣ�1--������0--��ɾ',
   CreateDate           datetime,
   CreateUserId         varchar(50),
   CreateUserName       varchar(50),
   ModifyDate           datetime,
   ModifyUserId         varchar(50),
   ModifyUserName       varchar(50)
);

alter table SysEmployee comment 'Ա����ְԱ';

/*==============================================================*/
/* Table: SysException                                          */
/*==============================================================*/
create table SysException
(
   ExceptionId          varchar(50) comment 'ϵͳ�쳣����',
   Source               varchar(200) comment '�쳣��Ϣ��Դ',
   Exception            varchar(200) comment '�쳣��Ϣ',
   Description          varchar(200) comment '�쳣��Ϣ����',
   OperationIp          varchar(50) comment '����IP',
   OperationIpCity      varchar(200) comment '����IP���ڳ���',
   CreateDate           datetime comment '����ʱ��'
);

alter table SysException comment 'ϵͳ�쳣';

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

alter table SysIpBlacklist comment 'IP������';

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

alter table SysItem comment '�����ֵ�����';

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

alter table SysItemDetail comment '�����ֵ���ϸ��';

/*==============================================================*/
/* Table: SysLog                                                */
/*==============================================================*/
create table SysLog
(
   logId                varchar(50) comment '������־����',
   OperationType        int comment '��������',
   TableName            varchar(50) comment '���ݿ��',
   BussinessName        varchar(50) comment 'ҵ������',
   ObjectID             varchar(50) comment '��������',
   OperationIp          varchar(50) comment '����IP',
   CreateDate           datetime comment '����ʱ��',
   CreateUserId         varchar(50) comment '�����û�����',
   CreateUserName       varchar(50) comment '�����û�',
   Result               varchar(500) comment '�������'
);

alter table SysLog comment 'ϵͳ������־';

/*==============================================================*/
/* Table: SysLogDetail                                          */
/*==============================================================*/
create table SysLogDetail
(
   LogDetailId          varchar(50) comment '������־��ϸ����',
   LogId                varchar(50) comment '������־����',
   FieldName            varchar(50) comment '�ֶ�����',
   FieldText            varchar(50) comment '�ֶ�ֵ',
   NewValue             varchar(50) comment '��ֵ',
   OldValue             varchar(50) comment '��ֵ',
   Remark               varchar(50) comment '��ע'
);

alter table SysLogDetail comment 'ϵͳ������־��ϸ';

/*==============================================================*/
/* Table: SysLoginLog                                           */
/*==============================================================*/
create table SysLoginLog
(
   LoginLogId           varchar(50) comment '��¼ϵͳ��־����',
   CreateDate           datetime comment '����ʱ��',
   Account              varchar(50) comment '��¼�˻�',
   Status               varchar(50) comment '��¼״̬',
   IpAddress            varchar(50) comment '��¼IP��ַ',
   IpAddressName        varchar(50) comment '��¼IP��ַ���ڵ�ַ'
);

alter table SysLoginLog comment '��¼ϵͳ��־';

/*==============================================================*/
/* Table: SysMenu                                               */
/*==============================================================*/
create table SysMenu
(
   MenuId               varchar(50) comment '�˵�����',
   ParentId             varchar(50) comment '��������',
   Code                 varchar(50) comment '���',
   FullName             varchar(50) comment '����',
   Description          varchar(50) comment '����',
   Img                  varchar(50) comment 'ͼ��',
   Category             varchar(50) comment '�˵�����',
   NavigateUrl          varchar(50) comment '������ַ',
   FormName             varchar(50) comment '������',
   Target               varchar(50) comment 'Ŀ��',
   IsUnfold             int comment '�Ƿ�չ��',
   AllowEdit            int comment '����༭',
   AllowDelete          int comment '����ɾ��',
   Enabled              int comment '��Ч��1--��Ч��0--��Ч',
   SortCode             int comment '������',
   DeleteMark           int comment 'ɾ����ǣ�1--������0--ɾ��',
   CreateDate           datetime comment '����ʱ��',
   CreateUserId         varchar(50) comment '�����û�����',
   CreateUserName       varchar(50) comment '�����û�',
   ModifyDate           datetime comment '�޸�ʱ��',
   ModifyUserId         varchar(50) comment '�޸��û�����',
   ModifyUserName       varchar(50) comment '�޸��û�'
);

alter table SysMenu comment 'ģ�飨�˵�������';

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

alter table SysMenuButton comment '�˵�����������ť��ϵ��';

/*==============================================================*/
/* Table: SysOrganization                                       */
/*==============================================================*/
create table SysOrganization
(
   OrganizationId       varchar(50) comment '��֯��������',
   ParentId             varchar(50) comment '��������',
   Code                 varchar(50) comment '���',
   ShortName            varchar(50) comment '���',
   FullName             varchar(50) comment '����',
   Category             varchar(50) comment '����',
   IsInnerOrganize      int comment '�ڲ���֯',
   Manager              varchar(50) comment '��������',
   AssistantManager     varchar(50) comment '��������',
   OuterPhone           varchar(50) comment '�绰',
   InnerPhone           varchar(50) comment '����',
   Fax                  varchar(50) comment '����',
   PostalCode           varchar(50) comment '�ʱ�',
   Address              varchar(50) comment '��ַ',
   Web                  varchar(50) comment '��ַ',
   Description          varchar(50),
   Enabled              int comment '��Ч��1--��Ч��0--��Ч',
   SortCode             int comment '������',
   DeleteMark           int comment 'ɾ����ǣ�1--������0--ɾ��',
   CreateDate           datetime comment '����ʱ��',
   CreateUserId         varchar(50) comment '�����û�����',
   CreateUserName       varchar(50) comment '�����û�',
   ModifyDate           datetime comment '�޸�ʱ��',
   ModifyUserId         varchar(50) comment '�޸��û�����',
   ModifyUserName       varchar(50) comment '�޸��û�'
);

alter table SysOrganization comment '��֯����������';

/*==============================================================*/
/* Table: SysRole                                               */
/*==============================================================*/
create table SysRole
(
   RoleId               varchar(50),
   OrganizationId       varchar(50) comment '��֯��������',
   ParentId             varchar(50) comment '��������',
   Code                 varchar(50) comment '��ɫ���',
   FullName             varchar(50) comment '��ɫ����',
   Category             varchar(50) comment '��ɫ����',
   Description          varchar(50),
   AllowEdit            int comment '����༭',
   AllowDelete          int comment '����ɾ��',
   Enabled              int comment '��Ч��1--��Ч��0--��Ч',
   SortCode             int comment '������',
   DeleteMark           int comment 'ɾ����ǣ�1--������0--��ɾ',
   CreateDate           datetime comment '����ʱ��',
   CreateUserId         varchar(50) comment '�����û�����',
   CreateUserName       varchar(50) comment '�����û�',
   ModifyDate           datetime comment '�޸�ʱ��',
   ModifyUserId         varchar(50) comment '�޸��û�����',
   ModifyUserName       varchar(50) comment '�޸��û�'
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
   ShortcutId           varchar(50) comment '��ҳ��ݹ�������',
   UserId               varchar(50) comment '�û�����',
   MenuId               varchar(50) comment '�˵�����',
   CreateDate           datetime comment '����ʱ��'
);

alter table SysShortcut comment '��ҳ��ݹ���';

/*==============================================================*/
/* Table: SysStaffOrganize                                      */
/*==============================================================*/
create table SysStaffOrganize
(
   StaffOrganizeId      varchar(50) comment '�û���֯��ϵ����',
   OrganizeId           varchar(50) comment '��֯����',
   UserId               varchar(50) comment '�û�����',
   CreateDate           datetime comment '����ʱ��',
   CreateUserId         varchar(50) comment '�����û�����',
   CreateUserName       varchar(50) comment '�����û�'
);

alter table SysStaffOrganize comment '�û��˻����Ź�ϵ';

/*==============================================================*/
/* Table: SysUser                                               */
/*==============================================================*/
create table SysUser
(
   UserId               varchar(50),
   Code                 varchar(50) comment '�û�����',
   Acount               varchar(50) comment '��¼�˻�',
   Password             varchar(50),
   SecretKey            varchar(50),
   RealName             varchar(50) comment '��ʵ����',
   Spell                varchar(50) comment '��ʵ����ƴ��',
   Alias                varchar(50) comment '����',
   RoleId               varchar(50) comment 'Ĭ�Ͻ�ɫ',
   Gender               varchar(50) comment '�Ա�',
   Mobile               varchar(50) comment '�ֻ�����',
   Telephone            varchar(50) comment '�̶��绰',
   Birthday             datetime,
   Email                varchar(50),
   Oicq                 varchar(50) comment 'QQ����',
   DutyId               varchar(50) comment '��λ',
   TitleId              varchar(50) comment 'ְ��',
   CompanyId            varchar(50) comment '��˾����',
   DepartmentId         varchar(50) comment '��������',
   WorkgroupId          varchar(50) comment '����������',
   Description          varchar(50),
   SecurityLevel        varchar(50) comment '��ȫ����',
   ChangePasswordDay    datetime comment '����޸���������',
   OpenId               int comment '�����¼��ʶ',
   IpAddress            varchar(50) comment 'IP��ַ',
   MacAddress           varchar(50) comment 'MA��ַ',
   logOnCount           bigint comment '��¼����',
   FirstVisit           datetime comment '��һ�η���ʱ��',
   PreviousVisit        datetime comment '��һ�η���ʱ��',
   LastVisit            datetime comment '���η���ʱ��',
   Enabled              int comment '��Ч��1--��Ч�� 0--��Ч',
   SortCode             int comment '������',
   DeleteMark           int comment 'ɾ����ǣ�1--������ 0--��ɾ',
   CreateDate           datetime comment '����ʱ��',
   CreateUserId         varchar(50) comment '�����û�����',
   CreateUserName       varchar(50) comment '�����û�',
   ModifyDate           datetime comment '�޸�ʱ��',
   ModifyUserId         varchar(50) comment '�޸��û�����',
   ModifyUserName       varchar(50) comment '�޸��û�'
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

alter table SysUserMenu comment '�û��˵���ϵ';

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

alter table SysUserMenuButton comment '�û��˵���ť��ϵ';

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

alter table SysUserRole comment '�û��ʻ���ɫ��ϵ';

