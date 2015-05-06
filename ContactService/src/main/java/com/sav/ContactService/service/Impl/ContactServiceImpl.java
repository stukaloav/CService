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
        Hibernate.initialize(contactDao.getAllContacts());
        return contactDao.getAllContacts();
    }
    @Override
    @Transactional
    public Contact getContactById(long id){
        return contactDao.getContactById(id);
    }
    @Override
    @Transactional
    public void addContact(Contact contact){
        contactDao.addContact(contact);
    }
    @Override
    @Transactional
    public void deleteContact(Contact contact){
        contactDao.deleteContact(contact);
    }
    @Override
    public Set<Hobby> getHobbiesFromContact(Contact contact) {
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
        contactDao.addFriendship(first, second);
    }
    @Override
    @Transactional
    public void removeFriendship(Contact first, Contact second) {
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
        return contactDao.getFriendsFromContact(contact);
    }
    @Override
    @Transactional
    public Set<Place> getPlacesFromContact(Contact contact) {
        return contactDao.getPlacesFromContact(contact);
    }
    @Override
    public void addPlaceToContact(Contact contact, Place place) {
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
