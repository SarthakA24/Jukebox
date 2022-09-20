package com.niit.jdp.model;

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
}
