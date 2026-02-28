package esb;

import org.springframework.web.client.RestTemplate;

public class VisaActivator {

	RestTemplate restTemplate = new RestTemplate();

	public void pay(Order order) {
		System.out.println("Visa payment: "+ order.toString());
		restTemplate.postForLocation("http://localhost:8091/orders", order);
	}
}
