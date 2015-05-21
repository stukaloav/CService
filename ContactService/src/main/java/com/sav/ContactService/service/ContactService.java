package com.sav.ContactService.service;

import com.sav.ContactService.model.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ContactService {
    //Methods that deal with ContactDao

    Set<Contact> getAllContactsSameHobby(Long hobbyId);
    Set<Contact> getAllContactsSamePlace(Long placeId);
    void createContact(String firstName, String lastName, Date birthDate);
    List<Contact> getAllContacts();
    void addHobbyToContact(Contact contact, Hobby hobby);
    Contact getContactById(long id);
    void addContact(Contact contact);
    void deleteContact(Contact contact);
    Set<Hobby> getHobbiesFromContact(Contact contact);
    void addFriendship(Contact first, Contact second);
    void removeFriendship(Contact first, Contact second);
    List<Friendship> getAllFriendPairs();
    Set<Contact> getFriendsFromContact(Contact contact);
    Set<Place> getPlacesFromContact(Contact contact);
    void addPlaceToContact(Contact contact, Place place);

    //Methods that deal with HobbyDao
    void addHobby(String title, String description);
    List<Hobby> getAllHobbies();
    Set<String> getAllHobbiesTitle();
    void addHobby(Hobby hobby);
    void deleteHobbyByTitle(String title);
    Hobby getHobbyById(Long Id);
    void removeHobbyFromContact(Contact contact, Hobby hobby);

    //Methods that deal with PlaceDao
    void addPlace(String title, double longitude, double latitude);
    void addPlace(Place place);
    List<Place> getAllPlaces();
    Place getPlaceById(long id);
    void removePlaceFromContact(Contact contact, Place place);

    //Methods that deal with MessageDao
    void storeMessage(Contact sender, Contact receiver, String content,
                      Date messageDate);
    List<Message> getConversation(Contact sender, Contact receiver);
    List<Message> getAllMessages();
    List<Message> getAllMessagesFromContact(Contact contact);
}
