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
-- Table structure for table `group_table`
--

DROP TABLE IF EXISTS `group_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_table` (
  `group_id` smallint NOT NULL AUTO_INCREMENT,
  `group_manager` smallint NOT NULL,
  `group_name` varchar(30) NOT NULL,
  `group_content` varchar(100) NOT NULL,
  `group_limit` smallint NOT NULL,
  `group_gender` tinyint NOT NULL,
  `group_age_min` smallint NOT NULL,
  `group_age_max` smallint NOT NULL,
  `group_image` text,
  `group_nick` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  KEY `FK_user_table_TO_group_table_1` (`group_manager`),
  CONSTRAINT `FK_user_table_TO_group_table_1` FOREIGN KEY (`group_manager`) REFERENCES `user_table` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_table`
--

LOCK TABLES `group_table` WRITE;
/*!40000 ALTER TABLE `group_table` DISABLE KEYS */;
INSERT INTO `group_table` VALUES (8,7,'보드게임 파티','보드게임 좋아하시는분 남녀노소 모여주세요~',5,0,2000,1980,'','보드게임히어로'),(14,16,'맛집 투어','서울, 경기 지역 맛집 투어입니다.\n음식에 관심 있으신 분들 구해요~~!',20,0,1999,1990,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/eec2f284-377c-4dd7-b120-b5e32787f5be_PHOTO_1873.JPG','yeeeh'),(15,15,'코딩만 해요','오늘이 무슨 요일인지도 몰라요.\n날짜도 모르구요.\n전 그냥 코딩만 해요',10,0,1999,1992,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/50886dcc-5958-4c29-af68-70083930d7e0_KakaoTalk_20220818_011316983.jpg','전싸피5'),(22,3,'[자전거 모임] 양재천 정기 자전거 모임','활동장소 : 양재천\n20대 자전거 모임입니다',20,0,2003,1994,'','행복꿀벌'),(23,1,'[당구 모임] 일타싸피','사당 근처에서 활동하는 20대 당구 모임입니다\n초보자, 고수 모두 환영합니다~',20,0,2003,1994,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-19/78821e8f-8ce9-4a8a-a099-448d74473cfe_20220819_032218.jpg','유혀눅'),(24,6,'[취미 공유] 버킷리스트','매주 모임 참가자들의 버킷리스트를 하나씩 함께 하는 모임입니다',50,0,2000,1980,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-19/dcb3a465-d38d-422a-b4cb-388ffd79e53e_1657459219494.jpg','파인애플피자');
/*!40000 ALTER TABLE `group_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 10:18:12
