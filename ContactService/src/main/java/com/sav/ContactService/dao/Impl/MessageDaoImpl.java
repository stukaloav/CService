package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.MessageDao;
import com.sav.ContactService.dto.MessageDTO;
import com.sav.ContactService.model.Message;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class MessageDaoImpl implements MessageDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<MessageDTO> getConversationDTO(Long firstContactId, Long secondContactId){
        List<MessageDTO> conversationDTO = new ArrayList<>();
        Query query = sessionFactory.getCurrentSession().
                createQuery("from Message m where " +
                        "(m.sender.id= ? and m.receiver.id= ?)" +
                        "or (m.sender.id = ? and m.receiver.id = ?)");
        query.setParameter(0, firstContactId);
        query.setParameter(1, secondContactId);
        query.setParameter(2, secondContactId);
        query.setParameter(3, firstContactId);
        List<Message> conversation = query.list();
        conversation.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        if(conversation != null){
            for (Message message: conversation){
                conversationDTO.add(new MessageDTO(
                        message.getId(),
                        message.getMessageDate(),
                        message.getContent(),
                        message.getSender().getFirstName(),
                        message.getSender().getLastName(),
                        message.getReceiver().getFirstName(),
                        message.getReceiver().getLastName()));
            }
        }
        return conversationDTO;
    }

}
