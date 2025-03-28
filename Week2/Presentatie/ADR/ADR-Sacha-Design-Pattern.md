Date: 28-03-2025

## Status
Accepted

## Context
Voor de casusopdracht is er behoefte aan een flexibele integratie van verschillende betalingssystemen binnen de architectuur. De applicatie moet betalingen kunnen verwerken via meerdere externe providers, zoals Tripadvisor, Booking.com en diverse vervoersdiensten.
Elke provider heeft zijn eigen API met unieke implementatiedetails, wat kan leiden tot complexe en inconsistente integraties. Het is essentieel om een generieke manier te bieden om met deze verschillende systemen te communiceren zonder de kernlogica van de applicatie onnodig complex te maken.

## Decision
Om de integratie van de verschillende betalingssystemen te standaardiseren en te vereenvoudigen, hebben we het Adapter Pattern toegepast. Dit patroon maakt het mogelijk om de verschillende externe API's te laten voldoen aan een uniforme interface binnen de applicatie.

We introduceren een PaymentAdapter-interface, die door specifieke adapters wordt geïmplementeerd voor elke externe betalingsprovider, zoals TripadvisorAdapter, BookingAPIAdapter en VervoerAdapter. De PaymentService zal enkel met de PaymentAdapter-interface werken en niet direct met de externe API’s, waardoor de afhankelijkheden worden geminimaliseerd en uitbreidingen eenvoudiger worden.

## Consequences

### Voordelen:

- Losse koppeling: De kern van de applicatie hoeft geen kennis te hebben van de specifieke implementaties van externe betalingsproviders.

- Makkelijk uitbreidbaar: Nieuwe betalingssystemen kunnen eenvoudig worden toegevoegd door een nieuwe adapter te implementeren.

- Ondersteuning voor meerdere betalingsproviders: Elke adapter kan de specifieke API-aanroepen van een externe provider afhandelen zonder de businesslogica aan te passen.

### Nadelen:

- Onderhoud van adapters: Wanneer een externe API verandert, moet de bijbehorende adapter mogelijk worden bijgewerkt.

## Conclusie
Het **Adapter Pattern** biedt de beste balans tussen flexibiliteit en onderhoudbaarheid binnen deze context. De implementatie maakt het mogelijk om zonder veel moeite extra betalingssystemen toe te voegen en voorkomt directe afhankelijkheden van externe API’s. Hierdoor blijft de applicatie robuust en schaalbaar.