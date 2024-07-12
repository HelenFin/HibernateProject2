package org.example;

import org.example.entity.Client;
import org.example.service.ClientCrudService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestClientCrudService {

    private ClientCrudService clientService;
    private Client client;

    @BeforeEach
    public void setup() {
        clientService = new ClientCrudService();
        client = new Client();
        client.setName("Test Client");
        client.setEmail("test.client@example.com");
        clientService.createClient(client);
    }

    @Test
    public void testCreateClient() {
        Client retrievedClient = clientService.readClient(client.getId());
        assertNotNull(retrievedClient);
        assertEquals("Test Client", retrievedClient.getName());
        assertEquals("test.client@example.com", retrievedClient.getEmail());
    }

    @Test
    public void testReadClient() {
        Client retrievedClient = clientService.readClient(client.getId());
        assertNotNull(retrievedClient);
        assertEquals("Test Client", retrievedClient.getName());
    }

    @Test
    public void testUpdateClient() {
        Client retrievedClient = clientService.readClient(client.getId());
        retrievedClient.setName("Updated Test Client");
        clientService.updateClient(retrievedClient);

        Client updatedClient = clientService.readClient(client.getId());
        assertNotNull(updatedClient);
        assertEquals("Updated Test Client", updatedClient.getName());
    }

    @Test
    public void testDeleteClient() {
        clientService.deleteClient(client.getId());
        Client deletedClient = clientService.readClient(client.getId());
        assertNull(deletedClient);
    }

    @AfterEach
    public void tearDown() {
        if (clientService.readClient(client.getId()) != null) {
            clientService.deleteClient(client.getId());
        }
    }
}
