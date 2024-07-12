package org.example;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.example.util.HibernateUtil;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;


public class Main {

    public static void main(String[] args) {


        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:file:./database/spacetravel", "sa", "")
                .locations("filesystem:src/main/resources/db/migration")
                .load();
        flyway.migrate();


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

//        try {
//            ClientCrudService clientService = new ClientCrudService();
//            PlanetCrudService planetService = new PlanetCrudService();
//
//
//            // Create Clients
//
//            Client client1 = new Client();
//            client1.setName("Client 2");
//            client1.setEmail("client.1@example.com");
//            clientService.createClient(client1);
//
//            // Create Planets
//
//            Planet planet1 = new Planet();
//            planet1.setId("TEST");
//            planet1.setName("Moon");
//            planet1.setDistance(87.2);
//            planetService.createPlanet(planet1);
//
//
//            // Delete Client
//            clientService.deleteClient(client1.getId());
//
//            // Delete Planet
//            planetService.deletePlanet(planet1.getId());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (sessionFactory != null) {
//                sessionFactory.close();
//            }
//        }
    }
}
