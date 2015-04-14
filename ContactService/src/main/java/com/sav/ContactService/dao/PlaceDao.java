package com.sav.ContactService.dao;

import com.sav.ContactService.model.Place;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaceDao {
    void addPlace(Place place);
    List<Place> getAllPlaces();
}
