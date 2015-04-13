package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.ContactDao;
import com.sav.ContactService.dao.HobbyDao;
import com.sav.ContactService.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ContactDaoImpl implements ContactDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private HobbyDao hobbyDao;
    @Override
    @Transactional
    public void addContact(Contact contact) {
        if (contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        sessionFactory.getCurrentSession().
                saveOrUpdate(contact);
    }
    @Override
    @Transactional
    public void deleteContact(Contact contact) {
        if (contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
            sessionFactory.getCurrentSession().delete(contact);

    }
    @Override
    @Transactional(readOnly = true)
    public Set<Contact> getAllContacts() {
        List<Contact> contacts = sessionFactory.getCurrentSession().
                createQuery("from Contact").list();
        Set<Contact> contactSet = new HashSet<Contact>();
        for (Contact contact: contacts){
            contactSet.add(contact);
        }
        return contactSet;

    }
    @Override
    @Transactional
    public Contact getContactById(long id){
            Contact contact = (Contact) sessionFactory.
                    getCurrentSession().get(Contact.class, id);
        if (contact == null){
            return null;
        }
        return contact;
    }
    @Override
    @Transactional
    public void addHobbyToContact(Contact contact, Hobby hobby){
        contact.getHobbies().add(hobby);
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
    }
    @Transactional
    private Long getIdFromHobby(Hobby hobby){
        if (hobby == null){
            return null;
        }
        Query query = sessionFactory.getCurrentSession().
                createQuery("select c from Hobby c where c=:parameter");
        query.setParameter("parameter", hobby);
        try {
            hobby = (Hobby) query.uniqueResult();
        }catch (HibernateException e){
            e: return null;
        }
        return hobby.getId();
    }
    @Override
    @Transactional
    public Long getIdFromContact(Contact contact){
        if (contact == null){
            return null;
        }
        Query query = sessionFactory.getCurrentSession().
                createQuery("select c from Contact c where c=:parameter");
        query.setParameter("parameter", contact);
        try {
            contact = (Contact) query.uniqueResult();
        }catch (HibernateException e){
            e: return null;
        }
        return contact.getId();
    }
    @Override
    @Transactional
    public void addFriendship(Contact first, Contact second){
        if (first.equals(second)){
            throw new IllegalArgumentException("first should not be equal to second");
        }
        if (first == null || second == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        Long firstId = first.getId();
        Long secondId = second.getId();
        first = (Contact) sessionFactory.getCurrentSession().get(Contact.class, firstId);
        second = (Contact) sessionFactory.getCurrentSession().get(Contact.class, secondId);
        first.getFriends().add(second);
        sessionFactory.getCurrentSession().saveOrUpdate(first);
        sessionFactory.getCurrentSession().saveOrUpdate(second);
    }
    @Override
    @Transactional
    public List<Friendship> getAllFriends(){
        return sessionFactory.getCurrentSession().
                createQuery("from Friendship").list();
    }
    @Override
    @Transactional
    public Set<Contact> getFriendsFromContact(Contact contact){
        if (contact == null){
            throw new IllegalArgumentException("contact should not be null");
        }
        List<Friendship> friendshipList =
                sessionFactory.getCurrentSession().
                        createQuery("from Friendship").list();
        if (friendshipList.isEmpty()){
            return null;
        }
        Set<Contact> contactFriends = new HashSet<Contact>();
        for (Friendship friendship: friendshipList){
           Long friendId = friendship.getSecondContactId();
            if(friendId.equals(contact.getId())){
                friendId = friendship.getFirstContactId();
            }
            contactFriends.add(getContactById(friendId));
        }
        return contactFriends;
    }
    @Override
    public Set<Hobby> getHobbiesFromContact(Contact contact) {
        if (contact == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        return contact.getHobbies();
    }
    @Override
    @Transactional
    public Set<Contact> getAllContactsSamePlace(String placeTitle){
        if (placeTitle == null){
            throw new IllegalArgumentException("argument should not be null");
        }
        List<ContactPlaces> contactPlacesesList =
                sessionFactory.getCurrentSession().
                        createQuery("from ContactPlaces").list();
        if (contactPlacesesList.isEmpty()){
            return null;
        }
        Set<Contact> contactsSamePlace = new HashSet<Contact>();
        for (ContactPlaces contactPlaces: contactPlacesesList){
            long contactId = contactPlaces.getContactId();
            Contact contact = getContactById(contactId);
            contactsSamePlace.add(contact);
        }
        return contactsSamePlace;
    }
    @Override
    @Transactional
    public void sendMessage(Contact sender, Contact receiver,
                            String content, Date messageDate) {
        Message message = new Message(messageDate, sender, receiver, content);
        sessionFactory.getCurrentSession().saveOrUpdate(message);
    }

}