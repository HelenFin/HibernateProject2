package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "planet")
public class Planet {

    @Id
    @Column(length = 10)
    @Pattern(regexp = "^[A-Z0-9]+$")
    private String id;

    @Column(nullable = false)
    @Size(min = 1, max = 500)
    private String name;

    @Column(nullable = false)
    private double distance;

    @OneToMany(mappedBy = "planet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
