package com.sav.ContactService.dao;

import com.sav.ContactService.model.Contact;
import com.sav.ContactService.model.Place;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaceDao {
    void addPlace(Place place);
    List<Place> getAllPlaces();
    Place getPlaceById(long placeId);
    void addPlaceToContact(Contact contact, long placeId);
    void removePlaceFromContact(Contact contact, long placeId);
}
