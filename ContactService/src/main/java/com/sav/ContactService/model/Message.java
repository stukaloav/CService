package com.sav.ContactService.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "MESSAGE_DATE")
    private Date messageDate;
    @ManyToOne (targetEntity = Contact.class)
    private Contact sender;
    @ManyToOne (targetEntity = Contact.class)
    private Contact receiver;
    @Column(name = "CONTENT")
    private String content;

    public Message() {
    }
    public Message(Date messageDate, Contact sender, Contact receiver, String content) {
        this.messageDate = messageDate;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getMessageDate() {
        return messageDate;
    }
    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
    public Contact getSender() {
        return sender;
    }
    public void setSender(Contact sender) {
        this.sender = sender;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Contact getReceiver() {
        return receiver;
    }
    public void setReceiver(Contact receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return id == message.id;
    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", messageDate=" + messageDate +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                '}';
    }
}
