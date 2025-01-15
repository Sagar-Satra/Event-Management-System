package com.eventmanagement.eventapp.services;

import com.eventmanagement.eventapp.daos.AddressDao;
import com.eventmanagement.eventapp.daos.EventDao;
import com.eventmanagement.eventapp.daos.UserDao;
import com.eventmanagement.eventapp.dto.EventDTO;
import com.eventmanagement.eventapp.models.Event;
import com.eventmanagement.eventapp.models.EventStatus;
import com.eventmanagement.eventapp.models.Role;
import com.eventmanagement.eventapp.models.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;


    public void createEvent(EventDTO eventDTO, User user) throws Exception {
            eventDTO.setOrganizer(user);
            //System.out.println("eventDTO PICTURE:" + eventDTO.getPicture());
            MultipartFile picture = eventDTO.getPicture();
            //System.out.println("picture PICTURE:" + picture.getOriginalFilename());
            Boolean picturePresent = eventDTO.getPicture().isEmpty();
            //System.out.println(picturePresent);
            //System.out.println("multipart PICTURE:" + picture.toString());
            String picturePath = "";
            if (picture != null  && !picture.isEmpty()) {
                picturePath = fileService.saveFile(picture, eventDTO.getTitle() + "-" + picture.getOriginalFilename());
                //System.out.println("this is picture name " + picture.getName());
                //System.out.println("this is picture " + picturePath);
            } else {
                picturePath = "/eventImages/default-image.png";
            }

            Event event = new Event();
            event.setTitle(eventDTO.getTitle());
            event.setDescription(eventDTO.getDescription());
            event.setOrganizer(user);
            event.setDate(eventDTO.getDate());
            event.setStartTime(eventDTO.getStartTime());
            event.setEndTime(eventDTO.getEndTime());
            event.setCapacity(eventDTO.getCapacity());
            event.setParticipants(eventDTO.getParticipants());
            event.setPicture(picturePath);
            event.setLocation(addressDao.getAddressById(eventDTO.getLocation()));
            event.setStatus(eventDTO.getStatus());
            if(user.getRole().equals(Role.ADMIN)){
                event.setStatus(EventStatus.APPROVED);
            }
            eventDao.createEvent(event);
    }

    public List<Event> getPendingEvents(){
        return eventDao.getPendingEvents();
    }

    public void approveRequest(Long eid) {
        eventDao.approveEvent(eid);
    }

    public void rejectRequest(Long eid) {
        eventDao.rejectEvent(eid);
    }

    public List<Event> getApprovedEvents(User user) {
        List<Event> events =  eventDao.getApprovedEvents();
        if (!user.getRole().equals(Role.ADMIN)) {
            events = events.stream().filter((event) -> event.getOrganizer().getId() != user.getId()).collect(Collectors.toList());
        }

        List<Event> notParticipatedEvents = new ArrayList<>();
        for(Event event : events){
            boolean isParticipated = false;
            for(User participant : event.getParticipants()){
                if (participant.getId() == user.getId()){
                    isParticipated = true;
                    break;
                }
            }

            if(!isParticipated){
                notParticipatedEvents.add(event);
            }
        }

        return notParticipatedEvents;
    }

    public void registerEvent(Long eid, User user) {
        Event event = eventDao.getEvent(eid);
        if(event == null){
            throw new IllegalArgumentException("Event doesn't exists");
        }
        if(event.getCapacity() == 0){
            throw new IllegalArgumentException("Event registration is full");
        }
        List<User> eventParticipants = event.getParticipants();
        for (User Participant : eventParticipants) {
            if (Participant.getId() == user.getId()) {
                throw new IllegalArgumentException("You have already registered for this Event. View Registered Events Tab.");
            }
        }
        if (event.getOrganizer().getId() == user.getId()) {
            throw new IllegalArgumentException("You cannot register for this Event as you are the organizer of this event. View Organized Events Tab.");
        }

        event.setCapacity(event.getCapacity() - 1);
        event.getParticipants().add(user);
        eventDao.updateEvent(event);
    }

    public Event getEventById(Long eid) {
        return eventDao.getEvent(eid);
    }

    public List<Event> getSearchedEvents(String title, User user) {
//        List<Event> events =  eventDao.getSearchedEvents(title);
//        List<Event> notParticipatedEvents = new ArrayList<>();
//        for(Event event : events){
//            boolean isParticipated = false;
//            for(User participant : event.getParticipants()){
//                if (participant.getId() == user.getId()){
//                    isParticipated = true;
//                    break;
//                }
//            }
//            if(!isParticipated){
//                notParticipatedEvents.add(event);
//            }
//        }
//        return notParticipatedEvents;
        return eventDao.getSearchedEvents(title);
    }
}
