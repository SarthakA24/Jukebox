package com.niit.jdp.service;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class JukeboxService {
    /**
     * This method is used to display details of a particular song
     *
     * @param song The song whose details are to be displayed.
     */
    public void displaySongDetails(Song song) {
        System.out.println("===============================");
        System.out.println("Playing the Song - " + song.getName());
        System.out.println("Song Details - ");
        System.out.println("Artist Name - " + song.getArtistName());
        System.out.println("Album Name - " + song.getAlbumName());
        System.out.println("Genre - " + song.getGenre());
        System.out.println("Song Duration - " + song.getDurationInSeconds() / 60 + " minutes " + song.getDurationInSeconds() % 60 + " seconds");
        System.out.println("===============================");
    }

    /**
     * This method is used to play songs from the playlist
     *
     * @param playlist The playlist from which the song is to be played.
     */
    public void playPlaylist(Connection connection, Playlist playlist) {
        // Call the shufflePlaylist() method to shuffle the songs list
        List<Song> shufflePlaylist = shufflePlaylist(playlist.getSongList());
        // Start a for each loop to iterate though the array and play the songs
        for (Song song : shufflePlaylist) {
            // pass the song object to playSong() method
            playSong(song);
        }
    }

    /**
     * This method is used to play a song
     *
     * @param song The song object that needs to be played
     */
    public void playSong(Song song) {
        // Create a file object that contains the song path
        File songFile = new File(song.getUrl());
        try {
            // Create an object for AudioInputStream class
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(songFile);
            // Get a clip object form audio system
            Clip clip = AudioSystem.getClip();
            // use the clip object to open the audio input stream
            clip.open(audioInputStream);
            // set a loop for the sound file
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            // Display the song details by calling displaySongDetails() method
            displaySongDetails(song);
            // start the sound file
            clip.start();
            // pause the current thread for the time the song is being played
            Thread.sleep(clip.getMicrosecondLength() / 1000L);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Song thread was interrupted");
        }
    }

    /**
     * This method is used to shuffle the given playlist by songs id and return the shuffled songs id
     *
     * @param songList List of song objects to shuffle
     * @return shuffledSongsId The shuffled array of songs id playlist
     */
    public List<Song> shufflePlaylist(List<Song> songList) {
        Collections.shuffle(songList);
        return songList;
    }

    /**
     * This method displays the user menu for the Jukebox
     */
    public void displayMenu() {
        System.out.println("===============================");
        System.out.println("WELCOME TO THE JUKEBOX");
        System.out.println("===============================");
        System.out.println("Please select from the options below - ");
        System.out.println("1. Display all Songs in the Jukebox.");
        System.out.println("2. Create a Playlist.");
        System.out.println("3. Play your playlists.");
        System.out.println("4. Add songs to a playlist.");
        System.out.println("5. Play songs from a playlist.");
        System.out.println("6. Exit the Jukebox.");
    }
}
