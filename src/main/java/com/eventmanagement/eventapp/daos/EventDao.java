package com.eventmanagement.eventapp.daos;

import com.eventmanagement.eventapp.models.Event;
import com.eventmanagement.eventapp.models.EventStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// EventDAO Class
@Repository
public class EventDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private AddressDao addressDao;

    // save a newly created event details in the database
    public void createEvent(Event event){
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        session.save(event);
        t.commit();
    }

    // fetch all the events from the database
    public List<Event> getEvents(){
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        Query<Event> query = session.createQuery("from Event" , Event.class);
        List<Event> events =  query.list();
        t.commit();
        return events;
    }

    // Updating the status of an event request to 'APPROVED' in the database
    public void approveEvent(Long eventId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        Event event = session.get(Event.class, eventId);
        event.setStatus(EventStatus.APPROVED);
        session.update(event);
        t.commit();
    }

    // Updating the status of an event request to 'REJECTED' in the database
    public void rejectEvent(Long eventId){
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        Event event = session.get(Event.class, eventId);
        event.setStatus(EventStatus.REJECTED);
        session.update(event);
        t.commit();
    }

    // fetching an event from the database by ID
    public Event getEvent(Long eventId){
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        Event event = session.get(Event.class, eventId);
        t.commit();
        return event;
    }

    // fetching the list of new event requests pending for admin decision
    public List<Event> getPendingEvents() {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Event e WHERE e.status = :status";
        List<Event> pendingEvents = session.createQuery(hql, Event.class)
                .setParameter("status", EventStatus.PENDING)
                .getResultList();
        t.commit();
        return pendingEvents;
    }

    // fetching the list of approved events
    public List<Event> getApprovedEvents() {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Event e WHERE e.status = :status";
        List<Event> pendingEvents = session.createQuery(hql, Event.class)
                .setParameter("status", EventStatus.APPROVED)
                .getResultList();
        t.commit();
        return pendingEvents;
    }

    // updating the details of an event in the database
    public void updateEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        session.update(event);
        t.commit();
    }

    // fetching the list of events as per search
    public List<Event> getSearchedEvents(String title) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();

        String hql = "FROM Event e WHERE LOWER(e.title) LIKE LOWER(:title)";
        List<Event> results = session.createQuery(hql, Event.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();

        t.commit();
        return results;
    }
}
