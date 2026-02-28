package esb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ESBApplication implements CommandLineRunner {

    private final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(ESBApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Domestic, normal shipping (<175), Mastercard payment
        restTemplate.postForLocation("http://localhost:8080/orders",
                new Order("D100", 120.0, "domestic", "mastercard"));

        // Domestic, next-day shipping (>175), Visa payment
        restTemplate.postForLocation("http://localhost:8080/orders",
                new Order("D200", 250.0, "domestic", "visa"));

        // International shipping, PayPal payment
        restTemplate.postForLocation("http://localhost:8080/orders",
                new Order("I300", 90.0, "international", "paypal"));
    }
}
