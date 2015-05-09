package com.sav.ContactService.dao;

import com.sav.ContactService.model.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ContactDao {
    void addContact(Contact contact);
    void deleteContact(Contact contact);
    List<Contact> getAllContacts();
    void addHobbyToContact(Contact id, Hobby hobby);
    Contact getContactById(long id);
    void addFriendship(Contact first, Contact second);
    void removeFriendship(Contact first, Contact second);
    List<Friendship> getAllFriends();
    Set<Contact> getFriendsFromContact(Contact contact);
    Set<Contact> getAllContactsSamePlace(Long placeId);
    Set<Hobby> getHobbiesFromContact(Contact contact);
    void removeHobbyFromContact(Contact contact, Long hobbyId);
    Set<Place> getPlacesFromContact(Contact contact);
    void addPlaceToContact(Contact contact, Place place);
    void sendMessage(Contact sender, Contact receiver, String content,
                     Date messageDate);
    Set<Contact> getAllContactsSameHobby(Long hobbyId);
}
