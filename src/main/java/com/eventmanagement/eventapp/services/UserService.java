package com.eventmanagement.eventapp.services;

import com.eventmanagement.eventapp.daos.AddressDao;
import com.eventmanagement.eventapp.daos.UserDao;
import com.eventmanagement.eventapp.dto.AddressDTO;
import com.eventmanagement.eventapp.dto.ProfileDTO;
import com.eventmanagement.eventapp.dto.UserLoginDTO;
import com.eventmanagement.eventapp.dto.UserRegisterDTO;
import com.eventmanagement.eventapp.models.Address;
import com.eventmanagement.eventapp.models.Event;
import com.eventmanagement.eventapp.models.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressDao addressDao;

    public void login(UserLoginDTO userLoginDTO, HttpSession session) throws IllegalAccessException {
            User currentUser = userDao.getUserByEmail(userLoginDTO.getEmail());
            if(currentUser == null){
                throw new IllegalAccessException("User not found. Please register");
            }
            if(!currentUser.getPassword().equals(userLoginDTO.getPassword())){
                throw new IllegalAccessException("Wrong password");
            }
            session.setAttribute("user" , currentUser);
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        // verify util...
        User currentUser = userDao.getUserByEmail(userRegisterDTO.getEmail());
        if(currentUser != null){
            throw new IllegalArgumentException("User email ID already exists.");
        }
        userDao.createUser(userDTOToUser(userRegisterDTO));
    }

    private static User userDTOToUser(UserRegisterDTO userRegisterDTO){
        User user = new User();
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setPhoneNumber(userRegisterDTO.getPhoneNumber());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        return user;
    }

    private static Address addressDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setStreet(addressDTO.getStreet());
        address.setState(addressDTO.getState());
        address.setZip(addressDTO.getZip());
        return address;

    }

    public void updateUser(ProfileDTO updatedUser, Long id , HttpSession session) {
        User currentUser = userDao.getUserById(id);
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setPassword(updatedUser.getPassword());
        currentUser.setPhoneNumber(updatedUser.getPhoneNumber());

        Address address = addressDTOToAddress(updatedUser.getPrimaryAddress());
        Address newPrimaryAddress;
        if(currentUser.getPrimaryAddress() == null){
            newPrimaryAddress = addressDao.createAddress(address);
        } else {
            address.setId(currentUser.getPrimaryAddress().getId());
            newPrimaryAddress = addressDao.updateAddress(address);
        }

        currentUser.setPrimaryAddress(newPrimaryAddress);


        if (updatedUser.getSecondaryAddress() != null ) {
            Address newSecondaryAddress;
            Address address2 = addressDTOToAddress(updatedUser.getSecondaryAddress());
            if(currentUser.getSecondaryAddress() == null){
                newSecondaryAddress = addressDao.createAddress(address2);
            }  else{
                address2.setId(currentUser.getSecondaryAddress().getId());
                newSecondaryAddress = addressDao.updateAddress(address2);
            }

            currentUser.setSecondaryAddress(newSecondaryAddress);

        }

        session.setAttribute("user", currentUser);
        userDao.updateUser(currentUser);
    }



    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    public List<Event> getMyBookedEvents(User user) {
        return userDao.getMyBookedEvents(user);
    }

    public List<Event> getMyOrganizedEvents(User user) {
        return userDao.getMyOrganizedEvents(user);
    }
}
