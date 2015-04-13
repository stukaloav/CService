package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.MessageDao;
import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Message;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class MessageDaoImpl implements MessageDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Message> getConversation(Contact sender, Contact receiver) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("from Message m where " +
                        "m.sender= ? and m.receiver= ?");
        query.setParameter(0, sender);
        query.setParameter(1, receiver);
        return query.list();
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
        Query query = sessionFactory.getCurrentSession().createQuery("from Message m where m.contact=:parameter");
        query.setParameter("parameter", contact);
        return query.list();

    }
}
