package ua.com.zamovlotel.zamovlotel.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import java.util.List;

@Data
@Table(name ="Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private int capacity;
    private String roomClass;
    private double pricePerNight;

    @OneToMany(mappedBy = "room")
    private List<Invoice> invoices;
}