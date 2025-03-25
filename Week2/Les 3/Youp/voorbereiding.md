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