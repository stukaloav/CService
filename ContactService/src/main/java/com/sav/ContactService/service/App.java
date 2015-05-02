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

//
        Contact contact1 = contactService.getContactById(1);
        Contact contact2 = contactService.getContactById(2);
//        Contact contact3 = contactService.getContactById(3);
//        Contact contact4 = contactService.getContactById(4);
//        Contact contact5 = contactService.getContactById(5);
//        Contact contact6 = contactService.getContactById(6);
//        Contact contact7 = contactService.getContactById(7);
//        Contact contact8 = contactService.getContactById(8);
//        Contact contact9 = contactService.getContactById(9);
//
//        System.out.println(contactService.getFriendsFromContact(contact1));



//        Contact contact1 = contactService.getContactById(1l);
//        Contact contact2 = contactService.getContactById(2l);
//
//
//        System.out.println(contactService.getAllMessages());
//
//        System.out.println(contactService.getConversation(contact1, contact2));
//
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
//
//        System.out.println(contactService.getAllContacts());

    }
}

