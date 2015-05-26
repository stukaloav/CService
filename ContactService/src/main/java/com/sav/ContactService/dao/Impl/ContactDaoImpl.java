package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.ContactDao;
import com.sav.ContactService.dto.ContactDTO;
import com.sav.ContactService.dto.HobbyDTO;
import com.sav.ContactService.dto.PlaceDTO;
import com.sav.ContactService.model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.sav.ContactService.util.EntityConverter.convertToDTO;

@Repository
public class ContactDaoImpl implements ContactDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ContactDTO addContact(String firstName, String lastName,
                                 int birthDate, int birthMonth, int birthYear){
        List<Contact> allContacts = sessionFactory.getCurrentSession().
                createQuery("from Contact").list();
        for(Contact contact: allContacts){
            if(firstName.equals(contact.getFirstName())&&
                    lastName.equals(contact.getLastName())&&
                    (birthDate == contact.getBirthDate().getDate())&&
                    (birthMonth == contact.getBirthDate().getMonth())&&
                    (birthYear == contact.getBirthDate().getYear())){
                return null;
            }
        }
        Contact contact = new Contact(firstName, lastName, new Date(birthYear, birthMonth, birthDate));
        sessionFactory.getCurrentSession().
                saveOrUpdate(contact);
        return convertToDTO(contact);
    }


    @Override
    public Set<ContactDTO> getAllContactsDTO() {
        List<Contact> contactList = sessionFactory.getCurrentSession().
                createQuery("from Contact").list();
        Set<ContactDTO> contactDTOs = new HashSet<>();
        for (Contact contact: contactList){
            contactDTOs.add(convertToDTO(contact));
        }
        return contactDTOs;
    }

    @Override
    public void addHobbyToContact(long contactId, long hobbyId){
        Contact contact = getContactById(contactId);
        if(contact == null){
            return;
        }
        Query query = sessionFactory.getCurrentSession().createQuery("from Hobby h where h.id = ?");
        Hobby hobby = (Hobby) query.setParameter(0, hobbyId).uniqueResult();
        if(hobby == null){
            return;
        }
        contact.getHobbies().add(hobby);
        sessionFactory.getCurrentSession().merge(contact);
    }

    private Contact getContactById(long id){
        return (Contact) sessionFactory.getCurrentSession().
                createQuery("from Contact c where c.id=:id").
                setParameter("id", id).uniqueResult();
    }

    @Override
    public ContactDTO getContactDTOById(long id){
        Contact contact = getContactById(id);
        if (contact == null){
            return null;
        }else {
            return convertToDTO(contact);
        }
    }

    @Override
    public void addFriendship(long firstId, long secondId){
        if (firstId == secondId){
            throw new IllegalArgumentException("first should not be equal to second");
        }
        Contact first = getContactById(firstId);
        Contact second = getContactById(secondId);
        if(first == null || second == null){
            return;
        }
        first.getFriends().add(second);
        sessionFactory.getCurrentSession().merge(first);
        sessionFactory.getCurrentSession().merge(second);
    }


    @Override
    public Set<ContactDTO> getFriendsDTOFromContact(long contactId){
        Contact contact = getContactById(contactId);
        if(contact == null){
            return null;
        }
        Set<Contact> friends = contact.getFriends();
        Set<Contact> inverseFriends = contact.getInverseFriends();
        Set<ContactDTO> contactDTOs = new HashSet<>();
        if(friends != null){
            for (Contact friend: friends){
                contactDTOs.add(convertToDTO(friend));
            }
        }
        if(inverseFriends != null){
            for (Contact inverseFriend: inverseFriends){
                contactDTOs.add(convertToDTO(inverseFriend));
            }
        }
        return contactDTOs;
    }


    @Override
    public Set<HobbyDTO> getHobbiesDTOFromContact(long contactId){
        Contact contact = getContactById(contactId);
        if (contact == null){
            return null;
        }
        Set<Hobby> hobbies = contact.getHobbies();
        if(hobbies == null){
            return null;
        }else {
            Set<HobbyDTO> hobbyDTOs = new HashSet<>();
            for (Hobby hobby: hobbies){
                hobbyDTOs.add(convertToDTO(hobby));
            }
            return hobbyDTOs;
        }
    }

    @Override
    public Set<PlaceDTO> getPlacesDTOFromContact(long id){
        Contact contact = getContactById(id);
        if(contact == null){
            return null;
        }
        Set<Place> places = contact.getPlaces();
        Set<PlaceDTO> placeDTOs = new HashSet<>();
        if(places == null){
            return null;
        }else {
            for (Place place: places){
                placeDTOs.add(convertToDTO(place));
            }
            return placeDTOs;
        }
    }

    @Override
    public void addPlaceToContact(long contactId, long placeId) {
        Contact contact = getContactById(contactId);
        if(contact == null){
            return;
        }
        Query query = sessionFactory.getCurrentSession().createQuery("from Place p where p.id = ?");
        query.setParameter(0, placeId);
        Place place = (Place)query.uniqueResult();
        if(place == null){
            return;
        }
        contact.getPlaces().add(place);
        sessionFactory.getCurrentSession().merge(contact);
    }

    @Override
    public Set<PlaceDTO> addNewPlaceToContact(long contactId, double latitude, double longitude, String title){
        Set<PlaceDTO> placeDTOs = new HashSet<>();
        Place place = new Place(title, longitude, latitude);
        Contact contact = getContactById(contactId);
        if(contact == null){
            return null;
        }
        contact.getPlaces().add(place);
        sessionFactory.getCurrentSession().saveOrUpdate(place);
        sessionFactory.getCurrentSession().merge(contact);
        Set<Place> places = contact.getPlaces();
        if (places != null){
            for (Place item: places){
                placeDTOs.add(convertToDTO(item));
            }
        }
        return placeDTOs;
    }

    @Override
    public Set<ContactDTO> getAllContactsDTOSamePlace(long placeId){
        Place place = (Place) sessionFactory.getCurrentSession().
                createQuery("from Place p where p.id = ?").
                setParameter(0, placeId).uniqueResult();
        if(place == null){
            return null;
        }
        Set<Contact> contactsSamePlace = place.getContacts();
        if(contactsSamePlace == null){
            return null;
        }
        Set<ContactDTO> contactDTOsSamePlace = new HashSet<>();
        for (Contact contact: contactsSamePlace){
            contactDTOsSamePlace.add(convertToDTO(contact));
        }
        return contactDTOsSamePlace;
    }

    @Override
    public Set<ContactDTO> getAllContactsDTOSameHobby(long hobbyId){
        Hobby hobby = (Hobby) sessionFactory.getCurrentSession().
                createQuery("from Hobby h where h.id = ?").
                setParameter(0, hobbyId).uniqueResult();
        if(hobby == null){
            return null;
        }
        Set<ContactDTO> contactDTOsSameHobby = new HashSet<>();
        Set<Contact> contactsSameHobby = hobby.getContacts();
        if(contactsSameHobby == null){
            return null;
        }
        for (Contact contact: contactsSameHobby){
            contactDTOsSameHobby.add(convertToDTO(contact));
        }
        return contactDTOsSameHobby;
    }

    @Override
    public ContactDTO doesUserExist(String firstName, String lastName,
                                    int birthDate, int birthMonth, int birthYear) {
        List<Contact> contactList = sessionFactory.
                getCurrentSession().createQuery("from Contact").list();
        if(contactList == null){
            return null;
        }
        for (Contact contact: contactList){
            if(firstName.equals(contact.getFirstName())&&
                    lastName.equals(contact.getLastName())&&
                    (birthDate == contact.getBirthDate().getDate())&&
                    (birthMonth == contact.getBirthDate().getMonth())&&
                    (birthYear == contact.getBirthDate().getYear())){
                return convertToDTO(contact);
            }
        }
        return null;
    }

    @Override
    public Set<HobbyDTO> addNewHobbyToContact(long contactId, String title, String description) {
        Contact contact = getContactById(contactId);
        Hobby hobby = new Hobby(title, description);
        if(contact == null){
            return null;
        }
        contact.getHobbies().add(hobby);
        sessionFactory.getCurrentSession().merge(contact);
        Set<HobbyDTO> hobbyDTOs = new HashSet<>();
        Set<Hobby> hobbies = contact.getHobbies();
        if(hobbies != null){
            for (Hobby items: hobbies){
                hobbyDTOs.add(convertToDTO(items));
            }
        }
        return hobbyDTOs;
    }

    @Override
    public void removeHobbyFromContact(long contactId, long hobbyId) {
        Contact contact = getContactById(contactId);
        if(contact == null){
            return;
        }
        Hobby hobby = (Hobby)sessionFactory.getCurrentSession().
                createQuery("from Hobby h where h.id = ?").
                setParameter(0, hobbyId).uniqueResult();
        if(hobby == null){
            return;
        }
        contact.getHobbies().remove(hobby);
        sessionFactory.getCurrentSession().merge(contact);
    }

    @Override
    public void removePlaceFromContact(long contactId, long placeId){
        Contact contact = getContactById(contactId);
        if(contact == null){
            return;
        }
        Place place = (Place)sessionFactory.getCurrentSession().
                createQuery("from Place p where p.id = ?").
                setParameter(0, placeId).uniqueResult();
        if(place == null){
            return;
        }
        contact.getPlaces().remove(place);
        sessionFactory.getCurrentSession().merge(contact);
    }

    @Override
    public void sendMessage(long senderId, long receiverId,
                            String content, Date messageDate) {
        Contact sender = getContactById(senderId);
        Contact receiver = getContactById(receiverId);
        Message message = new Message(messageDate, sender, receiver, content);
        sessionFactory.getCurrentSession().saveOrUpdate(message);
    }

    @Override
    public void removeFriendship(long firstId, long secondId) {
        if (firstId == secondId){
            throw new IllegalArgumentException("first should not be equal to second");
        }
        Contact first = getContactById(firstId);
        Contact second = getContactById(secondId);
        if (first == null || second == null){
            return;
        }
        first.getFriends().remove(second);
        sessionFactory.getCurrentSession().merge(first);
        sessionFactory.getCurrentSession().merge(second);
    }

}