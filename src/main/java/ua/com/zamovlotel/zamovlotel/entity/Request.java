package ua.com.zamovlotel.zamovlotel.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table(name ="Request")
public class Request {
    @Id
    private Long id;
    private Client client;
    private int roomCapacity;
    private String roomClass;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
    private Invoice invoice;
}