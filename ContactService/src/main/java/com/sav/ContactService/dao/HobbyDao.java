package com.sav.ContactService.dao;

import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Hobby;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface HobbyDao {
    void addHobby(Hobby hobby);
    void deleteHobbyByTitle(String title);
    Set<Contact> getAllContactsWithHobby();
    List<Hobby> getAllHobbies();
    Hobby getHobbyById(long id);

}
