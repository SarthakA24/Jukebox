package com.niit.jdp;

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
                    List<String> playlistsName = playlistRepository.getPlaylistsName(connection);
                    String[] playlistNames = new String[playlistsName.size()];
                    playlistsName.toArray(playlistNames);
                }
            } while (choice != 6);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}