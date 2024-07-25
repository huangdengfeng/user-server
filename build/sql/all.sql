CREATE DATABASE IF NOT EXISTS `user_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION = 'N' */;
USE `user_db`;

--
-- Table structure for table `t_oauth`
--

DROP TABLE IF EXISTS `t_oauth`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_oauth`
(
    `uid`         bigint       NOT NULL,
    `oauth_type`  tinyint      NOT NULL COMMENT 'auth类型',
    `oauth_id`    varchar(100) NOT NULL,
    `union_id`    varchar(100) DEFAULT NULL,
    `status`      tinyint      NOT NULL,
    `create_time` datetime     NOT NULL,
    `update_time` datetime     NOT NULL,
    PRIMARY KEY (`uid`, `oauth_type`, `oauth_id`),
    UNIQUE KEY `idx_t_oauth_oauth_type_oauth_id` (`oauth_type`, `oauth_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_relation`
--

DROP TABLE IF EXISTS `t_relation`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_relation`
(
    `uid`            bigint       NOT NULL,
    `relation_type`  tinyint      NOT NULL,
    `relation_value` varchar(100) NOT NULL,
    `status`         tinyint      NOT NULL,
    `create_time`    datetime     NOT NULL,
    `update_time`    datetime     NOT NULL,
    PRIMARY KEY (`uid`, `relation_type`, `relation_value`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user`
(
    `uid`         bigint      NOT NULL AUTO_INCREMENT,
    `user_name`   varchar(100) DEFAULT NULL,
    `password`    varchar(100) DEFAULT NULL,
    `secret_key`  varchar(32) NOT NULL,
    `status`      tinyint     NOT NULL,
    `create_time` datetime    NOT NULL,
    `update_time` datetime    NOT NULL,
    PRIMARY KEY (`uid`),
    UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1000000
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_user_profile`
--

DROP TABLE IF EXISTS `t_user_profile`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_profile`
(
    `uid`         bigint   NOT NULL,
    `nick_name`   varchar(100) DEFAULT NULL,
    `name`        varchar(100) DEFAULT NULL,
    `mobile`      varchar(45)  DEFAULT NULL,
    `avatar`      varchar(100) DEFAULT NULL,
    `email`       varchar(100) DEFAULT NULL,
    `birthday`    date         DEFAULT NULL,
    `address`     varchar(100) DEFAULT NULL,
    `create_time` datetime NOT NULL,
    `update_time` datetime NOT NULL,
    PRIMARY KEY (`uid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2024-07-25 19:39:21
