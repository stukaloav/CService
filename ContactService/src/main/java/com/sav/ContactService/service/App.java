package com.sav.ContactService.service;

import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Hobby;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("app-context.xml");

        ContactService contactService = context.getBean(ContactService.class);
        contactService.createContact("Sasha", "Stukalo", new Date(86, 1, 1));
        contactService.createContact("Luba", "Fedorchak", new Date(86, 1, 1));
        contactService.createContact("Jonatan", "Swift", new Date(86, 1, 1));
        contactService.createContact("Lora", "Ditrich", new Date(86, 1, 1));
        contactService.addHobby("football", "favourite team is Dnipro");
        contactService.addPlace("Lviv", 0.21, 0.15);
        Contact contact = new Contact("Jane", "Patric", new Date(23, 1, 4));
        Hobby hobby = new Hobby("movies", "Detective");
        contactService.addPlace("Paris", 0.2, 0.3);
        contactService.addHobbyToContact(contact, hobby);
        System.out.println(contactService.getHobbiesFromContact(contact));

        Contact contact1 = contactService.getContactById(1l);
        Contact contact2 = contactService.getContactById(2l);


        System.out.println(contactService.getAllMessages());

        System.out.println(contactService.getConversation(contact1, contact2));

        contactService.storeMessage(contact1, contact2, "hello! How are you?", new Date(25, 2, 5));
        contactService.storeMessage(contact2, contact1, "I am fine, thanks, and you?", new Date(25, 2, 5));
        contactService.storeMessage(contact1, contact2, "I am fine too, thank you. Would you go to cinema with me?", new Date(25, 2, 5));
        contactService.storeMessage(contact2, contact1, "Thank you, but I can not", new Date(25, 2, 5));
        contactService.storeMessage(contact1, contact2, "What a pity! Why?", new Date(25, 2, 5));
        contactService.storeMessage(contact2, contact1, "I am too busy", new Date(25, 2, 5));
        contactService.storeMessage(contact1, contact2, "What are you doing?", new Date(25, 2, 5));
        contactService.storeMessage(contact2, contact1, "I am learning java!", new Date(25, 2, 5));

        System.out.println(contactService.getConversation(contact1, contact2));

        System.out.println(contactService.getAllMessagesFromContact(contact1));

        System.out.println(contactService.getAllContacts());

    }
}

