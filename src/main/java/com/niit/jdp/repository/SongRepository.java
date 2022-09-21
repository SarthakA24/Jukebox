package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongRepository {
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
     * This method returns the song object based on the song id from the database
     *
     * @param songList The list of songs from where to search
     * @param songId   The song id to find
     * @return The song object found based on the id
     */
    public Song getSongById(List<Song> songList, int songId) {
        // Return the song object based on the song id
        return songList.stream().filter(song -> song.getId() == songId).findFirst().orElse(new Song());
    }

    /**
     * This method is used to get all the songs of a particular artist as a list from the table in database.
     *
     * @param songList   The list of songs from where to search
     * @param artistName The name of the artist.
     * @return The list of songs of the artist.
     */
    public List<Song> searchSongsByArtist(List<Song> songList, String artistName) {
        // Filter the songs and add the song with the given artist name to list and return the list
        return songList.stream().filter(song -> song.getArtistName().equalsIgnoreCase(artistName)).collect(Collectors.toList());
    }

    /**
     * This method is used to get all the songs of a particular genre as a list from the table in database.
     *
     * @param songList The list of songs from where to search
     * @param genre    The genre of the song.
     * @return The list of songs of the genre.
     */
    public List<Song> searchSongsByGenre(List<Song> songList, String genre) {
        // Filter the songs and add the song with the given genre name to list and return the list
        return songList.stream().filter(song -> song.getArtistName().equalsIgnoreCase(genre)).collect(Collectors.toList());
    }

    /**
     * This method is used to search for a specific song from the table in database
     *
     * @param songList The list of songs from where to search
     * @param songName The name of the song.
     * @return The songs if found with that name, empty song object otherwise.
     */
    public Song searchSongsByName(List<Song> songList, String songName) {
        return songList.stream().filter(song -> song.getName().equalsIgnoreCase(songName)).findFirst().orElse(new Song());
    }

    /**
     * This method is used to display all the songs from the provided list of songs
     *
     * @param songsList The list of songs to be displayed.
     */
    public void displayAllSongs(List<Song> songsList) {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.format("%1s | %31s | %5s | %15s | %20s", "| Song Name", "Song Duration (in Seconds)", "Artist Name", "Album Name", "Genre |\n");
        System.out.println("---------------------------------------------------------------------------------------------");
        songsList.forEach(System.out::println);
    }
}
