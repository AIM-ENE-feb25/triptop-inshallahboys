# Date: 21-03-2025

## Status
**Accepted**

## Context
Voor Triptop willen we reizigers een soepele manier bieden om hun reis samen te stellen, inclusief activiteiten. Dit betekent dat we betrouwbare en actuele gegevens over tours, bezienswaardigheden en andere ervaringen nodig hebben.

## Considered Options
### Waarom deze criteria:
- **Diversiteit van activiteiten:** Een breed scala aan ervaringen maakt het platform aantrekkelijker.
- **Real-time beschikbaarheid:** Actuele informatie over beschikbare tours en activiteiten voorkomt teleurstelling.
- **Integratiegemak:** Een goed gedocumenteerde API maakt snelle en gemakkelijke integratie mogelijk.
- **Gebruikersbeoordelingen:** Ingebouwde reviews helpen gebruikers bij hun keuze.
- **Wereldwijde dekking:** Brede dekking maakt het platform geschikt voor internationale reizigers.

### Vergelijking van opties

| Factoren                | Tripadvisor API         | Expedia API         | Eigen database          |
|------------------------|------------------------|---------------------|------------------------|
| **Diversiteit van activiteiten** | Zeer uitgebreid        | Zeer uitgebreid     | Beperkt tot eigen aanbod |
| **Real-time beschikbaarheid** | Ja                     | Ja                  | Nee                    |
| **Integratiegemak**        | Goed gedocumenteerde REST API | Goed gedocumenteerde REST API | Hoge ontwikkelkosten |
| **Gebruikersbeoordelingen**   | Ja, via Tripadvisor-platform | Ja, via Expedia-platform | Afhankelijk van eigen implementatie |
| **Wereldwijde dekking**   | Uitgebreid wereldwijd  | Uitgebreid wereldwijd | Beperkt tot eigen aanbod |

## Bronnen
- [Tripadvisor](https://www.tripadvisor.com/)
- [Expedia Developer](https://developer.expediagroup.com/)

## Decision
We hebben ervoor gekozen om **Expedia** te gebruiken over **Tripadvisor** en een eigen database.

## Consequences
Dit onderzoek is nog niet volledig onderzocht en er zijn nog veel onbekende nadelen van Expedia die we nog niet weten!