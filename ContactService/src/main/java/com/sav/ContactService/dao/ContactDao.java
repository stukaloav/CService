package com.sav.ContactService.dao;

import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Friendship;
import com.sav.ContactService.model.Hobby;
import com.sav.ContactService.model.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ContactDao {
    void addContact(Contact contact);
    void deleteContact(Contact contact);
    Set<Contact> getAllContacts();

    @Transactional
    Contact getContactById(long id);

    @Transactional
    void addHobbyToContact(Contact id, Hobby hobby);

    @Transactional
    Long getIdFromContact(Contact contact);

    @Transactional
    void addFriendship(Contact first, Contact second);

    @Transactional
    List<Friendship> getAllFriends();

    @Transactional
    Set<Contact> getFriendsFromContact(Contact contact);

    @Transactional
    Set<Contact> getAllContactsSamePlace(String placeTitle);

    @Transactional
    Set<Hobby> getHobbiesFromContact(Contact contact);

    @Transactional
    void sendMessage(Contact contact, String content,
                     Date messageDate);

}
