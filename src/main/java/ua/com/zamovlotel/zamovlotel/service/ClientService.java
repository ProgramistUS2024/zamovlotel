package ua.com.zamovlotel.zamovlotel.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Administrator;
import ua.com.zamovlotel.zamovlotel.entity.Client;
import ua.com.zamovlotel.zamovlotel.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    // Пошук усіх клієнтів
    public Flux<Client> findAll() {
        return clientRepository.findAll();
    }

    // Пошук клієнта за унікальним id
    public Mono<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    // Пошук клієнтів за ім'ям
    public Flux<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

    // Пошук клієнтів за номером телефону
    public Flux<Client> findByPhoneNumber(String phoneNumber) {
        return clientRepository.findByPhoneNumber(phoneNumber);
    }

    // Пошук клієнтів за електронною поштою
    public Flux<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Mono<? extends Client> save(Client existingClient) {
        return clientRepository.save(existingClient);
    }

    public Mono<Void> deleteById(Long id) {
        return clientRepository.deleteById(id);
    }
}
