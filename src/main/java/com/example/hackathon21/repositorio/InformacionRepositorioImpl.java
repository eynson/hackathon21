package com.example.hackathon21.repositorio;

import com.example.hackathon21.dto.RespuestaDTO;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Repository
public class InformacionRepositorioImpl implements IInformacionRepositorio {

    private static final String NUMERO = "number";

    @Value("${config.base.endpoint}")
    private String url;

    private Cache<String, RespuestaDTO> caffeineCache;

    private static final Logger log = LoggerFactory.getLogger(InformacionRepositorioImpl.class);

    public InformacionRepositorioImpl() {
        iniciarCache(10); 
    }

    @Override
    public Mono<RespuestaDTO> obtenerInformacionAPI(String numeroPeticion) {
        RespuestaDTO respuestaDtoCache = caffeineCache.getIfPresent(numeroPeticion);
        if (respuestaDtoCache != null) {
            log.info("consultando en la cache");
            return Mono.just(respuestaDtoCache);
        } else {
            return registrarWebClient().get()
                    .uri(builder -> builder.queryParam(NUMERO, numeroPeticion).build())
                    .retrieve()
                    .bodyToMono(RespuestaDTO.class)
                    .retryWhen(Retry.backoff(8, Duration.ofSeconds(4)))
                    .doOnNext( respuesta -> {
                        iniciarCache(respuesta.getValiditySeconds());
                        caffeineCache.put(numeroPeticion, respuesta);
                    });
        }
    }

    private WebClient registrarWebClient() {
        return WebClient.create(url);
    }

    private void iniciarCache(int expiracionToken){
        caffeineCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(Duration.ofSeconds(expiracionToken))
                .build();
    }

}
