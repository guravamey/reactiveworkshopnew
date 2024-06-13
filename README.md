# Reactive workshop

## Prerequisites :

- Maven
- Java 17
- IDE (IntelliJ IDE preferred, Community edition will work fine)
- Basic understanding of Java, including collections & streams, Springboot

## Agenda : 

Total Duration : 3 hrs

- Presentation - 40 mins
- Break : 5 mins
- Hands-On - 
  - Observational Exercises : Ex 1 and Ex 2 : 45 mins
  - Break : 10 mins
  - Coding Exercises : Ex 3, Ex 4, Ex 5 : 1.20 hrs

## Repository Setup

- Clone the repo : **git@github.com:elsevier-research/scopustech-reactive-workshop.git**
- Run "mvn clean install"
- If lombok support is absent, follow the section "For Lombok" in the readme.md

## Steps :

### Ex 1 :

- **Understanding Mono and Flux** :
    - Open "FluxAndMonoServicesTest" test class.
    - Each test calls a service class method. Try to expect the output by reading the service method called from the test class, Run the test and validate your prediction.
    - Observe the communication between a publisher and subscriber in the logs (Methods like, onSubscribe, OnNext,  OnComplete)

### Ex 2 :

- **Check a reactive controller flow** :
    - Run the application by hitting the Green Play button in **
      ReactiveWorkshopApplication** class or by following the section "Starting the application in
      the readme.md"
    - Check the pom for webflux changes dependencies ("NOTE :" section).
    - Check request flow for "/pokelist" endpoint in "PokemonController" class.
    - You can access the endpoint via : http://localhost:8080/pokelist from browser or use
      swagger : http://localhost:8080/swagger-ui.html.

### Ex 3 :

- **Creating a new endpoint : Consume a new api connection : "pokemon/{pokemonName}"**
    - Create a client mapping for GET "https://pokeapi.co/api/v2/pokemon/{pokemonName}}". You can add it
      in IPokemonAPIClient interface. You can use PokemonData object as a response model.
    - Create a method in PokemonService class to call the above client method.
    - Create an endpoint in PokemonController to use the service method to expose the response. You
      can name the endpoint as GET "/pokemon/{pokemonName}".

### Ex 4 (Aspirational) : 
- Create a web-test-client test
  - Refer ReactiveWorkshopControllerTest class for test case example
  - Add a test for newly created endpoint.
  
### Ex 5 (Aspirational) :

- **Create a new Pokemon summary GET controller which uses both clients from IPokemonApiClient**
    - Create a new endpoint in PokemonController as GET "/pokemon/summary"
    - Create a method in PokemonService which will do the following :
        - Calls the getPokemonListFromPokeAPI from IPokemonApiClient
        - For each item in the response, call the newly created endpoint from Ex 3 in the IPokemonApiClient with pokemon name.
        - Concatenate the responses from both client calls. You can use PokemonSummary model object
          for mapping output response. ( _Hint : Use map, zipWith_ )
        - ```json
          [{"name": "bulbasaur","url": "https://pokeapi.co/api/v2/pokemon/1/", "base_experience":  101}]
          ```

### Ex 6 (Aspirational) : 
- Check Streaming Controller
  - Open http://localhost:8080/flux in the browser.
  - Observe the stream of data received
  - Observe the logs.

### Ex 7 (Aspirational) : 
- Check Streaming Controller
  - Open http://localhost:8080/flux in the browser.
  - Observe the stream of data received
  - Cancel the request from the browser
  - Observe the logs. (The flux ends on cancel signal and does not emit all the events)

## Extended Setup Info

### For Lombok :

Add the plugin Lombok from IntelliJ Market Place :

```IntelliJ Idea -> Preferences -> Plugins -> Search for Lombok```

After installing Lombok :

```IntelliJ Idea -> Preferences -> Build, Execution and Deployment -> Annotation Processors -> Enable Annotation processing```

### Starting the application :

Go in scopustech-reactive-workshop folder on terminal and run the following commands :

```bash
mvn clean install
mvn spring-boot:run
```

Application will start on port 8080 by default.

Swagger endpoint available on ```http://localhost:8080/swagger-ui.html```

Healthcheck endpoints available on ```http://localhost:8080/actuator/health/liveness```
and ```http://localhost:8080/actuator/health/readiness```