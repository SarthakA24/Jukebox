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
}
