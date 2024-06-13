package com.elsevier.scopus.reactiveworkshop.clients;

import com.elsevier.scopus.reactiveworkshop.models.PokemonList;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

/**
 * Reactive feign client for pokemon apis Note : This part is generally extracted in
 * scopustech-libraries as a generic client library
 */
@ReactiveFeignClient(name = "pokemonapi", url = "${pokemonapi.url}")
public interface IPokemonApiClient {

  @GetMapping(value = "/pokemon?limit=10&offset=0")
  Mono<PokemonList> getPokemonListFromPokeAPI();

}
