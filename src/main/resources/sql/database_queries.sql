-- create a database `jukebox`
CREATE
DATABASE IF NOT EXISTS `jukebox`;

-- use the database `jukebox`
USE
`jukebox`;

-- create a table `song`
CREATE TABLE `jukebox`.`song`
(
    `id`                  INT         NOT NULL AUTO_INCREMENT,
    `name`                VARCHAR(45) NOT NULL,
    `duration_in_seconds` INT         NOT NULL,
    `url`                 VARCHAR(90) NOT NULL,
    `artist_name`         VARCHAR(45) NOT NULL,
    `album_name`          VARCHAR(45) NOT NULL,
    `genre`               VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);
