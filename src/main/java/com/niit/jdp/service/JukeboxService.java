package com.niit.jdp.service;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongRepository;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class JukeboxService {
    private long microsecondPosition;
    private static final String PRINT_LINES = "===============================";
    /**
     * This method is used to display details of a particular song
     *
     * @param song The song whose details are to be displayed.
     */
    public void displaySongDetails(Song song) {
        System.out.println(PRINT_LINES);
        System.out.println("Playing the Song - " + song.getName());
        System.out.println("Song Details - ");
        System.out.println("Artist Name - " + song.getArtistName());
        System.out.println("Album Name - " + song.getAlbumName());
        System.out.println("Genre - " + song.getGenre());
        System.out.println("Song Duration - " + song.getDurationInSeconds() / 60 + " minutes " + song.getDurationInSeconds() % 60 + " seconds");
        System.out.println(PRINT_LINES);
    }

    /**
     * This method is used to play songs from the playlist
     *
     * @param playlist The playlist from which the song is to be played.
     */
    public void playPlaylist(Playlist playlist) {
        List<Song> songList = playlist.getSongList();
        System.out.println("Songs in the Playlist - " + playlist.getPlaylistName());
        new SongRepository().displayAllSongs(songList);
        // Call the shuffledSongsList() method to shuffle the songs list
        List<Song> shuffledSongsList = shufflePlaylist(songList);
        System.out.println("Shuffled Playlist - " + playlist.getPlaylistName());
        new SongRepository().displayAllSongs(songList);
        // Display the songs in the playlist
        // Start a for each loop to iterate though the array and play the songs
        for (Song song : shuffledSongsList) {
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
            boolean isSongPlaying = true;
            int choice;
            do {
                displayPlayerMenu();
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if (choice == 1) {
                    isSongPlaying = pauseResumeSong(clip, isSongPlaying);
                } else {
                    clip.stop();
                    clip.close();
                }
            } while (choice != 2);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public boolean pauseResumeSong(Clip clip, boolean isSongPlaying) {
        if (isSongPlaying) {
            this.microsecondPosition = clip.getMicrosecondPosition();
            clip.stop();
            System.out.println("Song Paused!");
            return false;
        } else {
            clip.setMicrosecondPosition(this.microsecondPosition);
            clip.start();
            System.out.println("Song Resumed!");
            return true;
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

    void displayPlayerMenu() {
        System.out.println("Press 1 to Resume/Pause the song");
        System.out.println("Press 2 to go to next song");
    }

    /**
     * This method displays the user menu for the Jukebox
     */
    public void displayMenu() {
        System.out.println(PRINT_LINES);
        System.out.println("WELCOME TO THE JUKEBOX");
        System.out.println(PRINT_LINES);
        System.out.println("Please select from the options below - ");
        System.out.println("1. Display all Songs in the Jukebox.");
        System.out.println("2. Create a Playlist.");
        System.out.println("3. Play your playlists.");
        System.out.println("4. Add songs to a playlist.");
        System.out.println("5. Remove songs to a playlist.");
        System.out.println("6. Edit Playlist name.");
        System.out.println("7. View songs by Genre.");
        System.out.println("8. View songs by Artist.");
        System.out.println("9. Exit the Jukebox.");
    }
}
