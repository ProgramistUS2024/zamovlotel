package ua.com.zamovlotel.zamovlotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Room;
import ua.com.zamovlotel.zamovlotel.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Отримати список всіх кімнат
    @GetMapping
    public Flux<Room> getAllRooms() {
        return roomService.findAll();
    }

    // Отримати кімнату за id
    @GetMapping("/{id}")
    public Mono<Room> getRoomById(@PathVariable Long id) {
        return roomService.findById(id);
    }

    // Пошук за номером кімнати (roomNumber)
    @GetMapping("/search/roomNumber")
    public Mono<Room> getRoomByRoomNumber(@RequestParam String roomNumber) {
        return roomService.findByRoomNumber(roomNumber);
    }

    // Пошук за ємністю кімнати (capacity)
    @GetMapping("/search/capacity")
    public Flux<Room> getRoomsByCapacity(@RequestParam int capacity) {
        return roomService.findByCapacity(capacity);
    }

    // Пошук за класом кімнати (roomClass)
    @GetMapping("/search/class")
    public Flux<Room> getRoomsByRoomClass(@RequestParam String roomClass) {
        return roomService.findByRoomClass(roomClass);
    }

    // Пошук за ціною за ніч (pricePerNight)
    @GetMapping("/search/price")
    public Flux<Room> getRoomsByPricePerNight(@RequestParam double pricePerNight) {
        return roomService.findByPricePerNight(pricePerNight);
    }

    // Пошук за класом кімнати та ємністю
    @GetMapping("/search/class-capacity")
    public Flux<Room> getRoomsByRoomClassAndCapacity(@RequestParam String roomClass, @RequestParam int capacity) {
        return roomService.findByRoomClassAndCapacity(roomClass, capacity);
    }

    // Створення нової кімнати
    @PostMapping
    public Mono<Room> createRoom(@RequestBody Room room) {
        return roomService.save(room);
    }

    // Оновлення існуючої кімнати
    @PutMapping("/{id}")
    public Mono<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomService.findById(id)
                .flatMap(existingRoom -> {
                    existingRoom.setRoomNumber(room.getRoomNumber());
                    existingRoom.setCapacity(room.getCapacity());
                    existingRoom.setRoomClass(room.getRoomClass());
                    existingRoom.setPricePerNight(room.getPricePerNight());
                    return roomService.save(existingRoom);
                });
    }

    // Видалення кімнати за id
    @DeleteMapping("/{id}")
    public Mono<Void> deleteRoom(@PathVariable Long id) {
        return roomService.deleteById(id);
    }
}
