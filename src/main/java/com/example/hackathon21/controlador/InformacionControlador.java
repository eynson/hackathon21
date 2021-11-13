package com.example.hackathon21.controlador;


import com.example.hackathon21.dto.RespuestaDTO;
import com.example.hackathon21.repositorio.IInformacionRepositorio;
import jdk.jfr.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class InformacionControlador {

    @Autowired
    private IInformacionRepositorio informacionRepositorio;

    @GetMapping
    public Mono<RespuestaDTO> obtenerInformacionAPI(@RequestParam(name = "number") String numero) {
        return informacionRepositorio.obtenerInformacionAPI(numero);
    }
}
