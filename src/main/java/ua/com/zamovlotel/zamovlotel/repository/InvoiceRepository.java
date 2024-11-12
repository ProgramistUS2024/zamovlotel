package ua.com.zamovlotel.zamovlotel.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Invoice;
import ua.com.zamovlotel.zamovlotel.entity.Room;

import java.time.LocalDate;

@Repository
public interface InvoiceRepository extends R2dbcRepository<Invoice, Long> {

    Flux<Invoice> findAll();
    // Пошук за унікальним id, очікуємо один або жоден результат
    Mono<Invoice> findById(Long id);

    // Пошук за номером (Room), можемо отримати кілька записів для одного номера
    Flux<Invoice> findByRoom(Room room);

    // Пошук за датою заїзду, можемо отримати кілька бронювань на одну дату
    Flux<Invoice> findByCheckInDate(LocalDate checkInDate);

    // Пошук за датою виїзду
    Flux<Invoice> findByCheckOutDate(LocalDate checkOutDate);

    // Пошук за статусом бронювання, можна отримати кілька записів для одного статусу
    Flux<Invoice> findByStatus(String status);

    // Пошук за датами заїзду та виїзду, щоб знайти бронювання в певному періоді
    Flux<Invoice> findByCheckInDateBeforeAndCheckOutDateAfter(LocalDate checkOutDate, LocalDate checkInDate);

    // Пошук за загальною сумою, щоб знайти бронювання в межах певної суми
    Flux<Invoice> findByTotalAmountBetween(double minAmount, double maxAmount);
}