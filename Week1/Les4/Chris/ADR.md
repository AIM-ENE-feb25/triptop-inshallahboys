Date: 21-03-2025

## Status
Accepted

## Context
Voor Triptop willen we reizigers een soepele manier bieden om hun reis samen te stellen, inclusief vlucht. Dit betekent dat we betrouwbare en actuele gegevens over vluchten nodig hebben.

## Considered Options
Waarom deze criteria:
1. **Data betrouwbaarheid en actualiteit**: Google Flights API biedt toegang tot real-time vluchtinformatie van meerdere luchtvaartmaatschappijen en boekingssites, wat zorgt voor actuele en nauwkeurige gegevens. Andere API’s kunnen een beperktere dataset hebben of minder frequent worden bijgewerkt.
2. **Prijs en toegankelijkheid**: Google Flights API is niet openbaar beschikbaar en kan prijzig zijn, terwijl alternatieve API’s zoals Amadeus, Skyscanner of Aviationstack mogelijk gratis of goedkoper zijn, afhankelijk van het gebruik.
3. **Integratiegemak**: Google Flights API kan sterke integraties bieden met andere Google-diensten (zoals Maps en Google Pay), wat nuttig kan zijn voor een reisplatform. Andere API’s kunnen eenvoudiger in gebruik zijn of betere documentatie bieden.
4. **Functionaliteit en beperkingen**: Sommige API’s bieden extra features zoals prijsvoorspellingen, filters op bagagekosten of CO₂-uitstoot, terwijl Google Flights API zich mogelijk meer richt op basisinformatie. De keuze hangt af van de gewenste functionaliteiten voor Triptop.
5. **Bestaat nog**: Bestaat de API nog?

| Factoren | Google flights-API | Flights-API |
| --- | --- | --- |
| Data betrouwbaarheid en actualiteit | Real-time vluchtinfo | Real-time vluchtinfo |
| Prijs en toegankelijkheid | Gratis via RapidAPI | Gratis via website |
| Integratiegemak | ++ | ++ |
| Functionaliteit en beperkingen | Google flights focust zich op basisinfo | Data zoals prijzen beschikbaar naast vlucht info |
| Bestaat nog? | Discontinued | Vervanger google flights |
### Bronnen
- https://aviationstack.com/
- https://www.flightapi.io/blog/google-flight-api-history-and-alternative/
## Decision
We hebben ervoor gekozen om niet voor Google flights te gaan aangezien deze gediscontinued is. Daarom kiezen wij voor het aanbevolen alternatief flights-api.

## Consequences
Dit onderzoek kon grondiger naar andere alternatieven, hierdoor kan het zijn dat er een beter alternatief aanwezig is.