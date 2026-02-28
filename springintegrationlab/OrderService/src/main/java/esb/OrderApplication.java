package esb;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class OrderApplication implements CommandLineRunner {

	RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		restTemplate.postForLocation("http://localhost:8080/orders", new Order("334", 120.0, false, PaymentType.VISA));
		restTemplate.postForLocation("http://localhost:8080/orders", new Order("338", 190.0, false, PaymentType.MASTERCARD));
		restTemplate.postForLocation("http://localhost:8080/orders", new Order("355", 185.0, true, PaymentType.PAYPAL));
	}
}
