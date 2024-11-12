package ua.com.zamovlotel.zamovlotel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ua.com.zamovlotel.zamovlotel.entity.Client;

@RestController
public class MyController{

    @GetMapping("/clients")
    public Flux<Client> getClients() {
        Flux<Client> clients = Flux.just(
                        new Client(),
                        new Client()
                )
                .skip(0)
                .take(2);

        return clients;
    }
}

