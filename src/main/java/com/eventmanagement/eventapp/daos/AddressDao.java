package com.eventmanagement.eventapp.daos;

import com.eventmanagement.eventapp.models.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

// AddressDao class
@Repository
public class AddressDao {


    @Autowired
    private SessionFactory sessionFactory;

    // save a new address
    public Address createAddress(Address address) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        session.persist(address);
        t.commit();
        return address;
    }

    // fetching an address by ID
    public Address getAddressById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        Address address = session.get(Address.class, id);
        t.commit();
        return address;
    }
//
//    public Address updateAddress(Address address) {
//        Session session = sessionFactory.getCurrentSession();
//        Transaction t = session.beginTransaction();
//        session.merge(address);
//        t.commit();
//        return address;
//    }

    // Updating an address in the database
    public Address updateAddress(Address address) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        session.update(address);
        t.commit();
        return address;
    }

    // fetching all the addresses from the database
    public List<Address> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        List<Address> addresses = (List<Address>) session.createQuery("from Address").list();
        t.commit();
        return addresses;
    }

    // fetching all the valid addresses which are available for a specific date and time
    public List<Address> getValidAddresses(LocalDate date, LocalTime startTime, LocalTime endTime) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            // HQL query to find addresses not overlapping with events on the same date and within the time range
            String hql = """
            SELECT DISTINCT a 
            FROM Address a
            WHERE a.availableForEvent = true
            AND a.id NOT IN (
                SELECT e.location.id 
                FROM Event e
                WHERE e.date = :date
                AND (
                    (e.startTime < :endTime AND e.endTime > :startTime)
                )
            )
        """;

            List<Address> addresses = session.createQuery(hql, Address.class)
                    .setParameter("date", date)
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .getResultList();

            transaction.commit();
            return addresses;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
