package com.niit.jdp.repository;

import java.sql.Connection;

public class PlaylistRepository {
    public boolean createNewPlaylist(Connection connection, String playlistName) {
        return false;
    }

    public boolean addSongToPlaylist(Connection connection, int songId) {
        return false;
    }

    public boolean removeSongFromPlaylist(Connection connection, int songId) {
        return false;
    }

    public boolean editPlaylistName(Connection connection, String oldPlaylistName, String newPlaylistName) {
        return false;
    }
}
