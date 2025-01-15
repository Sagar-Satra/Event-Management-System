package com.eventmanagement.eventapp.daos;

import com.eventmanagement.eventapp.models.Event;
import com.eventmanagement.eventapp.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// UserDAO Class
@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    // saving the details of a user in the database
    public void createUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        session.save(user);
        t.commit();
    }

    // fetching a user by ID from the database
    public User getUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        User user = session.get(User.class, id);
        t.commit();
        return user;
    }

    // fetching a user by Email from the database to check uniqueness of the user
    public User getUserByEmail(String email){
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();

        String hql = "FROM User WHERE email = :email";
        User user = session.createQuery(hql , User.class).setParameter("email", email).uniqueResult();

        t.commit();
        return user;
    }

    // updating the details of the user in the database
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        session.update(user);
        t.commit();
    }

    // fetch the list of events which are booked/registered by a particular User
    public List<Event> getMyBookedEvents(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Event e JOIN e.participants u WHERE u.id = :userId";
        List<Event> events = session.createQuery(hql, Event.class)
                .setParameter("userId", user.getId())
                .getResultList();

        t.commit();
        return events;
    }

    // fetch the list of events which are organized by a particular user
    public List<Event> getMyOrganizedEvents(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Event e WHERE e.organizer.id = :userId";
        List<Event> events = session.createQuery(hql, Event.class)
                .setParameter("userId", user.getId())
                .getResultList();
        t.commit();
        return events;
    }
}
