package com.niit.jdp.model;

import java.util.Objects;

public class Song {
    private int id;
    private String name;
    private int durationInSeconds;
    private String url;
    private String artistName;
    private String albumName;
    private String genre;

    public Song() {
    }

    public Song(int id, String name, int durationInSeconds, String url, String artistName, String albumName, String genre) {
        this.id = id;
        this.name = name;
        this.durationInSeconds = durationInSeconds;
        this.url = url;
        this.artistName = artistName;
        this.albumName = albumName;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return getId() == song.getId() && getDurationInSeconds() == song.getDurationInSeconds() && Objects.equals(getName(), song.getName()) && Objects.equals(getUrl(), song.getUrl()) && Objects.equals(getArtistName(), song.getArtistName()) && Objects.equals(getAlbumName(), song.getAlbumName()) && Objects.equals(getGenre(), song.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDurationInSeconds(), getUrl(), getArtistName(), getAlbumName(), getGenre());
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", durationInSeconds=" + getDurationInSeconds() +
                ", url='" + getUrl() + '\'' +
                ", artistName='" + getArtistName() + '\'' +
                ", albumName='" + getAlbumName() + '\'' +
                ", genre='" + getGenre() + '\'' +
                '}';
    }
}
