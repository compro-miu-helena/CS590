package esb;

import org.springframework.web.client.RestTemplate;

public class PayPalActivator {

	RestTemplate restTemplate = new RestTemplate();

	public void pay(Order order) {
		System.out.println("Paypal payment: "+ order.toString());
		restTemplate.postForLocation("http://localhost:8092/orders", order);
	}
}
