package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongRepository implements Repository<Song> {
    /**
     * This method is used to get all the songs as a list from the table in database.
     *
     * @param connection The connection to the database.
     * @return The list of songs.
     */
    @Override
    // This method is used to get all the songs as a list from the table in database.
    public List<Song> getAll(Connection connection) throws SQLException {
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
     * This method finds the song by the song id in the songs list
     *
     * @param songList The list of songs that you want to search through
     * @param songId   The id of the song you want to get
     * @return The song object found by the song id, empty song object if not found
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
     * @return The list of song found by the artist name, empty list if not found
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
     * @return The list of song found by the genre, empty list if not found
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
     * @return The song object if found with that name, empty song object if not found.
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
        System.out.println("+-------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.format("| %1$-45s| %2$-25s| %3$-20s| %4$-25s| %5$-15s|", "Song", "Duration (in Seconds)", "Artist Name", "Album Name", "Genre");
        System.out.println("\n+-------------------------------------------------------------------------------------------------------------------------------------------+");
        songsList.forEach(System.out::println);
        System.out.println("+-------------------------------------------------------------------------------------------------------------------------------------------+");
    }
}
