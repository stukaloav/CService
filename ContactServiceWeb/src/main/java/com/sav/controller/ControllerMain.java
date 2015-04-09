package com.sav.controller;

import com.sav.ContactService.service.ContactService;
import com.sav.ContactService.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

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
    public ModelAndView getAllContacts(){
        ModelAndView modelAndView = new ModelAndView();
        Set<Contact> contacts = contactService.getAllContacts();
        modelAndView.addObject("contacts", contacts);
        modelAndView.setViewName("contacts");
        return modelAndView;
    }
}
