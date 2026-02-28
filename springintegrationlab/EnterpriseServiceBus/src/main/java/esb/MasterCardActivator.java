package esb;

import org.springframework.web.client.RestTemplate;

public class MasterCardActivator {

	RestTemplate restTemplate = new RestTemplate();

	public void pay(Order order) {
		System.out.println("MasterCard payment: "+ order.toString());
		restTemplate.postForLocation("http://localhost:8090/orders", order);
	}
}
