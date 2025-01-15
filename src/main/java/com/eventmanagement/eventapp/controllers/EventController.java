package com.eventmanagement.eventapp.controllers;

import com.eventmanagement.eventapp.dto.EventDTO;
import com.eventmanagement.eventapp.dto.SearchDTO;
import com.eventmanagement.eventapp.models.Address;
import com.eventmanagement.eventapp.models.Event;
import com.eventmanagement.eventapp.models.User;
import com.eventmanagement.eventapp.services.AddressService;
import com.eventmanagement.eventapp.services.EventService;
import com.eventmanagement.eventapp.services.PdfService;
import com.eventmanagement.eventapp.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

// Controller for event related operations
@Controller
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private Event event;

    // api for creating a new event, backend form validations and returning a success page
    @PostMapping("/createEvent")
    public String createEvent(@ModelAttribute @Valid EventDTO eventDTO , BindingResult result, RedirectAttributes redirectAttributes, ModelMap map,
                              HttpSession session, @RequestParam int step) {
        try {
            if (result.hasErrors()|| eventDTO.getEndTime().isBefore(eventDTO.getStartTime())) {
                if(!result.hasErrors()){
                    result.addError(new ObjectError("" , "End Time should not be before Start Time"));
                }
                redirectAttributes.addFlashAttribute("validationError", result.getAllErrors());
                return "redirect:/home/createEvent?step=" + step;
            }
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access. Please login.");
                return "redirect:/login";
            }
            if (step == 1) {
                session.setAttribute("event" , eventDTO);
                List<Address> addresses = addressService.getValidAddresses(eventDTO);
                map.addAttribute("addresses", addresses);
                return "chooseEventAddress";
            } else {
                eventService.createEvent(eventDTO , user);
                return "eventSuccess";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error Creating an Event: " + e.getMessage());
            return "redirect:/home/createEvent";
        }
    }

    // method for registering a user ID for a particular event ID
    @PostMapping("/{eid}/register")
    public String registerEvent(@PathVariable Long eid, RedirectAttributes redirectAttributes, HttpSession session, ModelMap modelMap) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access. Please login.");
                return "redirect:/login";
            }
            eventService.registerEvent(eid, user);
            modelMap.addAttribute("eventID" , eid);
            modelMap.addAttribute("userID" , user.getId());
            return "eventRegistrationSuccess";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/home";
        }
    }

    // download PDF (view) ticket for a registered event
    @PostMapping("/ticket")
    public View ticket(@RequestParam Long eventId, @RequestParam Long userId) {
        try {
            Event event = eventService.getEventById(eventId);
            User user = userService.getUserById(userId);
            if (event == null || user == null) {
                throw new IllegalArgumentException("Error generating ticket. Please try again later.");
            }

            View view = new PdfService(event, user);
            return view;
        } catch (Exception e) {
            return null;
        }
    }

    // search for an event
    @PostMapping("/search")
    public String searchEvents(@Valid SearchDTO searchDTO, RedirectAttributes redirectAttributes, ModelMap modelMap, HttpSession session){
        try{
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectAttributes.addFlashAttribute("message","Unauthorized Access. Please Login.");
                return "redirect:/login";
            }
            List<Event> searchResults = eventService.getSearchedEvents(searchDTO.getTitle(), user);
            modelMap.addAttribute("events" , searchResults);
            return "searchResults";
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Error Searching an Event. " + e.getMessage());
            return "redirect:/error";
        }
    }
}
