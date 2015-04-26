package com.sav.controller;

import com.sav.ContactService.service.ContactService;
import com.sav.ContactService.model.Contact;
import com.sav.DTO.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody Long addContact(
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
                return -1l;
            }
        }
        Contact contact = new Contact(firstName, lastName, new Date(birthYear, birthMonth, birthDate));
        contactService.addContact(contact);
        return contact.getId();
    }

    @RequestMapping(value = "/userEnter", method = RequestMethod.GET)
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
}
