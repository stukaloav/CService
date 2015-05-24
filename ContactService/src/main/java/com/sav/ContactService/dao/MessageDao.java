package com.sav.ContactService.dao;

import com.sav.ContactService.dto.MessageDTO;

import java.util.List;

public interface MessageDao {

    List<MessageDTO> getConversationDTO(Long firstContactId, Long secondContactId);

}
