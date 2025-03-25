# Hoe kunnen we verschillende betalingssystemen integreren voor de verschillende bouwstenen

## Componenten 
- Bouwsteen Controller
- Bouwsteen Service
- Bouwsteen Repository
- TripadvisorAdapter
- BookingAPIAdapter
- VervoerAdapter


| **Component**           | **Verantwoordelijkheid** | **Toepassing van Design Principles** |
|-------------------------|------------------------|--------------------------------|
| **Bouwsteen Controller** | Ontvangt HTTP-verzoeken, valideert invoer en stuurt de aanvraag door naar de service laag. | **SRP**: Alleen verantwoordelijk voor request handling. |
| **Bouwsteen Service**    | Bevat de businesslogica en roept de juiste betalingsadapter aan. | **Encapsulate What Varies**: De logica voor verschillende betalingssystemen is hier losgekoppeld. |
| **Bouwsteen Repository** | Interactie met de database (ophalen en opslaan van gegevens zoals transacties). | **Separation of Concerns**: Data-opslag is gescheiden van businesslogica. |
| **TripadvisorAdapter**   | Verzorgt communicatie met het betalings- en boekingssysteem van Tripadvisor. | **Encapsulate What Varies**: Specifiek voor Tripadvisor. |
| **BookingAPIAdapter**    | Verzorgt communicatie met de betalings- en boekingssysteem van Booking.com. | **Encapsulate What Varies**: Specifiek voor Booking.com. |
| **VervoerAdapter**       | Verzorgt communicatie met een vervoersbetalingssysteem (bijv. trein, taxi of vliegtuigreserveringen). | **Encapsulate What Varies**: Specifiek voor vervoerssystemen. |


| **Interface**          | **Methoden** |
|------------------------|-------------|
| **PaymentAdapter**     | `boolean processPayment(double amount);` |
| **PaymentService**     | `boolean processPayment(String provider, double amount);` |
| **PaymentRepository**  | `void saveTransaction(String transactionId, String provider, double amount, String status);`<br>`String getTransactionStatus(String transactionId);` |
| **PaymentController**  | `ResponseEntity<String> makePayment(String provider, double amount);` |

```mermaid
sequenceDiagram
    participant Gebruiker
    participant PaymentController
    participant PaymentService
    participant PaymentAdapter
    participant PaymentRepository

    Gebruiker->>PaymentController: Verzoek om betaling (provider, amount)
    PaymentController->>PaymentService: processPayment(provider, amount)
    PaymentService->>PaymentAdapter: processPayment(amount)
    PaymentAdapter-->>PaymentService: Betalingsresultaat
    PaymentService->>PaymentRepository: saveTransaction(transactionId, provider, amount, status)
    PaymentRepository-->>PaymentService: Opslag bevestigd
    PaymentService-->>PaymentController: Betalingsresultaat
    PaymentController-->>Gebruiker: HTTP-response met resultaat
```

```mermaid
classDiagram
    class PaymentController {
        +processPayment(provider: String, amount: double): ResponseEntity<String>
    }

    class PaymentService {
        +processPayment(provider: String, amount: double): boolean
        -paymentRepository: PaymentRepository
        -adapters: Map<String, PaymentAdapter>
    }

    class PaymentAdapter {
        <<interface>>
        +processPayment(amount: double): boolean
    }

    class TripadvisorAdapter {
        +processPayment(amount: double): boolean
    }

    class BookingAPIAdapter {
        +processPayment(amount: double): boolean
    }

    class VervoerAdapter {
        +processPayment(amount: double): boolean
    }

    class PaymentRepository {
        +saveTransaction(transactionId: String, provider: String, amount: double, status: String): void
        +getTransactionStatus(transactionId: String): String
    }

    %% Relaties tussen de klassen
    PaymentController --> PaymentService : gebruikt
    PaymentService --> PaymentAdapter : gebruikt
    PaymentService --> PaymentRepository : gebruikt
    PaymentAdapter <|.. TripadvisorAdapter : implementatie
    PaymentAdapter <|.. BookingAPIAdapter : implementatie
    PaymentAdapter <|.. VervoerAdapter : implementatie
```