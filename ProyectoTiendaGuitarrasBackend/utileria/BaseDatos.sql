-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: guitar_store
-- ------------------------------------------------------
-- Server version	9.3.0

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
-- structure for Database `guitar_store`
--

CREATE DATABASE IF NOT EXISTS guitar_store;
USE guitar_store;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` varchar(36) NOT NULL,
  `order_id` varchar(36) NOT NULL,
  `product_id` bigint NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `total` double NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES ('4a5e57f2-2a06-4015-a55e-29a392559e2b','5bdb94fa-0b75-422e-9cad-205d924026fc',1,'Lukather',299,1,299,'guitarra_01'),('9eb94e41-571a-41d6-a100-82af6bcb7cc3','5bdb94fa-0b75-422e-9cad-205d924026fc',12,'Hazel',379,2,758,'guitarra_12'),('c1f34d52-8cb7-4e39-9053-b0eaecdd7a52','d2b1cc18-e9fa-4c4e-9e98-f117ea52fa58',1,'Lukather',299,1,299,'guitarra_01'),('f78f8fcf-4471-4560-9923-1d661f2f49f4','d2b1cc18-e9fa-4c4e-9e98-f117ea52fa58',12,'Hazel',379,2,758,'guitarra_12');
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` varchar(36) NOT NULL,
  `created_at` timestamp NOT NULL,
  `customer_name` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'PENDING',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('5bdb94fa-0b75-422e-9cad-205d924026fc','2025-05-27 22:25:49','Edwin Ivan S','CREATED'),('d2b1cc18-e9fa-4c4e-9e98-f117ea52fa58','2025-05-27 21:25:22','Edwin Ivan S','CREATED');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Lukather','guitarra_01','Sonido potente con tonos cálidos. Ideal para solos intensos y acompañamientos suaves. Diseño clásico con acabado profesional.',299,10),(2,'SRV','guitarra_02','Perfecta para estilos agresivos y versátiles. Mástil rápido, cuerpo liviano y excelente sustain para estudio y escenario.',349,10),(3,'Borland','guitarra_03','Guitarra de alta precisión con pastillas activas. Tonos limpios y definidos. Equilibrada para ritmos y melodías complejas.',329,10),(4,'VAI','guitarra_04','Construida con materiales premium. Ideal para jazz y blues. Tono envolvente y respuesta dinámica al toque.',299,10),(5,'Thompson','guitarra_05','Guitarra moderna con acabado brillante. Excelente para géneros contemporáneos. Tonalidad nítida y cuerpo ergonómico.',399,10),(6,'White','guitarra_06','Versatilidad total para músicos exigentes. Compatible con pedales de efectos. Sonido natural y gran estabilidad de afinación.',329,10),(7,'Cobain','guitarra_07','Diseñada para el escenario. Ofrece presencia sonora impactante y comodidad al tocar. Muy valorada por profesionales.',349,10),(8,'Dale','guitarra_08','Hecha para riffs pesados y armonías claras. Acabado mate y electrónica de calidad. Sonido robusto y bien definido.',379,10),(9,'Krieger','guitarra_09','Modelo clásico con mejoras actuales. Ideal para principiantes y expertos. Buena proyección acústica y durabilidad.',289,10),(10,'Campbell','guitarra_10','Sonido profundo y sustain prolongado. Diseño elegante y cómodo para largas sesiones. Excelente balance entre cuerdas.',349,10),(11,'Reed','guitarra_11','Ideal para sesiones en vivo y grabación. Su tono preciso y construcción sólida aseguran confiabilidad y carácter sonoro.',399,10),(12,'Hazel','guitarra_12','Estilo retro con tecnología moderna. Combinación perfecta entre tradición y potencia sonora. Construcción robusta.',379,10);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-27 19:45:35
