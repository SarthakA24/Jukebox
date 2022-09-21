package com.niit.jdp.model;

import java.util.Objects;

public class Playlist {
    private String playlistName;
    private String songsId;

    public Playlist() {
    }

    public Playlist(String playlistName, String songsId) {
        this.playlistName = playlistName;
        this.songsId = songsId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getSongsId() {
        return songsId;
    }

    public void setSongsId(String songsId) {
        this.songsId = songsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(getPlaylistName(), playlist.getPlaylistName()) && Objects.equals(getSongsId(), playlist.getSongsId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlaylistName(), getSongsId());
    }

    @Override
    public String toString() {
        return "Playlist{" + "playlistName='" + getPlaylistName() + '\'' + ", songsList=" + getSongsId() + '}';
    }
}
