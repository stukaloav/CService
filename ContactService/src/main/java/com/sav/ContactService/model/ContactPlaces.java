package com.sav.ContactService.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CONTACT_PLACES")
public class ContactPlaces implements Serializable{
    @Id
    @Column(name = "CONTACT_ID")
    private long contactId;
    @Id
    @Column(name = "PLACE_ID")
    private long placeId;

    public long getContactId() {
        return contactId;
    }
    public void setContactId(long contactId) {
        this.contactId = contactId;
    }
    public long getPlaceId() {
        return placeId;
    }
    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "ContactPlaces{" +
                "contactId=" + contactId +
                ", placeId=" + placeId +
                '}';
    }
}
