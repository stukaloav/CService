package com.sav.controller;

import com.google.gson.Gson;
import com.sav.ContactService.service.ContactService;
import com.sav.ContactService.model.Contact;
import com.sav.DTO.ContactDTO;
import com.thoughtworks.xstream.XStream;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
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
            contactDTOs.add(new ContactDTO(contact.getFirstName(),
                    contact.getLastName(), contact.getBirthDate()));
        }
        return contactDTOs;
    }
}
