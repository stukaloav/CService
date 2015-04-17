package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.ContactDao;
import com.sav.ContactService.dao.HobbyDao;
import com.sav.ContactService.model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ContactDaoImpl implements ContactDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addContact(Contact contact) {
        if (contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        sessionFactory.getCurrentSession().
                saveOrUpdate(contact);
    }
    @Override
    public void deleteContact(Contact contact) {
        if (contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
            sessionFactory.getCurrentSession().delete(contact);

    }
    @Override
    public List<Contact> getAllContacts() {
        return sessionFactory.getCurrentSession().
                createQuery("from Contact").list();
    }
    @Override
    public void addHobbyToContact(Contact contact, Hobby hobby){
        contact.getHobbies().add(hobby);
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
    }
    @Override
    public Contact getContactById(long id){
        return (Contact) sessionFactory.getCurrentSession().
                createQuery("from Contact c where c.id=:id").
                setParameter("id", id).uniqueResult();
    }
    @Override
    public void addFriendship(Contact first, Contact second){
        if (first.equals(second)){
            throw new IllegalArgumentException("first should not be equal to second");
        }
        if (first == null || second == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        sessionFactory.getCurrentSession().saveOrUpdate(first);
        sessionFactory.getCurrentSession().saveOrUpdate(second);
        first.getFriends().add(second);
    }
    @Override
    public List<Friendship> getAllFriends(){
        return sessionFactory.getCurrentSession().
                createQuery("from Friendship").list();
    }
    @Override
    public List<Contact> getFriendsFromContact(Contact contact){
        if (contact == null){
            throw new IllegalArgumentException("contact should not be null");
        }
        Query query = sessionFactory.getCurrentSession().
                createQuery("from Friends f where " +
                        "f.firstFriend = ? or f.secondFriend = ?");
        query.setParameter(0, contact);
        query.setParameter(1, contact);
        return query.list();
    }
    @Override
    public List<Hobby> getHobbiesFromContact(Contact contact) {
        if (contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        return contact.getHobbies();
    }
    @Override
    public List<Contact> getAllContactsSamePlace(String placeTitle){
        if (placeTitle == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        List<ContactPlaces> contactPlacesesList =
                sessionFactory.getCurrentSession().
                        createQuery("from ContactPlaces").list();
        if (contactPlacesesList.isEmpty()){
            return null;
        }
        List<Contact> contactsSamePlace = new ArrayList<>();
        for (ContactPlaces contactPlaces: contactPlacesesList){
            long contactId = contactPlaces.getContactId();
            Contact contact = getContactById(contactId);
            contactsSamePlace.add(contact);
        }
        return contactsSamePlace;
    }
    @Override
    public void sendMessage(Contact sender, Contact receiver,
                            String content, Date messageDate) {
        Message message = new Message(messageDate, sender, receiver, content);
        sessionFactory.getCurrentSession().saveOrUpdate(message);
    }

}