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
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try {
            // Create an object for the DatabaseService class
            DatabaseService databaseService = new DatabaseService();
            // Connect to the database
            boolean isDatabaseConnected = databaseService.connect();
            if (isDatabaseConnected) {
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
                int choice;
                do {
                    // display the jukebox menu
                    jukeboxService.displayMenu();
                    choice = scanner.nextInt();
                    List<Song> allSongs = songRepository.getAllSongs(connection);
                    if (choice == 1) {
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
                            IntStream.range(0, playlistsName.size()).mapToObj(index -> index + 1 + ". " + playlistsName.get(index)).forEach(System.out::println);
                            System.out.println("Please enter the playlist name that you need to play - ");
                            scanner.nextLine();
                            String selectedPlaylist = scanner.nextLine().toLowerCase();
                            if (playlistsName.contains(selectedPlaylist)) {
                                Playlist playlistByName = playlistRepository.getPlaylistByName(connection, selectedPlaylist);
                                jukeboxService.playPlaylist(playlistByName);
                            } else {
                                System.err.println("Incorrect playlist name!!");
                            }
                        }
                    } else if (choice == 4) {
                        System.out.println("Enter the playlist name - ");
                        scanner.nextLine();
                        String playlistName = scanner.nextLine();
                        List<String> playlistsName = playlistRepository.getPlaylistsName(connection);
                        if (playlistsName.contains(playlistName)) {
                            System.out.println("Songs in the Jukebox - ");
                            songRepository.displayAllSongs(allSongs);
                            System.out.println("Enter the song name to add to the playlist - ");
                            String songName = scanner.nextLine();
                            Song song = songRepository.searchSongsByName(allSongs, songName);
                            boolean result = playlistRepository.addSongToPlaylist(connection, song, playlistName);
                            if (result) {
                                System.out.println("Song added to the playlist successfully!");
                            } else {
                                System.err.println("Incorrect song name!");
                            }
                        } else {
                            System.err.println("Playlist with the name " + playlistName + " does not exist!");
                        }
                    } else if (choice == 5) {
                        System.out.println("Enter the playlist name - ");
                        scanner.nextLine();
                        String playlistName = scanner.nextLine();
                        List<String> playlistsName = playlistRepository.getPlaylistsName(connection);
                        if (playlistsName.contains(playlistName)) {
                            Playlist playlistByName = playlistRepository.getPlaylistByName(connection, playlistName);
                            playlistRepository.displayPlaylist(playlistByName);
                            System.out.println("Enter the name of the song to remove - ");
                            String songName = scanner.nextLine();
                            Song songToRemove = songRepository.searchSongsByName(allSongs, songName);
                            boolean result = playlistRepository.removeSongFromPlaylist(connection, songToRemove, playlistName);
                            if (result) {
                                System.out.println("Song removed from the playlist successfully!");
                            } else {
                                System.err.println("Incorrect song name!");
                            }
                        } else {
                            System.err.println("Playlist with the name " + playlistName + " does not exist!");
                        }
                    } else if (choice == 6) {
                        System.out.println("Enter the playlist name you want to edit - ");
                        scanner.nextLine();
                        String oldPlaylistName = scanner.nextLine();
                        List<String> playlistsName = playlistRepository.getPlaylistsName(connection);
                        if (playlistsName.contains(oldPlaylistName)) {
                            System.out.println("Enter the new playlist name - ");
                            String newPlaylistName = scanner.nextLine();
                            boolean result = playlistRepository.editPlaylistName(connection, oldPlaylistName, newPlaylistName);
                            if (result) {
                                System.out.println("Playlist name updated successfully!");
                            }
                        } else {
                            System.err.println("Playlist with the name " + oldPlaylistName + " does not exist!");
                        }
                    } else if (choice == 7) {
                        System.out.println("Enter the Genre - ");
                        scanner.nextLine();
                        String genreName = scanner.nextLine();
                        List<Song> searchedSongsByGenre = songRepository.searchSongsByGenre(allSongs, genreName);
                        if (searchedSongsByGenre.size() != 0) {
                            songRepository.displayAllSongs(searchedSongsByGenre);
                        } else {
                            System.err.println("No songs found with the genre " + genreName);
                        }
                    } else if (choice == 8) {
                        System.out.println("Enter the Artist Name - ");
                        scanner.nextLine();
                        String artistName = scanner.nextLine();
                        List<Song> searchedSongsByArtist = songRepository.searchSongsByArtist(allSongs, artistName);
                        if (searchedSongsByArtist.size() != 0) {
                            songRepository.displayAllSongs(searchedSongsByArtist);
                        } else {
                            System.err.println("No songs found with the genre " + artistName);
                        }
                    }
                } while (choice != 9);
            } else {
                System.err.println("!!Database Not Connected!!");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}