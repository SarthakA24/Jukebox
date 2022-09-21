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

-- alter the table to include new fields
ALTER TABLE `jukebox`.`playlist`
    ADD COLUMN `playlist_id` INT NOT NULL FIRST,
    CHANGE COLUMN `list_of_songs` `songs_id` VARCHAR(45) NULL DEFAULT NULL,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`playlist_id`);
;

-- make the playlist id auto increment
ALTER TABLE `jukebox`.`playlist`
    CHANGE COLUMN `playlist_id` `playlist_id` INT NOT NULL AUTO_INCREMENT;

-- made the playlist name unique
ALTER TABLE `jukebox`.`playlist`
    ADD UNIQUE INDEX `playlist_name_UNIQUE` (`playlist_name` ASC) VISIBLE;
;
