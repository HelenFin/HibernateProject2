package org.example.service;

import org.example.entity.Planet;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudService {

    public void createPlanet(Planet planet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(planet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Planet readPlanet(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Planet.class, id);
        }
    }

    public void updatePlanet(Planet planet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(planet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletePlanet(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.delete(planet);
                System.out.println("Planet is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Planet> getAllPlanets() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        }
    }
}
