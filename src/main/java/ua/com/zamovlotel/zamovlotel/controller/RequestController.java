package ua.com.zamovlotel.zamovlotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Client;
import ua.com.zamovlotel.zamovlotel.entity.Invoice;
import ua.com.zamovlotel.zamovlotel.entity.Request;
import ua.com.zamovlotel.zamovlotel.service.RequestService;

import java.time.LocalDate;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    // Отримати список всіх запитів
    @GetMapping
    public Flux<Request> getAllRequests() {
        return requestService.findAll();
    }

    // Отримати запит за id
    @GetMapping("/{id}")
    public Mono<Request> getRequestById(@PathVariable Long id) {
        return requestService.findById(id);
    }

    // Пошук за клієнтом (Client)
    @GetMapping("/search/client")
    public Flux<Request> getRequestsByClient(@RequestParam Client client) {
        return requestService.findByClient(client);
    }

    // Пошук за ємністю кімнати (roomCapacity)
    @GetMapping("/search/capacity")
    public Flux<Request> getRequestsByRoomCapacity(@RequestParam int roomCapacity) {
        return requestService.findByRoomCapacity(roomCapacity);
    }

    // Пошук за класом кімнати (roomClass)
    @GetMapping("/search/class")
    public Flux<Request> getRequestsByRoomClass(@RequestParam String roomClass) {
        return requestService.findByRoomClass(roomClass);
    }

    // Пошук за датою заїзду (checkInDate)
    @GetMapping("/search/checkin")
    public Flux<Request> getRequestsByCheckInDate(@RequestParam LocalDate checkInDate) {
        return requestService.findByCheckInDate(checkInDate);
    }

    // Пошук за датою виїзду (checkOutDate)
    @GetMapping("/search/checkout")
    public Flux<Request> getRequestsByCheckOutDate(@RequestParam LocalDate checkOutDate) {
        return requestService.findByCheckOutDate(checkOutDate);
    }

    // Пошук за статусом
    @GetMapping("/search/status")
    public Flux<Request> getRequestsByStatus(@RequestParam String status) {
        return requestService.findByStatus(status);
    }

    // Пошук за клієнтом та статусом
    @GetMapping("/search/client-status")
    public Flux<Request> getRequestsByClientAndStatus(@RequestParam Client client, @RequestParam String status) {
        return requestService.findByClientAndStatus(client, status);
    }

    // Пошук за інвойсом
    @GetMapping("/search/invoice")
    public Flux<Request> getRequestsByInvoice(@RequestParam Invoice invoice) {
        return requestService.findByInvoice(invoice);
    }

    // Пошук за датами заїзду та виїзду
    @GetMapping("/search/dates")
    public Flux<Request> getRequestsByCheckInDateBeforeAndCheckOutDateAfter(
            @RequestParam LocalDate checkInDate,
            @RequestParam LocalDate checkOutDate) {
        return requestService.findByCheckInDateBeforeAndCheckOutDateAfter(checkOutDate, checkInDate);
    }

    // Створення нового запиту
    @PostMapping
    public Mono<Request> createRequest(@RequestBody Request request) {
        return requestService.save(request);
    }

    // Оновлення існуючого запиту
    @PutMapping("/{id}")
    public Mono<Request> updateRequest(@PathVariable Long id, @RequestBody Request request) {
        return requestService.findById(id)
                .flatMap(existingRequest -> {
                    existingRequest.setClient(request.getClient());
                    existingRequest.setRoomCapacity(request.getRoomCapacity());
                    existingRequest.setRoomClass(request.getRoomClass());
                    existingRequest.setCheckInDate(request.getCheckInDate());
                    existingRequest.setCheckOutDate(request.getCheckOutDate());
                    existingRequest.setStatus(request.getStatus());
                    existingRequest.setInvoice(request.getInvoice());
                    return requestService.save(existingRequest);
                });
    }

    // Видалення запиту за id
    @DeleteMapping("/{id}")
    public Mono<Void> deleteRequest(@PathVariable Long id) {
        return requestService.deleteById(id);
    }
}
