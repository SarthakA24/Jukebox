package com.niit.jdp;

import com.niit.jdp.exception.IncorrectSongNameException;
import com.niit.jdp.exception.PlaylistAlreadyExistsException;
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
        int choice = 0;
        do {
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
                    // display the jukebox menu
                    jukeboxService.displayMenu();
                    // take the choice input from the user
                    choice = scanner.nextInt();
                    // get all the songs list and the playlists names from the database
                    List<Song> allSongs = songRepository.getAll(connection);
                    List<String> playlistsName = playlistRepository.getAll(connection);
                    // start switch cases
                    switch (choice) {
                        case 1:
                            songRepository.displayAllSongs(allSongs);
                            break;
                        case 2:
                            songRepository.displayAllSongs(allSongs);
                            System.out.println("Enter the song name to play - ");
                            scanner.nextLine();
                            String songNameToPlay = scanner.nextLine();
                            Song songToPlay = songRepository.searchSongsByName(allSongs, songNameToPlay);
                            jukeboxService.playSong(songToPlay);
                            break;
                        case 3:
                            jukeboxService.playPlaylist(new Playlist(1, "Play All Songs", allSongs));
                            break;
                        case 4:
                            System.out.println("Enter the playlist name you want to create - ");
                            scanner.nextLine();
                            String playlistName = scanner.nextLine();
                            playlistRepository.createNewPlaylist(connection, playlistName);
                            break;
                        case 5:
                            System.out.println("Your Playlists are -");
                            playlistsName.forEach(System.out::println);
                            System.out.println("Enter the playlist name in which song is to be added - ");
                            scanner.nextLine();
                            String addSongToPlaylistName = scanner.nextLine();
                            if (playlistsName.contains(addSongToPlaylistName)) {
                                System.out.println("Songs in the Jukebox - ");
                                songRepository.displayAllSongs(allSongs);
                                System.out.println("Enter the song name to add to the playlist - ");
                                String songName = scanner.nextLine();
                                Song song = songRepository.searchSongsByName(allSongs, songName);
                                playlistRepository.addSongToPlaylist(connection, song, addSongToPlaylistName);
                            } else {
                                System.err.println("Playlist does not exist!");
                            }
                            break;
                        case 6:
                            System.out.println("Your Playlists are -");
                            playlistsName.forEach(System.out::println);
                            System.out.println("Please enter the playlist name that you need to play - ");
                            scanner.nextLine();
                            String selectedPlaylist = scanner.nextLine().toLowerCase();
                            Playlist playlistByName = playlistRepository.getPlaylistByName(connection, selectedPlaylist);
                            jukeboxService.playPlaylist(playlistByName);
                            break;
                        case 7:
                            System.out.println("Enter the playlist name - ");
                            scanner.nextLine();
                            String removeSongFromPlaylistName = scanner.nextLine();
                            Playlist playlist = playlistRepository.getPlaylistByName(connection, removeSongFromPlaylistName);
                            playlistRepository.displayPlaylist(playlist);
                            System.out.println("Enter the name of the song to remove - ");
                            String songName = scanner.nextLine();
                            Song songToRemove = songRepository.searchSongsByName(allSongs, songName);
                            playlistRepository.removeSongFromPlaylist(connection, songToRemove, removeSongFromPlaylistName);
                            break;
                        case 8:
                            System.out.println("Enter the playlist name you want to edit - ");
                            scanner.nextLine();
                            String oldPlaylistName = scanner.nextLine();
                            System.out.println("Enter the new playlist name - ");
                            String newPlaylistName = scanner.nextLine();
                            playlistRepository.editPlaylistName(connection, oldPlaylistName, newPlaylistName);
                            break;
                        case 9:
                            System.out.println("Enter the Genre - ");
                            scanner.nextLine();
                            String genreName = scanner.nextLine();
                            List<Song> searchedSongsByGenre = songRepository.searchSongsByGenre(allSongs, genreName);
                            songRepository.displayAllSongs(searchedSongsByGenre);
                            break;
                        case 10:
                            System.out.println("Enter the Artist Name - ");
                            scanner.nextLine();
                            String artistName = scanner.nextLine();
                            List<Song> searchedSongsByArtist = songRepository.searchSongsByArtist(allSongs, artistName);
                            songRepository.displayAllSongs(searchedSongsByArtist);
                            break;
                        case 11:
                            System.out.println("Playlists are -");
                            playlistsName.forEach(System.out::println);
                            System.out.println("Enter the playlist name - ");
                            scanner.nextLine();
                            String playlistToView = scanner.nextLine();
                            Playlist playlistToDisplay = playlistRepository.getPlaylistByName(connection, playlistToView);
                            playlistRepository.displayPlaylist(playlistToDisplay);
                            break;
                        case 12:
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                } else {
                    System.err.println("!!Database Not Connected!!");
                }
            } catch (PlaylistAlreadyExistsException | IncorrectSongNameException | SQLException exception) {
                System.err.println(exception.getMessage());
                exception.printStackTrace();
                choice = 0;
            }
        } while (choice != 11);
    }
}