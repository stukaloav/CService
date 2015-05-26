package com.sav.ContactService.dto;

public class PlaceDTO {

    private long id;

    private String title;

    private double longitude;

    private double latitude;

    public PlaceDTO(long id, String title, double longitude, double latitude) {
        this.id = id;
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public PlaceDTO(String title, double longitude, double latitude) {
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "PlaceDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
