package com.eventmanagement.eventapp.services;

import com.eventmanagement.eventapp.daos.AddressDao;
import com.eventmanagement.eventapp.dto.EventDTO;
import com.eventmanagement.eventapp.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressService {

    @Autowired
    private AddressDao addressDao;

    public List<Address> getAllAddress() {
        return addressDao.getAll();
    }

    public List<Address> getValidAddresses(EventDTO eventDTO) {
        return addressDao.getValidAddresses(eventDTO.getDate(), eventDTO.getStartTime(), eventDTO.getEndTime());
    }
}
