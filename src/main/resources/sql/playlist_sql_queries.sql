-- use the database `jukebox`
USE
    `jukebox`;

-- Create a table `playlist`
CREATE TABLE IF NOT EXISTS `jukebox`.`playlist`
(
    `playlist_name` VARCHAR(20) NOT NULL,
    `list_of_songs` VARCHAR(45) NULL,
    PRIMARY KEY (`playlist_name`)
);