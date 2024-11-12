package ua.com.zamovlotel.zamovlotel.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Invoice;
import ua.com.zamovlotel.zamovlotel.entity.Room;

@Repository
public interface RoomRepository extends R2dbcRepository<Room, Long> {

    Flux<Room> findAll();
    // Пошук за унікальним id, очікуємо один або жоден результат
    Mono<Room> findById(Long id);

    // Пошук за номером кімнати (roomNumber), кожен номер є унікальним, тому використовуємо Mono
    Mono<Room> findByRoomNumber(String roomNumber);

    // Пошук за ємністю кімнати (capacity), може бути кілька кімнат з однаковою ємністю
    Flux<Room> findByCapacity(int capacity);

    // Пошук за класом кімнати (roomClass), може бути кілька кімнат одного класу
    Flux<Room> findByRoomClass(String roomClass);

    // Пошук за ціною за ніч (pricePerNight), може бути кілька кімнат з однаковою ціною
    Flux<Room> findByPricePerNight(double pricePerNight);

    // Пошук за класом кімнати та ємністю, можна отримати кілька кімнат з однаковим класом і ємністю
    Flux<Room> findByRoomClassAndCapacity(String roomClass, int capacity);

}
