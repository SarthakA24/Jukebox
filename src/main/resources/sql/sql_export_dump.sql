CREATE
    DATABASE IF NOT EXISTS `jukebox` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE
    `jukebox`;
-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: localhost    Database: jukebox
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist`
(
    `playlist_id`   int         NOT NULL AUTO_INCREMENT,
    `playlist_name` varchar(20) NOT NULL,
    `songs_id`      varchar(45) DEFAULT NULL,
    PRIMARY KEY (`playlist_id`),
    UNIQUE KEY `playlist_name_UNIQUE` (`playlist_name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK
    TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist`
    DISABLE KEYS */;
INSERT INTO `playlist`
VALUES (1, 'sarthak', '4,1,');
/*!40000 ALTER TABLE `playlist`
    ENABLE KEYS */;
UNLOCK
    TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `song`
(
    `id`                  int         NOT NULL AUTO_INCREMENT,
    `name`                varchar(90) NOT NULL,
    `duration_in_seconds` int         NOT NULL,
    `url`                 varchar(90) NOT NULL,
    `artist_name`         varchar(45) NOT NULL,
    `album_name`          varchar(45) NOT NULL,
    `genre`               varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK
    TABLES `song` WRITE;
/*!40000 ALTER TABLE `song`
    DISABLE KEYS */;
INSERT INTO `song`
VALUES (1, 'Spectre', 231, 'src/main/resources/songs/Alan_Walker_Spectre_NCS_Release.wav', 'Alan Walker', 'The Spectre',
        'Electronic'),
       (2, 'Legends Never Die (Alan Walker Remix)', 166, 'src/main/resources/songs/Alan_Walker-Legends_Never_Die.wav',
        'Alan Walker', 'League of Legends', 'Electronic'),
       (3, 'Cradles', 210, 'src/main/resources/songs/Sub Urban - Cradles.wav', 'Sub Urban', 'Cradles', 'Indie'),
       (4, 'Waiting For Love', 230, 'src/main/resources/songs/Avicii - Waiting For Love.wav', 'Avicii',
        'Hot Summer Hits 2015', 'EDM'),
       (5, 'Wake Me Up', 272, 'src/main/resources/songs/Avicii - Wake Me Up.wav', 'Avicii', 'True', 'EDM'),
       (6, 'The Nights', 176, 'src/main/resources/songs/Avicii - The Nights.wav', 'Avicii', 'The Days / Nights EP',
        'EDM'),
       (7, 'See You Again', 230, 'src/main/resources/songs/See You Again.wav', 'Wiz Khalifa', 'Most Wanted, Vol. 2',
        'Pop'),
       (8, 'Ignite', 229, 'src/main/resources/songs/Ignite.wav', 'K-391', 'Ignite (Remixes)', 'Electronics'),
       (9, 'Bad Liar', 260, 'src/main/resources/songs/Bad Liar.wav', 'Imagine Dragons', 'Origins', 'Pop'),
       (10, 'Hymn for the Weekend', 215, 'src/main/resources/songs/Hymn_For_The_Weekend_Remix.wav', 'Coldplay',
        'A Head Full of Dreams', 'Pop');
/*!40000 ALTER TABLE `song`
    ENABLE KEYS */;
UNLOCK
    TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2022-09-22 14:29:02
