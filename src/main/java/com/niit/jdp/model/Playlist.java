package com.niit.jdp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(getPlaylistName(), playlist.getPlaylistName()) && Objects.equals(getSongsList(), playlist.getSongsList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlaylistName(), getSongsList());
    }

    @Override
    public String toString() {
        return "Playlist{" + "playlistName='" + getPlaylistName() + '\'' + ", songsList=" + getSongsList() + '}';
    }
}
