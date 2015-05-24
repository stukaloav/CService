package com.sav.ContactService.dao;

import com.sav.ContactService.dto.ContactDTO;
import com.sav.ContactService.dto.HobbyDTO;
import com.sav.ContactService.dto.PlaceDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ContactDao {

    ContactDTO addContact(String firstName, String lastName,
                          int birthDate, int birthMonth, int birthYear);

    Set<ContactDTO> getAllContactsDTO();

    void addHobbyToContact(long contactId, long hobbyId);

    ContactDTO getContactDTOById(long id);

    void addFriendship(long firstId, long secondId);

    void removeFriendship(long firstId, long secondId);

    Set<PlaceDTO> addNewPlaceToContact(long contactId, double latitude,
                                       double longitude, String title);

    Set<ContactDTO> getAllContactsDTOSamePlace(long placeId);

    Set<ContactDTO> getFriendsDTOFromContact(long contactId);

    Set<HobbyDTO> getHobbiesDTOFromContact(long contactId);

    void removeHobbyFromContact(long contactId, long hobbyId);

    Set<PlaceDTO> getPlacesDTOFromContact(long id);

    void addPlaceToContact(long contactId, long placeId);

    void sendMessage(long sender, long receiver,
                     String content, Date messageDate);

    Set<ContactDTO> getAllContactsDTOSameHobby(long hobbyId);

    ContactDTO doesUserExist(String firstName, String lastName,
                             int birthDate, int birthMonth, int birthYear);

    Set<HobbyDTO> addNewHobbyToContact(long contactId, String title,
                                       String description);

    void removePlaceFromContact(long contactId, long placeId);

}
