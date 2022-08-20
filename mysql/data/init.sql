/* 
SQLyog v3.64
Host - localhost : Database - autoecia
**************************************************************
Server version 4.1.18
*/

SET FOREIGN_KEY_CHECKS=0;

create database if not exists `autoecia`;

use `autoecia`;

/*
Table structure for cam_cambio
*/

drop table if exists `cam_cambio`;
CREATE TABLE `cam_cambio` (
  `cam_codigo` int(10) unsigned NOT NULL auto_increment,
  `cam_descricao` varchar(255) default NULL,
  PRIMARY KEY  (`cam_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
Table structure for car_carro
*/

drop table if exists `car_carro`;
CREATE TABLE `car_carro` (
  `car_codigo` int(10) unsigned NOT NULL auto_increment,
  `car_tipocarro` int(10) unsigned NOT NULL default '0',
  `car_cambio` int(10) unsigned NOT NULL default '0',
  `car_versao` int(10) unsigned NOT NULL default '0',
  `car_quilometragem` bigint(20) default NULL,
  `car_blindado` tinyint(3) unsigned default '0',
  `car_cor` varchar(255) default NULL,
  `car_placa` tinyint(3) unsigned default NULL,
  `car_deficiente` tinyint(3) unsigned NOT NULL default '0',
  `car_garantia` tinyint(3) unsigned NOT NULL default '0',
  `car_unico_dono` tinyint(3) unsigned NOT NULL default '0',
  `car_particular` tinyint(3) unsigned NOT NULL default '1',
  PRIMARY KEY  (`car_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
Table structure for cto_carro_tem_opcional
*/

drop table if exists `cto_carro_tem_opcional`;
CREATE TABLE `cto_carro_tem_opcional` (
  `cto_opcional` int(10) unsigned NOT NULL default '0',
  `cto_carro` int(10) unsigned NOT NULL default '0',
  `cto_importante` tinyint(3) unsigned NOT NULL default '1',
  PRIMARY KEY  (`cto_opcional`,`cto_carro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
Table structure for fun_funcao
*/

drop table if exists `fun_funcao`;
CREATE TABLE `fun_funcao` (
  `fun_codigo` int(10) unsigned NOT NULL default '0',
  `fun_descricao` varchar(255) NOT NULL default '',
  `fun_menu` char(1) NOT NULL default '',
  `fun_acao` varchar(255) NOT NULL default '',
  `fun_status` char(1) NOT NULL default '',
  `fun_criacao` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `fun_alteracao` timestamp NOT NULL default '0000-00-00 00:00:00',
  `fun_menurestrito` char(1) NOT NULL default '',
  `fun_ordem` int(10) unsigned NOT NULL default '1',
  `fun_parametro` varchar(255) NOT NULL default '',
  `fun_pathmenu` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`fun_codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*
Table structure for mar_marca
*/

drop table if exists `mar_marca`;
CREATE TABLE `mar_marca` (
  `mar_codigo` int(10) unsigned NOT NULL auto_increment,
  `mar_descricao` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`mar_codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*
Table structure for mod_modelo
*/

drop table if exists `mod_modelo`;
CREATE TABLE `mod_modelo` (
  `mod_codigo` int(10) unsigned NOT NULL auto_increment,
  `mod_marca` int(10) unsigned NOT NULL default '0',
  `mod_descricao` varchar(255) default NULL,
  PRIMARY KEY  (`mod_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
Table structure for opc_opcional
*/

drop table if exists `opc_opcional`;
CREATE TABLE `opc_opcional` (
  `opc_codigo` int(10) unsigned NOT NULL auto_increment,
  `opc_descricao` varchar(255) default NULL,
  PRIMARY KEY  (`opc_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
Table structure for per_perfil
*/

drop table if exists `per_perfil`;
CREATE TABLE `per_perfil` (
  `per_codigo` int(10) unsigned NOT NULL default '0',
  `per_descricao` varchar(30) NOT NULL default '',
  `per_status` char(1) NOT NULL default '',
  `per_criacao` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `per_alteracao` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`per_codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*
Table structure for ptf_perfil_tem_funcao
*/

drop table if exists `ptf_perfil_tem_funcao`;
CREATE TABLE `ptf_perfil_tem_funcao` (
  `ptf_perfil` int(10) unsigned NOT NULL default '0',
  `ptf_funcao` int(10) unsigned NOT NULL default '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*
Table structure for tic_tipocarro
*/

drop table if exists `tic_tipocarro`;
CREATE TABLE `tic_tipocarro` (
  `tic_codigo` int(10) unsigned NOT NULL default '0',
  `tic_descricao` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`tic_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
Table structure for usu_usuario
*/

drop table if exists `usu_usuario`;
CREATE TABLE `usu_usuario` (
  `usu_codigo` int(10) unsigned NOT NULL default '0',
  `usu_perfil` int(10) unsigned NOT NULL default '0',
  `usu_senha` varchar(255) NOT NULL default '',
  `usu_login` varchar(255) NOT NULL default '',
  `usu_nome` varchar(255) NOT NULL default '',
  `usu_status` char(1) NOT NULL default '',
  `usu_email` varchar(255) NOT NULL default '',
  `usu_ddd` char(3) NOT NULL default '',
  `usu_telefone` varchar(255) NOT NULL default '',
  `usu_ramal` varchar(255) NOT NULL default '',
  `usu_criacao` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `usu_alteracao` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`usu_codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*
Table structure for ver_versao
*/

drop table if exists `ver_versao`;
CREATE TABLE `ver_versao` (
  `ver_codigo` int(10) unsigned NOT NULL auto_increment,
  `ver_modelo` int(10) unsigned NOT NULL default '0',
  `ver_descricao` varchar(255) NOT NULL default '',
  `ver_ano` int(4) unsigned NOT NULL default '0',
  PRIMARY KEY  (`ver_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS=1;



/* 
SQLyog v3.64
Host - localhost : Database - autoecia
**************************************************************
Server version 4.1.18
*/

SET FOREIGN_KEY_CHECKS=0;

create database if not exists `autoecia`;

use `autoecia`;

/*
Table data for autoecia.fun_funcao
*/

INSERT INTO `fun_funcao` VALUES 
(1,'p�gina inicial','1','frame','1','2007-01-18 09:42:26','0000-00-00 00:00:00','0',1,'frame','imagens/pagina.gif'),
(2,'acess�rios e pe�as','1','frame','1','2007-01-18 09:43:49','0000-00-00 00:00:00','0',2,'acessorios','imagens/quemsomos.gif'),
(3,'vender ve�culo','1','frame','1','2007-01-18 09:46:13','0000-00-00 00:00:00','0',3,'vender',''),
(4,'comprar ve�culo','1','frame','1','2007-01-18 09:46:13','0000-00-00 00:00:00','0',4,'comprar',''),
(5,'leil�o reverso','1','frame','1','2007-01-18 09:54:28','0000-00-00 00:00:00','0',5,'reverso',''),
(6,'seguros','1','frame','1','2007-01-18 09:54:28','0000-00-00 00:00:00','0',6,'seguros',''),
(7,'tunning','1','frame','1','2007-01-18 09:56:36','0000-00-00 00:00:00','0',7,'tunning','');

/*
Table data for autoecia.per_perfil
*/

INSERT INTO `per_perfil` VALUES 
(1,'Administrador','1','2007-01-18 09:34:25','0000-00-00 00:00:00'),
(2,'Operador','1','2007-01-18 09:39:27','0000-00-00 00:00:00'),
(3,'Visitante','1','2007-01-18 10:07:48','0000-00-00 00:00:00');

/*
Table data for autoecia.ptf_perfil_tem_funcao
*/

INSERT INTO `ptf_perfil_tem_funcao` VALUES 
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10),
(1,11),
(1,12),
(1,13),
(2,1),
(2,2),
(2,3),
(2,4),
(2,5),
(2,6),
(2,7),
(2,8),
(2,9),
(2,10),
(2,11),
(2,12),
(2,13),
(3,1),
(3,2),
(3,3),
(3,4),
(3,5),
(3,6),
(3,7),
(3,8),
(3,9),
(3,10),
(3,11),
(3,12),
(3,13);

/*
Table data for autoecia.usu_usuario
*/

INSERT INTO `usu_usuario` VALUES 
(1,1,'admin','kalcarvalho','Kal Carvalho','1','','','','','2007-01-18 09:40:19','0000-00-00 00:00:00'),
(2,3,'123','visitante','Visitante','1','','','','','2007-01-18 15:18:25','0000-00-00 00:00:00');

SET FOREIGN_KEY_CHECKS=1;

