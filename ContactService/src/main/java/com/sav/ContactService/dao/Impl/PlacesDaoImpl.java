package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.PlaceDao;
import com.sav.ContactService.dto.PlaceDTO;
import com.sav.ContactService.model.Place;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlacesDaoImpl implements PlaceDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<PlaceDTO> getAllPlacesDTO(){
        List<PlaceDTO> placeDTOs = new ArrayList<>();
        List<Place> placeList = sessionFactory.getCurrentSession().
                createQuery("from Place").list();
        for (Place place: placeList){
            placeDTOs.add(new PlaceDTO(place.getId(), place.getTitle(),
                    place.getLongitude(), place.getLatitude()));
        }
        return placeDTOs;
    }

}
