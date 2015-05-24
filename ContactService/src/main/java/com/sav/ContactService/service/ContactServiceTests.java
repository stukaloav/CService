package com.sav.ContactService.service;

import com.sav.ContactService.dao.*;
        import com.sav.ContactService.model.*;
        import com.sav.ContactService.service.Impl.ContactServiceImpl;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.runners.MockitoJUnitRunner;

        import java.util.Date;
        import java.util.List;
        import java.util.Set;

        import static org.hamcrest.CoreMatchers.any;
        import static org.mockito.Mockito.*;

        import static org.hamcrest.CoreMatchers.equalTo;
        import static org.hamcrest.CoreMatchers.is;
        import static org.junit.Assert.*;
        import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTests {

    @Mock
    private HobbyDao hobbyDao;
    @Mock
    private ContactDao contactDao;
    @Mock
    private MessageDao messageDao;
    @Mock
    private PlaceDao placeDao;


    @InjectMocks
    ContactService contactService = new ContactServiceImpl();


    @Test
    public void addNewPlaceToContactTest(){
        contactService.addNewPlaceToContact(1l, 1d, 1d, "title");
        verify(contactDao).addNewPlaceToContact(1l, 1d, 1d, "title");
    }

    @Test
    public void doesUserExistTest(){
        contactService.doesUserExist("firstName", "lastName", 1, 1, 1);
        verify(contactDao).doesUserExist("firstName", "lastName", 1, 1, 1);
    }

    @Test
    public void getAllContactsDTOSameHobbyTest(){
        contactService.getAllContactsDTOSameHobby(1l);
        verify(contactDao).getAllContactsDTOSameHobby(1l);
    }

    @Test
    public void getAllContactsDTOSamePlaceTest(){
        contactService.getAllContactsDTOSamePlace(1l);
        verify(contactDao).getAllContactsDTOSamePlace(1l);
    }

    @Test
    public void getAllContactsDTOTest(){
        contactService.getAllContactsDTO();
        verify(contactDao).getAllContactsDTO();
    }

    @Test
    public void addHobbyToContactTest(){
        contactService.addHobbyToContact(1l, 1l);
        verify(contactDao).addHobbyToContact(1l, 1l);
    }

    @Test
    public void getContactDTOByIdTest() {
        contactService.getContactDTOById(1l);
        verify(contactDao).getContactDTOById(1l);
    }

    @Test
    public void addContactTest(){
        contactService.addContact("firstName", "lastName", 1, 1, 1);
        verify(contactDao).addContact("firstName", "lastName", 1, 1, 1);
    }

    @Test
    public void getHobbiesDTOFromContactTest() {
        contactService.getHobbiesDTOFromContact(1l);
        verify(contactDao).getHobbiesDTOFromContact(1l);
    }

    @Test
    public void addFriendshipTest(){
        contactService.addFriendship(1l, 2l);
        verify(contactDao).addFriendship(1l, 2l);
    }

    @Test
    public void removeFriendshipTest() {
        contactService.removeFriendship(1l, 2l);
        verify(contactDao).removeFriendship(1l, 2l);
    }

    @Test
    public void getFriendsDTOFromContactTest(){
        contactService.getFriendsDTOFromContact(1l);
        verify(contactDao).getFriendsDTOFromContact(1l);
    }

    @Test
    public void getPlacesDTOFromContactTest(){
        contactService.getPlacesDTOFromContact(1l);
        verify(contactDao).getPlacesDTOFromContact(1l);
    }

    @Test
    public void addPlaceToContactTest(){
        contactService.addNewPlaceToContact(1l, 1d, 1d, "title");
        verify(contactDao).addNewPlaceToContact(1l, 1d, 1d, "title");
    }

    @Test
    public void addNewHobbyToContactTest(){
        contactService.addNewHobbyToContact(1l, "title", "description");
        verify(contactDao).addNewHobbyToContact(1l, "title", "description");
    }

    @Test
    public void getAllHobbiesDTOTest(){
        contactService.getAllHobbiesDTO();
        verify(hobbyDao).getAllHobbiesDTO();
    }

    @Test
    public void removeHobbyFromContact(){
        contactService.removeHobbyFromContact(1l, 1l);
        verify(contactDao).removeHobbyFromContact(1l, 1l);
    }

    @Test
    public void getAllPlacesDTOTest(){
        contactService.getAllPlacesDTO();
        verify(placeDao).getAllPlacesDTO();
    }

    @Test
    public void removePlaceFromContactTest(){
        contactService.removePlaceFromContact(1l, 1l);
        verify(contactDao).removePlaceFromContact(1l, 1l);
    }

    @Test
    public void storeMessageTest(){
        contactService.storeMessage(1l, 2l, "content", new Date(1, 1, 1));
        verify(contactDao).sendMessage(1l, 2l, "content", new Date(1, 1, 1));
    }

    @Test
    public void getConversationDTOTest(){
        contactService.getConversationDTO(1l, 2l);
        verify(messageDao).getConversationDTO(1l, 2l);
    }

}
