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
                playlistNamesList.add(result.toLowerCase());
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
        List<Song> songList = new ArrayList<>();
        // Create a String query to get the playlist information from the database
        String selectQuery = "SELECT * FROM `jukebox`.`playlist` WHERE (`playlist_name` = ? );";
        // Create a prepared statement object to set the values and execute the query and get the result set
        SongRepository songRepository = new SongRepository();
        Song song = new Song();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            // Set the values to the query
            preparedStatement.setString(1, playlistName);
            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();
            String songsId = "";
            while (resultSet.next()) {
                //Set the values to the playlist object
                playlistByName.setPlaylistName(resultSet.getString("playlist_name"));
                songsId = resultSet.getString("songs_id");
            }
            // Split the songs id string to get the individual song id and iterate over the list to get the song object
            String[] songsIdArray = songsId.split(",");
            List<Song> allSongsList = songRepository.getAllSongs(connection);
            for (String songId : songsIdArray) {
                songList.add(songRepository.getSongById(allSongsList, Integer.parseInt(songId)));
                playlistByName.setSongList(songList);
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
    public boolean createNewPlaylist(Connection connection, String playlistName) throws SQLException {
        // Create a string insert query to add a row in the database
        String insertQuery = "INSERT INTO `jukebox`.`playlist` (`playlist_name`) VALUES (?);";
        // Create a prepared statement object to set the playlist name and execute the query
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, playlistName);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    /**
     * This method is used to add a song to a playlist.
     *
     * @param connection   The connection to the database.
     * @param songToAdd    The song to add to the playlist.
     * @param playlistName The name of the playlist to which the song needs to be added
     * @return true if the song is added successfully, false otherwise.
     */
    public boolean addSongToPlaylist(Connection connection, Song songToAdd, String playlistName) throws SQLException {
        // Call the method to get the playlist by name
        Playlist playlistByName = getPlaylistByName(connection, playlistName);
        // Create a string of songs id to update in the table
        String songIdToUpdate = "";
        // Add the already present songs id to the string and then append the song id to be added
        List<Song> songsInList = playlistByName.getSongList();
        for (Song song : songsInList) {
            songIdToUpdate = song.getId() + "," + songToAdd.getId() + ",";
        }
        // Create an update query to update the songs id in the database
        String updateQuery = "UPDATE `jukebox`.`playlist` SET `songs_id` = ? WHERE (`playlist_name` = ?);";
        // Create a prepared statement to execute the query
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, songIdToUpdate);
            preparedStatement.setString(2, playlistName);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    /**
     * This method is used to remove a song from a playlist.
     *
     * @param connection   The connection to the database.
     * @param songId       The id of the song to be removed from the playlist.
     * @param playlistName The name of the playlist from which the song needs to be removed
     * @return true if the song is removed successfully, false otherwise.
     */
    public boolean removeSongFromPlaylist(Connection connection, int songId, String playlistName) {
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
    public boolean editPlaylistName(Connection connection, String oldPlaylistName, String newPlaylistName) throws SQLException {
        // Create an update query to update the playlist name
        String updateQuery = "UPDATE `jukebox`.`playlist` SET `playlist_name` = ? WHERE (`playlist_name` = ?);";
        // Create a prepared statement to execute the query
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newPlaylistName);
            preparedStatement.setString(2, oldPlaylistName);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
