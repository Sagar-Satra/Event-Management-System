package com.eventmanagement.eventapp.controllers;

import com.eventmanagement.eventapp.models.Event;
import com.eventmanagement.eventapp.models.Role;
import com.eventmanagement.eventapp.models.User;
import com.eventmanagement.eventapp.services.EventService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

// AdminController - For admin related activities only
@Controller
public class AdminController {

    @Autowired
    private EventService eventService;
    @Autowired
    private Event event;

    // to view list of new event requests, pending for approval by admin
    @GetMapping("/admin/home/eventRequests")
    public String pendingEvents(HttpSession session , ModelMap map , RedirectAttributes redirectAttributes){
        try{
        User user = (User) session.getAttribute("user");
        if(user == null ){
            redirectAttributes.addFlashAttribute("message", "Unauthorized Access. Please login.");
            return "redirect:/login";
        }

        if(!isAdmin(user)){
            redirectAttributes.addFlashAttribute("message", "Unauthorized Access.");
            return "redirect:/home";
        }

        List<Event> pendingEvents = eventService.getPendingEvents();
        map.put("pendingEvents", pendingEvents);
        return "eventRequests";
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "Error fetching event requests. Please try again. " + e.getMessage());
            return "redirect:/error";
        }
    }

    // API for approving an event request by admin
    @RequestMapping("/api/admin/event/{eid}/approve")
    public String approveEvent(HttpSession session , @PathVariable Long eid , RedirectAttributes redirectAttributes ){
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access. Please login.");
                return "redirect:/login";
            }

            if (!isAdmin(user)) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access.");
                return "redirect:/home";
            }

            eventService.approveRequest(eid);
            redirectAttributes.addFlashAttribute("message", "Approved");
            return "redirect:/admin/home/eventRequests";
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "Error in processing event. Please try again. " + e.getMessage());
            return "redirect:/error";
        }
    }

    // API for rejecting an event request by admin
    @RequestMapping("/api/admin/event/{eid}/reject")
    public String rejectEvent(HttpSession session , @PathVariable Long eid , RedirectAttributes redirectAttributes ){
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access. Please login.");
                return "redirect:/login";
            }

            if (!isAdmin(user)) {
                redirectAttributes.addFlashAttribute("message", "Unauthorized Access.");
                return "redirect:/home";
            }

            eventService.rejectRequest(eid);
            redirectAttributes.addFlashAttribute("message", "Rejected");
            return "redirect:/admin/home/eventRequests";
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "Error in processing event. Please try again. " + e.getMessage());
            return "redirect:/error";
        }
    }

    // to check if the user is an admin with role as ADMIN
    private static boolean isAdmin(User user){
        return user.getRole().equals(Role.ADMIN);
    }

    // handler method to safeguard incorrect api/url calls and route them properly
    @GetMapping({"/admin", "/admin/home", "/eventRequests", "/admin/eventRequests", "/api/admin/event", "/api/admin", "/api", "/approve", "/reject"} )
    public String handleUnknownAdminURL(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("user") != null) {
            session.invalidate();
            redirectAttributes.addFlashAttribute("error", "Invalid Page Request. You have been logged out. Please login again.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid Page Request. Please login.");
        }
        return "redirect:/login";
    }
}
