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

    class TransportAdapter {
        +processPayment(amount: double): boolean
    }

    class PaymentRepository {
        +saveTransaction(transactionId: String, provider: String, amount: double, status: String): void
        +getTransactionStatus(transactionId: String): String
    }

    %% Relaties tussen de klassen
    PaymentController --> PaymentService : uses
    PaymentService --> PaymentAdapter : uses
    PaymentService --> PaymentRepository : uses
    PaymentAdapter <|.. TripadvisorAdapter : implements
    PaymentAdapter <|.. BookingAPIAdapter : implements
    PaymentAdapter <|.. VervoerAdapter : implements
```