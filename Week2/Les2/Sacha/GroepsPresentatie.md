# Dependency Inversion Principle (DIP)

## Definitie
Het **Dependency Inversion Principle (DIP)** stelt dat:

- Hoog-niveau modules (zoals de businesslogica) niet direct afhankelijk moeten zijn van laag-niveau modules (zoals databases of externe services). Beiden moeten afhankelijk zijn van abstracties.
- Abstracties mogen niet afhankelijk zijn van details; details moeten afhankelijk zijn van abstracties.

## Consequenties van het toepassen van DIP
- **Flexibiliteit:** Code wordt minder afhankelijk van specifieke implementaties, wat veranderingen eenvoudiger maakt.
- **Testbaarheid:** Mocking en dependency injection maken unit testing makkelijker.
- **Herbruikbaarheid:** Onderdelen kunnen eenvoudiger in andere projecten worden gebruikt.
- **Doorgeefluiken:** Je krijgt meer klasses die ongeveer hetzelfde doen.

## Codevoorbeeld van DIP in Java


### Met DIP (losgekoppeld via abstractie)
```java
interface Database {
    void connect();
}
//low-level module
class MySQLDatabase implements Database {
    public void connect() {
        System.out.println("Verbinden met MySQL-database...");
    }
}
//low-level module
class PostgreSQLDatabase implements Database {
    public void connect() {
        System.out.println("Verbinden met PostgreSQL-database...");
    }
}

//high-level module
class DataService {
    private Database database;

    public DataService(Database database) { // Dependency injection
        this.database = database;
    }

    public void fetchData() {
        database.connect();
        System.out.println("Data ophalen...");
    }
}
```
**Oplossing:** `DataService` is nu afhankelijk van de **abstractie** `Database`, niet van een specifieke implementatie.

**Voordeel:** We kunnen eenvoudig wisselen tussen `MySQLDatabase` en `PostgreSQLDatabase` zonder `DataService` te wijzigen.

## Design Properties waar DIP op gebaseerd is
**Coupling:** het gebruik van dependency injection door middel van een interface.
