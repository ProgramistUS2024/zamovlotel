package ua.com.zamovlotel.zamovlotel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Administrator;
import ua.com.zamovlotel.zamovlotel.repository.AdministratorRepository;


@Service
@RequiredArgsConstructor
public class AdministratorService {
    private final AdministratorRepository administratorRepository;

    public Flux<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    // Пошук адміністратора за унікальним id
    public Mono<Administrator> findById(Long id) {
        return administratorRepository.findById(id);
    }

    // Пошук адміністраторів за ім'ям
    public Flux<Administrator> findByName(String name) {
        return administratorRepository.findByName(name);
    }

    // Пошук адміністраторів за номером телефону
    public Flux<Administrator> findByPhoneNumber(String phoneNumber) {
        return administratorRepository.findByPhoneNumber(phoneNumber);
    }

    // Пошук адміністраторів за електронною поштою
    public Flux<Administrator> findByEmail(String email) {
        return administratorRepository.findByEmail(email);
    }

    public Mono<Administrator> save(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    public Mono<Void> deleteById(Long id) {
        return administratorRepository.deleteById(id);
    }
}
