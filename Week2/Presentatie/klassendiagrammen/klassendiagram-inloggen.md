```mermaid
classDiagram

class User {
    -id : int
    -username : string
    -password: string
}

class LoginController{
    getToken(username, password)
}

class LoginService{
    -user : User
    +isAuth() : boolean
    -getToken(username, password) : ResponseEntity<String>
    +checkForAcces(String username, String application, String token) : boolean
}

class LoginAdapter{
    <<interface>>
    getToken(username, password)
}

class OAUTHLoginAdapter{
    getToken(username, password)
}


class PaymentService {
    Loginservice loginservice
    +processPayment(provider: String, amount: double): boolean
    -paymentRepository: PaymentRepository
    -adapters: Map<String, PaymentAdapter>
}

class BuildingBlockService {
    loginService: LoginService
    + saveBuildingBlock(Integer): void
}

LoginController --> LoginService : sends data
LoginService --> LoginAdapter : sends data to adapter
LoginAdapter ..> OAUTHLoginAdapter : gets token from API
PaymentService --> LoginService : checks for auth
BuildingBlockService --> LoginService : checks for auth
User --> LoginService : uses

```
