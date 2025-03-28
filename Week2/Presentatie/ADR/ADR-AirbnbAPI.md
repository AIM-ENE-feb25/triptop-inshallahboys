Date: 21-03-2025

## Status
Pending

## Context
Voor Triptop willen we reizigers een soepele manier bieden om hun reis samen te stellen, inclusief overnachtingen. Dit betekent dat we betrouwbare en actuele gegevens over hotels, appartementen en andere accommodaties nodig hebben.

| Class::Attribuut         | Is input voor API+Endpoint       | Wordt gevuld door API+Endpoint   | Wordt geleverd door eindgebruiker | Moet worden opgeslagen in de applicatie |
| ------------------------ | -------------------------------- | -------------------------------- | --------------------------------- | --------------------------------------- |
| Accommodatie::location   | Airbnb /search Property by place |                                  | x                                 | x                                       |
| Accommodatie::currency   | Airbnb /search Property by place |                                  | x                                 |                                         |
| Accommodatie::adults     | Airbnb /search Property by place |                                  | x                                 | x                                       |
| Accommodatie::children   | Airbnb /search Property by place |                                  | x                                 | x                                       |
| Accommodatie::checkin    | Airbnb /search Property by place |                                  | x                                 | x                                       |
| Accommodatie::checkout   | Airbnb /search Property by place |                                  | x                                 | x                                       |
| Accommodatie::priceMin   | Airbnb /search Property by place |                                  | x                                 |                                         |
| Accommodatie::priceMax   | Airbnb /search Property by place |                                  | x                                 |                                         |
| Accommodatie::starRating |                                  | Airbnb /search Property by place |                                   |                                         |
| Accommodatie::price      |                                  | Airbnb /search Property by place |                                   | x                                       |
| Accommodatie::images     |                                  | Airbnb /search Property by place |                                   |                                         |
| Accommodatie::title      |                                  | Airbnb /search Property by place |                                   | x                                       |

## Considered Options
Frontend en Backend

## Decision


## Consequences
