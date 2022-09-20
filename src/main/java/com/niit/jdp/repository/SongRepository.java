package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.Connection;
import java.util.List;

public class SongRepository {
    public boolean addSongToCatalog(Connection connection, Song song) {
        return false;
    }

    public boolean deleteSongFromCatalog(Connection connection, int songId) {
        return false;
    }

    public List<Song> getAllSongs(Connection connection) {
        return List.of();
    }

    public List<Song> searchSongsByArtist(Connection connection, String artistName) {
        return List.of();
    }

    public List<Song> searchSongsByGenre(Connection connection, String genre) {
        return List.of();
    }

    public List<Song> searchSongsByName(Connection connection, String songName) {
        return List.of();
    }
}
