package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class SongRepositoryTest {
    DatabaseService databaseService;
    SongRepository songRepository;
    Connection connection;
    List<Song> songList;


    @BeforeEach
    void setUp() throws SQLException {
        databaseService = new DatabaseService();
        songRepository = new SongRepository();
        databaseService.connect();
        connection = databaseService.getConnection();
        songList = new ArrayList<>();
        Song faded = new Song(1, "Faded", 200, "src/test/url/faded", "Alan Walker", "Faded", "EDM");
        Song badLiar = new Song(2, "Bad Liar", 250, "src/test/url/bad_liar", "Imagine Dragons", "Origins", "Pop");
        songList.add(faded);
        songList.add(badLiar);
    }

    @AfterEach
    void tearDown() {
        databaseService = null;
        connection = null;
        songRepository = null;
        songList = null;
    }

    @Test
    void getSongByIdSuccess() {
        Assertions.assertEquals("Bad Liar", songRepository.getSongById(songList, 2).getName());
    }

    @Test
    void getSongByIdFailure() {
        Assertions.assertNull(songRepository.getSongById(songList, 3).getName());
    }

    @Test
    void searchSongsByArtist() {
    }

    @Test
    void searchSongsByGenre() {
    }

    @Test
    void searchSongsByName() {
    }
}