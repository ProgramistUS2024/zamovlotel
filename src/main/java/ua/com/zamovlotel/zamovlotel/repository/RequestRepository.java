package ua.com.zamovlotel.zamovlotel.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Client;
import ua.com.zamovlotel.zamovlotel.entity.Invoice;
import ua.com.zamovlotel.zamovlotel.entity.Request;

import java.time.LocalDate;

@Repository
public interface RequestRepository extends R2dbcRepository<Request, Long> {

    Flux<Request> findAll();
    // Пошук за унікальним id, очікуємо один або жоден результат
    Mono<Request> findById(Long id);

    // Пошук за клієнтом (Client), можна отримати кілька бронювань для одного клієнта
    Flux<Request> findByClient(Client client);

    // Пошук за ємністю кімнати, можна отримати кілька бронювань для однієї ємності
    Flux<Request> findByRoomCapacity(int roomCapacity);

    // Пошук за класом кімнати, можна отримати кілька бронювань для одного класу
    Flux<Request> findByRoomClass(String roomClass);

    // Пошук за датою заїзду, кілька бронювань можуть бути на одну і ту ж дату
    Flux<Request> findByCheckInDate(LocalDate checkInDate);

    // Пошук за датою виїзду
    Flux<Request> findByCheckOutDate(LocalDate checkOutDate);

    // Пошук за статусом бронювання
    Flux<Request> findByStatus(String status);

    // Пошук за клієнтом та статусом
    Flux<Request> findByClientAndStatus(Client client, String status);

    // Пошук за інвойсом (Invoice), можливо, кілька бронювань можуть бути з одним інвойсом
    Flux<Request> findByInvoice(Invoice invoice);

    // Пошук за датами заїзду та виїзду
    Flux<Request> findByCheckInDateBeforeAndCheckOutDateAfter(LocalDate checkOutDate, LocalDate checkInDate);

}