package esb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class InternationalShippingActivator {

	RestTemplate restTemplate = new RestTemplate();

	public Order ship(Order order) {
		System.out.println("international shipping : "+ order.toString());
		restTemplate.postForLocation("http://localhost:8084/orders", order);
		return order;
	}
}
