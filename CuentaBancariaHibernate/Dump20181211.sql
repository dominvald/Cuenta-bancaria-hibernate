CREATE DATABASE  IF NOT EXISTS `testcrudhibernate` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testcrudhibernate`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: testcrudhibernate
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cif` varchar(12) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido1` varchar(30) DEFAULT NULL,
  `apellido2` varchar(30) DEFAULT NULL,
  `id_direccion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkClienteDireccion_idx` (`id_direccion`),
  CONSTRAINT `fkClienteDireccion` FOREIGN KEY (`id_direccion`) REFERENCES `direcciones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=628 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (618,'77777777b','Lorena','Alonso ','Martín',268),(620,'0000000T','Ernesto','González','Pérez',270),(621,'E99151987','Gustavo','Villazala','Mateos',271),(622,'D62237730','Federico','García','Lorca',272),(623,'37091099B','Vanesa','Gutierrez','Campillo',273),(624,'H62193909','Javier','Domínguez','Domínguez',274),(625,'12132271R','Rodrigo','Gonzáles','Pérez',275),(626,'58420137E','Alberto','Prieto','Del Riego',276),(627,'80853584M','Juan','Villoria','Fernández',277);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direcciones`
--

DROP TABLE IF EXISTS `direcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direcciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'INSERT INTO direcciones VALUES (1, "Jim", "2012-01-12")',
  `direccion` varchar(50) NOT NULL,
  `cp` int(11) DEFAULT NULL,
  `provincia` varchar(30) DEFAULT NULL,
  `poblacion` varchar(30) DEFAULT NULL,
  `pais` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=278 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
INSERT INTO `direcciones` VALUES (268,'C/ La Alegría, 3',25555,'León','León','España'),(270,'C/ Pendón de Baeza',33333,'Salamanca','Quintana','España'),(271,'C/ Real, 8',24750,'León','León','España'),(272,'C/ La Encina',25869,'Granada','Fuente Vaqueros','España'),(273,'Plaza España,34',24750,'Salamanca','La Aldea','España'),(274,'C/ La Colada,23',27896,'Madrid','Vallecas','España'),(275,'Calle Arriba',45489,'Ourense','Oviña','España'),(276,'Calle de Prueba',11111,'Provincia Prueba','Población Prueba','País Prueba'),(277,'Calle Central,45',24750,'León','Valdesandinas','España');
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimientos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `importe` double NOT NULL,
  `saldo` double DEFAULT '0',
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  `cliente_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_movimientos_cliente1_idx` (`cliente_id`),
  CONSTRAINT `fk_movimientos_cliente1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=389 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (352,1.01,1.01,'2018-12-11 15:58:37',621),(353,3000,3001.01,'2018-12-11 15:58:58',621),(354,-1.25,2999.76,'2018-12-11 15:59:10',621),(355,-45,2954.76,'2018-12-11 15:59:21',621),(356,-234,2720.76,'2018-12-11 16:12:38',621),(357,-2720.36,0.4,'2018-12-11 16:13:29',621),(358,45,45,'2018-12-11 16:18:50',624),(359,32.25,77.25,'2018-12-11 16:19:09',624),(360,-20,57.25,'2018-12-11 16:19:20',624),(361,56.23,113.48,'2018-12-11 16:19:31',624),(362,-45.23,68.25,'2018-12-11 16:19:41',624),(363,23456,23524.25,'2018-12-11 16:19:54',624),(364,-23524.25,0,'2018-12-11 16:20:14',624),(365,1234,1234,'2018-12-11 16:20:31',624),(366,45,1279,'2018-12-11 16:20:52',624),(367,23.25,1302.25,'2018-12-11 16:21:00',624),(368,-45,1257.25,'2018-12-11 16:21:07',624),(369,56,1313.25,'2018-12-11 16:21:14',624),(370,48,1361.25,'2018-12-11 16:21:23',624),(371,-47.25,1314,'2018-12-11 16:21:39',624),(372,23,1337,'2018-12-11 16:21:50',624),(373,-234,1103,'2018-12-11 16:22:04',624),(374,345,1448,'2018-12-11 16:22:11',624),(375,34,1482,'2018-12-11 16:22:19',624),(376,98,1580,'2018-12-11 16:22:35',624),(379,-45,1535,'2018-12-11 16:35:48',624),(380,56,1591,'2018-12-11 16:44:38',624),(381,23,23,'2018-12-11 16:47:45',624),(382,34.45,34.45,'2018-12-11 16:47:57',624),(383,23,23,'2018-12-11 16:48:49',624),(384,89,112,'2018-12-11 16:49:48',624),(385,23,23,'2018-12-11 16:51:04',626),(386,23,46,'2018-12-11 16:52:50',626),(387,34.89,80.89,'2018-12-11 16:53:10',626),(388,-56.89,24,'2018-12-11 16:53:17',626);
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-11 17:40:37
