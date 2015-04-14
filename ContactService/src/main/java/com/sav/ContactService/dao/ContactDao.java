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
    List<Contact> getAllContacts();
    void addHobbyToContact(Contact id, Hobby hobby);

    Contact getContactById(long id);

    void addFriendship(Contact first, Contact second);
    List<Friendship> getAllFriends();
    List<Contact> getFriendsFromContact(Contact contact);
    Set<Contact> getAllContactsSamePlace(String placeTitle);
    Set<Hobby> getHobbiesFromContact(Contact contact);
    void sendMessage(Contact sender, Contact receiver, String content,
                     Date messageDate);
}
