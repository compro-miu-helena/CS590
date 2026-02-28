package esb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class NextDayShippingActivator {

	RestTemplate restTemplate = new RestTemplate();

	public void ship(Order order) {
		System.out.println("Nest day shipping: "+ order.toString());
		restTemplate.postForLocation("http://localhost:8083/orders", order);
	}
}
