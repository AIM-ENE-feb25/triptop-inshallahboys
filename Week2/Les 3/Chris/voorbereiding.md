# Vraag: Hoe kunnen we verschillende identity providers met verschillende interfaces integreren voor het gehele systeem?

## Voorbereiding
- LoginController
- LoginService
- IdentityProvider - API / Adapter. (Ik denk dat we hier even overna moeten gaan denken om hier ook een adapter te gaan gebruiken.)
- UserRepository

## Verantwoordelijkheden
**LoginController**:
- Het krijgen van de gegevens vanuit de frontend.
- Het doorsturen van de gegevens naar de LoginService.
**LoginService**:
- Het doorsturen van de gegevens naar de IdentityProvider.
- Het ontvangen van de token en terugsturen.
**IdentityProvider**:
- Het teruggeven van een token waarmee de gebruiker geauthenticeerd kan worden.
UserRepository:
- Het opslaan van de gebruiker in de database.
- Het ophalen van de gebruiker uit de database.

## Interfaces
**IdentityProvider**: (Hiervan is het een goed idee om een interface te maken. Zo kunnen we makkelijk verschillende identity providers toevoegen.)
- `getToken(username: string, password: string): string`

## Volgorde
Controller -> Service -> (Adapter -> API) -> Repo

## Opdelen
- De IdentityProvider kan opgedeeld worden in een API en een Adapter. De API is de interface die de IdentityProvider implementeert. De Adapter is de klasse die de API implementeert en de gegevens van de IdentityProvider omzet naar de gegevens die de Service verwacht.
- Service blijft service
- Controller blijft controller
- Repository blijft repository