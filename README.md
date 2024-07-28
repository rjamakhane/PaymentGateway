# Payment Service

This is a Spring Boot application for handling payment services using Stripe.

## Technologies Used

- Java
- Spring Boot
- Maven
- Stripe API

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Stripe account and API key

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/payment-service.git
    cd payment-service
    ```

2. Set up your Stripe API key in the `application.properties` file:
    ```properties
    stripe.secret=your_stripe_secret_key
    ```

3. Build the project using Maven:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Usage

### Creating a Payment Link

To create a payment link, you can use the `StripPaymentGateway` class. Here is an example of how to use it:

```java
import com.example.paymentservice.paymentgateways.StripPaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentService {
    @Autowired
    private StripPaymentGateway stripPaymentGateway;

    public String createPaymentLink(Long orderId, Long amount, String phoneNumber) {
        try {
            return stripPaymentGateway.createPaymentLink(orderId, amount, phoneNumber);
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

## Project Structure

- `src/main/java/com/example/paymentservice/` - Main application code
- `src/main/resources/` - Configuration files
- `src/test/java/com/example/paymentservice/` - Test cases

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.