package ua.com.zamovlotel.zamovlotel.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.zamovlotel.zamovlotel.entity.Client;
import ua.com.zamovlotel.zamovlotel.entity.Greeting;


@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hello, Spring")));
    }

    public Mono<ServerResponse> home(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Main page!"));
    }

    public Mono<ServerResponse> getClients(ServerRequest request) {

        String start = request
                .queryParam("start")
                .orElse("0");


        Flux<Client> clients = Flux.just(
                        new Client(),
                        new Client(),
                        new Client()
                )
                .skip(Long.valueOf(start))
                .take(2);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(clients, Client.class);
    }



    public Mono<ServerResponse> anketa(ServerRequest request) {

        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("This is page anketa"));
    }
    public Mono<ServerResponse> room(ServerRequest request) {

        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("This is page room"));
    }


}

