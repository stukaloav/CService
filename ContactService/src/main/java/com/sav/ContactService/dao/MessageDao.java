package com.sav.ContactService.dao;

import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageDao {
    @Transactional
    public List<Message> getConversation(Contact from, Contact to);

    @Transactional
    public List<Message> getAllMessages();

    @Transactional
    List<Message> getAllMessagesFromContact(Contact receiver);
}
