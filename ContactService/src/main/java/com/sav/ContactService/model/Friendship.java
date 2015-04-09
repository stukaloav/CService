package com.sav.ContactService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "FRIENDSHIP")
public class Friendship implements Serializable{
    @Id
    @Column(name = "FIRST_CONTACT_ID")
    private long firstContactId;
    @Id
    @Column(name = "SECOND_CONTACT_ID")
    private long secondContactId;

    public long getFirstContactId() {
        return firstContactId;
    }
    public void setFirstContactId(long firstContactId) {
        this.firstContactId = firstContactId;
    }
    public long getSecondContactId() {
        return secondContactId;
    }
    public void setSecondContactId(long secondContactId) {
        this.secondContactId = secondContactId;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "firstContactId=" + firstContactId +
                ", secondContactId=" + secondContactId +
                '}';
    }
}
