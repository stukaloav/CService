package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.HobbyDao;
import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.ContactHobbies;
import com.sav.ContactService.model.Hobby;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class HobbyDaoImpl implements HobbyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addHobby(Hobby hobby) {
        sessionFactory.getCurrentSession().save(hobby);
    }

    @Transactional
    private Set<Long> getIdOfAllContactsWithHobby() {
        List<ContactHobbies> contactHobbiesList =
                sessionFactory.getCurrentSession().
                        createQuery("from ContactHobbies").list();
        if (contactHobbiesList == null){
            return null;
        }
        Set<Long> contactsId = new HashSet<Long>();
        for (ContactHobbies contactHobbies: contactHobbiesList){
            contactsId.add(contactHobbies.getContactId());
        }
        return contactsId;
    }

    @Transactional
    private Contact getContactById(long id){
        return (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);

    }

    @Override
    @Transactional
    public Set<Contact> getAllContactsWithHobby() {
        Set<Long> contactId = getIdOfAllContactsWithHobby();
        if (contactId == null){
            return null;
        }
        Set<Contact> contactsWithHobby = new HashSet<Contact>();
        for (Long id: contactId){
            contactsWithHobby.add(getContactById(id));
        }
        return contactsWithHobby;
    }

    @Override
    @Transactional
    public List<Hobby> getAllHobbies() {
        return sessionFactory.getCurrentSession().
                createQuery("from Hobby").list();
    }

    @Override
    @Transactional
    public Hobby getHobbyById(long id) {
        return (Hobby) sessionFactory.getCurrentSession().get(Hobby.class, id);
    }

    @Override
    @Transactional
    public void deleteHobbyByTitle(String title){
        if (title == null){
            return;
        }
        List<Hobby> hobbyList = getAllHobbies();
        if (hobbyList.isEmpty()){
            return;
        }
        for (Hobby hobby: hobbyList){
            if (title.equals(hobby.getTitle())){
                sessionFactory.getCurrentSession().delete(hobby);
            }
        }
    }

}
