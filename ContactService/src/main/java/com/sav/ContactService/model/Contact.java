package com.sav.ContactService.model;


import javax.persistence.*;
import java.io.Serializable;
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
    private Set<Hobby> hobbies;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "FRIENDSHIP"
            , joinColumns = {@JoinColumn(name = "FIRST_CONTACT_ID")}
            , inverseJoinColumns = {@JoinColumn(name = "SECOND_CONTACT_ID")})
    private Set<Contact> friends;

    @ManyToMany(mappedBy = "friends")
    private Set<Contact> inverseFriends;

    public Contact() {
    }

    public Contact(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Set<Hobby> getHobbies() {
        if (hobbies == null){
            hobbies = new HashSet<Hobby>();
        }
        return hobbies;
    }
    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
    public Set<Contact> getFriends() {
        return friends;
    }
    public void setFriends(Set<Contact> friends) {
        this.friends = friends;
    }
    public Set<Contact> getInverseFriends() {
        return inverseFriends;
    }
    public void setInverseFriends(Set<Contact> inverseFriends) {
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

        if (id != contact.id) return false;
        if (birthDate != null ? !birthDate.equals(contact.birthDate) : contact.birthDate != null) return false;
        if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return  (int) (id ^ (id >>> 32));
    }
}
