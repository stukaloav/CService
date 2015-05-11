package tests;

        import com.sav.ContactService.dao.*;
        import com.sav.ContactService.model.*;
        import com.sav.ContactService.service.ContactService;
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
    public void createContactTest() {
        contactService.createContact("first name", "last name", new Date());
        verify(contactDao).addContact((Contact) anyObject());
    }

    @Test
    public void getAllContactSameHobby(){
        contactService.getAllContactsSameHobby(1l);
        verify(contactDao).getAllContactsSameHobby(1l);
    }

    @Test
    public void getAllContactsSamePlaceTest(){
        contactService.getAllContactsSamePlace(1l);
        verify(contactDao).getAllContactsSamePlace(1l);
    }

    @Test
    public void getAllContactsTest(){
        contactService.getAllContacts();
        verify(contactDao).getAllContacts();
    }

    @Test
    public void addHobbyToContactTest(){
        Contact contact = new Contact();
        Hobby hobby = new Hobby();
        contactService.addHobbyToContact(contact, hobby);
        verify(contactDao).addHobbyToContact(contact, hobby);
    }

    @Test
    public void getContactByIdTest(){
        contactService.getContactById(1l);
        verify(contactDao).getContactById(1l);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getContactByIdMinusOneTest(){
        contactService.getContactById(-1l);
    }

    @Test
    public void addContactTest(){
        Contact contact = new Contact();
        contactService.addContact(contact);
        verify(contactDao).addContact(contact);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addContactNullTest(){
        contactService.addContact(null);
    }

    @Test
    public void deleteContactTest(){
        Contact contact = new Contact();
        contactService.deleteContact(contact);
        verify(contactDao).deleteContact(contact);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteContactNullTest(){
        contactService.deleteContact(null);
    }

    @Test
    public void getHobbiesFromContactTest(){
        Contact contact = new Contact();
        contactService.getHobbiesFromContact(contact);
        verify(contactDao).getHobbiesFromContact(contact);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getHobbiesFromContactNullTest(){
        contactService.getHobbiesFromContact(null);
    }

    @Test
    public void addFriendshipTest(){
        Contact first = new Contact();
        Contact second = new Contact();
        contactService.addFriendship(first, second);
        verify(contactDao).addFriendship(first, second);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addFriendshipFirstNullTest(){
        Contact second = new Contact();
        contactService.addFriendship(null, second);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addFriendshipSecondNullTest(){
        Contact first = new Contact();
        contactService.addFriendship(first, null);
    }


    @Test
    public void removeFriendshipTest(){
        Contact first = new Contact();
        Contact second = new Contact();
        contactService.removeFriendship(first, second);
        verify(contactDao).removeFriendship(first, second);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeFriendshipFirstNullTest(){
        Contact second = new Contact();
        contactService.removeFriendship(null, second);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeFriendshipSecondNullTest(){
        Contact first = new Contact();
        contactService.removeFriendship(first, null);
    }

    @Test
    public void getAllFriendPairs(){
        contactService.getAllFriendPairs();
        verify(contactDao).getAllFriends();
    }

    @Test
    public void getFriendsFromContactTest(){
        Contact contact = new Contact();
        contactService.getFriendsFromContact(contact);
        verify(contactDao).getFriendsFromContact(contact);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getFriendsFromContactNullTest(){
        contactService.getFriendsFromContact(null);
    }

    @Test
    public void getPlacesFromContactTest(){
        Contact contact = new Contact();
        contactService.getFriendsFromContact(contact);
        verify(contactDao).getFriendsFromContact(contact);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPlacesFromContactNullTest(){
        contactService.getPlacesFromContact(null);
    }

    @Test
    public void addPlaceToContactTest(){
        Contact contact = new Contact();
        Place place = new Place();
        contactService.addPlaceToContact(contact, place);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPlaceToContactContactNullTest(){
        Place place = new Place();
        contactService.addPlaceToContact(null, place);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPlaceToContactPlaceNullTest(){
        Contact contact = new Contact();
        contactService.addPlaceToContact(contact, null);
    }

//    //Methods that deal with HobbyDao
//    void addHobby(String title, String description);
//    List<Hobby> getAllHobbies();
//    Set<String> getAllHobbiesTitle();
//    void addHobby(Hobby hobby);
//    void deleteHobbyByTitle(String title);
//    Hobby getHobbyById(Long Id);
//    void removeHobbyFromContact(Contact contact, Hobby hobby);
//
//    //Methods that deal with PlaceDao
//    void addPlace(String title, double longitude, double latitude);
//    void addPlace(Place place);
//    List<Place> getAllPlaces();
//    Place getPlaceById(long id);
//    void removePlaceFromContact(Contact contact, Place place);
//
//    //Methods that deal with MessageDao
//    void storeMessage(Contact sender, Contact receiver, String content,
//                      Date messageDate);
//    List<Message> getConversation(Contact sender, Contact receiver);
//    List<Message> getAllMessages();
//    List<Message> getAllMessagesFromContact(Contact contact);
////




//    @Test(expected = IllegalArgumentException.class)
//    public void createContactTestForNullFirstArgument(){
//        contactService.createContact(null, "last name");
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void createContactTestForNullSecondArgument(){
//        contactService.createContact("first name", null);
//    }
//
//    @Test
//    public void addHobbyTest(){
//        contactService.addHobby("title", "description");
//        verify(hobbyDao).addHobby((Hobby) anyObject());
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void addHobbyTestForNullFirstArgument(){
//        contactService.addHobby(null, "description");
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void addHobbyTestForNullSecondArgument(){
//        contactService.addHobby("title", null);
//    }
//
//    @Test
//    public void addPlaceTest(){
//        contactService.addPlace("title", "description", 0.5, 1.25);
//        verify(placeDao).addPlace((Place) anyObject());
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void addPlaceTestForNullFirstArgument(){
//        contactService.addPlace(null, "description", 0, 0);
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void addPlaceTestForNullSecondArgument(){
//        contactService.addPlace("title", null, 0, 0);
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void addPlaceTestForCorrectMinLongitude(){
//        contactService.addPlace("title", "description", -1, 0);
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void addPlaceTestForCorrectMaxLongitude(){
//        contactService.addPlace("title", "description", 181, 0);
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void addPlaceTestForCorrectMinLatitude(){
//        contactService.addPlace("title", "description", 0, -1);
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void addPlaceTestForCorrectMaxLatitude(){
//        contactService.addPlace("title", "description", 0, 91);
//    }
//
//    @Test
//    public void addFriendshipTest(){
//        contactService.addFriendship((Contact) anyObject(), (Contact) anyObject());
//        verify(contactDao).addFriendship((Contact) anyObject(), (Contact) anyObject());
//    }
//
//    @Test
//    public void getContactByNameTest(){
//        when(contactService.getContactByName("first name", "last name")).thenReturn(null);
//        contactService.getContactByName("first name", "last name");
//    }
}