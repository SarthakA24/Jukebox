package com.niit.jdp.repository;

import com.niit.jdp.service.DatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class PlaylistRepositoryTest {
    PlaylistRepository playlistRepository;
    DatabaseService databaseService;
    Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        playlistRepository = new PlaylistRepository();
        databaseService = new DatabaseService();
        databaseService.connect();
        connection = databaseService.getConnection();
    }

    @AfterEach
    void tearDown() {
        playlistRepository = null;
        databaseService = null;
        connection = null;
    }

    @Test
    void getPlaylistByNameCorrectCase() throws SQLException {
        Assertions.assertEquals("sarthak", playlistRepository.getPlaylistByName(connection, "sarthak").getPlaylistName());
    }

    @Test
    void getPlaylistByNameIncorrectCase() throws SQLException {
        Assertions.assertNotNull(playlistRepository.getPlaylistByName(connection, "sArThAk").getPlaylistName());
    }

    @Test
    void getPlaylistsName() throws SQLException {
        Assertions.assertNotNull(playlistRepository.getPlaylistsName(connection).get(0));
    }
}