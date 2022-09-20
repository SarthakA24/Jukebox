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
    `name`                VARCHAR(90) NOT NULL,
    `duration_in_seconds` INT         NOT NULL,
    `url`                 VARCHAR(90) NOT NULL,
    `artist_name`         VARCHAR(45) NOT NULL,
    `album_name`          VARCHAR(45) NOT NULL,
    `genre`               VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

-- Insert the songs in the database one by one
INSERT INTO `jukebox`.`song` (`name`, `duration_in_seconds`, `url`, `artist_name`, `album_name`, `genre`)
VALUES ('Spectre', '231', 'src/main/resources/songs/Alan_Walker_Spectre_NCS_Release.wav', 'Alan Walker', 'The Spectre',
        'Electronic');

INSERT INTO `jukebox`.`song` (`name`, `duration_in_seconds`, `url`, `artist_name`, `album_name`, `genre`)
VALUES ('Legends Never Die (Alan Walker Remix)', '166', 'src/main/resources/songs/Alan_Walker-Legends_Never_Die.wav',
        'Alan Walker', 'League of Legends', 'Electronic');

INSERT INTO `jukebox`.`song` (`name`, `duration_in_seconds`, `url`, `artist_name`, `album_name`, `genre`)
VALUES ('Cradles', '210', 'src/main/resources/songs/Sub Urban - Cradles.wav', 'Sub Urban', 'Cradles', 'Indie');

INSERT INTO `jukebox`.`song` (`name`, `duration_in_seconds`, `url`, `artist_name`, `album_name`, `genre`)
VALUES ('Waiting For Love', '230', 'src/main/resources/songs/Avicii - Waiting For Love.wav', 'Avicii',
        'Hot Summer Hits 2015', 'EDM');

INSERT INTO `jukebox`.`song` (`name`, `duration_in_seconds`, `url`, `artist_name`, `album_name`, `genre`)
VALUES ('Wake Me Up', '272', 'src/main/resources/songs/Avicii - Wake Me Up.wav', 'Avicii', 'True', 'EDM');

INSERT INTO `jukebox`.`song` (`name`, `duration_in_seconds`, `url`, `artist_name`, `album_name`, `genre`)
VALUES ('The Nights', '176', 'src/main/resources/songs/Avicii - The Nights.wav', 'Avicii', 'The Days / Nights EP',
        'EDM');

INSERT INTO `jukebox`.`song` (`name`, `duration_in_seconds`, `url`, `artist_name`, `album_name`, `genre`)
VALUES ('See You Again', '230', 'src/main/resources/songs/See You Again.wav', 'Wiz Khalifa', 'Most Wanted, Vol. 2',
        'Pop');

INSERT INTO `jukebox`.`song` (`name`, `duration_in_seconds`, `url`, `artist_name`, `album_name`, `genre`)
VALUES ('Ignite', '229', 'src/main/resources/songs/Ignite.wav', 'K-391', 'Ignite (Remixes)', 'Electronics');

INSERT INTO `jukebox`.`song` (`name`, `duration_in_seconds`, `url`, `artist_name`, `album_name`, `genre`)
VALUES ('Bad Liar', '260', 'src/main/resources/songs/Bad Liar.wav', 'Imagine Dragons', 'Origins', 'Pop');

INSERT INTO `jukebox`.`song` (`name`, `duration_in_seconds`, `url`, `artist_name`, `album_name`, `genre`)
VALUES ('Hymn for the Weekend', '215', 'src/main/resources/songs/Hymn_For_The_Weekend_Remix.wav', 'Coldplay',
        'A Head Full of Dreams', 'Pop');
