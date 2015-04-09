package com.sav.ContactService.service;

import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Friendship;
import com.sav.ContactService.model.Hobby;
import com.sav.ContactService.model.Place;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ContactService {
    //Methods that deal with ContactDao
    @Transactional
    void createContact(String firstName, String lastName, Date birthDate);
    @Transactional
    Set<Contact> getAllContacts();
    @Transactional
    void addHobbyToContact(Contact contact, Hobby hobby);
    @Transactional
    Contact getContactById(long id);
    @Transactional
    void addContact(Contact contact);
    @Transactional
    void deleteContact(Contact contact);
    @Transactional
    Long getIdFromContact(Contact contact);
    Set<Hobby> getHobbiesFromContact(Contact contact);
    @Transactional
    void addFriendship(Contact first, Contact second);
    @Transactional
    List<Friendship> getAllFriendPairs();
    @Transactional
    Set<Contact> getFriendsContacts(Contact contact);

    //Methods that deal with HobbyDao
    @Transactional
    void addHobby(String title, String description);
    @Transactional
    List<Hobby> getAllHobbies();
    @Transactional
    Set<String> getAllHobbiesTitle();
    @Transactional
    void addHobby(Hobby hobby);
    @Transactional
    void deleteHobbyByTitle(String title);

    //Methods that deal with PlaceDao
    @Transactional
    void addPlace(String title, double longitude, double latitude);
    @Transactional
    void addPlace(Place place);
    @Transactional
    List<Place> getAllPlaces();
}
