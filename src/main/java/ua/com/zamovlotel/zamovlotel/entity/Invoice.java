package ua.com.zamovlotel.zamovlotel.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Data
@Table(name ="Invoice")
public class Invoice {
    @Id
    private Long id;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalAmount;
    private String status;
}
