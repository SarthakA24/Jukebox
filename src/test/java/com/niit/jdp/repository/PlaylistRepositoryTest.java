package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
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

class PlaylistRepositoryTest {
    PlaylistRepository playlistRepository;
    DatabaseService databaseService;
    Connection connection;
    List<Song> songList;

    @BeforeEach
    void setUp() throws SQLException {
        playlistRepository = new PlaylistRepository();
        databaseService = new DatabaseService();
        databaseService.connect();
        connection = databaseService.getConnection();
        songList = new ArrayList<>();
        Song faded = new Song(1, "Faded", 200, "src/test/url/faded", "Alan Walker", "Faded", "EDM");
        Song badLiar = new Song(2, "Bad Liar", 250, "src/test/url/bad_liar", "Imagine Dragons", "Origins", "Pop");
        songList.add(faded);
        songList.add(badLiar);
        Playlist playlist = new Playlist(1, "Sarthak Playlist", songList);
    }

    @AfterEach
    void tearDown() {
        playlistRepository = null;
        databaseService = null;
        connection = null;
        songList = null;
    }

    @Test
    void getPlaylistsNameCorrectCase() throws SQLException {
        Playlist sarthak = playlistRepository.getPlaylistByName(connection, "sarthak");
        Assertions.assertEquals("sarthak", sarthak.getPlaylistName());
    }

    @Test
    void getPlaylistsNameIncorrectCase() throws SQLException {
        Playlist sarthak = playlistRepository.getPlaylistByName(connection, "sArThAk");
        Assertions.assertEquals("sarthak", sarthak.getPlaylistName());
    }

    @Test
    void getPlaylistByName() {
    }
}