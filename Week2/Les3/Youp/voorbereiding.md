# Hoe kunnen we verschillende externe vervoersservices (zoals Google Maps of een veerdienst API) integreren zonder afhankelijk te worden van hun specifieke implementaties?

## Voorbereiding:
- BouwsteenController
- BouwsteenService
- BouwsteenRepository
- VervoerAdapter

## Verantwoordelijkheden

| Component           | Verantwoordelijkheid                                                                      | Toepassing van Design Principles |
| ------------------- | ----------------------------------------------------------------------------------------- | -------------------------------- |
| BouwsteenController | Ontvangt HTTP-verzoeken, valideert invoer en stuurt de aanvraag door naar de service laag | **Separation of Concerns**       |
| BouwsteenService    | Verwerking van businesslogica en interactie met BouwsteenRepository                       | **SRP**                          |
| BouwsteenRepository | Interactie met de database en VervoersAdapter                                             | **Encapsulate What Varies**      |
| VervoerAdapter      | Interactie met vervoers API's zoals flightsAPI                                            | **Cohesion**                     |

## Interfaces
- VervoersAdapter
```java
Route getRoute(Location start, Location end, TransportMode mode); 
List<TransportOption> getAvailableTransportOptions(Location start, Location end);
```

## Volgorde
Controllers -> Service -> Repository -> Adapter -> API
```puml
@startuml  
  
actor reiziger  
participant "BouwsteenController" as Controller  
participant "BouwsteenService" as Service  
participant "BouwsteenRepository" as Repository  
participant "VervoerAdapter" as Adapter  
participant "Externe VervoersAPI" as API  
  
  
reiziger -> Controller : Verzoek route ophalen  
Controller -> Service : Verwerk route-aanvraag  
Service -> Repository : Haal beschikbare transportopties op  
Repository -> Adapter : Roep externe API aan  
  
opt Externe API succesvol  
    Adapter -> API : Vraag routegegevens op  
    API --> Adapter : Geeft routegegevens terug  
    Adapter --> Repository : Geef route terug  
    Repository --> Service : Geef route terug  
    Service --> Controller : Verzend route naar gebruiker  
    Controller --> reiziger : Toon route op UI  
end  
  
opt Externe API faalt  
    Adapter -> API : Vraag routegegevens op  
    API --> Adapter : Fout! API niet bereikbaar  
    Adapter --> Repository : Geeft foutmelding terug  
    Repository --> Service : Geeft foutmelding door  
    Service --> Controller : Retourneert fout aan gebruiker  
    Controller --> reiziger : Toon foutmelding: "Route-informatie tijdelijk niet beschikbaar"  
end  
  
@enduml
```
## Opdelen
- Controller blijft controller
- Service blijft service
- Repository blijft repository
- Adapter blijft Adapter
```mermaid
classDiagram

class BuildingBlockController {
    + saveBuildingBlock(Integer id, String start, String end): ResponseEntity<Integer, String>
}

class BuildingBlockService {
    loginService: LoginService
    + saveBuildingBlock(Integer id, String start, String end): void
}

class LoginService{
    +isAuth() : boolean
    -getToken(username, password) : ResponseEntity<String>
}

class Route {
    - locationStart: String
    - locationEnd: String
    - id: Integer
    - type: String
}

class TravelAdapter {
    <<interface>>
    + getRoute(String start, String end, Integer id): Route
}

class flightsAPI {
    + getRoute(Location start, Location end, Integer): Route
}

class NSAdapter {
    + getRoute(String start, String end, Integer id): Route
}

class CarBooksAdapter {
    + getRoute(String start, String end, Integer id): Route
}

BuildingBlockController --> BuildingBlockService : uses
BuildingBlockService --> LoginService : checks if user is auth
BuildingBlockService --> TravelAdapter : uses
BuildingBlockService --> Route : uses
TravelAdapter <|.. flightsAPI : implements
TravelAdapter <|.. NSAdapter : implements
TravelAdapter <|.. CarBooksAdapter : implements
```