package com.example.hackathon21.controlador;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/healthz")
public class HealthzControlador {

    @GetMapping
    public Mono<ResponseEntity> obtenerEstado(){
        return Mono.just(ResponseEntity.ok().build());
    }

}
