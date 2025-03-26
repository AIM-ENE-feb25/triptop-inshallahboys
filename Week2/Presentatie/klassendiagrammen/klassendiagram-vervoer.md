```mermaid
classDiagram

class BuildingBlockController {
    + saveBuildingBlock(Integer): ResponseEntity<Integer>
}

class BuildingBlockService {
    loginService: LoginService
    + saveBuildingBlock(Integer): void
}

class LoginService{
    +isAuth() : boolean
    -getToken(username, password) : ResponseEntity<String>
}

class VervoerAdapter {
    <<interface>>
    + getRoute(Location start, Location end, Integer): Route
}

class flightsAPI{
    + getRoute(Location start, Location end, Integer): Route
}

BuildingBlockController --> BuildingBlockService : uses
BuildingBlockService --> LoginService : checks if user is auth
BuildingBlockService --> VervoerAdapter : uses
VervoerAdapter <|.. flightsAPI : implements

```