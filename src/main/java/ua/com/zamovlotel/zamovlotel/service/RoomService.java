package ua.com.zamovlotel.zamovlotel.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Invoice;
import ua.com.zamovlotel.zamovlotel.entity.Room;
import ua.com.zamovlotel.zamovlotel.repository.RoomRepository;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    // Пошук всіх записів
    public Flux<Room> findAll() {
        return roomRepository.findAll();
    }

    // Пошук за унікальним id
    public Mono<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    // Пошук за номером кімнати (roomNumber)
    public Mono<Room> findByRoomNumber(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    // Пошук за ємністю кімнати (capacity)
    public Flux<Room> findByCapacity(int capacity) {
        return roomRepository.findByCapacity(capacity);
    }

    // Пошук за класом кімнати (roomClass)
    public Flux<Room> findByRoomClass(String roomClass) {
        return roomRepository.findByRoomClass(roomClass);
    }

    // Пошук за ціною за ніч (pricePerNight)
    public Flux<Room> findByPricePerNight(double pricePerNight) {
        return roomRepository.findByPricePerNight(pricePerNight);
    }

    // Пошук за класом кімнати та ємністю
    public Flux<Room> findByRoomClassAndCapacity(String roomClass, int capacity) {
        return roomRepository.findByRoomClassAndCapacity(roomClass, capacity);
    }

    public Mono<Room> save(Room room) {
        return roomRepository.save(room);
    }

    public Mono<Void> deleteById(Long id) {
        return roomRepository.deleteById(id);
    }
}
