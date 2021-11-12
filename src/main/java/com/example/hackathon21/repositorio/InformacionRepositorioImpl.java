package com.example.hackathon21.repositorio;

import com.example.hackathon21.dto.RespuestaDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Repository
public class InformacionRepositorioImpl implements IInformacionRepositorio {

    

    @Override
    public Mono<RespuestaDTO> obtenerInformacionAPI() {
        return null;
    }
}
