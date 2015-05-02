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

    public MessageDTO(Long id, Date messageDate, String content) {
        this.messageDate = messageDate;
        this.content = content;
        this.id = id;
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
}
