package ua.com.zamovlotel.zamovlotel.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Client;
import ua.com.zamovlotel.zamovlotel.entity.Invoice;
import ua.com.zamovlotel.zamovlotel.entity.Request;
import ua.com.zamovlotel.zamovlotel.repository.RequestRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    // Пошук всіх записів
    public Flux<Request> findAll() {
        return requestRepository.findAll();
    }

    // Пошук за унікальним id
    public Mono<Request> findById(Long id) {
        return requestRepository.findById(id);
    }

    // Пошук за клієнтом
    public Flux<Request> findByClient(Client client) {
        return requestRepository.findByClient(client);
    }

    // Пошук за ємністю кімнати
    public Flux<Request> findByRoomCapacity(int roomCapacity) {
        return requestRepository.findByRoomCapacity(roomCapacity);
    }

    // Пошук за класом кімнати
    public Flux<Request> findByRoomClass(String roomClass) {
        return requestRepository.findByRoomClass(roomClass);
    }

    // Пошук за датою заїзду
    public Flux<Request> findByCheckInDate(LocalDate checkInDate) {
        return requestRepository.findByCheckInDate(checkInDate);
    }

    // Пошук за датою виїзду
    public Flux<Request> findByCheckOutDate(LocalDate checkOutDate) {
        return requestRepository.findByCheckOutDate(checkOutDate);
    }

    // Пошук за статусом бронювання
    public Flux<Request> findByStatus(String status) {
        return requestRepository.findByStatus(status);
    }

    // Пошук за клієнтом та статусом
    public Flux<Request> findByClientAndStatus(Client client, String status) {
        return requestRepository.findByClientAndStatus(client, status);
    }

    // Пошук за інвойсом
    public Flux<Request> findByInvoice(Invoice invoice) {
        return requestRepository.findByInvoice(invoice);
    }

    // Пошук за датами заїзду та виїзду
    public Flux<Request> findByCheckInDateBeforeAndCheckOutDateAfter(LocalDate checkOutDate, LocalDate checkInDate) {
        return requestRepository.findByCheckInDateBeforeAndCheckOutDateAfter(checkOutDate, checkInDate);
    }

    public Mono<Request> save(Request request) {
        return requestRepository.save(request);
    }

    public Mono<Void> deleteById(Long id) {
        return requestRepository.deleteById(id);
    }
}
