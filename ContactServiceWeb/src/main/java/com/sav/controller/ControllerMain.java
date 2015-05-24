package com.sav.controller;

import com.sav.ContactService.service.ContactService;
import com.sav.ContactService.dto.ContactDTO;
import com.sav.ContactService.dto.HobbyDTO;
import com.sav.ContactService.dto.MessageDTO;
import com.sav.ContactService.dto.PlaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class ControllerMain {

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage(){
        return "index";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> getAllContacts(){
        return contactService.getAllContactsDTO();
    }


    @RequestMapping(value = "/hobbies", method = RequestMethod.GET)
    public @ResponseBody List<HobbyDTO> getAllHobbies(){
        return contactService.getAllHobbiesDTO();
    }

    @RequestMapping(value = "/contactsSameHobby", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> contactsSameHobby(
            @RequestParam(value = "hobbyId", required = true) long hobbyId){
        return contactService.getAllContactsDTOSameHobby(hobbyId);
    }

    @RequestMapping(value = "/places", method = RequestMethod.GET)
    public @ResponseBody List<PlaceDTO> getAllPlaces(){
        return contactService.getAllPlacesDTO();
    }

    @RequestMapping(value = "/contactsSamePlace", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> contactsSamePlace(
            @RequestParam(value = "placeId", required = true) long placeId){
        return contactService.getAllContactsDTOSamePlace(placeId);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ContactDTO addContact(
                            @RequestParam(value = "firstName", required = true) String firstName,
                            @RequestParam(value = "lastName", required = true) String lastName,
                            @RequestParam(value = "birthDate", required = true) int birthDate,
                            @RequestParam(value = "birthMonth", required = true) int birthMonth,
                            @RequestParam(value = "birthYear", required = true) int birthYear){
        return contactService.addContact(firstName, lastName, birthDate, birthMonth, birthYear);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody ContactDTO doesUserExist(
            @RequestParam(value = "firstName", required = true) String firstName,
            @RequestParam(value = "lastName", required = true) String lastName,
            @RequestParam(value = "birthDate", required = true) int birthDate,
            @RequestParam(value = "birthMonth", required = true) int birthMonth,
            @RequestParam(value = "birthYear", required = true) int birthYear){
        return contactService.doesUserExist(firstName, lastName, birthDate, birthMonth, birthYear);
    }

    @RequestMapping(value = "/getContactById", method = RequestMethod.GET)
    public @ResponseBody ContactDTO getContactById(
            @RequestParam(value = "contactId", required = true) long contactId){
        return contactService.getContactDTOById(contactId);
    }

    @RequestMapping(value = "/getPlaces", method = RequestMethod.GET)
    public @ResponseBody Set<PlaceDTO> getPlaces(
            @RequestParam(value = "contactId", required = true) long contactId){
        return contactService.getPlacesDTOFromContact(contactId);
    }

    @RequestMapping(value = "/getHobbies", method = RequestMethod.GET)
    public @ResponseBody Set<HobbyDTO> getHobbies(
            @RequestParam(value = "contactId", required = true) long contactId){
       return contactService.getHobbiesDTOFromContact(contactId);
    }

    @RequestMapping(value = "/getFriends", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> getFriends(
            @RequestParam(value = "contactId", required = true) long contactId){
        return contactService.getFriendsDTOFromContact(contactId);
    }

    @RequestMapping(value = "/getConversation", method = RequestMethod.GET)
    public @ResponseBody List<MessageDTO> getConversation(
            @RequestParam(value = "senderId", required = true) long senderId,
            @RequestParam(value = "receiverId", required = true) long receiverId){
        return contactService.getConversationDTO(senderId, receiverId);
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public @ResponseBody List<MessageDTO> getConversation(
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "messageDate", required = true) Date messageDate,
            @RequestParam(value = "senderId", required = true) long senderId,
            @RequestParam(value = "receiverId", required = true) long receiverId){
        contactService.storeMessage(senderId, receiverId, content, messageDate);
        return contactService.getConversationDTO(senderId, receiverId);
    }

    @RequestMapping(value = "/addPlace", method = RequestMethod.POST)
    public @ResponseBody Set<PlaceDTO> addNewPlaceToContact(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "latitude", required = true) double latitude,
            @RequestParam (value = "longitude", required = true) double longitude,
            @RequestParam (value = "title", required = true) String title){
        return contactService.addNewPlaceToContact(contactId, latitude, longitude, title);
    }

    @RequestMapping(value = "/addPlaceToContact", method = RequestMethod.POST)
    public @ResponseBody Set<ContactDTO> addPlaceToContact(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "placeId", required = true) long placeId){
        contactService.addPlaceToContact(contactId, placeId);
        return contactService.getAllContactsDTOSamePlace(placeId);
    }

    @RequestMapping(value = "/addHobbyToContact", method = RequestMethod.POST)
    public @ResponseBody Set<ContactDTO> addHobbyToContact(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "hobbyId", required = true) long hobbyId){
        contactService.addHobbyToContact(contactId, hobbyId);
        return contactService.getAllContactsDTOSameHobby(hobbyId);
    }

    @RequestMapping(value = "/removeHobbyFromContact", method = RequestMethod.POST)
    public @ResponseBody Set<ContactDTO> removeHobbyFromContact(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "hobbyId", required = true) long hobbyId){
        contactService.removeHobbyFromContact(contactId, hobbyId);
        return contactService.getAllContactsDTOSameHobby(hobbyId);
    }

    @RequestMapping(value = "/removePlaceFromContact", method = RequestMethod.POST)
    public @ResponseBody Set<ContactDTO> removePlaceFromContact(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "placeId", required = true) long placeId){
        contactService.removePlaceFromContact(contactId, placeId);
        return contactService.getAllContactsDTOSamePlace(placeId);
    }

    @RequestMapping(value = "/addFriendship", method = RequestMethod.POST)
    public @ResponseBody Set<ContactDTO> addFriendship(
            @RequestParam (value = "userId", required = true) long userId,
            @RequestParam (value = "contactId", required = true) long contactId){
        contactService.addFriendship(userId, contactId);
        return contactService.getFriendsDTOFromContact(contactId);
    }

    @RequestMapping(value = "/removeFriendship", method = RequestMethod.POST)
    public @ResponseBody Set<ContactDTO> removeFriendship(
            @RequestParam (value = "userId", required = true) long userId,
            @RequestParam (value = "contactId", required = true) long contactId){
        contactService.removeFriendship(userId, contactId);
        return contactService.getFriendsDTOFromContact(contactId);
    }

    @RequestMapping(value = "/addNewHobby", method = RequestMethod.POST)
    public @ResponseBody Set<HobbyDTO> addNewHobby(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "title", required = true) String title,
            @RequestParam (value = "description", required = true) String description){
        return contactService.addNewHobbyToContact(contactId, title, description);
    }

}
