package com.sav.ContactService.service;

import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Hobby;
import com.sav.ContactService.model.Place;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("app-context.xml");

        ContactService contactService = context.getBean(ContactService.class);

        System.out.println(contactService.getFriendsDTOFromContact(1l));
        contactService.addContact("Sasha", "Stukalo", 5, 10, 115);
        System.out.println(contactService.getAllContactsDTO());

    }
}

