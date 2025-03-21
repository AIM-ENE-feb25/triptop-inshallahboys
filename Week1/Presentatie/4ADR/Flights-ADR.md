Date: 21-03-2025

## Status
Accepted

## Context
Voor Triptop willen we reizigers een soepele manier bieden om hun reis samen te stellen, inclusief vlucht. Dit betekent dat we betrouwbare en actuele gegevens over vluchten nodig hebben.

## Considered Options
Waarom deze criteria:
1. **Data betrouwbaarheid en actualiteit**: Is de data betrouwbaar en actueel? Dus als een vlucht verandert verandert de api dan ook gelijk?
2. **Prijs en toegankelijkheid**: Is de API zelf gratis en toegankelijk?
3. **Integratiegemak**: Hoe makkelijk is het om te integreren in de applicatie?
4. **Functionaliteit en beperkingen**: Sommige API’s bieden extra features zoals prijsvoorspellingen, filters op bagagekosten of CO₂-uitstoot. Wat voor functionaliteiten biedt de API?
5. **Onderhoud**: Bestaat de API nog? Wordt hij onderhouden?

| Factoren | Google flights-API | Flights-API |
| --- | --- | --- |
| Data betrouwbaarheid en actualiteit | Real-time vluchtinfo | Real-time vluchtinfo |
| Prijs en toegankelijkheid | Gratis via RapidAPI | Gratis via website |
| Integratiegemak | ++ | ++ |
| Functionaliteit en beperkingen | Google flights focust zich op basisinfo | Data zoals prijzen beschikbaar naast vlucht info |
| Onderhoud | Discontinued | Vervanger google flights |
### Bronnen
- https://aviationstack.com/
- https://www.flightapi.io/blog/google-flight-api-history-and-alternative/
## Decision
We hebben ervoor gekozen om niet voor Google flights te gaan aangezien deze gediscontinued is. Daarom kiezen wij voor het aanbevolen alternatief flights-api.

## Consequences
Dit onderzoek kon grondiger naar andere alternatieven, hierdoor kan het zijn dat er een beter alternatief aanwezig is.