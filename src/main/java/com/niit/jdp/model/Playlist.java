package com.niit.jdp.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String playlistName;
    private List<Song> songsList;

    public Playlist() {
        this.songsList = new ArrayList<>();
    }

    public Playlist(String playlistName, List<Song> songsList) {
        this.playlistName = playlistName;
        this.songsList = songsList;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Song> getSongsList() {
        return songsList;
    }

    public void setSongsList(List<Song> songsList) {
        this.songsList = songsList;
    }
}
