package com.niit.jdp;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.service.DatabaseService;
import com.niit.jdp.service.JukeboxService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Create an object for the DatabaseService class
            DatabaseService databaseService = new DatabaseService();
            // Connect to the database
            databaseService.connect();
            // Print the connection status
            databaseService.printConnectionStatus();
            // Get the connection
            Connection connection = databaseService.getConnection();
            // declare the objects for the JukeboxService, SongRepository and PlaylistRepository classes
            JukeboxService jukeboxService = new JukeboxService();
            SongRepository songRepository = new SongRepository();
            PlaylistRepository playlistRepository = new PlaylistRepository();
            // initialise the scanner
            Scanner scanner = new Scanner(System.in);
            int choice = 0;
            do {
                // display the jukebox menu
                jukeboxService.displayMenu();
                choice = scanner.nextInt();
                if (choice == 1) {
                    List<Song> allSongs = songRepository.getAllSongs(connection);
                    songRepository.displayAllSongs(allSongs);
                } else if (choice == 2) {
                    System.out.println("Enter the playlist name - ");
                    scanner.nextLine();
                    String playlistName = scanner.nextLine();
                    boolean result = playlistRepository.createNewPlaylist(connection, playlistName);
                    if (result) {
                        System.out.println("Playlist Created Successfully!");
                    } else {
                        System.err.println("Playlist with the name " + playlistName + " already exists!");
                    }
                } else if (choice == 3) {
                    List<String> playlistsName = playlistRepository.getPlaylistsName(connection);
                    System.out.println("Your Playlists are -");
                    if (playlistsName.size() == 0) {
                        System.err.println("There are no playlists created!!");
                    } else {
                        for (int index = 0; index < playlistsName.size(); index++) {
                            System.out.println(index + 1 + ". " + playlistsName.get(index));
                        }
                        System.out.println("Please enter the playlist name that you need to play - ");
                        scanner.nextLine();
                        String selectedPlaylist = scanner.nextLine().toLowerCase();
                        if (playlistsName.contains(selectedPlaylist)) {
                            Playlist playlistByName = playlistRepository.getPlaylistByName(connection, selectedPlaylist);
                            jukeboxService.playPlaylist(connection, playlistByName);
                        } else {
                            System.err.println("Incorrect playlist name!!");
                        }
                    }
                }
            } while (choice != 5);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}