package com.sav.ContactService.service.Impl;

import com.sav.ContactService.dao.*;
import com.sav.ContactService.dto.*;
import com.sav.ContactService.service.ContactService;
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

    @Override
    @Transactional(readOnly = true)
    public ContactDTO doesUserExist(String firstName, String lastName, int birthDate, int birthMonth, int birthYear) {
        return contactDao.doesUserExist(firstName, lastName, birthDate, birthMonth, birthYear);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ContactDTO> getAllContactsDTOSameHobby(long hobbyId){
        return contactDao.getAllContactsDTOSameHobby(hobbyId);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ContactDTO> getAllContactsDTO(){
        return contactDao.getAllContactsDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public ContactDTO getContactDTOById(long id){
        if(id < 0){
            throw new IllegalArgumentException("id should not be negative");
        }
        return contactDao.getContactDTOById(id);
    }

    @Override
    public ContactDTO addContact(String firstName, String lastName,
                                 int birthDate, int birthMonth, int birthYear){
        return contactDao.addContact(firstName, lastName,
                birthDate, birthMonth, birthYear);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<HobbyDTO> getHobbiesDTOFromContact(long contactId){
        return contactDao.getHobbiesDTOFromContact(contactId);
    }

    @Override
    public void addHobbyToContact(long contactId, long hobbyId) {
        contactDao.addHobbyToContact(contactId, hobbyId);
    }

    @Override
    public Set<HobbyDTO> addNewHobbyToContact(long contactId, String title, String description){
        return contactDao.addNewHobbyToContact(contactId, title, description);
    }

    @Override
    public void addFriendship(long firstId, long secondId){
        contactDao.addFriendship(firstId, secondId);
    }

    @Override
    public void removeFriendship(long firstId, long secondId) {
        contactDao.removeFriendship(firstId, secondId);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ContactDTO> getFriendsDTOFromContact(long contactId){
        return contactDao.getFriendsDTOFromContact(contactId);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PlaceDTO> getPlacesDTOFromContact(long contactId){
        return contactDao.getPlacesDTOFromContact(contactId);
    }

    @Override
    public void addPlaceToContact(long contactId, long placeId) {
        contactDao.addPlaceToContact(contactId, placeId);
    }

    @Override
    public Set<PlaceDTO> addNewPlaceToContact(long contactId, double latitude, double longitude, String title) {
        return contactDao.addNewPlaceToContact(contactId, latitude, longitude, title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HobbyDTO> getAllHobbiesDTO(){
        return hobbyDao.getAllHobbiesDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ContactDTO> getAllContactsDTOSamePlace(long placeId){
        return contactDao.getAllContactsDTOSamePlace(placeId);
    }

    @Override
    public void removeHobbyFromContact(long contactId, long hobbyId) {
        contactDao.removeHobbyFromContact(contactId, hobbyId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlaceDTO> getAllPlacesDTO(){
        return placeDao.getAllPlacesDTO();
    }

    @Override
    public void removePlaceFromContact(long contactId, long placeId) {
        contactDao.removePlaceFromContact(contactId, placeId);
    }

    @Override
    public void storeMessage(long senderId, long receiverId, String content,
                             Date messageDate) {
        contactDao.sendMessage(senderId, receiverId, content, messageDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageDTO> getConversationDTO(long firstContactId, long secondContactId){
        return messageDao.getConversationDTO(firstContactId, secondContactId);
    }

}
