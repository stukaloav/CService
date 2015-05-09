package com.sav.ContactService.service;

import com.sav.ContactService.model.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ContactService {
    //Methods that deal with ContactDao

    @Transactional
    Set<Contact> getAllContactsSameHobby(Long hobbyId);
    @Transactional
    Set<Contact> getAllContactsSamePlace(Long placeId);
    @Transactional
    void createContact(String firstName, String lastName, Date birthDate);
    @Transactional
    List<Contact> getAllContacts();
    @Transactional
    void addHobbyToContact(Contact contact, Hobby hobby);
    @Transactional
    Contact getContactById(long id);
    @Transactional
    void addContact(Contact contact);
    @Transactional
    void deleteContact(Contact contact);
    @Transactional
    Set<Hobby> getHobbiesFromContact(Contact contact);
    @Transactional
    void addFriendship(Contact first, Contact second);
    @Transactional
    void removeFriendship(Contact first, Contact second);
    @Transactional
    List<Friendship> getAllFriendPairs();
    @Transactional
    Set<Contact> getFriendsFromContact(Contact contact);
    @Transactional
    Set<Place> getPlacesFromContact(Contact contact);
    @Transactional
    void addPlaceToContact(Contact contact, Place place);

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
    @Transactional
    Hobby getHobbyById(Long Id);
    @Transactional
    void removeHobbyFromContact(Contact contact, Hobby hobby);

    //Methods that deal with PlaceDao
    @Transactional
    void addPlace(String title, double longitude, double latitude);
    @Transactional
    void addPlace(Place place);
    @Transactional
    List<Place> getAllPlaces();
    @Transactional
    Place getPlaceById(long id);
    @Transactional
    void removePlaceFromContact(Contact contact, Place place);

    //Methods that deal with MessageDao
    @Transactional
    void storeMessage(Contact sender, Contact receiver, String content,
                      Date messageDate);
    @Transactional
    List<Message> getConversation(Contact sender, Contact receiver);
    @Transactional
    List<Message> getAllMessages();
    @Transactional
    List<Message> getAllMessagesFromContact(Contact contact);
}
