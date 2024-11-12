package ua.com.zamovlotel.zamovlotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Administrator;
import ua.com.zamovlotel.zamovlotel.service.AdministratorService;

@RestController
@RequestMapping("/api/administrators")
public class AdministratorController {

    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    // Отримати всіх адміністраторів
    @GetMapping
    public Flux<Administrator> getAllAdministrators() {
        return administratorService.findAll();
    }

    // Отримати адміністратора за id
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Administrator>> getAdministratorById(@PathVariable Long id) {
        return administratorService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Пошук адміністратора за ім'ям
    @GetMapping("/search/name")
    public Flux<Administrator> getAdministratorsByName(@RequestParam String name) {
        return administratorService.findByName(name);
    }

    // Пошук адміністратора за номером телефону
    @GetMapping("/search/phoneNumber")
    public Flux<Administrator> getAdministratorsByPhoneNumber(@RequestParam String phoneNumber) {
        return administratorService.findByPhoneNumber(phoneNumber);
    }

    // Пошук адміністратора за електронною поштою
    @GetMapping("/search/email")
    public Flux<Administrator> getAdministratorsByEmail(@RequestParam String email) {
        return administratorService.findByEmail(email);
    }

    // Створити нового адміністратора
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Administrator> createAdministrator(@RequestBody Administrator administrator) {
        return administratorService.save(administrator);
    }

    // Оновити адміністратора
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Administrator>> updateAdministrator(@PathVariable Long id, @RequestBody Administrator administrator) {
        return administratorService.findById(id)
                .flatMap(existingAdmin -> {
                    existingAdmin.setName(administrator.getName());
                    existingAdmin.setPhoneNumber(administrator.getPhoneNumber());
                    existingAdmin.setEmail(administrator.getEmail());
                    return administratorService.save(existingAdmin);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Видалити адміністратора за id
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteAdministrator(@PathVariable Long id) {
        return administratorService.findById(id)
                .flatMap(existingAdmin ->
                        administratorService.deleteById(id)
                                .then(Mono.just(ResponseEntity.noContent().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
