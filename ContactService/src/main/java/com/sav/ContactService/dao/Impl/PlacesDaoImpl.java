package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.PlaceDao;
import com.sav.ContactService.model.Place;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PlacesDaoImpl implements PlaceDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    @Transactional
    public void addPlace(Place place) {
        sessionFactory.getCurrentSession().save(place);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Place> getAllPlaces() {
        return sessionFactory.getCurrentSession().
                createQuery("from Place").list();
    }

}