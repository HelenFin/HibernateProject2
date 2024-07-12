package org.example.service;

import org.example.entity.Ticket;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketCrudService {

    public void createTicket(Ticket ticket) {
        if (ticket.getClient() == null || ticket.getClient().getId() == 0) {
            throw new IllegalArgumentException("Ticket must have a valid client");
        }
        if (ticket.getPlanet() == null || ticket.getPlanet().getId() == null) {
            throw new IllegalArgumentException("Ticket must have a valid planet");
        }

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Ticket readTicket(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    public void updateTicket(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTicket(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.delete(ticket);
                System.out.println("Ticket is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Ticket> getAllTickets() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        }
    }
}
