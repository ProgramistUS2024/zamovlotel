package ua.com.zamovlotel.zamovlotel.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Client;

@Repository
public interface ClientRepository extends R2dbcRepository<Client, Long> {

    Flux<Client> findAll();
    // Пошук за унікальним id, очікуємо один або жоден результат
    Mono<Client> findById(Long id);

    // Пошук за ім'ям, може бути кілька адміністраторів з однаковим ім'ям
    Flux<Client> findByName(String name);

    // Пошук за номером телефону, також може бути кілька адміністраторів з однаковим номером
    Flux<Client> findByPhoneNumber(String phoneNumber);

    // Пошук за електронною поштою, може бути кілька адміністраторів з однаковою поштою
    Flux<Client> findByEmail(String email);

}