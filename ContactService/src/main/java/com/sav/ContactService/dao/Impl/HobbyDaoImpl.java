package com.sav.ContactService.dao.Impl;

import com.sav.ContactService.dao.HobbyDao;
import com.sav.ContactService.dto.HobbyDTO;
import com.sav.ContactService.model.Hobby;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.sav.ContactService.util.EntityConverter.convertToDTO;

@Repository
public class HobbyDaoImpl implements HobbyDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<HobbyDTO> getAllHobbiesDTO(){
        List<Hobby> hobbyList = sessionFactory.getCurrentSession().
                createQuery("from Hobby").list();
        List<HobbyDTO> hobbyDTOs = new ArrayList<>();
        for (Hobby hobby: hobbyList){
            hobbyDTOs.add(convertToDTO(hobby));
        }
        return hobbyDTOs;
    }

}
