-- MySQL dump 10.13  Distrib 5.5.57, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ettl_jarvis
-- ------------------------------------------------------
-- Server version	5.5.57-0+deb8u1

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
-- Table structure for table `ettl_deposit_type`
--

DROP TABLE IF EXISTS `ettl_deposit_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_deposit_type` (
  `deposit_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`deposit_type_id`),
  UNIQUE KEY `UK_c2342woa9moyr1261dhykhn5w` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_deposit_type`
--

LOCK TABLES `ettl_deposit_type` WRITE;
/*!40000 ALTER TABLE `ettl_deposit_type` DISABLE KEYS */;
INSERT INTO `ettl_deposit_type` VALUES (1,'','CASH'),(2,'','BANK_DEPOSIT'),(3,'','CREDIT_CARD'),(4,'','DEBIT_CARD'),(5,'','BANK_TRANSFER');
/*!40000 ALTER TABLE `ettl_deposit_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_distribution_channel`
--

DROP TABLE IF EXISTS `ettl_distribution_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_distribution_channel` (
  `distribution_channel_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`distribution_channel_id`),
  UNIQUE KEY `UK_powlyubbxgysxgdsqhb2oexy9` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_distribution_channel`
--

LOCK TABLES `ettl_distribution_channel` WRITE;
/*!40000 ALTER TABLE `ettl_distribution_channel` DISABLE KEYS */;
INSERT INTO `ettl_distribution_channel` VALUES (1,'','RECEIPT'),(2,'','SMS');
/*!40000 ALTER TABLE `ettl_distribution_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_import_batch`
--

DROP TABLE IF EXISTS `ettl_import_batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_import_batch` (
  `import_batch_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) DEFAULT NULL,
  `import_date_time` datetime DEFAULT NULL,
  `number_of_vouchers` bigint(20) DEFAULT NULL,
  `voucher_provider_batch_id` varchar(255) DEFAULT NULL,
  `voucher_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`import_batch_id`),
  KEY `FKaflv1bnsselrb3r8yui9ry745` (`voucher_id`),
  CONSTRAINT `FKaflv1bnsselrb3r8yui9ry745` FOREIGN KEY (`voucher_id`) REFERENCES `ettl_voucher` (`voucher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_import_batch`
--

LOCK TABLES `ettl_import_batch` WRITE;
/*!40000 ALTER TABLE `ettl_import_batch` DISABLE KEYS */;
INSERT INTO `ettl_import_batch` VALUES (1,'VOUCHER_BATCH_20161118_10000.TXT','2017-09-09 11:03:41',45,'10000',1),(2,'Voms020715_173154.dec','2017-09-11 11:32:23',50,'173154',14);
/*!40000 ALTER TABLE `ettl_import_batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_incoming_payment`
--

DROP TABLE IF EXISTS `ettl_incoming_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_incoming_payment` (
  `incoming_payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bank_reference` varchar(255) DEFAULT NULL,
  `bank_statement_id` varchar(255) DEFAULT NULL,
  `depositor_reference` varchar(255) DEFAULT NULL,
  `payment_amount` decimal(19,2) DEFAULT NULL,
  `payment_time` datetime DEFAULT NULL,
  `time_loaded` datetime NOT NULL,
  `bank_id` bigint(20) DEFAULT NULL,
  `deposit_type_id` bigint(20) NOT NULL,
  `wallet_id` bigint(20) NOT NULL,
  PRIMARY KEY (`incoming_payment_id`),
  KEY `FKefuhydxuvffi37ewyfksc3bfw` (`bank_id`),
  KEY `FKagiy2d4v6p1ejotljk0uoqc71` (`deposit_type_id`),
  KEY `FKlbp9n3rc3lj3jjn4b3n4fl0qt` (`wallet_id`),
  CONSTRAINT `FKlbp9n3rc3lj3jjn4b3n4fl0qt` FOREIGN KEY (`wallet_id`) REFERENCES `symbiosis_wallet` (`wallet_id`),
  CONSTRAINT `FKagiy2d4v6p1ejotljk0uoqc71` FOREIGN KEY (`deposit_type_id`) REFERENCES `ettl_deposit_type` (`deposit_type_id`),
  CONSTRAINT `FKefuhydxuvffi37ewyfksc3bfw` FOREIGN KEY (`bank_id`) REFERENCES `symbiosis_bank` (`bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_incoming_payment`
--

LOCK TABLES `ettl_incoming_payment` WRITE;
/*!40000 ALTER TABLE `ettl_incoming_payment` DISABLE KEYS */;
INSERT INTO `ettl_incoming_payment` VALUES (1,'',NULL,'',2000.00,NULL,'2017-09-09 11:01:50',NULL,1,1),(2,'',NULL,'MM WALLET',100.00,NULL,'2017-09-11 11:26:21',NULL,1,2);
/*!40000 ALTER TABLE `ettl_incoming_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_pin_import_config`
--

DROP TABLE IF EXISTS `ettl_pin_import_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_pin_import_config` (
  `pin_import_config_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  `amount_in_contents` bit(1) NOT NULL,
  `amount_in_filename` bit(1) NOT NULL,
  `amount_line_num` int(11) DEFAULT NULL,
  `amount_pos` int(11) DEFAULT NULL,
  `amount_regex` varchar(255) DEFAULT NULL,
  `batch_id_in_contents` bit(1) NOT NULL,
  `batch_id_in_filename` bit(1) NOT NULL,
  `batch_id_line_num` int(11) DEFAULT NULL,
  `batch_id_pos` int(11) DEFAULT NULL,
  `batch_id_regex` varchar(255) DEFAULT NULL,
  `delimiter` varchar(255) NOT NULL,
  `divide_amount_by` int(11) NOT NULL,
  `expiry_date_format` varchar(255) DEFAULT NULL,
  `expiry_in_contents` bit(1) NOT NULL,
  `expiry_in_filename` bit(1) NOT NULL,
  `expiry_line_num` int(11) DEFAULT NULL,
  `expiry_pos` int(11) DEFAULT NULL,
  `expiry_regex` varchar(255) DEFAULT NULL,
  `filename_regex` varchar(255) DEFAULT NULL,
  `pgp_key_pass` varchar(255) DEFAULT NULL,
  `pgp_public_key_file` varchar(255) DEFAULT NULL,
  `pin_length` int(11) NOT NULL,
  `pin_pos` int(11) NOT NULL,
  `pin_start_line` int(11) NOT NULL,
  `serial_length` int(11) NOT NULL,
  `serial_pos` int(11) NOT NULL,
  `total_num_in_contents` bit(1) NOT NULL,
  `total_num_in_filename` bit(1) NOT NULL,
  `total_num_line_num` int(11) DEFAULT NULL,
  `total_num_pos` int(11) DEFAULT NULL,
  `total_num_regex` varchar(255) DEFAULT NULL,
  `service_provider_id` bigint(20) DEFAULT NULL,
  `voucher_provider_id` bigint(20) DEFAULT NULL,
  `voucher_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pin_import_config_id`),
  UNIQUE KEY `UK_gmjmaitl2sup6h0g8fyiyui73` (`name`),
  KEY `FKe74trnc1kjxdiq6stjj8snbio` (`service_provider_id`),
  KEY `FKavqxp2nlbbby9n9ltpdqbi2hf` (`voucher_provider_id`),
  KEY `FKf7awqvinew3q6n7r1qqcdrq8v` (`voucher_type_id`),
  CONSTRAINT `FKf7awqvinew3q6n7r1qqcdrq8v` FOREIGN KEY (`voucher_type_id`) REFERENCES `ettl_voucher_type` (`voucher_type_id`),
  CONSTRAINT `FKavqxp2nlbbby9n9ltpdqbi2hf` FOREIGN KEY (`voucher_provider_id`) REFERENCES `ettl_voucher_provider` (`voucher_provider_id`),
  CONSTRAINT `FKe74trnc1kjxdiq6stjj8snbio` FOREIGN KEY (`service_provider_id`) REFERENCES `ettl_service_provider` (`service_provider_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_pin_import_config`
--

LOCK TABLES `ettl_pin_import_config` WRITE;
/*!40000 ALTER TABLE `ettl_pin_import_config` DISABLE KEYS */;
INSERT INTO `ettl_pin_import_config` VALUES (1,'','AIRTEL_AIRTEL','','\0',2,3,'(\\d+),(\\d+),(\\d+),(.*)','\0','',NULL,2,'VOUCHER_BATCH_(\\d+)_(\\d+)',',',100,'yyyyMMdd','','\0',2,5,'(\\d+),(\\d+),(\\d+),(\\d+),(\\d+),(.*)','VOUCHER_BATCH_(\\d+)_(\\d+)',NULL,NULL,14,1,2,12,2,'','\0',1,1,'(\\d+),(\\d+),(.*)',1,1,1),(2,'','VODAFONE_VODAFONE','\0','',NULL,0,NULL,'\0','',NULL,2,'Voms(\\d+)_(\\d+).dec','##',100,NULL,'\0','\0',NULL,NULL,NULL,'Voms(\\d+)_(\\d+).dec',NULL,NULL,14,2,2,15,3,'','\0',1,1,'Total Vouchers=(\\d+),(.*)',4,4,1),(3,'','TIGO_TIGO','','\0',2,1,'FaceValue:(\\d+)','','\0',1,1,'Batch:(\\d+)',' ',10000,'yyyyMMdd','','\0',4,1,'StopDate:(\\d+)','(\\d+)K.txt',NULL,NULL,16,2,6,11,1,'\0','\0',NULL,NULL,NULL,5,5,1),(4,'','MTN_MTN','','\0',1,3,'(\\d+),(\\d+),(\\d+),(.*)','\0','',NULL,1,'(.*)_(.*)_(.*)_(.*)_(.*)',',',100,'yyyyMMdd','','\0',1,5,'(\\d+),(\\d+),(\\d+),(\\d+),(\\d+),(.*)','GHA(.*)','[ETTL*PGPk3y]','mtn_pgp_private.key',13,1,1,10,2,'\0','\0',NULL,NULL,NULL,3,3,1);
/*!40000 ALTER TABLE `ettl_pin_import_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_pinbased_voucher`
--

DROP TABLE IF EXISTS `ettl_pinbased_voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_pinbased_voucher` (
  `pinbased_voucher_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `distributed_date` datetime DEFAULT NULL,
  `pin_alias` varchar(255) DEFAULT NULL,
  `serial_number` varchar(255) DEFAULT NULL,
  `voucher_expiry_date` datetime DEFAULT NULL,
  `voucher_pin` varchar(255) DEFAULT NULL,
  `import_batch_id` bigint(20) DEFAULT NULL,
  `voucher_id` bigint(20) DEFAULT NULL,
  `voucher_status_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pinbased_voucher_id`),
  UNIQUE KEY `UK_860b74sirps51j7glg12n1a2t` (`serial_number`),
  KEY `FKeoggllxi91tmf1pli174wy7qu` (`import_batch_id`),
  KEY `FKtbstbwintknjwp97mfqteer4u` (`voucher_id`),
  KEY `FK6kg86y7667v9909gv5ckj4out` (`voucher_status_id`),
  CONSTRAINT `FK6kg86y7667v9909gv5ckj4out` FOREIGN KEY (`voucher_status_id`) REFERENCES `ettl_voucher_status` (`voucher_status_id`),
  CONSTRAINT `FKeoggllxi91tmf1pli174wy7qu` FOREIGN KEY (`import_batch_id`) REFERENCES `ettl_import_batch` (`import_batch_id`),
  CONSTRAINT `FKtbstbwintknjwp97mfqteer4u` FOREIGN KEY (`voucher_id`) REFERENCES `ettl_voucher` (`voucher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_pinbased_voucher`
--

LOCK TABLES `ettl_pinbased_voucher` WRITE;
/*!40000 ALTER TABLE `ettl_pinbased_voucher` DISABLE KEYS */;
INSERT INTO `ettl_pinbased_voucher` VALUES (1,'2017-09-11 11:06:37',NULL,'100017220119','2017-12-31 00:00:00','TFKDA6p9X8r5bYOzQEHWag==',1,1,2),(2,'2017-09-11 11:23:00',NULL,'100017220120','2017-12-31 00:00:00','G8jgG+dhGG+Su0ChTCWF6g==',1,1,2),(3,'2017-09-11 11:33:28',NULL,'100017220121','2017-12-31 00:00:00','BrBp0dAW8oK8oxcbqtJTzw==',1,1,2),(4,'2017-09-20 13:37:53',NULL,'100017220122','2017-12-31 00:00:00','P8bUNJDJTOZwgXzsoXZNdA==',1,1,2),(5,'2017-09-20 16:57:20',NULL,'100017220123','2017-12-31 00:00:00','he97Ch6lQrcZTUvR2jCLpg==',1,1,2),(6,'2017-09-20 17:46:06',NULL,'100017220219','2017-12-31 00:00:00','TFKDA6p9X8r5bYOzQEHWag==',1,1,2),(7,NULL,NULL,'100017220220','2017-12-31 00:00:00','G8jgG+dhGG+Su0ChTCWF6g==',1,1,1),(8,NULL,NULL,'100017220221','2017-12-31 00:00:00','BrBp0dAW8oK8oxcbqtJTzw==',1,1,1),(9,NULL,NULL,'100017220222','2017-12-31 00:00:00','P8bUNJDJTOZwgXzsoXZNdA==',1,1,1),(10,NULL,NULL,'100017220223','2017-12-31 00:00:00','he97Ch6lQrcZTUvR2jCLpg==',1,1,1),(11,NULL,NULL,'100017220319','2017-12-31 00:00:00','TFKDA6p9X8r5bYOzQEHWag==',1,1,1),(12,NULL,NULL,'100017220320','2017-12-31 00:00:00','G8jgG+dhGG+Su0ChTCWF6g==',1,1,1),(13,NULL,NULL,'100017220321','2017-12-31 00:00:00','BrBp0dAW8oK8oxcbqtJTzw==',1,1,1),(14,NULL,NULL,'100017220322','2017-12-31 00:00:00','P8bUNJDJTOZwgXzsoXZNdA==',1,1,1),(15,NULL,NULL,'100017220323','2017-12-31 00:00:00','he97Ch6lQrcZTUvR2jCLpg==',1,1,1),(16,NULL,NULL,'100017220419','2017-12-31 00:00:00','TFKDA6p9X8r5bYOzQEHWag==',1,1,1),(17,NULL,NULL,'100017220420','2017-12-31 00:00:00','G8jgG+dhGG+Su0ChTCWF6g==',1,1,1),(18,NULL,NULL,'100017220421','2017-12-31 00:00:00','BrBp0dAW8oK8oxcbqtJTzw==',1,1,1),(19,NULL,NULL,'100017220422','2017-12-31 00:00:00','P8bUNJDJTOZwgXzsoXZNdA==',1,1,1),(20,NULL,NULL,'100017220423','2017-12-31 00:00:00','he97Ch6lQrcZTUvR2jCLpg==',1,1,1),(21,NULL,NULL,'100017220519','2017-12-31 00:00:00','TFKDA6p9X8r5bYOzQEHWag==',1,1,1),(22,NULL,NULL,'100017220520','2017-12-31 00:00:00','G8jgG+dhGG+Su0ChTCWF6g==',1,1,1),(23,NULL,NULL,'100017220521','2017-12-31 00:00:00','BrBp0dAW8oK8oxcbqtJTzw==',1,1,1),(24,NULL,NULL,'100017220522','2017-12-31 00:00:00','P8bUNJDJTOZwgXzsoXZNdA==',1,1,1),(25,NULL,NULL,'100017220523','2017-12-31 00:00:00','he97Ch6lQrcZTUvR2jCLpg==',1,1,1),(26,NULL,NULL,'100017220619','2017-12-31 00:00:00','TFKDA6p9X8r5bYOzQEHWag==',1,1,1),(27,NULL,NULL,'100017220620','2017-12-31 00:00:00','G8jgG+dhGG+Su0ChTCWF6g==',1,1,1),(28,NULL,NULL,'100017220621','2017-12-31 00:00:00','BrBp0dAW8oK8oxcbqtJTzw==',1,1,1),(29,NULL,NULL,'100017220622','2017-12-31 00:00:00','P8bUNJDJTOZwgXzsoXZNdA==',1,1,1),(30,NULL,NULL,'100017220623','2017-12-31 00:00:00','he97Ch6lQrcZTUvR2jCLpg==',1,1,1),(31,NULL,NULL,'100017220719','2017-12-31 00:00:00','TFKDA6p9X8r5bYOzQEHWag==',1,1,1),(32,NULL,NULL,'100017220720','2017-12-31 00:00:00','G8jgG+dhGG+Su0ChTCWF6g==',1,1,1),(33,NULL,NULL,'100017220721','2017-12-31 00:00:00','BrBp0dAW8oK8oxcbqtJTzw==',1,1,1),(34,NULL,NULL,'100017220722','2017-12-31 00:00:00','P8bUNJDJTOZwgXzsoXZNdA==',1,1,1),(35,NULL,NULL,'100017220723','2017-12-31 00:00:00','he97Ch6lQrcZTUvR2jCLpg==',1,1,1),(36,NULL,NULL,'100017220819','2017-12-31 00:00:00','TFKDA6p9X8r5bYOzQEHWag==',1,1,1),(37,NULL,NULL,'100017220820','2017-12-31 00:00:00','G8jgG+dhGG+Su0ChTCWF6g==',1,1,1),(38,NULL,NULL,'100017220821','2017-12-31 00:00:00','BrBp0dAW8oK8oxcbqtJTzw==',1,1,1),(39,NULL,NULL,'100017220822','2017-12-31 00:00:00','P8bUNJDJTOZwgXzsoXZNdA==',1,1,1),(40,NULL,NULL,'100017220823','2017-12-31 00:00:00','he97Ch6lQrcZTUvR2jCLpg==',1,1,1),(41,NULL,NULL,'100017220919','2017-12-31 00:00:00','TFKDA6p9X8r5bYOzQEHWag==',1,1,1),(42,NULL,NULL,'100017220920','2017-12-31 00:00:00','G8jgG+dhGG+Su0ChTCWF6g==',1,1,1),(43,NULL,NULL,'100017220921','2017-12-31 00:00:00','BrBp0dAW8oK8oxcbqtJTzw==',1,1,1),(44,NULL,NULL,'100017220922','2017-12-31 00:00:00','P8bUNJDJTOZwgXzsoXZNdA==',1,1,1),(45,NULL,NULL,'100017220923','2017-12-31 00:00:00','he97Ch6lQrcZTUvR2jCLpg==',1,1,1),(46,'2017-09-20 11:06:17',NULL,'510200000000002','2018-09-11 11:32:23','Vjo3uAD56u+WLkNwt3Zwtg==',2,14,2),(47,'2017-09-20 13:36:33',NULL,'510200000000003','2018-09-11 11:32:23','o0WDawtxq4UiXabIJPbZzA==',2,14,2),(48,NULL,NULL,'510200000000004','2018-09-11 11:32:23','H0oHsse6MqM/k9O/ODPrbw==',2,14,1),(49,NULL,NULL,'510200000000005','2018-09-11 11:32:23','HYBxcS30wUHXIlLa9t721w==',2,14,1),(50,NULL,NULL,'510200000000006','2018-09-11 11:32:23','bTCY5ViSSjn40360767bfA==',2,14,1),(51,NULL,NULL,'510200000000007','2018-09-11 11:32:23','0SewhEJYzVpTXxzgiJFoYg==',2,14,1),(52,NULL,NULL,'510200000000008','2018-09-11 11:32:23','LOaVpBwbWba0N7QOREqv6g==',2,14,1),(53,NULL,NULL,'510200000000009','2018-09-11 11:32:23','DSBNiLv1MwvcuGAkz0/eJQ==',2,14,1),(54,NULL,NULL,'510200000000010','2018-09-11 11:32:23','qBjwjTqrKUqvTvleK23eUQ==',2,14,1),(55,NULL,NULL,'510200000000011','2018-09-11 11:32:23','YXI7O6RLSkSk4DnbrfA95g==',2,14,1),(56,NULL,NULL,'510200000000012','2018-09-11 11:32:23','AZu8yiuInkK1O6uTcsC7vw==',2,14,1),(57,NULL,NULL,'510200000000013','2018-09-11 11:32:23','l4JZZpP2Lf14wY2BoRJecw==',2,14,1),(58,NULL,NULL,'510200000000014','2018-09-11 11:32:23','XuHg4WCOQWA54Hp746hqZw==',2,14,1),(59,NULL,NULL,'510200000000015','2018-09-11 11:32:23','jCeurQW5hHhGsqccc9om6Q==',2,14,1),(60,NULL,NULL,'510200000000016','2018-09-11 11:32:23','4OerjIuYT6X2J8tjYWAeFw==',2,14,1),(61,NULL,NULL,'510200000000017','2018-09-11 11:32:23','muJa0Rg9bFQmvBmDUzvxfg==',2,14,1),(62,NULL,NULL,'510200000000018','2018-09-11 11:32:23','ySPZKjM9fP6hwOZCMJfGUQ==',2,14,1),(63,NULL,NULL,'510200000000019','2018-09-11 11:32:23','2DmCDxxfGj9FxtsKVyv9Fg==',2,14,1),(64,NULL,NULL,'510200000000020','2018-09-11 11:32:23','2eCE/4zESWbqiaA8RJfpBw==',2,14,1),(65,NULL,NULL,'510200000000021','2018-09-11 11:32:23','GQEN6Wd5ey1MBmsfNH2cxg==',2,14,1),(66,NULL,NULL,'510200000000022','2018-09-11 11:32:23','xw8BpUHM+KY8fWFWwFujNg==',2,14,1),(67,NULL,NULL,'510200000000023','2018-09-11 11:32:23','5IblFo9YWaiMCa6cj4ZFdQ==',2,14,1),(68,NULL,NULL,'510200000000024','2018-09-11 11:32:23','25NZckPraM4XaFSZ7bYlQg==',2,14,1),(69,NULL,NULL,'510200000000025','2018-09-11 11:32:23','H3uTRYe6xJ1fIpfojk7Mrg==',2,14,1),(70,NULL,NULL,'510200000000026','2018-09-11 11:32:23','QfjkH9Nk0E7hin81YnMF/Q==',2,14,1),(71,NULL,NULL,'510200000000027','2018-09-11 11:32:23','T5G5cnuncCqNMNzV8NLsvQ==',2,14,1),(72,NULL,NULL,'510200000000028','2018-09-11 11:32:23','SEYz5Z7QZtXzPcVi/QliJg==',2,14,1),(73,NULL,NULL,'510200000000029','2018-09-11 11:32:23','zYsGXCX2MhenAZTSdCxyqA==',2,14,1),(74,NULL,NULL,'510200000000030','2018-09-11 11:32:23','27fv+OeTo5K8Q+94FppY+g==',2,14,1),(75,NULL,NULL,'510200000000031','2018-09-11 11:32:23','RdpegrLVH0tWeNU3X8j/OA==',2,14,1),(76,NULL,NULL,'510200000000032','2018-09-11 11:32:23','4CI3VY8jXFIwREIbMQSgdg==',2,14,1),(77,NULL,NULL,'510200000000033','2018-09-11 11:32:23','Yvo06BUveJFmFSQXZ2391g==',2,14,1),(78,NULL,NULL,'510200000000034','2018-09-11 11:32:23','oZtTBxtXBuyCw7M2O1PgPA==',2,14,1),(79,NULL,NULL,'510200000000035','2018-09-11 11:32:23','B0nVpqquRz4PPg/CzEGzgA==',2,14,1),(80,NULL,NULL,'510200000000036','2018-09-11 11:32:23','FDYU9li4OwoXMWVyzTno1A==',2,14,1),(81,NULL,NULL,'510200000000037','2018-09-11 11:32:23','91C36V1KKNGB8cQ/iZTCCA==',2,14,1),(82,NULL,NULL,'510200000000038','2018-09-11 11:32:23','rcJEJSf5LFq7qkHjKogdOA==',2,14,1),(83,NULL,NULL,'510200000000039','2018-09-11 11:32:23','7NMfB6KP6HNY3saP+TWpSw==',2,14,1),(84,NULL,NULL,'510200000000040','2018-09-11 11:32:23','K1nfptj+uSkiqCtvVkigdQ==',2,14,1),(85,NULL,NULL,'510200000000041','2018-09-11 11:32:23','q0PnQgzr6UXxVzqitlzK4w==',2,14,1),(86,NULL,NULL,'510200000000042','2018-09-11 11:32:23','SySkn67UcWq/GYRIqV72HA==',2,14,1),(87,NULL,NULL,'510200000000043','2018-09-11 11:32:23','fRa0jeu3HTSZBay8VPy/1g==',2,14,1),(88,NULL,NULL,'510200000000044','2018-09-11 11:32:23','d1+vj4DD9F+GalMvyf40QQ==',2,14,1),(89,NULL,NULL,'510200000000045','2018-09-11 11:32:23','fNXKI4hZfSg/1sBPfj4pFw==',2,14,1),(90,NULL,NULL,'510200000000046','2018-09-11 11:32:23','B1D3CPVq6nGWsth66z9QUg==',2,14,1),(91,NULL,NULL,'510200000000047','2018-09-11 11:32:23','lPxdrPW9NXZxFgBWKQ3X2Q==',2,14,1),(92,NULL,NULL,'510200000000048','2018-09-11 11:32:23','nZAq62MzI5MQB4/wL2ASSw==',2,14,1),(93,NULL,NULL,'510200000000049','2018-09-11 11:32:23','U9iuyx406ID6tlXjlbiAkA==',2,14,1),(94,NULL,NULL,'510200000000050','2018-09-11 11:32:23','+dIzQ7ZORQKE4nP5VZBVFg==',2,14,1),(95,NULL,NULL,'510200000000051','2018-09-11 11:32:23','sQJq1Ts+zRI9Gjq0y6gXzg==',2,14,1);
/*!40000 ALTER TABLE `ettl_pinbased_voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_pos_machine`
--

DROP TABLE IF EXISTS `ettl_pos_machine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_pos_machine` (
  `pos_machine_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_name` varchar(255) NOT NULL,
  `imei1` varchar(16) NOT NULL,
  `imei2` varchar(16) NOT NULL,
  `imsi1` varchar(16) DEFAULT NULL,
  `imsi2` varchar(16) DEFAULT NULL,
  `machine_name` varchar(255) NOT NULL,
  `msisdn1` varchar(12) DEFAULT NULL,
  `msisdn2` varchar(12) DEFAULT NULL,
  `auth_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pos_machine_id`),
  UNIQUE KEY `UK_1jrgb5m13dr2xvyr6po6jgwnp` (`imei1`),
  UNIQUE KEY `UK_erk6267jrgu947ir26ulqi9s7` (`imei2`),
  KEY `FKsphonmj8ywm2doamm84j3p52b` (`auth_user_id`),
  CONSTRAINT `FKsphonmj8ywm2doamm84j3p52b` FOREIGN KEY (`auth_user_id`) REFERENCES `symbiosis_auth_user` (`auth_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_pos_machine`
--

LOCK TABLES `ettl_pos_machine` WRITE;
/*!40000 ALTER TABLE `ettl_pos_machine` DISABLE KEYS */;
INSERT INTO `ettl_pos_machine` VALUES (1,'home','359128041055936','359128046055931','9648041533615379','9648045518450382','demo',NULL,NULL,2),(2,'empower','359128041083821','359128046083826','9620010100331449','','fiademo',NULL,NULL,4);
/*!40000 ALTER TABLE `ettl_pos_machine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_service_provider`
--

DROP TABLE IF EXISTS `ettl_service_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_service_provider` (
  `service_provider_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `service_provider_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`service_provider_id`),
  UNIQUE KEY `UK_csx18megge8qn4bryo7xw8h67` (`service_provider_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_service_provider`
--

LOCK TABLES `ettl_service_provider` WRITE;
/*!40000 ALTER TABLE `ettl_service_provider` DISABLE KEYS */;
INSERT INTO `ettl_service_provider` VALUES (1,'','Airtel'),(2,'\0','Glo'),(3,'','MTN'),(4,'','Vodafone'),(5,'','Tigo'),(6,'\0','Multichoice'),(7,'','WAEC');
/*!40000 ALTER TABLE `ettl_service_provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_voucher`
--

DROP TABLE IF EXISTS `ettl_voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_voucher` (
  `voucher_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) NOT NULL,
  `is_fixed` bit(1) NOT NULL,
  `is_pin_based` bit(1) NOT NULL,
  `units` varchar(255) DEFAULT NULL,
  `voucher_provider_discount` double NOT NULL,
  `voucher_value` decimal(19,2) DEFAULT NULL,
  `service_provider_id` bigint(20) NOT NULL,
  `voucher_provider_id` bigint(20) NOT NULL,
  `voucher_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`voucher_id`),
  KEY `FKm22bee8c3ye8kxh635nxl6a1r` (`service_provider_id`),
  KEY `FKcb6bvm0hy6su7bamohueptcf6` (`voucher_provider_id`),
  KEY `FKdcy4bc6drdehrprko9sb5qsrn` (`voucher_type_id`),
  CONSTRAINT `FKdcy4bc6drdehrprko9sb5qsrn` FOREIGN KEY (`voucher_type_id`) REFERENCES `ettl_voucher_type` (`voucher_type_id`),
  CONSTRAINT `FKcb6bvm0hy6su7bamohueptcf6` FOREIGN KEY (`voucher_provider_id`) REFERENCES `ettl_voucher_provider` (`voucher_provider_id`),
  CONSTRAINT `FKm22bee8c3ye8kxh635nxl6a1r` FOREIGN KEY (`service_provider_id`) REFERENCES `ettl_service_provider` (`service_provider_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_voucher`
--

LOCK TABLES `ettl_voucher` WRITE;
/*!40000 ALTER TABLE `ettl_voucher` DISABLE KEYS */;
INSERT INTO `ettl_voucher` VALUES (1,'','','','GHS',8,1.00,1,1,1),(2,'','','','GHS',8,2.00,1,1,1),(3,'','','','GHS',8,5.00,1,1,1),(4,'','','','GHS',8,10.00,1,1,1),(5,'','','','GHS',8,20.00,1,1,1),(6,'','','','GHS',6.5,1.00,3,3,1),(7,'','','','GHS',6.5,2.00,3,3,1),(8,'','','','GHS',6.5,5.00,3,3,1),(9,'','','','GHS',6.5,10.00,3,3,1),(10,'','','','GHS',6.5,20.00,3,3,1),(11,'','','','GHS',10,1.00,4,4,1),(12,'','','','GHS',10,2.00,4,4,1),(13,'','','','GHS',10,5.00,4,4,1),(14,'','','','GHS',10,10.00,4,4,1),(15,'','','','GHS',10,20.00,4,4,1),(16,'','','','GHS',8,1.00,5,5,1),(17,'','','','GHS',8,2.00,5,5,1),(18,'','','','GHS',8,5.00,5,5,1),(19,'','','','GHS',8,10.00,5,5,1),(20,'','','','GHS',10,7.50,7,7,4);
/*!40000 ALTER TABLE `ettl_voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_voucher_provider`
--

DROP TABLE IF EXISTS `ettl_voucher_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_voucher_provider` (
  `voucher_provider_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `voucher_provider_name` varchar(255) DEFAULT NULL,
  `current_balance` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`voucher_provider_id`),
  UNIQUE KEY `UK_228fe6q3vepitke42kvngqxks` (`voucher_provider_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_voucher_provider`
--

LOCK TABLES `ettl_voucher_provider` WRITE;
/*!40000 ALTER TABLE `ettl_voucher_provider` DISABLE KEYS */;
INSERT INTO `ettl_voucher_provider` VALUES (1,'','Airtel',NULL),(2,'\0','Glo',NULL),(3,'','MTN',NULL),(4,'','Vodafone',NULL),(5,'','Tigo',NULL),(6,'\0','CowryPay',NULL),(7,'','WAEC',NULL);
/*!40000 ALTER TABLE `ettl_voucher_provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_voucher_purchase`
--

DROP TABLE IF EXISTS `ettl_voucher_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_voucher_purchase` (
  `voucher_purchase_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cashier_name` varchar(255) DEFAULT NULL,
  `is_transaction_reconciled` bit(1) DEFAULT NULL,
  `recipient` varchar(255) DEFAULT NULL,
  `transaction_time` datetime DEFAULT NULL,
  `voucher_provider_value` decimal(19,2) DEFAULT NULL,
  `voucher_value` decimal(19,2) DEFAULT NULL,
  `wallet_cost` decimal(19,2) DEFAULT NULL,
  `distribution_channel_id` bigint(20) NOT NULL,
  `pinbased_voucher_id` bigint(20) DEFAULT NULL,
  `response_code_id` bigint(20) NOT NULL,
  `auth_user_id` bigint(20) NOT NULL,
  `symbiosis_user_id` bigint(20) NOT NULL,
  `voucher_id` bigint(20) NOT NULL,
  `wallet_id` bigint(20) NOT NULL,
  PRIMARY KEY (`voucher_purchase_id`),
  KEY `FKmoj1apivx0xmlgohti3vc67lb` (`distribution_channel_id`),
  KEY `FKt8hxq86l0jecvbrwk2wuex6s3` (`pinbased_voucher_id`),
  KEY `FKpth9u23cf7mmyb8tgplxgtmlt` (`response_code_id`),
  KEY `FK3hiuwxe6uk5v2ydxiabnx6mgm` (`auth_user_id`),
  KEY `FK29mmykbsxcr3dw3iye1ujh3aq` (`symbiosis_user_id`),
  KEY `FKs5il4ptf8vrg02bdki4iptp31` (`voucher_id`),
  KEY `FKfvbnwjgovhku1ahlnj2mo3wxg` (`wallet_id`),
  CONSTRAINT `FKfvbnwjgovhku1ahlnj2mo3wxg` FOREIGN KEY (`wallet_id`) REFERENCES `symbiosis_wallet` (`wallet_id`),
  CONSTRAINT `FK29mmykbsxcr3dw3iye1ujh3aq` FOREIGN KEY (`symbiosis_user_id`) REFERENCES `symbiosis_user` (`symbiosis_user_id`),
  CONSTRAINT `FK3hiuwxe6uk5v2ydxiabnx6mgm` FOREIGN KEY (`auth_user_id`) REFERENCES `symbiosis_auth_user` (`auth_user_id`),
  CONSTRAINT `FKmoj1apivx0xmlgohti3vc67lb` FOREIGN KEY (`distribution_channel_id`) REFERENCES `ettl_distribution_channel` (`distribution_channel_id`),
  CONSTRAINT `FKpth9u23cf7mmyb8tgplxgtmlt` FOREIGN KEY (`response_code_id`) REFERENCES `symbiosis_response_code` (`id`),
  CONSTRAINT `FKs5il4ptf8vrg02bdki4iptp31` FOREIGN KEY (`voucher_id`) REFERENCES `ettl_voucher` (`voucher_id`),
  CONSTRAINT `FKt8hxq86l0jecvbrwk2wuex6s3` FOREIGN KEY (`pinbased_voucher_id`) REFERENCES `ettl_pinbased_voucher` (`pinbased_voucher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_voucher_purchase`
--

LOCK TABLES `ettl_voucher_purchase` WRITE;
/*!40000 ALTER TABLE `ettl_voucher_purchase` DISABLE KEYS */;
INSERT INTO `ettl_voucher_purchase` VALUES (1,'tsungai','\0',NULL,'2017-09-11 11:06:37',0.92,1.00,0.96,1,1,0,2,1,1,1),(2,'tsungai','\0',NULL,'2017-09-11 11:23:00',0.92,1.00,0.96,1,2,0,2,1,1,1),(3,'fiademo','\0',NULL,'2017-09-11 11:31:34',9.00,10.00,9.65,1,NULL,60,4,2,14,2),(4,'fiademo','\0',NULL,'2017-09-11 11:33:28',0.92,1.00,0.96,1,3,0,4,2,1,2),(5,'fiademo','\0',NULL,'2017-09-20 11:06:16',9.00,10.00,9.65,1,46,0,4,2,14,2),(6,'tsungai','\0',NULL,'2017-09-20 13:36:26',0.90,1.00,0.96,1,NULL,60,2,1,11,1),(7,'tsungai','\0',NULL,'2017-09-20 13:36:33',9.00,10.00,9.65,1,47,0,2,1,14,1),(8,'tsungai','\0',NULL,'2017-09-20 13:37:52',0.92,1.00,0.96,1,4,0,2,1,1,1),(9,'tsungai','\0',NULL,'2017-09-20 16:57:20',0.92,1.00,0.96,1,5,0,2,1,1,1),(10,'tsungai','\0',NULL,'2017-09-20 17:46:06',0.92,1.00,0.96,1,6,0,2,1,1,1);
/*!40000 ALTER TABLE `ettl_voucher_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_voucher_status`
--

DROP TABLE IF EXISTS `ettl_voucher_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_voucher_status` (
  `voucher_status_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`voucher_status_id`),
  UNIQUE KEY `UK_hp5gaabclb0nqv85ece093l50` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_voucher_status`
--

LOCK TABLES `ettl_voucher_status` WRITE;
/*!40000 ALTER TABLE `ettl_voucher_status` DISABLE KEYS */;
INSERT INTO `ettl_voucher_status` VALUES (1,'','NEW'),(2,'','SOLD'),(3,'','EXPIRED');
/*!40000 ALTER TABLE `ettl_voucher_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_voucher_type`
--

DROP TABLE IF EXISTS `ettl_voucher_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_voucher_type` (
  `voucher_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`voucher_type_id`),
  UNIQUE KEY `UK_keh2aydu093jgkbymqflylht7` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_voucher_type`
--

LOCK TABLES `ettl_voucher_type` WRITE;
/*!40000 ALTER TABLE `ettl_voucher_type` DISABLE KEYS */;
INSERT INTO `ettl_voucher_type` VALUES (1,'','AIRTIME'),(2,'\0','INTERNET'),(3,'\0','DSTV'),(4,'\0','RESULT CHECKER PIN');
/*!40000 ALTER TABLE `ettl_voucher_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ettl_wallet_group_voucher`
--

DROP TABLE IF EXISTS `ettl_wallet_group_voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ettl_wallet_group_voucher` (
  `wallet_group_voucher_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `wallet_discount` double DEFAULT NULL,
  `voucher_id` bigint(20) DEFAULT NULL,
  `wallet_group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`wallet_group_voucher_id`),
  KEY `FK7yjmoreac62wdfu1rvdpplk47` (`voucher_id`),
  KEY `FKhb9mkk59tjjg6dhpfh6epv1ve` (`wallet_group_id`),
  CONSTRAINT `FKhb9mkk59tjjg6dhpfh6epv1ve` FOREIGN KEY (`wallet_group_id`) REFERENCES `symbiosis_wallet_group` (`wallet_group_id`),
  CONSTRAINT `FK7yjmoreac62wdfu1rvdpplk47` FOREIGN KEY (`voucher_id`) REFERENCES `ettl_voucher` (`voucher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ettl_wallet_group_voucher`
--

LOCK TABLES `ettl_wallet_group_voucher` WRITE;
/*!40000 ALTER TABLE `ettl_wallet_group_voucher` DISABLE KEYS */;
INSERT INTO `ettl_wallet_group_voucher` VALUES (1,3.5,1,1),(2,3.5,2,1),(3,3.5,3,1),(4,3.5,4,1),(5,3.5,5,1),(6,3.5,6,1),(7,3.5,7,1),(8,3.5,8,1),(9,3.5,9,1),(10,3.5,10,1),(11,3.5,11,1),(12,3.5,12,1),(13,3.5,13,1),(14,3.5,14,1),(15,3.5,15,1),(16,3.5,16,1),(17,3.5,17,1),(18,3.5,18,1),(19,3.5,19,1),(20,3.5,20,1),(32,4,1,2),(33,4,2,2),(34,4,3,2),(35,4,4,2),(36,4,5,2),(37,4,6,2),(38,4,7,2),(39,4,8,2),(40,4,9,2),(41,4,10,2),(42,4,11,2),(43,4,12,2),(44,4,13,2),(45,4,14,2),(46,4,15,2),(47,4,16,2),(48,4,17,2),(49,4,18,2),(50,4,19,2),(51,4,20,2),(63,5,1,3),(64,5,2,3),(65,5,3,3),(66,5,4,3),(67,5,5,3),(68,5,6,3),(69,5,7,3),(70,5,8,3),(71,5,9,3),(72,5,10,3),(73,5,11,3),(74,5,12,3),(75,5,13,3),(76,5,14,3),(77,5,15,3),(78,5,16,3),(79,5,17,3),(80,5,18,3),(81,5,19,3),(82,5,20,3);
/*!40000 ALTER TABLE `ettl_wallet_group_voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_auth_group`
--

DROP TABLE IF EXISTS `symbiosis_auth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_auth_group` (
  `auth_group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`auth_group_id`),
  UNIQUE KEY `UK_hgv13ftpbik5wkyqu7ehyt6n` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_auth_group`
--

LOCK TABLES `symbiosis_auth_group` WRITE;
/*!40000 ALTER TABLE `symbiosis_auth_group` DISABLE KEYS */;
INSERT INTO `symbiosis_auth_group` VALUES (1,'','SUPER_USER'),(2,'','WEB_ADMIN'),(3,'','WEB_CLERK'),(4,'','WEB_AGENT'),(5,'','POS_AGENT'),(6,'','POS_ADMIN');
/*!40000 ALTER TABLE `symbiosis_auth_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_auth_group_role`
--

DROP TABLE IF EXISTS `symbiosis_auth_group_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_auth_group_role` (
  `auth_group_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  `auth_group_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`auth_group_role_id`),
  UNIQUE KEY `UK_huhccujuxvo4sh7wgk4fo3bip` (`name`),
  KEY `FKbeh8gdun5t8gygs7qws5ftsfu` (`auth_group_id`),
  KEY `FKffrra5lb7bwqh0ypvymf57gis` (`role_id`),
  CONSTRAINT `FKffrra5lb7bwqh0ypvymf57gis` FOREIGN KEY (`role_id`) REFERENCES `symbiosis_role` (`role_id`),
  CONSTRAINT `FKbeh8gdun5t8gygs7qws5ftsfu` FOREIGN KEY (`auth_group_id`) REFERENCES `symbiosis_auth_group` (`auth_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_auth_group_role`
--

LOCK TABLES `symbiosis_auth_group_role` WRITE;
/*!40000 ALTER TABLE `symbiosis_auth_group_role` DISABLE KEYS */;
INSERT INTO `symbiosis_auth_group_role` VALUES (1,'','SUPER_USER_ROLE_POS_MANAGE_SETTINGS',1,17),(2,'','SUPER_USER_ROLE_WEB_ADVANCED_MANAGE_USERS',1,6),(3,'','SUPER_USER_ROLE_WEB_MANAGE_EVD',1,1),(4,'','SUPER_USER_ROLE_WEB_MANAGE_PROVIDERS',1,3),(5,'','SUPER_USER_ROLE_WEB_MANAGE_SETTINGS',1,16),(6,'','SUPER_USER_ROLE_WEB_MANAGE_STOCK',1,2),(7,'','SUPER_USER_ROLE_WEB_MANAGE_USERS',1,5),(8,'','SUPER_USER_ROLE_WEB_MANAGE_VOUCHERS',1,4),(9,'','SUPER_USER_ROLE_WEB_VIEW_AUTHENTICATION_REPORTS',1,9),(10,'','SUPER_USER_ROLE_WEB_VIEW_PAYMENT_REPORTS',1,10),(11,'','SUPER_USER_ROLE_WEB_VIEW_REPORTS',1,7),(12,'','SUPER_USER_ROLE_WEB_VIEW_SINGLE_AUTHENTICATION_REPORTS',1,13),(13,'','SUPER_USER_ROLE_WEB_VIEW_SINGLE_PAYMENT_REPORTS',1,14),(14,'','SUPER_USER_ROLE_WEB_VIEW_SINGLE_REPORTS',1,12),(15,'','SUPER_USER_ROLE_WEB_VIEW_SINGLE_TRANSACTION_REPORTS',1,15),(16,'','SUPER_USER_ROLE_WEB_VIEW_SYSTEM_REPORTS',1,8),(17,'','SUPER_USER_ROLE_WEB_VIEW_TRANSACTION_REPORTS',1,11),(32,'','WEB_ADMIN_ROLE_WEB_ADVANCED_MANAGE_USERS',2,6),(33,'','WEB_ADMIN_ROLE_WEB_MANAGE_EVD',2,1),(34,'','WEB_ADMIN_ROLE_WEB_MANAGE_PROVIDERS',2,3),(35,'','WEB_ADMIN_ROLE_WEB_MANAGE_SETTINGS',2,16),(36,'','WEB_ADMIN_ROLE_WEB_MANAGE_STOCK',2,2),(37,'','WEB_ADMIN_ROLE_WEB_MANAGE_USERS',2,5),(38,'','WEB_ADMIN_ROLE_WEB_MANAGE_VOUCHERS',2,4),(39,'','WEB_ADMIN_ROLE_WEB_VIEW_AUTHENTICATION_REPORTS',2,9),(40,'','WEB_ADMIN_ROLE_WEB_VIEW_PAYMENT_REPORTS',2,10),(41,'','WEB_ADMIN_ROLE_WEB_VIEW_REPORTS',2,7),(42,'','WEB_ADMIN_ROLE_WEB_VIEW_SINGLE_AUTHENTICATION_REPORTS',2,13),(43,'','WEB_ADMIN_ROLE_WEB_VIEW_SINGLE_PAYMENT_REPORTS',2,14),(44,'','WEB_ADMIN_ROLE_WEB_VIEW_SINGLE_REPORTS',2,12),(45,'','WEB_ADMIN_ROLE_WEB_VIEW_SINGLE_TRANSACTION_REPORTS',2,15),(46,'','WEB_ADMIN_ROLE_WEB_VIEW_SYSTEM_REPORTS',2,8),(47,'','WEB_ADMIN_ROLE_WEB_VIEW_TRANSACTION_REPORTS',2,11),(63,'','WEB_CLERK_ROLE_WEB_MANAGE_EVD',3,1),(64,'','WEB_CLERK_ROLE_WEB_MANAGE_SETTINGS',3,16),(65,'','WEB_CLERK_ROLE_WEB_MANAGE_STOCK',3,2),(66,'','WEB_CLERK_ROLE_WEB_MANAGE_USERS',3,5),(67,'','WEB_CLERK_ROLE_WEB_VIEW_AUTHENTICATION_REPORTS',3,9),(68,'','WEB_CLERK_ROLE_WEB_VIEW_PAYMENT_REPORTS',3,10),(69,'','WEB_CLERK_ROLE_WEB_VIEW_REPORTS',3,7),(70,'','WEB_CLERK_ROLE_WEB_VIEW_SINGLE_AUTHENTICATION_REPORTS',3,13),(71,'','WEB_CLERK_ROLE_WEB_VIEW_SINGLE_PAYMENT_REPORTS',3,14),(72,'','WEB_CLERK_ROLE_WEB_VIEW_SINGLE_REPORTS',3,12),(73,'','WEB_CLERK_ROLE_WEB_VIEW_SINGLE_TRANSACTION_REPORTS',3,15),(74,'','WEB_CLERK_ROLE_WEB_VIEW_SYSTEM_REPORTS',3,8),(75,'','WEB_CLERK_ROLE_WEB_VIEW_TRANSACTION_REPORTS',3,11),(78,'','WEB_AGENT_ROLE_WEB_MANAGE_SETTINGS',4,16),(79,'','WEB_AGENT_ROLE_WEB_VIEW_SINGLE_AUTHENTICATION_REPORTS',4,13),(80,'','WEB_AGENT_ROLE_WEB_VIEW_SINGLE_PAYMENT_REPORTS',4,14),(81,'','WEB_AGENT_ROLE_WEB_VIEW_SINGLE_REPORTS',4,12),(82,'','WEB_AGENT_ROLE_WEB_VIEW_SINGLE_TRANSACTION_REPORTS',4,15),(85,'','POS_ADMIN_ROLE_POS_MANAGE_SETTINGS',6,17);
/*!40000 ALTER TABLE `symbiosis_auth_group_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_auth_user`
--

DROP TABLE IF EXISTS `symbiosis_auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_auth_user` (
  `auth_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `auth_token` varchar(255) DEFAULT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  `last_auth_date` datetime DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `registration_date` datetime NOT NULL,
  `auth_group_id` bigint(20) NOT NULL,
  `channel_id` bigint(20) NOT NULL,
  `symbiosis_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`auth_user_id`),
  KEY `FKtluugi5lq0tyfmcntoa7fchoa` (`auth_group_id`),
  KEY `FKri7qo4j97bna2yyidfcy3j5kc` (`channel_id`),
  KEY `FKla1btij7x3do3p48gviuuo5sr` (`symbiosis_user_id`),
  CONSTRAINT `FKla1btij7x3do3p48gviuuo5sr` FOREIGN KEY (`symbiosis_user_id`) REFERENCES `symbiosis_user` (`symbiosis_user_id`),
  CONSTRAINT `FKri7qo4j97bna2yyidfcy3j5kc` FOREIGN KEY (`channel_id`) REFERENCES `symbiosis_channel` (`channel_id`),
  CONSTRAINT `FKtluugi5lq0tyfmcntoa7fchoa` FOREIGN KEY (`auth_group_id`) REFERENCES `symbiosis_auth_group` (`auth_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_auth_user`
--

LOCK TABLES `symbiosis_auth_user` WRITE;
/*!40000 ALTER TABLE `symbiosis_auth_user` DISABLE KEYS */;
INSERT INTO `symbiosis_auth_user` VALUES (1,NULL,NULL,'2017-09-20 22:32:58','2017-09-20 22:32:58','2017-09-08 22:23:59',1,2,1),(2,NULL,NULL,'2017-09-20 17:46:06','2017-09-20 13:34:51','2017-09-09 11:01:32',5,3,1),(3,'P7hjQ5aoJraDKeK7',NULL,'2017-09-20 11:50:48','2017-09-20 11:50:48','2017-09-11 11:20:22',2,2,2),(4,NULL,NULL,'2017-09-20 11:06:16',NULL,'2017-09-11 11:26:50',5,3,2);
/*!40000 ALTER TABLE `symbiosis_auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_bank`
--

DROP TABLE IF EXISTS `symbiosis_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_bank` (
  `bank_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `bank_name` varchar(255) DEFAULT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `contact_fax` varchar(255) DEFAULT NULL,
  `contact_phone` varchar(255) DEFAULT NULL,
  `physical_address` varchar(255) DEFAULT NULL,
  `swift_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bank_id`),
  UNIQUE KEY `UK_mlho9qmo5aq7yxp8mxae7tnks` (`bank_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_bank`
--

LOCK TABLES `symbiosis_bank` WRITE;
/*!40000 ALTER TABLE `symbiosis_bank` DISABLE KEYS */;
/*!40000 ALTER TABLE `symbiosis_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_channel`
--

DROP TABLE IF EXISTS `symbiosis_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_channel` (
  `channel_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  `is_pin_based` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`channel_id`),
  UNIQUE KEY `UK_fv8mvxo8egkcqxasxcrunfs7b` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_channel`
--

LOCK TABLES `symbiosis_channel` WRITE;
/*!40000 ALTER TABLE `symbiosis_channel` DISABLE KEYS */;
INSERT INTO `symbiosis_channel` VALUES (1,'','DESKTOP','\0'),(2,'','WEB','\0'),(3,'','POS_MACHINE',''),(4,'','POS_TILL',''),(5,'','USSD',''),(6,'','ANDROID',''),(7,'','IOS',''),(8,'','WIN_MOBILE','');
/*!40000 ALTER TABLE `symbiosis_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_company`
--

DROP TABLE IF EXISTS `symbiosis_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_company` (
  `company_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_city` varchar(255) DEFAULT NULL,
  `address_line_1` varchar(255) DEFAULT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `phone1` varchar(255) DEFAULT NULL,
  `phone2` varchar(255) DEFAULT NULL,
  `registration_number` varchar(255) DEFAULT NULL,
  `vat_number` varchar(255) DEFAULT NULL,
  `address_country_id` bigint(20) NOT NULL,
  PRIMARY KEY (`company_id`),
  UNIQUE KEY `UK_no7ovojgvy8p6qk29i0bw2ljg` (`company_name`),
  UNIQUE KEY `UK_lae52jm4qxokvdqye4iaysscn` (`registration_number`),
  UNIQUE KEY `UK_q6xhhpchs19bf9g5ucqmbskoe` (`vat_number`),
  KEY `FKcsj7x7jjds480uqfbnb3thbhn` (`address_country_id`),
  CONSTRAINT `FKcsj7x7jjds480uqfbnb3thbhn` FOREIGN KEY (`address_country_id`) REFERENCES `symbiosis_country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_company`
--

LOCK TABLES `symbiosis_company` WRITE;
/*!40000 ALTER TABLE `symbiosis_company` DISABLE KEYS */;
INSERT INTO `symbiosis_company` VALUES (1,'Harare','4 Janeen Close','Groombridge, Mt. Pleasant','T3raTech','263785107830','27627938765',NULL,NULL,2),(2,'Accra','87, Spintex Road','Accra','Empower','','','','',1);
/*!40000 ALTER TABLE `symbiosis_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_country`
--

DROP TABLE IF EXISTS `symbiosis_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  `dialing_code` varchar(6) NOT NULL,
  `iso_code_2` varchar(2) NOT NULL,
  `iso_code_3` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_60ep9qsf9uym3emjv55lvrnsa` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_country`
--

LOCK TABLES `symbiosis_country` WRITE;
/*!40000 ALTER TABLE `symbiosis_country` DISABLE KEYS */;
INSERT INTO `symbiosis_country` VALUES (1,'','GHANA','233','GH','GHA'),(2,'\0','ZIMBABWE','263','ZW','ZWE'),(3,'\0','SOUTH AFRICA','27','ZA','ZAF');
/*!40000 ALTER TABLE `symbiosis_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_event_type`
--

DROP TABLE IF EXISTS `symbiosis_event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_event_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bt0916d41joc3fc2h6of7socg` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_event_type`
--

LOCK TABLES `symbiosis_event_type` WRITE;
/*!40000 ALTER TABLE `symbiosis_event_type` DISABLE KEYS */;
INSERT INTO `symbiosis_event_type` VALUES (1,'','REGISTRATION'),(2,'','LOGIN'),(3,'','VOUCHER_IMPORT'),(4,'','LOAD_WALLET'),(5,'','UPDATE_MERCHANT'),(6,'','CREATE_VOUCHER'),(7,'','UPDATE_VOUCHER'),(8,'','CREATE_VOUCHER_TYPE'),(9,'','UPDATE_VOUCHER_TYPE'),(10,'','CREATE_SERVICE_PROVIDER'),(11,'','UPDATE_SERVICE_PROVIDER'),(12,'','CREATE_VOUCHER_PROVIDER'),(13,'','UPDATE_VOUCHER_PROVIDER'),(14,'','CREATE_WALLET_GROUP'),(15,'','UPDATE_WALLET_GROUP'),(16,'','UPDATE_WALLET_GROUP_VOUCHER'),(17,'','UPDATE_USER'),(18,'','CREATE_USER'),(19,'','UPDATE_PASSWORD'),(20,'','RESET_PASSWORD'),(21,'','RESET_PIN');
/*!40000 ALTER TABLE `symbiosis_event_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_language`
--

DROP TABLE IF EXISTS `symbiosis_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_language` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ebv47cvu7xc5qp6bqbkxir0a6` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_language`
--

LOCK TABLES `symbiosis_language` WRITE;
/*!40000 ALTER TABLE `symbiosis_language` DISABLE KEYS */;
INSERT INTO `symbiosis_language` VALUES (1,'','ENGLISH');
/*!40000 ALTER TABLE `symbiosis_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_preference`
--

DROP TABLE IF EXISTS `symbiosis_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_preference` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ll5ypwf40j7h4sls07ry7ldcj` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_preference`
--

LOCK TABLES `symbiosis_preference` WRITE;
/*!40000 ALTER TABLE `symbiosis_preference` DISABLE KEYS */;
INSERT INTO `symbiosis_preference` VALUES (1,'','PF_WEB_THEME');
/*!40000 ALTER TABLE `symbiosis_preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_request_response_log`
--

DROP TABLE IF EXISTS `symbiosis_request_response_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_request_response_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `incoming_request` longtext NOT NULL,
  `incoming_request_time` datetime NOT NULL,
  `outgoing_response` longtext,
  `outgoing_response_time` datetime DEFAULT NULL,
  `auth_user_id` bigint(20) DEFAULT NULL,
  `channel_id` bigint(20) NOT NULL,
  `event_type_id` bigint(20) NOT NULL,
  `response_code_id` bigint(20) DEFAULT NULL,
  `system_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKex0che03h501tv775kao6nr3o` (`auth_user_id`),
  KEY `FK3l7j2c6bb2gglt5k87c5p4ad7` (`channel_id`),
  KEY `FKs451iakcqmbs9vncqlrictxks` (`event_type_id`),
  KEY `FKb495yuug2ry4dtau2str9fqqw` (`response_code_id`),
  KEY `FKbvhcx76nan19sougoq5bcjvo` (`system_user_id`),
  CONSTRAINT `FKbvhcx76nan19sougoq5bcjvo` FOREIGN KEY (`system_user_id`) REFERENCES `symbiosis_user` (`symbiosis_user_id`),
  CONSTRAINT `FK3l7j2c6bb2gglt5k87c5p4ad7` FOREIGN KEY (`channel_id`) REFERENCES `symbiosis_channel` (`channel_id`),
  CONSTRAINT `FKb495yuug2ry4dtau2str9fqqw` FOREIGN KEY (`response_code_id`) REFERENCES `symbiosis_response_code` (`id`),
  CONSTRAINT `FKex0che03h501tv775kao6nr3o` FOREIGN KEY (`auth_user_id`) REFERENCES `symbiosis_auth_user` (`auth_user_id`),
  CONSTRAINT `FKs451iakcqmbs9vncqlrictxks` FOREIGN KEY (`event_type_id`) REFERENCES `symbiosis_event_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `symbiosis_response_code`
--

DROP TABLE IF EXISTS `symbiosis_response_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_response_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  `response_message` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gn84q3wi45k9fyv7wvc60kpmw` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=356 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_response_code`
--

LOCK TABLES `symbiosis_response_code` WRITE;
/*!40000 ALTER TABLE `symbiosis_response_code` DISABLE KEYS */;
INSERT INTO `symbiosis_response_code` VALUES (-1,'','CONFIGURATION_INVALID','Specified configuration is not valid'),(0,'','SUCCESS','Successful'),(1,'','GENERAL_ERROR','A general error occurred'),(2,'','INPUT_INCOMPLETE_REQUEST','Incomplete request specified'),(3,'','INPUT_INVALID_REQUEST','Invalid request specified'),(4,'','INPUT_INVALID_EMAIL','Email provided was not valid'),(5,'','INPUT_INVALID_MSISDN','Phone number provided was not valid'),(6,'','INPUT_INVALID_FIRST_NAME','First name provided was not valid'),(7,'','INPUT_INVALID_LAST_NAME','Last name provided was not valid'),(8,'','INPUT_INVALID_USERNAME','Username provided was not valid'),(9,'','INPUT_INVALID_PASSWORD','Password provided was not valid'),(10,'','INPUT_INVALID_NAME','Name provided was not valid'),(11,'','INPUT_INVALID_AMOUNT','Invalid amount specified'),(12,'','INPUT_INVALID_WALLET','Invalid wallet specified'),(13,'','INPUT_INVALID_CASHIER','Invalid cashier name specified'),(15,'','DATA_NOT_FOUND','Data does not exist'),(16,'','NOT_SUPPORTED','Not supported'),(20,'','AUTH_INSUFFICIENT_PRIVILEGES','Insufficient privileges for current operation'),(21,'','AUTH_AUTHENTICATION_FAILED','Authentication failed'),(22,'','AUTH_INCORRECT_PASSWORD','Password is incorrect'),(23,'','AUTH_NON_EXISTENT','Account does not exist'),(30,'','ACC_ACTIVE','Account is active'),(31,'','ACC_INACTIVE','Account is inactive'),(32,'','ACC_SUSPENDED','Account has been suspended'),(33,'','ACC_CLOSED','Account has been closed'),(34,'','ACC_PASSWORD_TRIES_EXCEEDED','Password tries exceeded'),(35,'','ACC_PASSWORD_EXPIRED','Password expired'),(40,'','CONNECTION_FAILED','Connection failed'),(41,'','UNKNOWN_HOST','Unknown host'),(42,'','CONNECTION_REFUSED','Connection Refused'),(43,'','TIMEOUT','Timeout elapsed before transaction completion'),(51,'','INSUFFICIENT_FUNDS','Insufficient funds'),(60,'','INSUFFICIENT_STOCK','Insufficient stock'),(80,'','EXISTING_DATA_FOUND','Existing data found'),(351,'','REGISTRATION_FAILED','Registration Failed'),(352,'','PREVIOUS_MSISDN_FOUND','Mobile number has been previously registered'),(353,'','PREVIOUS_EMAIL_FOUND','Email has been previously registered'),(354,'','PREVIOUS_REGISTRATION_FOUND','Previous registration found');
/*!40000 ALTER TABLE `symbiosis_response_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_role`
--

DROP TABLE IF EXISTS `symbiosis_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_aunvk4v2fauxbago3ck3kk76d` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_role`
--

LOCK TABLES `symbiosis_role` WRITE;
/*!40000 ALTER TABLE `symbiosis_role` DISABLE KEYS */;
INSERT INTO `symbiosis_role` VALUES (1,'','ROLE_WEB_MANAGE_EVD'),(2,'','ROLE_WEB_MANAGE_STOCK'),(3,'','ROLE_WEB_MANAGE_PROVIDERS'),(4,'','ROLE_WEB_MANAGE_VOUCHERS'),(5,'','ROLE_WEB_MANAGE_USERS'),(6,'','ROLE_WEB_ADVANCED_MANAGE_USERS'),(7,'','ROLE_WEB_VIEW_REPORTS'),(8,'','ROLE_WEB_VIEW_SYSTEM_REPORTS'),(9,'','ROLE_WEB_VIEW_AUTHENTICATION_REPORTS'),(10,'','ROLE_WEB_VIEW_PAYMENT_REPORTS'),(11,'','ROLE_WEB_VIEW_TRANSACTION_REPORTS'),(12,'','ROLE_WEB_VIEW_SINGLE_REPORTS'),(13,'','ROLE_WEB_VIEW_SINGLE_AUTHENTICATION_REPORTS'),(14,'','ROLE_WEB_VIEW_SINGLE_PAYMENT_REPORTS'),(15,'','ROLE_WEB_VIEW_SINGLE_TRANSACTION_REPORTS'),(16,'','ROLE_WEB_MANAGE_SETTINGS'),(17,'','ROLE_POS_MANAGE_SETTINGS');
/*!40000 ALTER TABLE `symbiosis_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_session`
--

DROP TABLE IF EXISTS `symbiosis_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `auth_token` varchar(255) NOT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `auth_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnsehg02xxc2dlhsfphodskqdn` (`auth_user_id`),
  CONSTRAINT `FKnsehg02xxc2dlhsfphodskqdn` FOREIGN KEY (`auth_user_id`) REFERENCES `symbiosis_auth_user` (`auth_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_session`
--

LOCK TABLES `symbiosis_session` WRITE;
/*!40000 ALTER TABLE `symbiosis_session` DISABLE KEYS */;
INSERT INTO `symbiosis_session` VALUES (1,'K3c1Z4rbUPZj2ALJ','Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-09 11:00:41',1),(2,'8hgAZzIyDjT0n2aG','Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-11 11:04:31',1),(3,'ps307zqYoBdAr2u4','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36','2017-09-11 11:24:09','2017-09-11 11:23:22',3),(4,'6E5V1bX2daoVOte2','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-11 11:25:28',3),(5,'275v2a6uT15Mw3PB','Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-11 11:25:29',1),(6,'AfMGgi2Vc52Zfh37','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-11 11:45:13',3),(7,'Q4zFLbldw46Fi39y','Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-11 11:46:19',1),(8,'Aipjr0W2zO55pyh2','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36','2017-09-11 12:14:24','2017-09-11 12:11:19',3),(9,'y6eA9T99j3660J9S','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-11 12:14:33',3),(10,'iazK52uM5LYy4LSp','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-12 09:27:34',3),(11,'oIj1c31Qv662keNL','Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-13 09:18:23',1),(12,'HNqe3sjXJBVqJyRw','Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-19 11:25:18',1),(13,'kBpChJzfK0WhtQSW','Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-20 01:30:49',1),(14,'08Hl8XTOoI7b1ud1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-20 10:25:59',3),(15,'6fcep9M2BB0A0p4u','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-20 11:07:19',3),(16,'UP370jbsR768IjLq','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36','2017-09-20 11:53:49','2017-09-20 11:50:48',3),(17,'E1Xjn66bHtRL12R2','Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-20 13:31:29',1),(18,'2SrLK3ccuRSXSD8K','Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36',NULL,'2017-09-20 22:32:58',1);
/*!40000 ALTER TABLE `symbiosis_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_user`
--

DROP TABLE IF EXISTS `symbiosis_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_user` (
  `symbiosis_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_birth` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `msisdn` varchar(12) DEFAULT NULL,
  `msisdn2` varchar(12) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `password_tries` int(11) NOT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `pin_tries` int(11) NOT NULL,
  `salt` varchar(128) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `country_id` bigint(20) NOT NULL,
  `language_id` bigint(20) NOT NULL,
  `user_status_id` bigint(20) NOT NULL,
  `wallet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`symbiosis_user_id`),
  UNIQUE KEY `UK_ggjh5wvu67b7v3h5bcj50sd5b` (`email`),
  UNIQUE KEY `UK_nb2svovqgswkxpk2afsf0y1k7` (`msisdn`),
  UNIQUE KEY `UK_j5i0uysbmyj5wwlml52hgx0v1` (`username`),
  KEY `FK3midvh4uot7bxxcgnyo0oahg7` (`country_id`),
  KEY `FK37ves4fds0w37pw0o1t5fwmyv` (`language_id`),
  KEY `FKmtx08spp2aufvwbnecve6asjr` (`user_status_id`),
  KEY `FKoc33xwof3b0xihqnvn1f0h7d7` (`wallet_id`),
  CONSTRAINT `FKoc33xwof3b0xihqnvn1f0h7d7` FOREIGN KEY (`wallet_id`) REFERENCES `symbiosis_wallet` (`wallet_id`),
  CONSTRAINT `FK37ves4fds0w37pw0o1t5fwmyv` FOREIGN KEY (`language_id`) REFERENCES `symbiosis_language` (`id`),
  CONSTRAINT `FK3midvh4uot7bxxcgnyo0oahg7` FOREIGN KEY (`country_id`) REFERENCES `symbiosis_country` (`id`),
  CONSTRAINT `FKmtx08spp2aufvwbnecve6asjr` FOREIGN KEY (`user_status_id`) REFERENCES `symbiosis_response_code` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_user`
--

LOCK TABLES `symbiosis_user` WRITE;
/*!40000 ALTER TABLE `symbiosis_user` DISABLE KEYS */;
INSERT INTO `symbiosis_user` VALUES (1,NULL,'tsungai.kaviya@gmail.com','Tsungai','Kaviya','263785107830','27627938765','659f6d313bb6fb10ae238ed2ecd4f3365a6a72b8ba8fbe891265a17a6a7335',0,'5bb5646bcf48e9f6511ab8c563306b46163758e11b8488ca8a4dde931eecd',0,'b4ou790Xz4jBfY0B','admin',2,1,30,1),(2,NULL,'fiademo.dogble@gmail.com','Fiademo','Dogble','233242279182','','30a02bd8b05e8ca2fc9c961caf38fd4123e43c990724ea5de5be745b1a86f9',0,'fd29c9fe88faa9517ac1b6297daec2eac89e986ad4636dcb383f89d581b141',0,'P7hjQ5aoJraDKeK7','demo',1,1,30,2);
/*!40000 ALTER TABLE `symbiosis_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_user_preference`
--

DROP TABLE IF EXISTS `symbiosis_user_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_user_preference` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `preference_value` varchar(255) DEFAULT NULL,
  `user_preference_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ihr5k67o84kl2d9i2ns1euk3` (`user_preference_id`),
  KEY `FK6mqaya22lkeu0u636e3u8xp1k` (`user_id`),
  CONSTRAINT `FK6mqaya22lkeu0u636e3u8xp1k` FOREIGN KEY (`user_id`) REFERENCES `symbiosis_user` (`symbiosis_user_id`),
  CONSTRAINT `FK1ihr5k67o84kl2d9i2ns1euk3` FOREIGN KEY (`user_preference_id`) REFERENCES `symbiosis_preference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_user_preference`
--

LOCK TABLES `symbiosis_user_preference` WRITE;
/*!40000 ALTER TABLE `symbiosis_user_preference` DISABLE KEYS */;
/*!40000 ALTER TABLE `symbiosis_user_preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_wallet`
--

DROP TABLE IF EXISTS `symbiosis_wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_wallet` (
  `wallet_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `current_balance` decimal(19,2) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `wallet_admin_user_id` bigint(20) NOT NULL,
  `wallet_group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`wallet_id`),
  UNIQUE KEY `UK_mhvcb8oxmqj2rdgv09hc54odg` (`company_id`),
  KEY `FK8vnjftvr3bp96ktds4j2al96d` (`wallet_admin_user_id`),
  KEY `FK9oq8c4my7q7hpg6kbuwydj8s2` (`wallet_group_id`),
  CONSTRAINT `FK9oq8c4my7q7hpg6kbuwydj8s2` FOREIGN KEY (`wallet_group_id`) REFERENCES `symbiosis_wallet_group` (`wallet_group_id`),
  CONSTRAINT `FK8vnjftvr3bp96ktds4j2al96d` FOREIGN KEY (`wallet_admin_user_id`) REFERENCES `symbiosis_user` (`symbiosis_user_id`),
  CONSTRAINT `FKpy763guae45ejbrht9a1gptd` FOREIGN KEY (`company_id`) REFERENCES `symbiosis_company` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_wallet`
--

LOCK TABLES `symbiosis_wallet` WRITE;
/*!40000 ALTER TABLE `symbiosis_wallet` DISABLE KEYS */;
INSERT INTO `symbiosis_wallet` VALUES (1,1985.55,1,1,1),(2,89.39,2,2,1);
/*!40000 ALTER TABLE `symbiosis_wallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbiosis_wallet_group`
--

DROP TABLE IF EXISTS `symbiosis_wallet_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbiosis_wallet_group` (
  `wallet_group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  `default_discount` double DEFAULT NULL,
  PRIMARY KEY (`wallet_group_id`),
  UNIQUE KEY `UK_5hnu1ie1lescwsacdgp1pl8cf` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbiosis_wallet_group`
--

LOCK TABLES `symbiosis_wallet_group` WRITE;
/*!40000 ALTER TABLE `symbiosis_wallet_group` DISABLE KEYS */;
INSERT INTO `symbiosis_wallet_group` VALUES (1,'','DEFAULT_MERCHANT_GROUP',3.5),(2,'','MEDIUM_VOLUME_MERCHANT_GROUP',4),(3,'','HIGH_VOLUME_MERCHANT_GROUP',5);
/*!40000 ALTER TABLE `symbiosis_wallet_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-20 23:26:29
