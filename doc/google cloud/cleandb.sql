TRUNCATE TABLE ettl_voucher_provider_payment;
TRUNCATE TABLE ettl_incoming_payment;
DELETE FROM ettl_voucher_purchase;
DELETE FROM ettl_pinbased_voucher;
DELETE FROM ettl_import_batch;
TRUNCATE TABLE ettl_pos_machine;
TRUNCATE TABLE symbiosis_session;
TRUNCATE TABLE symbiosis_request_response_log;

TRUNCATE TABLE symbiosis_company;
INSERT INTO `symbiosis_company` VALUES (1,'Harare','4 Janeen Close','Groombridge, Mt. Pleasant','T3raTech','263785107830','27627938765',NULL,NULL,2),(2,'Accra','87 Spintex road','Accra','T3raTech','','','','',1);
INSERT INTO `symbiosis_company` VALUES (2,'Accra','87 Spintex road','Accra','T3raTech','','','','',1);

TRUNCATE TABLE symbiosis_wallet;
INSERT INTO `symbiosis_wallet` VALUES (1,0.00,1,1,1),(2,0.00,2,2,1);
INSERT INTO `symbiosis_wallet` VALUES (2,0.00,2,2,1);

TRUNCATE TABLE symbiosis_user;
INSERT INTO `symbiosis_user` VALUES (1,NULL,'tsungai.kaviya@gmail.com','Tsungai','Kaviya','233785107830','27627938765','343da6a61efa907a7042ad8594e815893ef4c63fa2d2ae01e6385136f81d2ea',0,'457e5bd629b068ac1c726786364465a403cde26cc10e0be68a487379f71',0,'b4ou790Xz4jBfY0B','admin',2,1,30,1),(2,NULL,'fiademo.dogble@gmail.com','Fiademo','Dogble','233242279182','','5736424348b747f110b95d33519a656d96e86bc67dfa4cd7af4c927ee3eba',0,NULL,0,'1sH0J40A6bPE7Gg9','demo',1,1,30,2);

INSERT INTO `symbiosis_user` VALUES (2,NULL,'fiademo.dogble@gmail.com','Fiademo','Dogble','233242279182','','5736424348b747f110b95d33519a656d96e86bc67dfa4cd7af4c927ee3eba',0,NULL,0,'1sH0J40A6bPE7Gg9','demo',1,1,30,2);


TRUNCATE TABLE symbiosis_auth_user;
INSERT INTO `symbiosis_auth_user` VALUES (1,NULL,NULL,'2018-07-21 23:59:30','2018-07-21 23:59:30','2017-09-21 13:21:10',1,2,1),(2,NULL,NULL,'2017-11-11 19:32:24',NULL,'2017-09-21 11:35:51',5,3,1),(3,'1sH0J40A6bPE7Gg9',NULL,'2018-07-25 17:15:00','2018-07-25 17:15:00','2017-09-25 23:56:47',2,2,2);
INSERT INTO `symbiosis_auth_user` VALUES (2,NULL,NULL,'2017-11-11 19:32:24',NULL,'2017-09-21 11:35:51',5,3,1),(3,'1sH0J40A6bPE7Gg9',NULL,'2018-07-25 17:15:00','2018-07-25 17:15:00','2017-09-25 23:56:47',2,2,2);

TRUNCATE TABLE ettl_wallet_group_voucher;
INSERT INTO `ettl_wallet_group_voucher` VALUES (6,3.5,6,1),(7,3.5,7,1),(8,3.5,8,1),(9,3.5,9,1),(10,3.5,10,1),(11,5,11,1),(12,5,12,1),(13,5,13,1),(14,5,14,1),(15,5,15,1),(16,3.5,16,1),(17,3.5,17,1),(18,3.5,18,1),(19,3.5,19,1),(20,3.5,20,1),(37,4,6,2),(38,4,7,2),(39,4,8,2),(40,4,9,2),(41,4,10,2),(42,4,11,2),(43,4,12,2),(44,4,13,2),(45,4,14,2),(46,4,15,2),(47,4,16,2),(48,4,17,2),(49,4,18,2),(50,4,19,2),(51,4,20,2),(68,5,6,3),(69,5,7,3),(70,5,8,3),(71,5,9,3),(72,5,10,3),(73,5,11,3),(74,5,12,3),(75,5,13,3),(76,5,14,3),(77,5,15,3),(78,5,16,3),(79,5,17,3),(80,5,18,3),(81,5,19,3),(82,5,20,3),(83,3.5,21,1),(84,4,21,2),(85,5,21,3);

TRUNCATE TABLE `ettl_voucher_type`;
INSERT INTO `ettl_voucher_type` VALUES (1,'','AIRTIME'),(2,'\0','INTERNET'),(3,'\0','DSTV');

TRUNCATE TABLE `ettl_voucher_provider`;
INSERT INTO `ettl_voucher_provider` VALUES (1,'\0','Airtel',NULL),(2,'\0','Glo',NULL),(3,'','MTN',NULL),(4,'','Vodafone',NULL),(5,'','AirtelTigo',NULL),(6,'\0','CowryPay',NULL),(7,'\0','WAEC',NULL);

TRUNCATE TABLE ettl_voucher;
INSERT INTO `ettl_voucher` VALUES (6,'','','','GHS',6.5,1.00,3,3,1),(7,'','','','GHS',6.5,2.00,3,3,1),(8,'','','','GHS',6.5,5.00,3,3,1),(9,'','','','GHS',6.5,10.00,3,3,1),(10,'','','','GHS',6.5,20.00,3,3,1),(11,'','','','GHS',10,1.00,4,4,1),(12,'','','','GHS',10,2.00,4,4,1),(13,'','','','GHS',10,5.00,4,4,1),(14,'','','','GHS',10,10.00,4,4,1),(15,'','','','GHS',10,20.00,4,4,1),(16,'','','','GHS',7,1.00,5,5,1),(17,'','','','GHS',7,2.00,5,5,1),(18,'','','','GHS',7,5.00,5,5,1),(19,'','','','GHS',7,10.00,5,5,1),(20,'','','','GHS',10,7.50,7,7,4),(21,'','','','GHS',7,20.00,5,5,1);

TRUNCATE TABLE `ettl_service_provider`;
INSERT INTO `ettl_service_provider` VALUES (1,'\0','Airtel'),(2,'\0','Glo'),(3,'','MTN'),(4,'','Vodafone'),(5,'','AirtelTigo'),(6,'\0','Multichoice'),(7,'\0','WAEC');

