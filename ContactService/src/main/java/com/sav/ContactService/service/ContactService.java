package com.sav.ContactService.service;

import com.sav.ContactService.dto.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ContactService {

    Set<PlaceDTO> addNewPlaceToContact(long contactId, double latitude, double longitude, String title);

    Set<ContactDTO> getAllContactsDTOSameHobby(long hobbyId);

    Set<ContactDTO> getAllContactsDTOSamePlace(long placeId);

    Set<ContactDTO> getAllContactsDTO();

    void addHobbyToContact(long contactId, long hobbyId);

    ContactDTO getContactDTOById(long id);

    ContactDTO addContact(String firstName, String lastName, int birthDate, int birthMonth, int birthYear);

    Set<HobbyDTO> getHobbiesDTOFromContact(long contactId);

    void addFriendship(long firstId, long secondId);

    void removeFriendship(long firstId, long secondId);

    Set<ContactDTO> getFriendsDTOFromContact(long contactId);

    Set<PlaceDTO> getPlacesDTOFromContact(long contactId);

    void addPlaceToContact(long contactId, long placeId);

    Set<HobbyDTO> addNewHobbyToContact(long contactId, String title, String description);

    List<HobbyDTO> getAllHobbiesDTO();

    void removeHobbyFromContact(long contactId, long hobbyId);

    List<PlaceDTO> getAllPlacesDTO();

    void removePlaceFromContact(long contactId, long placeId);

    void storeMessage(long senderId, long receiverId, String content,
                      Date messageDate);

    List<MessageDTO> getConversationDTO(long firstContactId, long secondContactId);

    ContactDTO doesUserExist(String firstName, String lastName, int birthDate,
                             int birthMonth, int birthYear);

}
