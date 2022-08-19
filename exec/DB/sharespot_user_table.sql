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
-- Table structure for table `user_table`
--

DROP TABLE IF EXISTS `user_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_table` (
  `user_id` smallint NOT NULL AUTO_INCREMENT,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `phone` char(11) NOT NULL,
  `gender` tinyint NOT NULL,
  `birth` date NOT NULL,
  `name` varchar(15) NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `introduce` text,
  `profile_image` text,
  `BD` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'isBadge',
  `AL` tinyint(1) NOT NULL DEFAULT '0',
  `GD` tinyint(1) NOT NULL COMMENT 'isGender',
  `BR` tinyint(1) NOT NULL COMMENT 'isBirth',
  `PB` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'isPublic',
  `user_grade` tinyint NOT NULL DEFAULT '0',
  `register_time` text,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_table`
--

LOCK TABLES `user_table` WRITE;
/*!40000 ALTER TABLE `user_table` DISABLE KEYS */;
INSERT INTO `user_table` VALUES (1,'hyundnr1126@naver.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01097260000',0,'1995-10-26','유현욱','유혀눅','서울 살아요~',NULL,1,0,1,1,0,0,'2022-08-17'),(2,'2003john@naver.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01028812875',0,'1994-12-08','신지한','신싸피',NULL,NULL,0,0,1,1,0,0,'2022-08-17'),(3,'happybee@naver.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01022223333',0,'1998-08-19','김상훈','행복꿀벌','bee happy~','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/915f4841-ca76-46dd-8d9f-15b10b021596_EbXhhKuUcAUn-ZV.jpg',1,0,1,1,0,0,'2022-08-17'),(4,'htest@naver.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01099997777',0,'1995-05-13','유현욱','하이',NULL,NULL,0,0,1,1,0,0,'2022-08-17'),(5,'ashley3053@gmail.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01025113053',1,'1997-01-17','조혜안','맛집찾아삼만리','맛집, 카페 투어 좋아해요!! 맛집투어 그룹에서 만나요❤','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/a5e44316-d207-40a3-9c18-0dc9fa56df60_20210824_132148.jpg',1,0,1,1,0,1,'2022-08-17'),(6,'sadbee@naver.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01049821023',0,'1998-08-02','최일구','파인애플피자',NULL,NULL,0,0,1,1,0,0,'2022-08-17'),(7,'chunssafy@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01012341234',0,'2022-08-17','전상현','전싸피',NULL,NULL,0,0,1,1,0,0,'2022-08-17'),(8,'gusuk1126@naver.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01099993333',0,'1995-10-26','유현욱','찐현욱','내가 진짜다','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/14d7cdab-8060-484e-99b9-504fc4f7e503_프사용.jpg',1,0,1,1,0,10,'2022-08-17'),(9,'apxjvm@naver.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01029659507',0,'1995-07-18','김태웅','태태웅','안녕하세요','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/8c201692-c6a1-413b-80fa-96068f017663_e-mail.jpg',1,0,1,1,0,1,'2022-08-17'),(10,'c2@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01012341234',0,'2022-08-17','전상현','전싸피2',NULL,NULL,0,0,1,1,0,0,'2022-08-17'),(11,'admin@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01011111111',1,'1997-01-17','셰어스팟','ShareSpot',NULL,NULL,0,0,1,1,0,0,'2022-08-17'),(12,'c3@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01012341234',0,'2022-08-17','전상현','전싸피3',NULL,NULL,0,0,1,1,0,0,'2022-08-17'),(13,'jossafy@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01011111111',1,'1996-02-10','조싸피','조조싸피싸피',NULL,NULL,1,0,1,1,0,0,'2022-08-17'),(14,'c4@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01012341234',0,'2022-08-17','전상현','전싸피4',NULL,NULL,0,0,1,1,0,0,'2022-08-17'),(15,'c5@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01012341234',0,'2022-08-17','전상현','전싸피5',NULL,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/176e61d0-a9d9-452c-a120-b23a8498dabe_KakaoTalk_20220814_223409196.jpg',0,0,1,1,0,0,'2022-08-17'),(16,'yywwxxzz@naver.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01037008097',1,'1997-07-17','여예원','yeeeh','맛집, 여행 좋아해요~!','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-17/32e82df4-c5be-4fb6-9f89-0ae5dc177e8a_IMG_3394.JPG',1,0,1,1,0,1,'2022-08-17'),(17,'hyean123@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01011111111',1,'1993-02-24','조싸피','맛집투어고고',NULL,NULL,1,0,1,1,0,1,'2022-08-17'),(18,'11@aa.cm','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01011111111',0,'2021-11-09','111111','11111111',NULL,NULL,0,0,0,0,0,0,'2022-08-17'),(19,'c7@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01012341234',0,'2022-08-18','전상현','밤새지않아',NULL,NULL,1,0,1,1,0,0,'2022-08-17'),(20,'moonhwa@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01011111111',1,'2001-05-31','박문화','달과별',NULL,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/bf76e5af-510d-4a46-8269-b389feef12ef_20200922_162354.jpg',1,0,1,1,0,1,'2022-08-18'),(21,'trip@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01011111111',1,'2008-08-29','김여행','나에게로떠나는여행',NULL,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/75ad3747-a07d-4c1d-9cfe-6734fc522c45_1657425229184-5.jpg',1,0,1,1,0,1,'2022-08-18'),(22,'dbgusdnr@naver.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01097975646',0,'1996-08-29','혀눅','운동조하',NULL,NULL,1,0,1,1,0,1,'2022-08-18'),(23,'life@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01011111111',1,'1981-02-19','이생활','생활백서',NULL,'https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/0c29a1ca-606a-44b6-90c5-90acefb44bc1_1549615226666-10.jpg',1,0,1,1,0,2,'2022-08-18'),(24,'dbgusdnr123@naver.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01012345678',0,'1995-02-16','ㅎㅇ','탐방러',NULL,NULL,0,0,1,1,0,0,'2022-08-18'),(25,'hyean1234@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01011111111',1,'2001-08-23','김배찌','배찌배찌',NULL,NULL,0,0,1,1,0,0,'2022-08-18'),(26,'fakehyean@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01011111111',1,'2005-05-27','가짜혜안','가짜혜안',NULL,NULL,1,0,1,1,0,1,'2022-08-18'),(27,'mtest@ssafy.com','1718c24b10aeb8099e3fc44960ab6949ab76a267352459f203ea1036bec382c2','01012341234',0,'1996-05-19','전상현','전탐정','당당치킨 3트간다','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/efdf8109-bdb3-4a5f-933f-f31f84a182e8_다운로드파일 (2).jpg',1,0,1,1,0,0,'2022-08-18'),(28,'wjtkdl96@naver.com','c8de1a48c4a81301331696dd2a10286cdcf7b69edfce6a41d77beda56cc81d7a','01085091168',0,'1996-05-19','전상현','햄볶음밥','햄볶음밥 맛있다','https://i7a505.p.ssafy.io/api/file?imagePath=/home/ubuntu/src/image/2022-08-18/16ba8484-5d88-4fce-94d1-d00b6553e467_Screenshot_20220817-184937_Chrome.jpg',1,0,1,1,0,0,'2022-08-18'),(29,'test@test.com','4f3114905885beabe4835aacc66d772c80a283999e4a8a2690f2f92a2634849b','01011111111',1,'2022-08-09','하이','하이하이',NULL,NULL,0,0,0,0,0,0,'2022-08-18'),(30,'fighting@fighting.com','846849547f1a1a0026efa5fd5d9e80cf42a280d060a081a6b4a9540b38bd3120','01012345678',0,'2021-10-13','화이팅하세요','화이팅하세요',NULL,NULL,0,0,1,1,0,0,'2022-08-19');
/*!40000 ALTER TABLE `user_table` ENABLE KEYS */;
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
