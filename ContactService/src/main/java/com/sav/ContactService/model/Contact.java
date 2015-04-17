package com.sav.ContactService.model;


import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.MappingException;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "CONTACT")
public class Contact implements Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="BIRTH_DATE")
    private Date birthDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="CONTACT_HOBBIES"
            , joinColumns={@JoinColumn(name = "CONTACT_ID")}
            , inverseJoinColumns={@JoinColumn(name = "HOBBY_ID")})
    private List<Hobby> hobbies;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "FRIENDSHIP"
            , joinColumns = {@JoinColumn(name = "FIRST_CONTACT_ID")}
            , inverseJoinColumns = {@JoinColumn(name = "SECOND_CONTACT_ID")})
    private List<Contact> friends;

    @ManyToMany(mappedBy = "friends")
    private List<Contact> inverseFriends;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Message> receivedMessages;

    public Contact() {
    }
    public Contact(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public List<Hobby> getHobbies() {
        if (hobbies == null){
            hobbies = new ArrayList<>();
        }
        return hobbies;
    }
    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
    public List<Contact> getFriends() {
        return friends;
    }
    public void setFriends(List<Contact> friends) {
        this.friends = friends;
    }
    public List<Contact> getInverseFriends() {
        return inverseFriends;
    }
    public void setInverseFriends(List<Contact> inverseFriends) {
        this.inverseFriends = inverseFriends;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public List<Message> getSentMessages() {
        return sentMessages;
    }
    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }
    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }
    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return id == contact.id;
    }
    @Override
    public int hashCode() {
        return  (int) (id ^ (id >>> 32));
    }
}
