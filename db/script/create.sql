/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/5/7 9:20:05                             */
/*==============================================================*/


drop table if exists PRD_CATEGORY;

drop table if exists PRD_PRODUCT;

drop table if exists PRD_PRODUCT_BARCODE;

drop table if exists PRD_PRODUCT_PIC;

drop table if exists PRD_PRODUCT_SPEC;

drop table if exists PRD_PRODUCT_SPEC_ATTR;

/*==============================================================*/
/* Table: PRD_CATEGORY                                          */
/*==============================================================*/
create table PRD_CATEGORY
(
   ID                   bigint not null comment '产品分类ID',
   NAME                 varchar(50) not null comment '分类名称',
   CODE                 varchar(50) not null comment '分类编码',
   IS_ENABLED           bool not null comment '是否启用',
   OP_ID                bigint not null comment '操作人ID',
   CREATE_TIME          datetime not null comment '创建时间',
   primary key (ID)
);

alter table PRD_CATEGORY comment '分类';

/*==============================================================*/
/* Table: PRD_PRODUCT                                           */
/*==============================================================*/
create table PRD_PRODUCT
(
   ID                   bigint not null comment '产品ID',
   CATEGORY_ID          bigint not null comment '产品分类ID',
   PRODUCT_NAME         varchar(100) not null comment '产品名称',
   IS_ENABLED           bool not null default true comment '是否启用',
   MANUFACTURER         varchar(50) comment '生产厂家',
   BRAND                varchar(20) comment '品牌',
   OP_ID                bigint not null comment '操作人',
   CREATE_TIME          datetime not null comment '创建时间',
   PRODUCT_DESC         varchar(200) comment '产品描述',
   primary key (ID)
);

alter table PRD_PRODUCT comment '产品';

/*==============================================================*/
/* Table: PRD_PRODUCT_BARCODE                                   */
/*==============================================================*/
create table PRD_PRODUCT_BARCODE
(
   ID                   bigint not null comment '产品条形码ID',
   PRODUCT_SPEC_ID      bigint not null comment '产品规格ID',
   CODE                 varchar(30) not null comment '编码',
   SHOP_ID              bigint comment '店铺ID',
   primary key (ID)
);

alter table PRD_PRODUCT_BARCODE comment '产品条形码';

/*==============================================================*/
/* Table: PRD_PRODUCT_PIC                                       */
/*==============================================================*/
create table PRD_PRODUCT_PIC
(
   ID                   char(10) not null comment '产品图片ID',
   PRODUCT_ID           bigint not null comment '产品ID',
   PIC_TYPE             tinyint not null comment '图片类型(1：主图  0：轮播图)',
   PIC_PATH             varchar(800) not null comment '图片路径',
   primary key (ID)
);

alter table PRD_PRODUCT_PIC comment '产品图片';

/*==============================================================*/
/* Table: PRD_PRODUCT_SPEC                                      */
/*==============================================================*/
create table PRD_PRODUCT_SPEC
(
   ID                   bigint not null comment '产品规格ID',
   PRODUCT_ID           bigint not null comment '产品ID',
   NAME                 varchar(50) comment '产品规格名称',
   CODE                 varchar(50) comment '规格编码',
   UNIT                 varchar(20) comment '单位',
   PIC_PATH             varchar(100) comment '图片路径',
   primary key (ID)
);

alter table PRD_PRODUCT_SPEC comment '产品规格';

/*==============================================================*/
/* Table: PRD_PRODUCT_SPEC_ATTR                                 */
/*==============================================================*/
create table PRD_PRODUCT_SPEC_ATTR
(
   ID                   bigint not null comment '产品规格属性ID',
   PRODUCT_SPEC_ID      bigint not null comment '产品规格ID',
   ATTR_NAME            varchar(50) not null comment '属性名称',
   ATTR_VALUE           varchar(50) not null comment '属性值',
   primary key (ID),
   key AK_ONLINE_SPEC_AND_ATTR_NAME_AND_ATTR_VALUE (ATTR_NAME, ATTR_VALUE)
);

alter table PRD_PRODUCT_SPEC_ATTR comment '产品规格属性';

alter table PRD_PRODUCT add constraint FK_Relationship_3 foreign key (CATEGORY_ID)
      references PRD_CATEGORY (ID) on delete restrict on update restrict;

alter table PRD_PRODUCT_BARCODE add constraint FK_Relationship_6 foreign key (PRODUCT_SPEC_ID)
      references PRD_PRODUCT_SPEC (ID) on delete restrict on update restrict;

alter table PRD_PRODUCT_PIC add constraint FK_Relationship_4 foreign key (PRODUCT_ID)
      references PRD_PRODUCT (ID) on delete restrict on update restrict;

alter table PRD_PRODUCT_SPEC add constraint FK_Relationship_2 foreign key (PRODUCT_ID)
      references PRD_PRODUCT (ID) on delete restrict on update restrict;

alter table PRD_PRODUCT_SPEC_ATTR add constraint FK_Relationship_5 foreign key (PRODUCT_SPEC_ID)
      references PRD_PRODUCT_SPEC (ID) on delete restrict on update restrict;

