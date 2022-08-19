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
-- Table structure for table `post_scrap`
--

DROP TABLE IF EXISTS `post_scrap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_scrap` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `user_id` smallint NOT NULL,
  `post_id` int NOT NULL,
  `post_image` text,
  PRIMARY KEY (`idx`),
  KEY `FK_user_table_TO_post_scrap_1` (`user_id`),
  KEY `FK_post_table_TO_post_scrap_1` (`post_id`),
  CONSTRAINT `FK_post_table_TO_post_scrap_1` FOREIGN KEY (`post_id`) REFERENCES `post_table` (`post_id`),
  CONSTRAINT `FK_user_table_TO_post_scrap_1` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_scrap`
--

LOCK TABLES `post_scrap` WRITE;
/*!40000 ALTER TABLE `post_scrap` DISABLE KEYS */;
INSERT INTO `post_scrap` VALUES (1,8,1,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/cef1202d-e652-49d8-9180-ef1c3d1ffbe8_청담 쌀국수.jpg'),(2,5,8,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/faff9427-714a-4cdd-98df-561de8c1c0fd_동네.jpg'),(3,5,7,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/e51b6583-e2de-4a50-b859-7bd58b4d38d0_군자 텐동.jpg'),(4,5,26,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/ce9b3bb8-2999-478c-84f5-a9b64e880149_IMG_3281.JPG'),(8,3,9,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/58ccf27e-95a5-4bfb-aad1-41372271642b_멀캠.jpg'),(10,19,41,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/5480a7b6-f86b-4c9b-b640-2c3d9ec988a8_칼국수.jpg'),(11,27,69,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/ed5b81d1-ceff-41fb-b126-88f9c8cac438_1549615226666-10.jpg'),(15,28,89,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/1a4ba209-e857-46d9-9c34-c91160194ae0_20220604_182046.jpg'),(17,9,93,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-19/7709d993-7e2c-4626-a3a7-6a5223ea8bdc_37C7848B-CE83-4282-8FFE-2E0A17289006.jpeg'),(18,9,91,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/b295d922-6425-4944-8ec3-ef174ed07ffe_1660833570387.png'),(19,6,91,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/b295d922-6425-4944-8ec3-ef174ed07ffe_1660833570387.png');
/*!40000 ALTER TABLE `post_scrap` ENABLE KEYS */;
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
