package org.example;

import org.example.entity.Planet;
import org.example.service.PlanetCrudService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlanetCrudService {

    private PlanetCrudService planetService;
    private Planet planet;

    @BeforeEach
    public void setup() {
        planetService = new PlanetCrudService();
        planet = new Planet();
        planet.setId("TEST");
        planet.setName("Test Planet");
        planet.setDistance(143.0);
        planetService.createPlanet(planet);
    }

    @Test
    public void testCreatePlanet() {
        Planet retrievedPlanet = planetService.readPlanet(planet.getId());
        assertNotNull(retrievedPlanet);
        assertEquals("Test Planet", retrievedPlanet.getName());
        assertEquals(143.0, retrievedPlanet.getDistance());
    }

    @Test
    public void testReadPlanet() {
        Planet retrievedPlanet = planetService.readPlanet(planet.getId());
        assertNotNull(retrievedPlanet);
        assertEquals("Test Planet", retrievedPlanet.getName());
    }

    @Test
    public void testUpdatePlanet() {
        Planet retrievedPlanet = planetService.readPlanet(planet.getId());
        retrievedPlanet.setDistance(187.0);
        planetService.updatePlanet(retrievedPlanet);

        Planet updatedPlanet = planetService.readPlanet(planet.getId());
        assertNotNull(updatedPlanet);
        assertEquals(187.0, updatedPlanet.getDistance());
    }

    @Test
    public void testDeletePlanet() {
        planetService.deletePlanet(planet.getId());
        Planet deletedPlanet = planetService.readPlanet(planet.getId());
        assertNull(deletedPlanet);
    }

    @AfterEach
    public void tearDown() {
        if (planetService.readPlanet(planet.getId()) != null) {
            planetService.deletePlanet(planet.getId());
        }
    }
}
