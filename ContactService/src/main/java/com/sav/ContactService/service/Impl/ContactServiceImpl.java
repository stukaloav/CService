package com.sav.ContactService.service.Impl;

import com.sav.ContactService.dao.ContactDao;
import com.sav.ContactService.dao.HobbyDao;
import com.sav.ContactService.dao.MessageDao;
import com.sav.ContactService.dao.PlaceDao;
import com.sav.ContactService.model.*;
import com.sav.ContactService.service.ContactService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;
    @Autowired
    private HobbyDao hobbyDao;
    @Autowired
    private PlaceDao placeDao;
    @Autowired
    private MessageDao messageDao;

    //Methods that deal with ContactDao
    @Override
    @Transactional(readOnly = true)
    public Set<Contact> getAllContactsSameHobby(Long hobbyId) {
        return contactDao.getAllContactsSameHobby(hobbyId);
    }

    @Override
    public void createContact(String firstName, String lastName, Date birthDate) {
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setBirthDate(birthDate);
        contactDao.addContact(contact);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    @Override
    @Transactional(readOnly = true)
    public Contact getContactById(long id){
        if(id < 0){
            throw new IllegalArgumentException("id should not be negative");
        }
        return contactDao.getContactById(id);
    }
    @Override
    public void addContact(Contact contact){
        if(contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        contactDao.addContact(contact);
    }
    @Override
    public void deleteContact(Contact contact){
        if(contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        contactDao.deleteContact(contact);
    }
    @Override
    @Transactional(readOnly = true)
    public Set<Hobby> getHobbiesFromContact(Contact contact) {
        if(contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        return contactDao.getHobbiesFromContact(contact);
    }
    @Override
    public void addHobbyToContact(Contact contact, Hobby hobby) {
        contactDao.addHobbyToContact(contact, hobby);
    }

    @Override
    public void addFriendship(Contact first, Contact second){
        if(first == null || second == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        contactDao.addFriendship(first, second);
    }

    @Override
    public void removeFriendship(Contact first, Contact second) {
        if(first == null || second == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        contactDao.removeFriendship(first, second);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Friendship> getAllFriendPairs(){
        return contactDao.getAllFriends();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Contact> getFriendsFromContact(Contact contact){
        if(contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        return contactDao.getFriendsFromContact(contact);
    }
    @Override
    @Transactional(readOnly = true)
    public Set<Place> getPlacesFromContact(Contact contact) {
        if(contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        return contactDao.getPlacesFromContact(contact);
    }

    @Override
    public void addPlaceToContact(Contact contact, Place place) {
        if(contact == null || place == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        contactDao.addPlaceToContact(contact, place);
    }

    //Methods that deal with HobbyDao
    @Override
    public void addHobby(String title, String description) {
        Hobby hobby = new Hobby();
        hobby.setTitle(title);
        hobby.setDescription(description);
        hobbyDao.addHobby(hobby);
    }

    @Override
    public List<Hobby> getAllHobbies(){
        return hobbyDao.getAllHobbies();}

    @Override
    @Transactional(readOnly = true)
    public Set<String> getAllHobbiesTitle(){
        List<Hobby> hobbies = hobbyDao.getAllHobbies();
        if (hobbies.isEmpty()){
            return null;
        }
        Set<String> hobbyTitles = new HashSet<>();
        for (Hobby hobby: hobbies){
            hobbyTitles.add(hobby.getTitle());
        }
        return hobbyTitles;
    }

    @Override
    public void addHobby(Hobby hobby){
        hobbyDao.addHobby(hobby);
    }

    @Override
    public void deleteHobbyByTitle(String title){
        hobbyDao.deleteHobbyByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public Hobby getHobbyById(Long hobbyId) {
        return hobbyDao.getHobbyById(hobbyId);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Contact> getAllContactsSamePlace(Long placeId) {
        return contactDao.getAllContactsSamePlace(placeId);
    }

    @Override
    public void removeHobbyFromContact(Contact contact, Hobby hobby) {
        contactDao.removeHobbyFromContact(contact, hobby.getId());
    }

    //Methods that deal with PlaceDao
    @Override
    public void addPlace(String title, double longitude, double latitude) {
        Place place = new Place();
        place.setTitle(title);
        place.setLongitude(longitude);
        place.setLatitude(latitude);
        placeDao.addPlace(place);
    }

    @Override
    public void addPlace(Place place){
        placeDao.addPlace(place);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Place> getAllPlaces() {
        return placeDao.getAllPlaces();
    }

    @Override
    @Transactional(readOnly = true)
    public Place getPlaceById(long id) {
        return placeDao.getPlaceById(id);
    }

    @Override
    public void removePlaceFromContact(Contact contact, Place place) {
        Long placeId = place.getId();
        placeDao.removePlaceFromContact(contact, placeId);
    }

    //Methods that deal with MessageDao
    @Override
    public void storeMessage(Contact sender, Contact receiver, String content,
                             Date messageDate) {
        contactDao.sendMessage(sender, receiver, content, messageDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getConversation(Contact sender, Contact receiver) {
        return messageDao.getConversation(sender, receiver);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAllMessagesFromContact(Contact contact) {
        return messageDao.getAllMessagesFromContact(contact);
    }

}
