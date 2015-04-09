package com.sav.ContactService.dao;

import com.sav.ContactService.model.Place;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaceDao {
    @Transactional
    void addPlace(Place place);
    @Transactional
    List<Place> getAllPlaces();
}
