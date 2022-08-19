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
-- Table structure for table `badge`
--

DROP TABLE IF EXISTS `badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `badge` (
  `user_id` smallint NOT NULL,
  `badge_food` tinyint NOT NULL,
  `badge_cafe` tinyint NOT NULL,
  `badge_culture` tinyint NOT NULL,
  `badge_trip` tinyint NOT NULL,
  `badge_life` tinyint NOT NULL,
  `badge_group` tinyint NOT NULL,
  `badge_feed` tinyint NOT NULL,
  `badge_comment` tinyint NOT NULL,
  `badge_meet` tinyint NOT NULL,
  `badge_follow` tinyint NOT NULL,
  `badge_follower` tinyint NOT NULL,
  `main_food` tinyint NOT NULL,
  `main_cafe` tinyint NOT NULL,
  `main_culture` tinyint NOT NULL,
  `main_trip` tinyint NOT NULL,
  `main_life` tinyint NOT NULL,
  `main_group` tinyint NOT NULL,
  `main_feed` tinyint NOT NULL,
  `main_comment` tinyint NOT NULL,
  `main_meet` tinyint NOT NULL,
  `main_follow` tinyint NOT NULL,
  `main_follower` tinyint NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK_user_table_TO_badge_1` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badge`
--

LOCK TABLES `badge` WRITE;
/*!40000 ALTER TABLE `badge` DISABLE KEYS */;
INSERT INTO `badge` VALUES (1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(3,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,1,2,0,0,0),(5,1,1,0,0,0,0,1,1,0,1,1,0,2,0,0,0,0,0,0,0,0,3),(8,10,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(9,0,0,0,1,0,0,1,1,0,1,1,0,0,0,1,0,0,2,3,0,0,0),(13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(16,0,0,0,1,0,0,1,1,0,0,0,0,0,0,2,0,0,1,3,0,0,0),(17,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(19,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0),(20,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(21,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(22,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(23,1,0,0,0,2,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0),(26,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(27,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(28,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0);
/*!40000 ALTER TABLE `badge` ENABLE KEYS */;
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
