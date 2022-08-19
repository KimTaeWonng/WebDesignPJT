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
-- Table structure for table `comment_table`
--

DROP TABLE IF EXISTS `comment_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_table` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `user_id` smallint NOT NULL,
  `comment` text NOT NULL,
  `user_image` text,
  `user_nick` varchar(45) NOT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `FK_post_table_TO_comment_table_1` (`post_id`),
  KEY `FK_comment_table_user_table` (`user_id`),
  CONSTRAINT `FK_comment_table_user_table` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`),
  CONSTRAINT `FK_post_table_TO_comment_table_1` FOREIGN KEY (`post_id`) REFERENCES `post_table` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_table`
--

LOCK TABLES `comment_table` WRITE;
/*!40000 ALTER TABLE `comment_table` DISABLE KEYS */;
INSERT INTO `comment_table` VALUES (1,1,8,'좋아보이네요',NULL,'찐현욱','2022-08-16 20:50:28'),(2,7,9,'튀김 존맛','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/8c201692-c6a1-413b-80fa-96068f017663_e-mail.jpg','태태웅','2022-08-17 08:39:55'),(3,5,8,'홀리몰리','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/14d7cdab-8060-484e-99b9-504fc4f7e503_프사용.jpg','찐현욱','2022-08-16 23:41:26'),(4,7,3,'헐 너무 맛있어 보여요!!!',NULL,'행복꿀벌','2022-08-17 02:45:34'),(5,9,3,'헐 밥 잘나온다~~!',NULL,'행복꿀벌','2022-08-17 02:50:33'),(6,20,3,'김볶 최고입니다',NULL,'행복꿀벌','2022-08-17 02:54:34'),(7,5,16,'풋풋하네요~','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/32e82df4-c5be-4fb6-9f89-0ae5dc177e8a_IMG_3394.JPG','yeeeh','2022-08-17 04:27:27'),(8,26,5,'우와 바다다!!! 나중에 나랑 같이가 ><','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/a5e44316-d207-40a3-9c18-0dc9fa56df60_20210824_132148.jpg','맛집찾아삼만리','2022-08-17 05:26:13'),(9,39,15,'나도 놀고 싶어요','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/176e61d0-a9d9-452c-a120-b23a8498dabe_KakaoTalk_20220814_223409196.jpg','전싸피5','2022-08-17 07:07:54'),(10,38,15,'갈 시간이 없어요','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/176e61d0-a9d9-452c-a120-b23a8498dabe_KakaoTalk_20220814_223409196.jpg','전싸피5','2022-08-17 07:09:03'),(11,20,16,'꺅 나랑 같이가 >_<','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/32e82df4-c5be-4fb6-9f89-0ae5dc177e8a_IMG_3394.JPG','yeeeh','2022-08-17 07:10:05'),(12,19,16,'헐 나도 분짜 너무 좋아해 ㅎㅎㅎ','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/32e82df4-c5be-4fb6-9f89-0ae5dc177e8a_IMG_3394.JPG','yeeeh','2022-08-17 07:10:30'),(13,19,16,'헐 나도 분짜 너무 좋아해 ㅎㅎㅎ','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/32e82df4-c5be-4fb6-9f89-0ae5dc177e8a_IMG_3394.JPG','yeeeh','2022-08-17 07:10:30'),(14,39,3,'행복해보여','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/ddce527c-020b-4874-99b3-2b37f1694674_bee_happy.png','행복꿀벌','2022-08-17 09:45:48'),(15,20,3,'보끔밥 맛있겠다','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/ddce527c-020b-4874-99b3-2b37f1694674_bee_happy.png','행복꿀벌','2022-08-17 09:59:14'),(16,70,27,'파주 너무 멀어요',NULL,'전탐정','2022-08-17 22:34:32'),(17,70,27,'파주 너무 멀어요',NULL,'전탐정','2022-08-17 22:34:52'),(18,69,9,'PPAP','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/8c201692-c6a1-413b-80fa-96068f017663_e-mail.jpg','태태웅','2022-08-17 22:38:17'),(19,34,3,'타워팰리스','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/915f4841-ca76-46dd-8d9f-15b10b021596_EbXhhKuUcAUn-ZV.jpg','행복꿀벌','2022-08-17 23:30:04'),(20,69,28,'PPAP',NULL,'햄볶음밥','2022-08-18 14:25:32'),(21,69,28,'PPAP',NULL,'햄볶음밥','2022-08-18 14:25:40'),(22,90,27,'부자의 스포츠 ㄷㄷ','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/efdf8109-bdb3-4a5f-933f-f31f84a182e8_다운로드파일 (2).jpg','전탐정','2022-08-18 14:26:37'),(23,89,28,'신라호텔 부럽다',NULL,'햄볶음밥','2022-08-18 14:30:32'),(24,90,3,'오우오우','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/915f4841-ca76-46dd-8d9f-15b10b021596_EbXhhKuUcAUn-ZV.jpg','행복꿀벌','2022-08-18 14:49:24'),(25,91,9,'맛있겠다','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/8c201692-c6a1-413b-80fa-96068f017663_e-mail.jpg','태태웅','2022-08-18 14:49:53'),(26,91,1,'진짜...',NULL,'유혀눅','2022-08-18 14:50:00'),(27,91,1,'ㅇ',NULL,'유혀눅','2022-08-18 15:11:50'),(28,91,1,'ㅇ',NULL,'유혀눅','2022-08-18 15:11:50'),(29,91,1,'홀리',NULL,'유혀눅','2022-08-18 15:12:03'),(30,86,6,'좋은데요',NULL,'파인애플피자','2022-08-18 18:55:45'),(31,86,6,'좋은데요',NULL,'파인애플피자','2022-08-18 18:55:48');
/*!40000 ALTER TABLE `comment_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 10:18:09
