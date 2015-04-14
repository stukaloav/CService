package com.sav.ContactService.dao;

import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageDao {
    public List<Message> getConversation(Contact first, Contact second);
    public List<Message> getAllMessages();
    List<Message> getAllMessagesFromContact(Contact receiver);
}
