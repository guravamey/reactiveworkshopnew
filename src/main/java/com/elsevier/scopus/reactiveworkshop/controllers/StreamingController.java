package com.elsevier.scopus.reactiveworkshop.controllers;

import com.elsevier.scopus.reactiveworkshop.services.FluxAndMonoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Rest controller for ReactiveWorkshop
 */
@RestController
@RequiredArgsConstructor
public class StreamingController {

  private final FluxAndMonoServices fluxAndMonoServices;

  /**
   * Example Endpoint for WebFlux
   *
   * @return ReactiveWorkshopResponse
   */
  @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<String> getFluxStream() {
    return fluxAndMonoServices.fruitsFluxConcatMap();
  }
}