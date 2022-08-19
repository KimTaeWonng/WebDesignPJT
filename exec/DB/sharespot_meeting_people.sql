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
-- Table structure for table `meeting_people`
--

DROP TABLE IF EXISTS `meeting_people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meeting_people` (
  `meeting_id` smallint NOT NULL,
  `user_id` smallint NOT NULL,
  `user_nick` varchar(30) DEFAULT NULL,
  `idx` int NOT NULL AUTO_INCREMENT,
  `user_image` text,
  PRIMARY KEY (`idx`),
  KEY `FK_meeting_TO_meeting_people_1` (`meeting_id`),
  CONSTRAINT `FK_meeting_TO_meeting_people_1` FOREIGN KEY (`meeting_id`) REFERENCES `meeting` (`meeting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meeting_people`
--

LOCK TABLES `meeting_people` WRITE;
/*!40000 ALTER TABLE `meeting_people` DISABLE KEYS */;
INSERT INTO `meeting_people` VALUES (21,27,'mobiletest',48,NULL),(21,3,'행복꿀벌',49,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/915f4841-ca76-46dd-8d9f-15b10b021596_EbXhhKuUcAUn-ZV.jpg'),(22,28,'햄볶음밥',50,NULL),(23,5,'맛집찾아삼만리',51,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/a5e44316-d207-40a3-9c18-0dc9fa56df60_20210824_132148.jpg');
/*!40000 ALTER TABLE `meeting_people` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 10:18:10
