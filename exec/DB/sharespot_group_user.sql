CREATE DATABASE  IF NOT EXISTS `sharespot` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sharespot`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: i7a505.p.ssafy.io    Database: sharespot
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `group_user`
--

DROP TABLE IF EXISTS `group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_user` (
  `group_id` smallint NOT NULL,
  `user_id` smallint NOT NULL,
  `user_nick` varchar(45) DEFAULT NULL,
  `idx` int NOT NULL AUTO_INCREMENT,
  `user_image` text,
  PRIMARY KEY (`idx`),
  KEY `FK_group_table_TO_group_user_1` (`group_id`),
  KEY `FK_group_table_TO_group_user_2` (`user_id`),
  CONSTRAINT `FK_group_table_TO_group_user_1` FOREIGN KEY (`group_id`) REFERENCES `group_table` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_user`
--

LOCK TABLES `group_user` WRITE;
/*!40000 ALTER TABLE `group_user` DISABLE KEYS */;
INSERT INTO `group_user` VALUES (8,4,'하이',4,NULL),(14,16,'yeeeh',17,NULL),(15,15,'전싸피5',18,NULL),(14,3,'행복꿀벌',21,NULL),(8,3,'행복꿀벌',23,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/ddce527c-020b-4874-99b3-2b37f1694674_bee_happy.png'),(15,19,'밤새지않아',26,NULL),(14,19,'밤새지않아',27,NULL),(14,8,'찐현욱',29,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/14d7cdab-8060-484e-99b9-504fc4f7e503_프사용.jpg'),(8,27,'mobiletest',37,NULL),(15,3,'행복꿀벌',39,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/915f4841-ca76-46dd-8d9f-15b10b021596_EbXhhKuUcAUn-ZV.jpg'),(22,3,'행복꿀벌',41,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/915f4841-ca76-46dd-8d9f-15b10b021596_EbXhhKuUcAUn-ZV.jpg'),(8,3,'행복꿀벌',42,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/915f4841-ca76-46dd-8d9f-15b10b021596_EbXhhKuUcAUn-ZV.jpg'),(14,28,'햄볶음밥',47,NULL),(14,5,'맛집찾아삼만리',48,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/a5e44316-d207-40a3-9c18-0dc9fa56df60_20210824_132148.jpg'),(22,5,'맛집찾아삼만리',49,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/a5e44316-d207-40a3-9c18-0dc9fa56df60_20210824_132148.jpg'),(23,1,'유혀눅',55,NULL),(24,6,'파인애플피자',56,NULL),(22,6,'파인애플피자',57,NULL),(14,9,'태태웅',59,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/8c201692-c6a1-413b-80fa-96068f017663_e-mail.jpg'),(14,6,'파인애플피자',60,NULL);
/*!40000 ALTER TABLE `group_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 10:18:11
