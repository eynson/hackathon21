package com.example.hackathon21.repositorio;

import com.example.hackathon21.dto.RespuestaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Repository
public class InformacionRepositorioImpl implements IInformacionRepositorio {

    private static final String NUMERO = "number";

    @Value("${config.base.endpoint}")
    private String url;

    @Override
    public Mono<RespuestaDTO> obtenerInformacionAPI(String numeroPeticion) {
        return invocarServicio(numeroPeticion)
                .flatMap(repuesta -> repuesta.bodyToMono(RespuestaDTO.class));
    }

    private Mono<ClientResponse> invocarServicio(String numeroPeticion) {
        return registrarWebClient().get()
                .uri(NUMERO, numeroPeticion)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(3)));
    }

    private WebClient registrarWebClient() {
        return WebClient.create(u

}
