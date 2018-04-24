/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     Чт 28 12 2017 11:04:35                       */
/*==============================================================*/


drop table if exists Activities;

drop table if exists Users;

/*==============================================================*/
/* Table: Activities                                            */
/*==============================================================*/
create table Activities
(
   id_activity          int not null,
   userID               int,
   nameActivity         varchar(100) not null,
   duration             int not null,
   marked               bool,
   primary key (id_activity)
);

/*==============================================================*/
/* Table: Users                                                 */
/*==============================================================*/
create table Users
(
   userID               int not null,
   userLogin            varchar(30) not null,
   userName             varchar(100) not null,
   userPassword         varchar(10) not null,
   activityName         varchar(100),
   userRole             bool not null,
   primary key (userID)
);

alter table Activities add constraint FK_Relationship_1 foreign key (userID)
      references Users (userID) on delete restrict on update restrict;

