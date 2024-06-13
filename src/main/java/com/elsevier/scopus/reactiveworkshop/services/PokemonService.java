package com.elsevier.scopus.reactiveworkshop.services;

import com.elsevier.scopus.reactiveworkshop.clients.IPokemonApiClient;
import com.elsevier.scopus.reactiveworkshop.models.PokemonList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PokemonService {

  private final IPokemonApiClient iPokemonApiClient;

  public Mono<PokemonList> getPokemonList() {
    return iPokemonApiClient.getPokemonListFromPokeAPI();
  }

}
