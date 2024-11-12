package ua.com.zamovlotel.zamovlotel.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Administrator;
import ua.com.zamovlotel.zamovlotel.entity.Invoice;
import ua.com.zamovlotel.zamovlotel.entity.Room;
import ua.com.zamovlotel.zamovlotel.repository.InvoiceRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;


    // Пошук всіх записів рахунків
    public Flux<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    // Пошук запису за унікальним id
    public Mono<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    // Пошук за номером (Room)
    public Flux<Invoice> findByRoom(Room room) {
        return invoiceRepository.findByRoom(room);
    }

    // Пошук за датою заїзду
    public Flux<Invoice> findByCheckInDate(LocalDate checkInDate) {
        return invoiceRepository.findByCheckInDate(checkInDate);
    }

    // Пошук за датою виїзду
    public Flux<Invoice> findByCheckOutDate(LocalDate checkOutDate) {
        return invoiceRepository.findByCheckOutDate(checkOutDate);
    }

    // Пошук за статусом бронювання
    public Flux<Invoice> findByStatus(String status) {
        return invoiceRepository.findByStatus(status);
    }

    // Пошук за датами заїзду та виїзду для знаходження бронювань у певному періоді
    public Flux<Invoice> findByCheckInDateBeforeAndCheckOutDateAfter(LocalDate checkOutDate, LocalDate checkInDate) {
        return invoiceRepository.findByCheckInDateBeforeAndCheckOutDateAfter(checkOutDate, checkInDate);
    }

    // Пошук за загальною сумою для знаходження бронювань у межах певної суми
    public Flux<Invoice> findByTotalAmountBetween(double minAmount, double maxAmount) {
        return invoiceRepository.findByTotalAmountBetween(minAmount, maxAmount);
    }

    public Mono<Invoice> save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Mono<Void> deleteById(Long id) {
        return invoiceRepository.deleteById(id);
    }
}
