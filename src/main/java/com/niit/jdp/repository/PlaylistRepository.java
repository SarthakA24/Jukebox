package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {

    /**
     * This method is used to get the names of all the playlists from the database
     *
     * @param connection The connection to the database
     * @return A string of playlists names
     */
    public List<String> getPlaylistsName(Connection connection) throws SQLException {
        List<String> playlistNamesList = new ArrayList<>();
        // Create a select query to get all the playlists names
        String selectQuery = "SELECT `playlist_name` FROM `jukebox`.`playlist`;";
        // Create a statement object to execute the query and get the result set
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectQuery);
            // iterate over the result set and add the playlists names in list
            while (resultSet.next()) {
                String result = resultSet.getString("playlist_name");
                playlistNamesList.add(result);
            }
        }
        return playlistNamesList;
    }

    /**
     * This method returns the Playlist object for the searched playlist name
     *
     * @param connection   The connection to the database
     * @param playlistName The name of playlist to fetch
     * @return The playlist object based on the search
     */
    public Playlist getPlaylistByName(Connection connection, String playlistName) throws SQLException {
        // Create a playlist object
        Playlist playlistByName = new Playlist();
        // Create a String query to get the playlist information from the database
        String selectQuery = "SELECT * FROM `jukebox`.`playlist` WHERE (`playlist_name` = ? );";
        // Create a prepared statement object to set the values and execute the query and get the result set
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, playlistName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Iterate over the result set and
                playlistByName.setPlaylistName(resultSet.getString("playlist_name"));
                playlistByName.setSongsId(resultSet.getString("list_of_songs"));
            }
        }
        return playlistByName;
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
