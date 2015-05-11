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
    @Transactional
    public Set<Contact> getAllContactsSameHobby(Long hobbyId) {
        return contactDao.getAllContactsSameHobby(hobbyId);
    }

    @Override
    @Transactional
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
    @Transactional
    public Contact getContactById(long id){
        if(id < 0){
            throw new IllegalArgumentException("id should not be negative");
        }
        return contactDao.getContactById(id);
    }
    @Override
    @Transactional
    public void addContact(Contact contact){
        if(contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        contactDao.addContact(contact);
    }
    @Override
    @Transactional
    public void deleteContact(Contact contact){
        if(contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        contactDao.deleteContact(contact);
    }
    @Override
    public Set<Hobby> getHobbiesFromContact(Contact contact) {
        if(contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        return contactDao.getHobbiesFromContact(contact);
    }
    @Override
    @Transactional
    public void addHobbyToContact(Contact contact, Hobby hobby) {
        contactDao.addHobbyToContact(contact, hobby);
    }
    @Override
    @Transactional
    public void addFriendship(Contact first, Contact second){
        if(first == null || second == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        contactDao.addFriendship(first, second);
    }
    @Override
    @Transactional
    public void removeFriendship(Contact first, Contact second) {
        if(first == null || second == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        contactDao.removeFriendship(first, second);
    }
    @Override
    @Transactional
    public List<Friendship> getAllFriendPairs(){
        return contactDao.getAllFriends();
    }
    @Override
    @Transactional
    public Set<Contact> getFriendsFromContact(Contact contact){
        if(contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        return contactDao.getFriendsFromContact(contact);
    }
    @Override
    @Transactional
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
    @Transactional
    public void addHobby(String title, String description) {
        Hobby hobby = new Hobby();
        hobby.setTitle(title);
        hobby.setDescription(description);
        hobbyDao.addHobby(hobby);
    }
    @Override
    @Transactional
    public List<Hobby> getAllHobbies(){
        return hobbyDao.getAllHobbies();}
    @Override
    @Transactional
    public Set<String> getAllHobbiesTitle(){
        List<Hobby> hobbies = hobbyDao.getAllHobbies();
        if (hobbies.isEmpty()){
            return null;
        }
        Set<String> hobbyTitles = new HashSet<String>();
        for (Hobby hobby: hobbies){
            hobbyTitles.add(hobby.getTitle());
        }
        return hobbyTitles;
    }
    @Override
    @Transactional
    public void addHobby(Hobby hobby){
        hobbyDao.addHobby(hobby);
    }
    @Override
    @Transactional
    public void deleteHobbyByTitle(String title){
        hobbyDao.deleteHobbyByTitle(title);
    }
    @Override
    @Transactional
    public Hobby getHobbyById(Long hobbyId) {
        return hobbyDao.getHobbyById(hobbyId);
    }
    @Override
    @Transactional
    public Set<Contact> getAllContactsSamePlace(Long placeId) {
        return contactDao.getAllContactsSamePlace(placeId);
    }
    @Override
    @Transactional
    public void removeHobbyFromContact(Contact contact, Hobby hobby) {
        contactDao.removeHobbyFromContact(contact, hobby.getId());
    }

    //Methods that deal with PlaceDao
    @Override
    @Transactional
    public void addPlace(String title, double longitude, double latitude) {
        Place place = new Place();
        place.setTitle(title);
        place.setLongitude(longitude);
        place.setLatitude(latitude);
        placeDao.addPlace(place);
    }
    @Override
    @Transactional
    public void addPlace(Place place){
        placeDao.addPlace(place);
    }
    @Override
    @Transactional
    public List<Place> getAllPlaces() {
        return placeDao.getAllPlaces();
    }
    @Override
    @Transactional
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
    @Transactional
    public void storeMessage(Contact sender, Contact receiver, String content,
                             Date messageDate) {
        contactDao.sendMessage(sender, receiver, content, messageDate);
    }
    @Override
    @Transactional
    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }
    @Override
    @Transactional
    public List<Message> getConversation(Contact sender, Contact receiver) {
        return messageDao.getConversation(sender, receiver);
    }
    @Override
    @Transactional
    public List<Message> getAllMessagesFromContact(Contact contact) {
        return messageDao.getAllMessagesFromContact(contact);
    }

}
