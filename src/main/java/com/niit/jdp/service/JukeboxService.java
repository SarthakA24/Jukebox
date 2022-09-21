package com.niit.jdp.service;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JukeboxService {
    /**
     * This method is used to display all the songs from the provided list of songs
     *
     * @param songsList The list of songs to be displayed.
     */
    public void displayAllSongs(List<Song> songsList) {
    }

    /**
     * This method is used to display details of a particular song
     *
     * @param song The song whose details are to be displayed.
     */
    public void displaySongDetails(Song song) {

    }

    /**
     * This method is used to play songs from the playlist
     *
     * @param playlist The playlist from which the song is to be played.
     */
    public void playPlaylist(Playlist playlist) {

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
     * This method is used to shuffle the playlist and play the songs of the playlist in a random order
     */
    public void shufflePlaylist() {

    }
}
