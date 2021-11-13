package com.example.hackathon21.repositorio;

import com.example.hackathon21.dto.RespuestaDTO;
import reactor.core.publisher.Mono;

public interface IInformacionRepositorio {

    public Mono<RespuestaDTO> obtenerInformacionAPI(String numeroPeticion);
}
