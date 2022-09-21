package com.niit.jdp.service;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongRepository;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
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
        System.out.println("Playing the Song" + song.getName());
        System.out.println("Song Details - ");
        System.out.println("Artist Name - " + song.getArtistName());
        System.out.println("Album Name - " + song.getAlbumName());
        System.out.println("Genre - " + song.getGenre());
        System.out.println("Song Duration - " + song.getDurationInSeconds() / 60 + " minutes" + song.getDurationInSeconds() % 60 + " seconds");
        System.out.println("===============================");
    }

    /**
     * This method is used to play songs from the playlist
     *
     * @param playlist The playlist from which the song is to be played.
     */
    public void playPlaylist(Connection connection, Playlist playlist) {
        try {
            // Split the songs id string from playlist and store it in an array pass the result to shufflePlaylist()
            // method to shuffle the array
            String[] shufflePlaylist = shufflePlaylist(playlist.getSongsId().split(","));
            SongRepository songRepository = new SongRepository();
            // Start a for each loop to iterate though the array and play the songs
            for (String songId : shufflePlaylist) {
                // Call the getSongById() to get the song object
                Song songById = songRepository.getSongById(connection, Integer.parseInt(songId));
                // pass the song object to playSong() method
                playSong(songById);
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
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
     * @param songsId Playlist to shuffle
     * @return shuffledSongsId The shuffled array of songs id playlist
     */
    public String[] shufflePlaylist(String[] songsId) {
        String[] shuffledSongsId = new String[songsId.length];
        List<String> shuffledSongsIdList = Arrays.asList(songsId);
        Collections.shuffle(shuffledSongsIdList);
        shuffledSongsIdList.toArray(shuffledSongsId);
        return shuffledSongsId;
    }
}
