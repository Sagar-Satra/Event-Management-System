package com.eventmanagement.eventapp.controllers;

import com.eventmanagement.eventapp.models.Address;
import com.eventmanagement.eventapp.models.Event;
import com.eventmanagement.eventapp.models.User;
import com.eventmanagement.eventapp.services.AddressService;
import com.eventmanagement.eventapp.services.EventService;
import com.eventmanagement.eventapp.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

// Home Controller for navigating to pages and perform event operations
@Controller
public class HomeController {

    @Autowired
    AddressService addressService;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    // default URL mapping
    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "redirect:/home";
    }

    // method for getting the list of approved events displayed on the Home page
    @GetMapping("/home")
    public String home(ModelMap map, RedirectAttributes redirectAttributes, HttpSession session) {
        try{
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access. Please Login.");
                return "redirect:/login";
            }
            List<Event> events = eventService.getApprovedEvents(user);
            map.addAttribute("events", events);
            return "home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error viewing Home Page. " + e.getMessage());
            return "redirect:/login";
        }
    }

    // method for getting all the addresses for creating a new event
    @GetMapping("/home/createEvent")
    public String createEvent(ModelMap map, RedirectAttributes redirectAttributes, HttpSession session) {
        try{
            if (session.getAttribute("user") == null) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access. Please Login.");
                return "redirect:/login";
            }
            List<Address> addresses = addressService.getAllAddress();
            map.addAttribute("addresses", addresses);
            return "createEvent";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error viewing Create Event Tab. " + e.getMessage());
            return "redirect:/home";
        }
    }

    // navigating to user account registration
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // navigating to login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // navigating to user profile page
    @GetMapping("/home/profile")
    public String profile(RedirectAttributes redirectAttributes, HttpSession session) {
        try{
            if (session.getAttribute("user") == null) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access. Please Login.");
                return "redirect:/login";
            }
            return "profile";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error Viewing Profile. " + e.getMessage());
            return "redirect:/home";
        }
    }

    // navigating to edit profile page
    @GetMapping("/home/profile/edit")
    public String editProfile(HttpSession session, ModelMap model, RedirectAttributes redirectAttributes) {
        try {
            if (session.getAttribute("user") == null) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access. Please Login.");
                return "redirect:/login";
            }
            model.addAttribute("updateUser", session.getAttribute("user"));
            return "editProfile";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error editing profile. " + e.getMessage());
            return "redirect:/home/profile";
        }
    }

    // api for getting the list of events registered and events organized by user and returning to corresponding pages
    @GetMapping("/home/registeredEvents")
    public String myEvents(HttpSession session, RedirectAttributes redirectAttributes, ModelMap modelMap, @RequestParam String type) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access. Please Login.");
                return "redirect:/login";
            }
            List<Event> eventList;

            if (type.equals("myEvents")) {
                eventList = userService.getMyBookedEvents(user);
                modelMap.addAttribute("title", "Registered Events");
            } else {
                eventList = userService.getMyOrganizedEvents(user);
                modelMap.addAttribute("title", "My Organized Events");
            }
            modelMap.addAttribute("events", eventList);
            modelMap.addAttribute("userId", user.getId());

            return "registeredEvents";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error Viewing Registered Events. " + e.getMessage());
            return "redirect:/home";
        }
    }

    // to logout
    @GetMapping("/logout")
    public String logout(HttpSession session , HttpServletResponse response, RedirectAttributes redirectAttributes) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        session.invalidate();
        redirectAttributes.addFlashAttribute("error", "You have been logged out");
        return "redirect:/login";
    }

    // for viewing the list of participants in an event
    @GetMapping("/home/registeredEvents/participants")
    public String getParticipants(HttpSession session, @RequestParam Long eventId, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        try{
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access. Please Login.");
                return "redirect:/login";
            }
            Event event = eventService.getEventById(eventId);
            modelMap.addAttribute("participants", event.getParticipants());
            return "participants";
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Error Viewing Participants. " + e.getMessage());
            return "redirect:/home";
        }
    }

    // incorrect URL call handling
    @GetMapping({"/registeredEvents/participants", "/registeredEvents", "/createEvent", "/participants", "/profile", "/profile/edit", "/edit"} )
    public String handleUnknownURL(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("user") != null) {
            session.invalidate();
            redirectAttributes.addFlashAttribute("error", "Invalid Page Request. You have been logged out. Please login again.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid Page Request. Please login.");
        }
        return "redirect:/login";
    }
}
