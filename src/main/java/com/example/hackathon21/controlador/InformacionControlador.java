package com.example.hackathon21.controlador;


import com.example.hackathon21.dto.RespuestaDTO;
import com.example.hackathon21.repositorio.IInformacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class InformacionControlador {

    @Autowired
    private IInformacionRepositorio informacionRepositorio;

    @GetMapping
    public Mono<RespuestaDTO> obtenerInformacionAPI() {
        return informacionRepositorio.obtenerInformacionAPI();
    }
}
