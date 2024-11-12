package ua.com.zamovlotel.zamovlotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Invoice;
import ua.com.zamovlotel.zamovlotel.entity.Room;
import ua.com.zamovlotel.zamovlotel.service.InvoiceService;

import java.time.LocalDate;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Отримати список всіх інвойсів
    @GetMapping
    public Flux<Invoice> getAllInvoices() {
        return invoiceService.findAll();
    }

    // Отримати інвойс за id
    @GetMapping("/{id}")
    public Mono<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.findById(id);
    }

    // Пошук за номером кімнати
    @GetMapping("/search/room")
    public Flux<Invoice> getInvoicesByRoom(@RequestParam Room room) {
        return invoiceService.findByRoom(room);
    }

    // Пошук за датою заїзду
    @GetMapping("/search/checkin")
    public Flux<Invoice> getInvoicesByCheckInDate(@RequestParam LocalDate checkInDate) {
        return invoiceService.findByCheckInDate(checkInDate);
    }

    // Пошук за датою виїзду
    @GetMapping("/search/checkout")
    public Flux<Invoice> getInvoicesByCheckOutDate(@RequestParam LocalDate checkOutDate) {
        return invoiceService.findByCheckOutDate(checkOutDate);
    }

    // Пошук за статусом
    @GetMapping("/search/status")
    public Flux<Invoice> getInvoicesByStatus(@RequestParam String status) {
        return invoiceService.findByStatus(status);
    }

    // Пошук за датами заїзду та виїзду
    @GetMapping("/search/dates")
    public Flux<Invoice> getInvoicesByCheckInDateBeforeAndCheckOutDateAfter(
            @RequestParam LocalDate checkInDate,
            @RequestParam LocalDate checkOutDate) {
        return invoiceService.findByCheckInDateBeforeAndCheckOutDateAfter(checkOutDate, checkInDate);
    }

    // Пошук за сумою
    @GetMapping("/search/amount")
    public Flux<Invoice> getInvoicesByTotalAmountBetween(
            @RequestParam double minAmount,
            @RequestParam double maxAmount) {
        return invoiceService.findByTotalAmountBetween(minAmount, maxAmount);
    }

    // Створення нового інвойсу
    @PostMapping
    public Mono<Invoice> createInvoice(@RequestBody Invoice invoice) {
        return invoiceService.save(invoice);
    }

    // Оновлення існуючого інвойсу
    @PutMapping("/{id}")
    public Mono<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        return invoiceService.findById(id)
                .flatMap(existingInvoice -> {
                    existingInvoice.setRoom(invoice.getRoom());
                    existingInvoice.setCheckInDate(invoice.getCheckInDate());
                    existingInvoice.setCheckOutDate(invoice.getCheckOutDate());
                    existingInvoice.setTotalAmount(invoice.getTotalAmount());
                    existingInvoice.setStatus(invoice.getStatus());
                    return invoiceService.save(existingInvoice);
                });
    }

    // Видалення інвойсу за id
    @DeleteMapping("/{id}")
    public Mono<Void> deleteInvoice(@PathVariable Long id) {
        return invoiceService.deleteById(id);
    }
}