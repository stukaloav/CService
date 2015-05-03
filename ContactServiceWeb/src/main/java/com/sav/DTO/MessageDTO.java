package com.sav.DTO;

import com.sav.util.JsonDateDeserial;
import com.sav.util.JsonDateSerial;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

public class MessageDTO {
    private Long id;
    private Date messageDate;
    private String content;
    private String senderFirstName;
    private String senderLastName;
    private String receiverFirstName;
    private String receiverLastName;

    public MessageDTO(Long id,
                      Date messageDate,
                      String content,
                      String senderFirstName,
                      String senderLastName,
                      String receiverFirstName,
                      String receiverLastName) {
        this.id = id;
        this.messageDate = messageDate;
        this.content = content;
        this.senderFirstName = senderFirstName;
        this.senderLastName = senderLastName;
        this.receiverFirstName = receiverFirstName;
        this.receiverLastName = receiverLastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonSerialize(using=JsonDateSerial.class, as = Date.class)
    public Date getMessageDate() {
        return messageDate;
    }

    @JsonDeserialize(using= JsonDateDeserial.class, as = Date.class)
    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public void setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }

    public String getReceiverFirstName() {
        return receiverFirstName;
    }

    public void setReceiverFirstName(String receiverFirstName) {
        this.receiverFirstName = receiverFirstName;
    }

    public String getReceiverLastName() {
        return receiverLastName;
    }

    public void setReceiverLastName(String receiverLastName) {
        this.receiverLastName = receiverLastName;
    }
}
