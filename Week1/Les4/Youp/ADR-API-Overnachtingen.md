Date: 21-03-2025

## Status
Accepted

## Context
Voor Triptop willen we reizigers een soepele manier bieden om hun reis samen te stellen, inclusief overnachtingen. Dit betekent dat we betrouwbare en actuele gegevens over hotels, appartementen en andere accommodaties nodig hebben.

## Considered Options
Waarom deze criteria:
1. **Grootte van het aanbod**: Hoe meer accommodaties, hoe beter de keuze voor gebruikers.
2. **Real-time prijsupdates**: Actuele prijzen zorgen voor betrouwbare informatie.
3. **Integratiegemak**: Een goed gedocumenteerde API maakt snelle en gemakkelijke integratie mogelijk.
4. **Betaalmogelijkheden**: Ingebouwde betalingen vereenvoudigen de gebruikerservaring.
5. **Wereldwijde dekking**: Brede dekking maakt het platform geschikt voor internationale reizigers.

| Factoren                   | Booking.com API               | Hotels.com API                                | Eigen database           |
| -------------------------- | ----------------------------- | --------------------------------------------- | ------------------------ |
| **Grootte van het aanbod** | Zeer uitgebreid               | Zeer uitgebreid                               | Beperkt tot eigen aanbod |
| **Real-time prijsupdates** | Ja                            | Ja                                            | Nee                      |
| **Integratiegemak**        | Goed gedocumenteerde REST API | Goed gedocumenteerde REST API                 | Hoge ontwikkelkosten     |
| **Betaalmogelijkheden**    | Ingebouwd via Booking.com     | Ingebouwd via Hotels.com                      | ?                        |
| **Wereldwijde dekking**    | Zeer uitgebreid wereldwijd    | Sterk, maar minder wereldwijd dan Booking.com | Beperkt tot eigen aanbod |
### Bronnen
- https://www.booking.com/content/about.html?aid=304142&label=gen173rf-1FCAEoggI46AdIM1gDaKkBiAEBmAExuAEXyAEP2AEB6AEB-AECiAIBogILY2hhdGdwdC5jb22oAgO4Arrh9L4GwAIB0gIkOTAwNTkyM2YtYTMyNS00NWNhLTlhYjItZTg3NzMwZTFlNTcz2AIF4AIB&sid=8beb52740f0dbc937b1f47f33aa90b54
- https://nl.hotels.com/
## Decision
We hebben ervoor gekozen om Booking.com te gebruiken over Hotels.com en eigen database.

## Consequences
We hebben dit onderzoek nog niet heel erg grondig uitgevoerd en hierdoor zijn er veel nadelen van booking.com die we simpel weg nog niet weten!