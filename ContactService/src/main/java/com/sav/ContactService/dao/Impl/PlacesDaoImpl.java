package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.PlaceDao;
import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Place;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PlacesDaoImpl implements PlaceDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPlace(Place place) {
        sessionFactory.getCurrentSession().save(place);
    }
    @Override
    public List<Place> getAllPlaces() {
        return sessionFactory.getCurrentSession().
                createQuery("from Place").list();
    }
    @Override
    public Place getPlaceById(long placeId) {
        return (Place) sessionFactory.getCurrentSession().
                createQuery("from Place c where c.id=:id").
                setParameter("id", placeId).uniqueResult();
    }

    @Override
    public void addPlaceToContact(Contact contact, long placeId) {
        Place place = getPlaceById(placeId);
        contact.getPlaces().add(place);
        sessionFactory.getCurrentSession().merge(contact);
    }

    @Override
    public void removePlaceFromContact(Contact contact, long placeId) {
        Place place = getPlaceById(placeId);
        contact.getPlaces().remove(place);
        sessionFactory.getCurrentSession().merge(contact);
    }
}
