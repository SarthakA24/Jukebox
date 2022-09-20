package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.Connection;
import java.util.List;

public class SongRepository {
    /**
     * This method is used to add a new song to the table in database.
     *
     * @param connection The connection to the database.
     * @param song       The song to be added to the database.
     * @return true if the song is added successfully, false otherwise.
     */
    public boolean addSongToCatalog(Connection connection, Song song) {
        return false;
    }

    /**
     * This method is used to remove a song from the table in database.
     *
     * @param connection The connection to the database.
     * @param songId     The id of the song to be removed from the database.
     * @return true if the song is removed successfully, false otherwise.
     */
    public boolean deleteSongFromCatalog(Connection connection, int songId) {
        return false;
    }

    /**
     * This method is used to get all the songs as a list from the table in database.
     *
     * @param connection The connection to the database.
     * @return The list of songs.
     */
    public List<Song> getAllSongs(Connection connection) {
        return List.of();
    }

    /**
     * This method is used to get all the songs of a particular artist as a list from the table in database.
     *
     * @param connection The connection to the database.
     * @param artistName The name of the artist.
     * @return The list of songs of the artist.
     */
    public List<Song> searchSongsByArtist(Connection connection, String artistName) {
        return List.of();
    }

    /**
     * This method is used to get all the songs of a particular genre as a list from the table in database.
     *
     * @param connection The connection to the database.
     * @param genre      The genre of the song.
     * @return The list of songs of the genre.
     */
    public List<Song> searchSongsByGenre(Connection connection, String genre) {
        return List.of();
    }

    /**
     * This method is used to search for a specific song from the table in database
     *
     * @param connection The connection to the database.
     * @param songName   The name of the song.
     * @return The songs if found with that name, empty song object otherwise.
     */
    public Song searchSongsByName(Connection connection, String songName) {
        return new Song();
    }
}
