package com.sav.ContactService.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "PLACE")
public class Place {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "LATITUDE")
    private double latitude;

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
        return "Place{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        return id == place.id;
    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
