package ua.com.zamovlotel.zamovlotel.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table(name ="Client")
public class Client {

    @Id
    private Long id;

    private String name;
    private String phoneNumber;
    private String email;
}
