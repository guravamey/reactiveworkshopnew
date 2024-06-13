package com.elsevier.scopus.reactiveworkshop.controllers;

import com.elsevier.scopus.reactiveworkshop.models.PokemonList;
import com.elsevier.scopus.reactiveworkshop.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Rest controller for ReactiveWorkshop
 */
@RestController
@RequiredArgsConstructor
public class PokemonController {

  private final PokemonService pokemonService;

  /**
   * Example Endpoint for WebFlux
   *
   * @return ReactiveWorkshopResponse
   */
  @GetMapping("/pokelist")
  public Mono<ResponseEntity<PokemonList>> getPokemonList() {
    Mono<PokemonList> pokemonList = pokemonService.getPokemonList();
    return pokemonList.map(pokelist -> ResponseEntity.status(HttpStatus.OK).body(pokelist));
  }
}