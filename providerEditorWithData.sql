-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: provider-db
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `date`
--

DROP TABLE IF EXISTS `date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `date` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(5) NOT NULL,
  `day` varchar(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `date`
--

LOCK TABLES `date` WRITE;
/*!40000 ALTER TABLE `date` DISABLE KEYS */;
INSERT INTO `date` VALUES (80,'11.30','monday'),(81,'15.00','monday'),(82,'18.00','monday'),(83,'11.30','tuesday'),(84,'15.00','tuesday'),(85,'18.00','tuesday'),(86,'11.30','wednesday'),(87,'15.00','wednesday'),(88,'18.00','wednesday'),(89,'11.30','thursday'),(90,'15.00','thursday'),(92,'11.30','friday'),(93,'15.00','friday'),(94,'18.00','friday'),(95,'11.30','saturday'),(100,'18.00','sunday');
/*!40000 ALTER TABLE `date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider` (
  `code` varchar(4) NOT NULL,
  `provider` varchar(50) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` VALUES ('PV01','provider1'),('PV02','provider2'),('PV03','provider3'),('PV04','provider4'),('PV05','provider5');
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider_date`
--

DROP TABLE IF EXISTS `provider_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider_date` (
  `id` int(11) NOT NULL,
  `code` varchar(4) NOT NULL,
  PRIMARY KEY (`id`,`code`),
  KEY `code` (`code`),
  CONSTRAINT `provider_date_ibfk_1` FOREIGN KEY (`code`) REFERENCES `provider` (`code`),
  CONSTRAINT `provider_date_ibfk_2` FOREIGN KEY (`id`) REFERENCES `date` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider_date`
--

LOCK TABLES `provider_date` WRITE;
/*!40000 ALTER TABLE `provider_date` DISABLE KEYS */;
INSERT INTO `provider_date` VALUES (80,'PV01'),(83,'PV01'),(85,'PV01'),(87,'PV01'),(89,'PV01'),(93,'PV01'),(94,'PV01'),(95,'PV01'),(100,'PV01'),(80,'PV02'),(83,'PV02'),(89,'PV02'),(90,'PV02'),(100,'PV02'),(81,'PV03'),(84,'PV03'),(87,'PV03'),(88,'PV03'),(89,'PV03'),(93,'PV03'),(95,'PV03'),(100,'PV03'),(81,'PV04'),(84,'PV04'),(87,'PV04'),(88,'PV04'),(89,'PV04'),(92,'PV04'),(95,'PV04'),(100,'PV04'),(82,'PV05'),(84,'PV05'),(86,'PV05'),(88,'PV05'),(89,'PV05'),(94,'PV05'),(100,'PV05');
/*!40000 ALTER TABLE `provider_date` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-16  9:06:28
