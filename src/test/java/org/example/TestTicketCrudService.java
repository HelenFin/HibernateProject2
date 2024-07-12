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
    private Planet planet;

    @BeforeEach
    public void setup() {
        ticketService = new TicketCrudService();
        clientService = new ClientCrudService();
        planetService = new PlanetCrudService();

        client = new Client();
        client.setName("Test Client");
        client.setEmail("test.client@example.com");
        clientService.createClient(client);

        planet = new Planet();
        planet.setId("TEST");
        planet.setName("Test Planet");
        planet.setDistance(143.0);
        planetService.createPlanet(planet);
    }

    @Test
    public void testCreateTicket() {
        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setPlanet(planet);
        ticket.setPurchaseDate(new Date());
        ticketService.createTicket(ticket);

        Ticket retrievedTicket = ticketService.readTicket(ticket.getId());
        assertNotNull(retrievedTicket);
        assertEquals(client.getId(), retrievedTicket.getClient().getId());
        assertEquals(planet.getId(), retrievedTicket.getPlanet().getId());
    }

    @Test
    public void testReadTicket() {
        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setPlanet(planet);
        ticket.setPurchaseDate(new Date());
        ticketService.createTicket(ticket);

        Ticket retrievedTicket = ticketService.readTicket(ticket.getId());
        assertNotNull(retrievedTicket);
    }

    @Test
    public void testUpdateTicket() {
        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setPlanet(planet);
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
        ticket.setPlanet(planet);
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
        if (planetService.readPlanet(planet.getId()) != null) {
            planetService.deletePlanet(planet.getId());
        }
    }
}