package com.sav.controller;

import com.sav.ContactService.model.Hobby;
import com.sav.ContactService.model.Message;
import com.sav.ContactService.model.Place;
import com.sav.ContactService.service.ContactService;
import com.sav.ContactService.model.Contact;
import com.sav.DTO.ContactDTO;
import com.sav.DTO.HobbyDTO;
import com.sav.DTO.MessageDTO;
import com.sav.DTO.PlaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloWorld(ModelMap modelMap){
        modelMap.addAttribute("message", "Hello World!");
        return "hello";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public @ResponseBody List<ContactDTO> getAllContacts(){
        List<Contact> contactList = contactService.getAllContacts();
        List<ContactDTO> contactDTOs = new ArrayList<>();
        for (Contact contact: contactList){
            contactDTOs.add(new ContactDTO(contact.getId(), contact.getFirstName(),
                    contact.getLastName(), contact.getBirthDate()));
        }
        return contactDTOs;
    }

    @RequestMapping(value = "/hobbies", method = RequestMethod.GET)
    public @ResponseBody List<HobbyDTO> getAllHobbies(){
        List<Hobby> hobbyList = contactService.getAllHobbies();
        List<HobbyDTO> hobbyDTOs = new ArrayList<>();
        for (Hobby hobby: hobbyList){
            hobbyDTOs.add(new HobbyDTO(hobby.getId(), hobby.getTitle(),
                    hobby.getDescription()));
        }
        return hobbyDTOs;
    }

    @RequestMapping(value = "/contactsSameHobby", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> contactsSameHobby(
            @RequestParam(value = "hobbyId", required = true) long hobbyId){
        Set<ContactDTO> contactDTOs = new HashSet<>();
        Set<Contact> contacts = contactService.getAllContactsSameHobby(hobbyId);
        for(Contact contact: contacts){
            contactDTOs.add(new ContactDTO(contact.getId(), contact.getFirstName(),
                    contact.getLastName(), contact.getBirthDate()));
        }
        return contactDTOs;
    }

    @RequestMapping(value = "/places", method = RequestMethod.GET)
    public @ResponseBody List<PlaceDTO> getAllPlaces(){
        List<Place> placeList = contactService.getAllPlaces();
        List<PlaceDTO> placeDTOs = new ArrayList<>();
        for (Place place: placeList){
            placeDTOs.add(new PlaceDTO(place.getId(), place.getTitle(),
                    place.getLongitude(), place.getLatitude()));
        }
        return placeDTOs;
    }

    @RequestMapping(value = "/contactsSamePlace", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> contactsSamePlace(
            @RequestParam(value = "placeId", required = true) long placeId){
        Set<ContactDTO> contactDTOs = new HashSet<>();
        Set<Contact> contacts = contactService.getAllContactsSamePlace(placeId);
        for(Contact contact: contacts){
            contactDTOs.add(new ContactDTO(contact.getId(), contact.getFirstName(),
                    contact.getLastName(), contact.getBirthDate()));
        }
        return contactDTOs;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody ContactDTO addContact(
                            @RequestParam(value = "firstName", required = true) String firstName,
                            @RequestParam(value = "lastName", required = true) String lastName,
                            @RequestParam(value = "birthDate", required = true) int birthDate,
                            @RequestParam(value = "birthMonth", required = true) int birthMonth,
                            @RequestParam(value = "birthYear", required = true) int birthYear){
        List<Contact> contactList = contactService.getAllContacts();
        for (Contact contact: contactList){
            if(firstName.equals(contact.getFirstName())&&
                    lastName.equals(contact.getLastName())&&
                    (birthDate == contact.getBirthDate().getDate())&&
                    (birthMonth == contact.getBirthDate().getMonth())&&
                    (birthYear == contact.getBirthDate().getYear())){
                return null;
            }
        }
        Contact contact = new Contact(firstName, lastName, new Date(birthYear, birthMonth, birthDate));
        contactService.addContact(contact);
        ContactDTO contactDTO = new ContactDTO(contact.getId(),
                contact.getFirstName(), contact.getLastName(),
                contact.getBirthDate());
        return contactDTO;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody ContactDTO doesUserExist(
            @RequestParam(value = "firstName", required = true) String firstName,
            @RequestParam(value = "lastName", required = true) String lastName,
            @RequestParam(value = "birthDate", required = true) int birthDate,
            @RequestParam(value = "birthMonth", required = true) int birthMonth,
            @RequestParam(value = "birthYear", required = true) int birthYear){
        ContactDTO user;
        List<Contact> contactList = contactService.getAllContacts();
        for (Contact contact: contactList){
            if(firstName.equals(contact.getFirstName())&&
                    lastName.equals(contact.getLastName())&&
                    (birthDate == contact.getBirthDate().getDate())&&
                    (birthMonth == contact.getBirthDate().getMonth())&&
                    (birthYear == contact.getBirthDate().getYear())){
                user = new ContactDTO(contact.getId(), contact.getFirstName(),
                        contact.getLastName(), contact.getBirthDate());
                return user;
            }
        }
        return null;
    }

    @RequestMapping(value = "/getContactById", method = RequestMethod.GET)
    public @ResponseBody ContactDTO getContactById(
            @RequestParam(value = "contactId", required = true) long contactId){
        ContactDTO contactDTO;
        Contact contact = contactService.getContactById(contactId);
        if (contact == null){
            contactDTO = null;
        }else {
            contactDTO = new ContactDTO(
                    contact.getId(),
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getBirthDate());
        }
        return contactDTO;
    }

    @RequestMapping(value = "/getPlaces", method = RequestMethod.GET)
    public @ResponseBody Set<PlaceDTO> getPlaces(
            @RequestParam(value = "contactId", required = true) long contactId){
        Contact contact = contactService.getContactById(contactId);
        Set<Place> places = contactService.getPlacesFromContact(contact);
        Set<PlaceDTO> placeDTOs = new HashSet<>();
        if(places == null){
            return null;
        }else {
            for (Place place: places){
                placeDTOs.add(new PlaceDTO(place.getTitle(), place.getLongitude(), place.getLatitude()));
            }
            return placeDTOs;
        }
    }

    @RequestMapping(value = "/getHobbies", method = RequestMethod.GET)
    public @ResponseBody Set<HobbyDTO> getHobbies(
            @RequestParam(value = "contactId", required = true) long contactId){
        Contact contact = contactService.getContactById(contactId);
        Set<Hobby> hobbies = contactService.getHobbiesFromContact(contact);
        Set<HobbyDTO> hobbyDTOs = new HashSet<>();
        if(hobbies == null){
            return null;
        }else {
            for (Hobby hobby: hobbies){
                hobbyDTOs.add(new HobbyDTO(hobby.getId(), hobby.getTitle(), hobby.getDescription()));
            }
            return hobbyDTOs;
        }
    }

    @RequestMapping(value = "/getFriends", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> getFriends(
            @RequestParam(value = "contactId", required = true) long contactId){
        Contact contact = contactService.getContactById(contactId);
        Set<Contact> friends = contactService.getFriendsFromContact(contact);
        Set<ContactDTO> contactDTOs = new HashSet<>();
        if(friends == null){
            return null;
        }else {
            for (Contact friend: friends){
                contactDTOs.add(new ContactDTO(
                        friend.getId(),
                        friend.getFirstName(),
                        friend.getLastName(),
                        friend.getBirthDate()));
            }
            return contactDTOs;
        }
    }

    @RequestMapping(value = "/getConversation", method = RequestMethod.GET)
    public @ResponseBody List<MessageDTO> getConversation(
            @RequestParam(value = "senderId", required = true) long senderId,
            @RequestParam(value = "receiverId", required = true) long receiverId){
        List<MessageDTO> conversationDTO = new ArrayList<>();
        Contact sender = contactService.getContactById(senderId);
        Contact receiver = contactService.getContactById(receiverId);
        List<Message> conversation = contactService.getConversation(sender, receiver);
        if (conversation == null){
            return null;
        }
        for (Message message: conversation){
            conversationDTO.add(new MessageDTO(
                    message.getId(),
                    message.getMessageDate(),
                    message.getContent(),
                    message.getSender().getFirstName(),
                    message.getSender().getLastName(),
                    message.getReceiver().getFirstName(),
                    message.getReceiver().getLastName()));
        }
        return conversationDTO;
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public @ResponseBody List<MessageDTO> getConversation(
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "messageDate", required = true) Date messageDate,
            @RequestParam(value = "senderId", required = true) long senderId,
            @RequestParam(value = "receiverId", required = true) long receiverId){
        Contact sender = contactService.getContactById(senderId);
        Contact receiver = contactService.getContactById(receiverId);
        contactService.storeMessage(sender, receiver, content, messageDate);
        List<MessageDTO> conversationDTO = new ArrayList<>();
        List<Message> conversation = contactService.getConversation(sender, receiver);
        if (conversation == null){
            return null;
        }
        for (Message message: conversation){
            conversationDTO.add(new MessageDTO(
                    message.getId(),
                    message.getMessageDate(),
                    message.getContent(),
                    message.getSender().getFirstName(),
                    message.getSender().getLastName(),
                    message.getReceiver().getFirstName(),
                    message.getReceiver().getLastName()));
        }
        return conversationDTO;
    }

    @RequestMapping(value = "/addPlace", method = RequestMethod.GET)
    public @ResponseBody Set<PlaceDTO> addNewPlaceToContact(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "latitude", required = true) double latitude,
            @RequestParam (value = "longitude", required = true) double longitude,
            @RequestParam (value = "title", required = true) String title){
        Contact contact = contactService.getContactById(contactId);
        Place place = new Place(title, longitude, latitude);
        contactService.addPlaceToContact(contact, place);
        Set<Place> places = contactService.getPlacesFromContact(contact);
        Set<PlaceDTO> placeDTOs = new HashSet<>();
        if(places == null){
            return null;
        }else {
            for (Place item: places){
                placeDTOs.add(new PlaceDTO(item.getTitle(), item.getLongitude(), item.getLatitude()));
            }
            return placeDTOs;
        }
    }

    @RequestMapping(value = "/addPlaceToContact", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> addPlaceToContact(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "placeId", required = true) long placeId){
        Contact contact = contactService.getContactById(contactId);
        Place place = contactService.getPlaceById(placeId);
        contactService.addPlaceToContact(contact, place);
        Set<ContactDTO> contactDTOs = new HashSet<>();
        Set<Contact> contacts = contactService.getAllContactsSamePlace(placeId);
        for(Contact item: contacts){
            contactDTOs.add(new ContactDTO(item.getId(), item.getFirstName(),
                    item.getLastName(), item.getBirthDate()));
        }
        return contactDTOs;
    }

    @RequestMapping(value = "/addHobbyToContact", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> addHobbyToContact(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "hobbyId", required = true) long hobbyId){
        Contact contact = contactService.getContactById(contactId);
        Hobby hobby = contactService.getHobbyById(hobbyId);
        contactService.addHobbyToContact(contact, hobby);
        Set<ContactDTO> contactDTOs = new HashSet<>();
        Set<Contact> contacts = contactService.getAllContactsSameHobby(hobbyId);
        for(Contact item: contacts){
            contactDTOs.add(new ContactDTO(item.getId(), item.getFirstName(),
                    item.getLastName(), item.getBirthDate()));
        }
        return contactDTOs;
    }

    @RequestMapping(value = "/removeHobbyFromContact", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> removeHobbyFromContact(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "hobbyId", required = true) long hobbyId){
        Contact contact = contactService.getContactById(contactId);
        Hobby hobby = contactService.getHobbyById(hobbyId);
        contactService.removeHobbyFromContact(contact, hobby);
        Set<ContactDTO> contactDTOs = new HashSet<>();
        Set<Contact> contacts = contactService.getAllContactsSameHobby(hobbyId);
        for(Contact item: contacts){
            contactDTOs.add(new ContactDTO(item.getId(), item.getFirstName(),
                    item.getLastName(), item.getBirthDate()));
        }
        return contactDTOs;
    }

    @RequestMapping(value = "/removePlaceFromContact", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> removePlaceFromContact(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "placeId", required = true) long placeId){
        Contact contact = contactService.getContactById(contactId);
        Place place = contactService.getPlaceById(placeId);
        contactService.removePlaceFromContact(contact, place);
        Set<ContactDTO> contactDTOs = new HashSet<>();
        Set<Contact> contacts = contactService.getAllContactsSamePlace(placeId);
        for(Contact item: contacts){
            contactDTOs.add(new ContactDTO(item.getId(), item.getFirstName(),
                    item.getLastName(), item.getBirthDate()));
        }
        return contactDTOs;
    }

    @RequestMapping(value = "/addFriendship", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> addFriendship(
            @RequestParam (value = "userId", required = true) long userId,
            @RequestParam (value = "contactId", required = true) long contactId){
        Set<ContactDTO> contactDTOs = new HashSet<>();
        Contact user = contactService.getContactById(userId);
        Contact friend = contactService.getContactById(contactId);
        contactService.addFriendship(user, friend);
        Set<Contact> friends = contactService.getFriendsFromContact(friend);
        for (Contact contact: friends){
            contactDTOs.add(new ContactDTO(
                    contact.getId(), contact.getFirstName(),
                    contact.getLastName(), contact.getBirthDate()));
        }
        return contactDTOs;
    }

    @RequestMapping(value = "/removeFriendship", method = RequestMethod.GET)
    public @ResponseBody Set<ContactDTO> removeFriendship(
            @RequestParam (value = "userId", required = true) long userId,
            @RequestParam (value = "contactId", required = true) long contactId){
        Set<ContactDTO> contactDTOs = new HashSet<>();
        Contact user = contactService.getContactById(userId);
        Contact friend = contactService.getContactById(contactId);
        contactService.removeFriendship(user, friend);
        Set<Contact> friends = contactService.getFriendsFromContact(friend);
        for (Contact contact: friends){
            contactDTOs.add(new ContactDTO(
                    contact.getId(), contact.getFirstName(),
                    contact.getLastName(), contact.getBirthDate()));
        }
        return contactDTOs;
    }


    @RequestMapping(value = "/addNewHobby", method = RequestMethod.GET)
    public @ResponseBody Set<HobbyDTO> addNewHobby(
            @RequestParam (value = "contactId", required = true) long contactId,
            @RequestParam (value = "title", required = true) String title,
            @RequestParam (value = "description", required = true) String description){
        Contact contact = contactService.getContactById(contactId);
        contactService.addHobbyToContact(contact, new Hobby(title, description));
        Set<Hobby> hobbies = contactService.getHobbiesFromContact(contact);
        Set<HobbyDTO> hobbyDTOs = new HashSet<>();
        if(hobbies == null){
            return null;
        }else {
            for (Hobby hobby: hobbies){
                hobbyDTOs.add(new HobbyDTO(hobby.getId(), hobby.getTitle(), hobby.getDescription()));
            }
            return hobbyDTOs;
        }
    }
}
