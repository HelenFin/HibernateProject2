package org.example.util;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:h2:file:./database/spacetravel");
            configuration.setProperty("hibernate.connection.username", "sa");
            configuration.setProperty("hibernate.connection.password", "");

            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");

            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Planet.class);
            configuration.addAnnotatedClass(Ticket.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
