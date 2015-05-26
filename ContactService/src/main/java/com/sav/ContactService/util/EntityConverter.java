package com.sav.ContactService.util;

import com.sav.ContactService.dto.ContactDTO;
import com.sav.ContactService.dto.HobbyDTO;
import com.sav.ContactService.dto.MessageDTO;
import com.sav.ContactService.dto.PlaceDTO;
import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Hobby;
import com.sav.ContactService.model.Message;
import com.sav.ContactService.model.Place;

public class EntityConverter {

    public static ContactDTO convertToDTO(Contact contact){
       return new ContactDTO(contact.getId(), contact.getFirstName(),
                contact.getLastName(), contact.getBirthDate());
    }

    public static HobbyDTO convertToDTO(Hobby hobby){
        return new HobbyDTO(hobby.getId(), hobby.getTitle(), hobby.getDescription());
    }

    public static PlaceDTO convertToDTO(Place place){
        return new PlaceDTO(place.getTitle(), place.getLongitude(), place.getLatitude());
    }

    public static MessageDTO convertToDTO(Message message){
        return new MessageDTO(message.getId(), message.getMessageDate(), message.getContent(),
                message.getSender().getFirstName(), message.getSender().getLastName(),
                message.getReceiver().getFirstName(), message.getReceiver().getLastName());
    }
}
