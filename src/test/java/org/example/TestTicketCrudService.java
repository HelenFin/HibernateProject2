package org.example;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.example.service.TicketCrudService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestTicketCrudService {

    private TicketCrudService ticketService;
    private ClientCrudService clientService;
    private PlanetCrudService planetService;
    private Client client;
    private Planet fromPlanet;
    private Planet toPlanet;

    @BeforeEach
    public void setup() {
        ticketService = new TicketCrudService();
        clientService = new ClientCrudService();
        planetService = new PlanetCrudService();

        client = new Client();
        client.setName("Test Client");
        client.setEmail("test.client@example.com");
        clientService.createClient(client);

        fromPlanet = new Planet();
        fromPlanet.setId("FROMTEST");
        fromPlanet.setName("From Test Planet");
        fromPlanet.setDistance(143.0);
        planetService.createPlanet(fromPlanet);

        toPlanet = new Planet();
        toPlanet.setId("TOTEST");
        toPlanet.setName("To Test Planet");
        toPlanet.setDistance(200.0);
        planetService.createPlanet(toPlanet);
    }

    @Test
    public void testCreateTicket() {
        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        ticket.setPurchaseDate(new Date());
        ticketService.createTicket(ticket);

        Ticket retrievedTicket = ticketService.readTicket(ticket.getId());
        assertNotNull(retrievedTicket);
        assertEquals(client.getId(), retrievedTicket.getClient().getId());
        assertEquals(fromPlanet.getId(), retrievedTicket.getFromPlanet().getId());
        assertEquals(toPlanet.getId(), retrievedTicket.getToPlanet().getId());
    }

    @Test
    public void testReadTicket() {
        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        ticket.setPurchaseDate(new Date());
        ticketService.createTicket(ticket);

        Ticket retrievedTicket = ticketService.readTicket(ticket.getId());
        assertNotNull(retrievedTicket);
    }

    @Test
    public void testUpdateTicket() {
        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        ticket.setPurchaseDate(new Date());
        ticketService.createTicket(ticket);

        ticket.setPurchaseDate(new Date());
        ticketService.updateTicket(ticket);

        Ticket updatedTicket = ticketService.readTicket(ticket.getId());
        assertNotNull(updatedTicket);
        assertEquals(ticket.getPurchaseDate(), updatedTicket.getPurchaseDate());
    }

    @Test
    public void testDeleteTicket() {
        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        ticket.setPurchaseDate(new Date());
        ticketService.createTicket(ticket);

        ticketService.deleteTicket(ticket.getId());
        Ticket deletedTicket = ticketService.readTicket(ticket.getId());
        assertNull(deletedTicket);
    }

    @AfterEach
    public void tearDown() {
        if (clientService.readClient(client.getId()) != null) {
            clientService.deleteClient(client.getId());
        }
        if (planetService.readPlanet(fromPlanet.getId()) != null) {
            planetService.deletePlanet(fromPlanet.getId());
        }
        if (planetService.readPlanet(toPlanet.getId()) != null) {
            planetService.deletePlanet(toPlanet.getId());
        }
    }
}
