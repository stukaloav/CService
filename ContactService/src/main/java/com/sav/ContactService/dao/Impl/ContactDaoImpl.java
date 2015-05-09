package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.ContactDao;
import com.sav.ContactService.dao.HobbyDao;
import com.sav.ContactService.model.*;
import org.hibernate.Hibernate;
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
            sessionFactory.getCurrentSession().merge(contact);
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

        first.getFriends().add(second);
        sessionFactory.getCurrentSession().merge(first);
        sessionFactory.getCurrentSession().merge(second);
    }
    @Override
    public List<Friendship> getAllFriends(){
        return sessionFactory.getCurrentSession().
                createQuery("from Friendship").list();
    }
    @Override
    public Set<Contact> getFriendsFromContact(Contact contact){
        if (contact == null){
            throw new IllegalArgumentException("contact should not be null");
        }
        Query query = sessionFactory.getCurrentSession().
                createQuery("from Friendship f where " + "f.firstContactId = ? or f.secondContactId = ?");
        query.setParameter(0, contact.getId());
        query.setParameter(1, contact.getId());
        List<Friendship> friendshipList = query.list();
        Set<Long> friendsIdSet = new HashSet<>();
        for (Friendship friendship: friendshipList){
            if(friendship.getFirstContactId() == contact.getId()){
                friendsIdSet.add(friendship.getSecondContactId());
            }else {
                friendsIdSet.add(friendship.getFirstContactId());
            }
        }
        Set<Contact> friends = new HashSet<>();
        for (Long friendsId: friendsIdSet){
            friends.add(getContactById(friendsId));
        }
        return friends;
    }
    @Override
    public Set<Hobby> getHobbiesFromContact(Contact contact) {
        if (contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        return contact.getHobbies();
    }

    @Override
    public Set<Place> getPlacesFromContact(Contact contact) {
        if (contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        return contact.getPlaces();
    }

    @Override
    public void addPlaceToContact(Contact contact, Place place) {
        contact.getPlaces().add(place);
        sessionFactory.getCurrentSession().merge(contact);
    }

    @Override
    public Set<Contact> getAllContactsSamePlace(Long placeId){
        if (placeId == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        Set<Contact> contactSamePlace = new HashSet<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from ContactPlaces cp where cp.placeId = ?");
        query.setParameter(0, placeId);
        List<ContactPlaces> contactPlacesList = query.list();
        for (ContactPlaces contactPlaces: contactPlacesList){
            contactSamePlace.add(getContactById(contactPlaces.getContactId()));
        }
        return contactSamePlace;
    }

    @Override
    public Set<Contact> getAllContactsSameHobby(Long hobbyId) {
        if (hobbyId == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        Set<Contact> contactSameHobby = new HashSet<>();
        Query query = sessionFactory.getCurrentSession().createQuery("from ContactHobbies cp where cp.hobbyId = ?");
        query.setParameter(0, hobbyId);
        List<ContactHobbies> contactHobbiesList = query.list();
        for (ContactHobbies contactHobbies: contactHobbiesList){
            contactSameHobby.add(getContactById(contactHobbies.getContactId()));
        }
        return contactSameHobby;
    }

    @Override
    public void removeHobbyFromContact(Contact contact, Long hobbyId) {
        Hobby hobby = (Hobby)sessionFactory.getCurrentSession().
                createQuery("from Hobby h where h.id = ?").
                setParameter(0, hobbyId).uniqueResult();
        contact.getHobbies().remove(hobby);
        sessionFactory.getCurrentSession().merge(contact);
    }

    @Override
    public void sendMessage(Contact sender, Contact receiver,
                            String content, Date messageDate) {
        Message message = new Message(messageDate, sender, receiver, content);
        sessionFactory.getCurrentSession().saveOrUpdate(message);
    }

    @Override
    public void removeFriendship(Contact first, Contact second) {
        if (first.equals(second)){
            throw new IllegalArgumentException("first should not be equal to second");
        }
        if (first == null || second == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        first.getFriends().remove(second);
        sessionFactory.getCurrentSession().merge(first);
        sessionFactory.getCurrentSession().merge(second);
    }
}