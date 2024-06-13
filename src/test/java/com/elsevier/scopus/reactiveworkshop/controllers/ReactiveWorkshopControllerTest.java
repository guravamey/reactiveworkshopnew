package com.elsevier.scopus.reactiveworkshop.controllers;

import com.elsevier.scopus.reactiveworkshop.models.PokemonList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration test on controller while packaging.
 */
@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class ReactiveWorkshopControllerTest {


  //For Webflux, We have to replace mockMvc tests with webtestclient.
  @Autowired
  private WebTestClient webTestClient;

  @Test
  void getPokemonList() {

    this.webTestClient.get().uri("/pokelist")
        .exchange()
        .expectStatus().isOk().expectBody(PokemonList.class);
  }
}
