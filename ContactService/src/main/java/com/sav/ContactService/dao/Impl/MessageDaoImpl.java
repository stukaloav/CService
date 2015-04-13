package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.MessageDao;
import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Message;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class MessageDaoImpl implements MessageDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    @Transactional
    public List<Message> getConversation(Contact first, Contact second) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("from Message m where " +
                        "m.sender= ? and m.receiver= ?");
        query.setParameter(0, first);
        query.setParameter(1, second);
        List<Message> fromFirstToSecond = query.list();
        query.setParameter(1, first);
        query.setParameter(0, second);
        List<Message> fromSecondToFirst = query.list();
        List<Message> conversation = new ArrayList<>();
        conversation.addAll(fromFirstToSecond);
        conversation.addAll(fromSecondToFirst);
        conversation.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        return conversation;
    }
    @Override
    @Transactional
    public List<Message> getAllMessages() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Message");
        return query.list();
    }
    @Override
    @Transactional
    public List<Message> getAllMessagesFromContact(Contact contact){
        Query query = sessionFactory.getCurrentSession().
                createQuery("from Message m where " +
                        "m.sender = ? and m.receiver = ?");
        query.setParameter(0, contact);
        query.setParameter(1, contact);
        return query.list();

    }
}
