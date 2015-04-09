package com.sav.ContactService.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CONTACT_HOBBIES")
public class ContactHobbies implements Serializable {
    @Id
    @Column(name = "CONTACT_ID")
    private long contactId;
    @Id
    @Column(name = "HOBBY_ID")
    private long hobbyId;

    public long getContactId() {
        return contactId;
    }
    public void setContactId(long contactId) {
        this.contactId = contactId;
    }
    public long getHobbyId() {
        return hobbyId;
    }
    public void setHobbyId(long hobbyId) {
        this.hobbyId = hobbyId;
    }

    @Override
    public String toString() {
        return "ContactHobbies{" +
                "contactId=" + contactId +
                ", hobbyId=" + hobbyId +
                '}';
    }
}
