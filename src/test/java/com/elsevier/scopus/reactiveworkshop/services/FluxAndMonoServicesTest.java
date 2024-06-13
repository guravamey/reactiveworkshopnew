package com.elsevier.scopus.reactiveworkshop.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;


//This class demonstrates various operations on Mono, Flux
class FluxAndMonoServicesTest {

  FluxAndMonoServices fluxAndMonoServices
      = new FluxAndMonoServices();

  //
  @Test
  void subscribeAPublisher() {

    // You do not get the publisher's value here as there is no one to subscribe it
    System.out.println("No Subscriber for Publisher : " + fluxAndMonoServices.fruitsFlux());

    // You get the desired output.
    // Observe logs for the event style communication happening between publisher and subscriber i.e. onSubscribe, onNext onComplete etc
    fluxAndMonoServices.fruitsFlux()
        .subscribe(s -> System.out.println("Flux -> s = " + s));

    fluxAndMonoServices.fruitMono()
        .subscribe(s -> System.out.println("Mono -> s = " + s));
  }


  //Get a flux
  //For test classes, StepVerifier Triggers the subscribe method. StepVerifier helps you to verify event streams.
  //In case of Rest controllers, the incoming http request will trigger subscribe
  //Please check the fluxAndMonoServices.<method> for actual operation logic. Present in "FluxAndMonoServices" service class
  @Test
  void fruitsFlux() {
    var fruitsFlux = fluxAndMonoServices.fruitsFlux();

    StepVerifier.create(fruitsFlux)
        .expectNext("Mango", "Orange", "Banana")
        .verifyComplete();
  }

  //Get a mono
  @Test
  void fruitMono() {
    var fruitsMono = fluxAndMonoServices.fruitMono();

    StepVerifier.create(fruitsMono)
        .expectNext("Mango")
        .verifyComplete();
  }

  //Map operation in a flux
  @Test
  void fruitsFluxMap() {
    var fruitsFlux = fluxAndMonoServices.fruitsFluxMap();

    StepVerifier.create(fruitsFlux)
        .expectNext("MANGO", "ORANGE", "BANANA")
        .verifyComplete();
  }

  //Filter operation in a flux
  @Test
  void fruitsFluxFilter() {
    var fruitsFlux = fluxAndMonoServices.fruitsFluxFilter(5).log();

    StepVerifier.create(fruitsFlux)
        .expectNext("Orange", "Banana")
        .verifyComplete();
  }

  //Combining filter and map operation
  @Test
  void fruitsFluxFilterMap() {
    var fruitsFlux = fluxAndMonoServices.fruitsFluxFilterMap(5);

    StepVerifier.create(fruitsFlux)
        .expectNext("ORANGE", "BANANA")
        .verifyComplete();
  }

  //Flatmap operation
  @Test
  void fruitsFluxFlatMap() {
    var fruitsFlux = fluxAndMonoServices.fruitsFluxFlatMap();

    StepVerifier.create(fruitsFlux)
        .expectNextCount(17)
        .verifyComplete();
  }

  //Flatmap async operation. Please note the order of items in the logs
  @Test
  void fruitsFluxFlatMapAsync() {
    var fruitsFlux = fluxAndMonoServices.fruitsFluxFlatMapAsync();

    StepVerifier.create(fruitsFlux)
        .expectNextCount(17)
        .verifyComplete();
  }

  //Concat Map. Please note the order of items in the logs. Compare it with flatMap
  @Test
  void fruitsFluxConcatMap() {

    var fruitsFlux = fluxAndMonoServices.fruitsFluxConcatMap();

    StepVerifier.create(fruitsFlux)
        .expectNextCount(17)
        .verifyComplete();
  }

  //Convert Mono into Flux
  @Test
  void fruitMonoFlatMapMany() {
    var fruitsFlux = fluxAndMonoServices.fruitMonoFlatMapMany();

    StepVerifier.create(fruitsFlux)
        .expectNextCount(5)
        .verifyComplete();
  }

  //Transforms one flux into another
  @Test
  void fruitsFluxTransform() {

    var fruitsFlux
        = fluxAndMonoServices.fruitsFluxTransform(5);

    StepVerifier.create(fruitsFlux)
        .expectNext("Orange", "Banana")
        .verifyComplete();
  }

  //Responds with default value if the transform operation returns empty
  @Test
  void fruitsFluxTransformDefaultIfEmpty() {
    var fruitsFlux
        = fluxAndMonoServices.fruitsFluxTransformDefaultIfEmpty(10);

    StepVerifier.create(fruitsFlux)
        .expectNext("Default")
        .verifyComplete();
  }

  //Switch to a new flux input if the transform operation on the first flux returns empty
  @Test
  void fruitsFluxTransformSwitchIfEmpty() {
    var fruitsFlux
        = fluxAndMonoServices.fruitsFluxTransformSwitchIfEmpty(8);

    StepVerifier.create(fruitsFlux)
        .expectNext("Pineapple", "Jack Fruit")
        .verifyComplete();

  }

  //Concatenates two flux
  @Test
  void fruitsFluxConcat() {

    var fruitsFlux = fluxAndMonoServices.fruitsFluxConcat().log();
    StepVerifier.create(fruitsFlux)
        .expectNext("Mango", "Orange", "Tomato", "Lemon")
        .verifyComplete();
  }

  //Concatenates two flux
  @Test
  void fruitsFluxConcatWith() {
    var fruitsFlux = fluxAndMonoServices.fruitsFluxConcatWith().log();
    StepVerifier.create(fruitsFlux)
        .expectNext("Mango", "Orange", "Tomato", "Lemon")
        .verifyComplete();
  }

  //Concatenates two mono to get a flux
  @Test
  void fruitsMonoConcatWith() {

    var fruitsFlux = fluxAndMonoServices.fruitsMonoConcatWith().log();
    StepVerifier.create(fruitsFlux)
        .expectNext("Mango", "Tomato")
        .verifyComplete();
  }

  //Merge two flux. Please compare the output with fruitsFluxConcatWith test case.
  //Concat maintains the order by lazy fetching. Merge operator does an eager fetch whenever it gets an event.
  @Test
  void fruitsFluxMerge() {
    var fruitsFlux = fluxAndMonoServices.fruitsFluxMerge().log();
    StepVerifier.create(fruitsFlux)
        .expectNext("Mango", "Tomato", "Orange", "Lemon")
        .verifyComplete();
  }

  //Same as merge.
  @Test
  void fruitsFluxMergeWith() {
    var fruitsFlux = fluxAndMonoServices.fruitsFluxMergeWith().log();
    StepVerifier.create(fruitsFlux)
        .expectNext("Mango", "Tomato", "Orange", "Lemon")
        .verifyComplete();
  }

  //Maintains the order of flux
  @Test
  void fruitsFluxMergeWithSequential() {
    var fruitsFlux = fluxAndMonoServices
        .fruitsFluxMergeWithSequential().log();
    StepVerifier.create(fruitsFlux)
        .expectNext("Mango", "Orange", "Tomato", "Lemon")
        .verifyComplete();
  }

  //Creates another flux by zipping multiple flux with required mapping
  @Test
  void fruitsFluxZip() {
    var fruitsFlux = fluxAndMonoServices
        .fruitsFluxZip().log();
    StepVerifier.create(fruitsFlux)
        .expectNext("MangoTomato", "OrangeLemon")
        .verifyComplete();
  }

  //Same as zip
  @Test
  void fruitsFluxZipWith() {
    var fruitsFlux = fluxAndMonoServices
        .fruitsFluxZipWith().log();
    StepVerifier.create(fruitsFlux)
        .expectNext("MangoTomato", "OrangeLemon")
        .verifyComplete();
  }

  //To get a tuple from zip operation and perform necessary transformation to get one flux
  @Test
  void fruitsFluxZipTuple() {
    var fruitsFlux = fluxAndMonoServices
        .fruitsFluxZipTuple().log();
    StepVerifier.create(fruitsFlux)
        .expectNext("MangoTomatoPotato", "OrangeLemonBeans")
        .verifyComplete();
  }

  //Zip with on Mono
  @Test
  void fruitsMonoZipWith() {

    var fruitsFlux = fluxAndMonoServices
        .fruitsMonoZipWith().log();
    StepVerifier.create(fruitsFlux)
        .expectNext("MangoTomato")
        .verifyComplete();
  }

  //It is a hook which is triggered on various doOn* events.
  @Test
  void fruitsFluxFilterDoOn() {
    var fruitsFlux = fluxAndMonoServices
        .fruitsFluxFilterDoOn(5).log();

    StepVerifier.create(fruitsFlux)
        .expectNext("Orange", "Banana")
        .verifyComplete();
  }

  //onError default handling for exceptions in a flux
  @Test
  void fruitsFluxOnErrorReturn() {
    var fruitsFlux = fluxAndMonoServices
        .fruitsFluxOnErrorReturn().log();

    StepVerifier.create(fruitsFlux)
        .expectNext("Apple", "Mango", "Orange")
        .verifyComplete();
  }

  //Does not terminate the flux operation even if error has occurred
  @Test
  void fruitsFluxOnErrorContinue() {
    var fruitsFlux = fluxAndMonoServices
        .fruitsFluxOnErrorContinue().log();

    StepVerifier.create(fruitsFlux)
        .expectNext("APPLE", "ORANGE")
        .verifyComplete();
  }

  //Maps one exception to other exception inside a flux operation
  @Test
  void fruitsFluxOnErrorMap() {
    //Hooks.onOperatorDebug();
    var fruitsFlux = fluxAndMonoServices
        .fruitsFluxOnErrorMap().log();

    StepVerifier.create(fruitsFlux)
        .expectNext("APPLE")
        .expectError(IllegalStateException.class)
        .verify();
  }

  //Try catch block
  @Test
  void fruitsFluxOnError() {
    var fruitsFlux = fluxAndMonoServices
        .fruitsFluxOnError().log();

    StepVerifier.create(fruitsFlux)
        .expectNext("APPLE")
        .expectError(RuntimeException.class)
        .verify();
  }
}