package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongRepository {
    /**
     * This method is used to add a new song to the table in database.
     *
     * @param connection The connection to the database.
     * @param song       The song to be added to the database.
     * @return true if the song is added successfully, false otherwise.
     */
    public boolean addSongToCatalog(Connection connection, Song song) throws SQLException {
        // declare a variable of string datatype to hold the insert query
        String insertStatement = "INSERT INTO `jukebox`.`song` " +
                "(`name`, `duration_in_seconds`, `url`, `artist_name`, `album_name`, `genre`) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        // declare an object for PreparedStatement to execute the query
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertStatement)) {
            // set the values for the parameters in the query
            preparedStatement.setString(1, song.getName());
            preparedStatement.setInt(2, song.getDurationInSeconds());
            preparedStatement.setString(3, song.getUrl());
            preparedStatement.setString(4, song.getArtistName());
            preparedStatement.setString(5, song.getAlbumName());
            preparedStatement.setString(6, song.getGenre());
            // execute the query
            return preparedStatement.executeUpdate() > 0;
        }
    }

    /**
     * This method is used to remove a song from the table in database.
     *
     * @param connection The connection to the database.
     * @param songId     The id of the song to be removed from the database.
     * @return true if the song is removed successfully, false otherwise.
     */
    public boolean deleteSongFromCatalog(Connection connection, int songId) throws SQLException {
        // declare a variable of string datatype to hold the delete query
        String deleteStatement = "DELETE FROM `jukebox`.`song` WHERE `id` = ?;";
        // declare an object for PreparedStatement to execute the query
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement)) {
            // set the values for the parameters in the query
            preparedStatement.setInt(1, songId);
            // execute the query
            return preparedStatement.executeUpdate() > 0;
        }
    }

    /**
     * This method is used to get all the songs as a list from the table in database.
     *
     * @param connection The connection to the database.
     * @return The list of songs.
     */
    public List<Song> getAllSongs(Connection connection) throws SQLException {
        List<Song> songsList = new ArrayList<>();
        // declare a variable of string datatype to hold the select query
        String selectStatement = "SELECT * FROM `jukebox`.`song`;";
        // declare an object for Statement to execute the query and store the result in a result set
        try (Statement statement = connection.createStatement()) {
            // execute the query
            ResultSet resultset = statement.executeQuery(selectStatement);
            // iterate through the result set and add the songs to the list
            while (resultset.next()) {
                Song song = new Song();
                song.setId(resultset.getInt("id"));
                song.setName(resultset.getString("name"));
                song.setDurationInSeconds(resultset.getInt("duration_in_seconds"));
                song.setUrl(resultset.getString("url"));
                song.setArtistName(resultset.getString("artist_name"));
                song.setAlbumName(resultset.getString("album_name"));
                song.setGenre(resultset.getString("genre"));
                // add the song to the list
                songsList.add(song);
            }
        }
        return songsList;
    }

    /**
     * This method is used to get all the songs of a particular artist as a list from the table in database.
     *
     * @param connection The connection to the database.
     * @param artistName The name of the artist.
     * @return The list of songs of the artist.
     */
    public List<Song> searchSongsByArtist(Connection connection, String artistName) throws SQLException {
        // Call the method getAllSongs() to get a list of songs
        List<Song> songList = getAllSongs(connection);
        // Filter the songs and add the song with the given artist name to list and return the list
        return songList.stream().filter(song -> song.getArtistName().equals(artistName)).collect(Collectors.toList());
    }

    /**
     * This method is used to get all the songs of a particular genre as a list from the table in database.
     *
     * @param connection The connection to the database.
     * @param genre      The genre of the song.
     * @return The list of songs of the genre.
     */
    public List<Song> searchSongsByGenre(Connection connection, String genre) throws SQLException {
        // Call the method getAllSongs() to get a list of songs
        List<Song> songList = getAllSongs(connection);
        // Filter the songs and add the song with the given genre name to list and return the list
        return songList.stream().filter(song -> song.getArtistName().equals(genre)).collect(Collectors.toList());
    }

    /**
     * This method is used to search for a specific song from the table in database
     *
     * @param connection The connection to the database.
     * @param songName   The name of the song.
     * @return The songs if found with that name, empty song object otherwise.
     */
    public Song searchSongsByName(Connection connection, String songName) throws SQLException {
        // Call the method getAllSongs() to get a list of songs
        List<Song> songList = getAllSongs(connection);
        return songList.stream().filter(song -> song.getName().equals(songName)).findFirst().orElse(new Song());
    }
}
