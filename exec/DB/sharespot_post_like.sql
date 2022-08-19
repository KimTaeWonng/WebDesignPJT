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
-- Table structure for table `post_like`
--

DROP TABLE IF EXISTS `post_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_like` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `user_id` smallint NOT NULL,
  `user_nick` varchar(45) NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `FK_post_table_TO_post_like_1` (`post_id`),
  CONSTRAINT `FK_post_table_TO_post_like_1` FOREIGN KEY (`post_id`) REFERENCES `post_table` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_like`
--

LOCK TABLES `post_like` WRITE;
/*!40000 ALTER TABLE `post_like` DISABLE KEYS */;
INSERT INTO `post_like` VALUES (2,26,5,'{\"userNick\":\"맛집찾아삼만리\"}'),(3,39,15,'{\"userNick\":\"전싸피5\"}'),(4,19,16,'{\"userNick\":\"yeeeh\"}'),(5,20,8,'{\"userNick\":\"찐현욱\"}'),(6,20,16,'{\"userNick\":\"yeeeh\"}'),(7,18,16,'{\"userNick\":\"yeeeh\"}'),(8,17,16,'{\"userNick\":\"yeeeh\"}'),(9,16,16,'{\"userNick\":\"yeeeh\"}'),(10,15,16,'{\"userNick\":\"yeeeh\"}'),(11,14,16,'{\"userNick\":\"yeeeh\"}'),(12,13,16,'{\"userNick\":\"yeeeh\"}'),(13,12,16,'{\"userNick\":\"yeeeh\"}'),(14,11,16,'{\"userNick\":\"yeeeh\"}'),(15,36,16,'{\"userNick\":\"yeeeh\"}'),(16,35,16,'{\"userNick\":\"yeeeh\"}'),(17,34,16,'{\"userNick\":\"yeeeh\"}'),(18,33,16,'{\"userNick\":\"yeeeh\"}'),(19,32,16,'{\"userNick\":\"yeeeh\"}'),(22,20,3,'{\"userNick\":\"행복꿀벌\"}'),(24,18,8,'{\"userNick\":\"찐현욱\"}'),(25,17,3,'{\"userNick\":\"행복꿀벌\"}'),(26,15,20,'{\"userNick\":\"달과별\"}'),(31,70,27,'{\"userNick\":\"mobiletest\"}'),(32,34,3,'{\"userNick\":\"행복꿀벌\"}'),(33,84,9,'{\"userNick\":\"태태웅\"}'),(37,69,28,'{\"userNick\":\"햄볶음밥\"}'),(38,89,28,'{\"userNick\":\"햄볶음밥\"}'),(39,91,16,'{\"userNick\":\"yeeeh\"}'),(40,90,16,'{\"userNick\":\"yeeeh\"}'),(41,89,16,'{\"userNick\":\"yeeeh\"}'),(42,86,16,'{\"userNick\":\"yeeeh\"}'),(44,36,6,'{\"userNick\":\"파인애플피자\"}'),(45,86,6,'{\"userNick\":\"파인애플피자\"}'),(46,91,6,'{\"userNick\":\"파인애플피자\"}');
/*!40000 ALTER TABLE `post_like` ENABLE KEYS */;
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
