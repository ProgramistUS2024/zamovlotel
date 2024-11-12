package ua.com.zamovlotel.zamovlotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Client;
import ua.com.zamovlotel.zamovlotel.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Отримати список всіх клієнтів
    @GetMapping
    public Flux<Client> getAllClients() {
        return clientService.findAll();
    }

    // Отримати клієнта за id
    @GetMapping("/{id}")
    public Mono<Client> getClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    // Пошук клієнтів за ім'ям
    @GetMapping("/search/name")
    public Flux<Client> getClientsByName(@RequestParam String name) {
        return clientService.findByName(name);
    }

    // Пошук клієнтів за номером телефону
    @GetMapping("/search/phone")
    public Flux<Client> getClientsByPhoneNumber(@RequestParam String phoneNumber) {
        return clientService.findByPhoneNumber(phoneNumber);
    }

    // Пошук клієнтів за email
    @GetMapping("/search/email")
    public Flux<Client> getClientsByEmail(@RequestParam String email) {
        return clientService.findByEmail(email);
    }

    // Створення нового клієнта
    @PostMapping
    public Mono<Client> createClient(@RequestBody Client client) {
        return (Mono<Client>) clientService.save(client);
    }

    // Оновлення існуючого клієнта
    @PutMapping("/{id}")
    public Mono<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.findById(id)
                .flatMap(existingClient -> {
                    existingClient.setName(client.getName());
                    existingClient.setPhoneNumber(client.getPhoneNumber());
                    existingClient.setEmail(client.getEmail());
                    return clientService.save(existingClient);
                });
    }

    // Видалення клієнта за id
    @DeleteMapping("/{id}")
    public Mono<Void> deleteClient(@PathVariable Long id) {
        return clientService.deleteById(id);
    }
}