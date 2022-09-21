package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;

import java.sql.Connection;

public class PlaylistRepository {

    /**
     * This method is used to get the names of all the playlists from the database
     *
     * @param connection The connection to the database
     * @return A string of playlists names
     */
    public String[] getPlaylistsName(Connection connection) {
        return new String[]{};
    }

    /**
     * This method returns the list
     *
     * @param connection   The connection to the database
     * @param playlistName The name of playlist to fetch
     * @return The playlist object based on the search
     */
    public Playlist getPlaylistByName(Connection connection, String playlistName) {
        return new Playlist();
    }

    /**
     * This method is used to create a new playlist.
     *
     * @param connection   The connection to the database.
     * @param playlistName The name of the playlist.
     * @return true if the playlist is created successfully, false otherwise.
     */
    public boolean createNewPlaylist(Connection connection, String playlistName) {
        return false;
    }

    /**
     * This method is used to add a song to a playlist.
     *
     * @param connection The connection to the database.
     * @param song       The song to be added to the playlist.
     * @return true if the song is added successfully, false otherwise.
     */
    public boolean addSongToPlaylist(Connection connection, Song song) {
        return false;
    }

    /**
     * This method is used to remove a song from a playlist.
     *
     * @param connection The connection to the database.
     * @param songId     The id of the song to be removed from the playlist.
     * @return true if the song is removed successfully, false otherwise.
     */
    public boolean removeSongFromPlaylist(Connection connection, int songId) {
        return false;
    }

    /**
     * This method is used to edit a playlist name.
     *
     * @param connection      The connection to the database.
     * @param oldPlaylistName The old name of the playlist.
     * @param newPlaylistName The new name of the playlist.
     * @return true if the playlist name is edited successfully, false otherwise.
     */
    public boolean editPlaylistName(Connection connection, String oldPlaylistName, String newPlaylistName) {
        return false;
    }
}
