
package esb;


import org.springframework.web.client.RestTemplate;

public class MonitorActivator {

	RestTemplate restTemplate = new RestTemplate();

	public void warehouse(Order order) {
		System.out.println("---Monitor warehouse"+order.toString());
		restTemplate.postForLocation("http://localhost:8099/warehouse", order);
	}

	public void internationalshipping(Order order) {
		System.out.println("---Monitor Internationalshipping "+order.toString());
		restTemplate.postForLocation("http://localhost:8099/internationalshipping", order);
	}

	public void normalshipping(Order order) {
		System.out.println("---Monitor Normalshipping "+order.toString());
		restTemplate.postForLocation("http://localhost:8099/normalshipping", order);
	}

	public void nextdayshipping(Order order) {
		System.out.println("---Monitor Nextdayshipping "+order.toString());
		restTemplate.postForLocation("http://localhost:8099/nextdayshipping", order);
	}

	public void visapayment(Order order) {
		System.out.println("---Monitor visapayment "+order.toString());
		restTemplate.postForLocation("http://localhost:8099/visapayment", order);
	}

	public void mastercardpayment(Order order) {
		System.out.println("---Monitor mastercardpayment "+order.toString());
		restTemplate.postForLocation("http://localhost:8099/mastercardpayment", order);
	}

	public void paypalpayment(Order order) {
		System.out.println("---Monitor paypalpayment "+order.toString());
		restTemplate.postForLocation("http://localhost:8099/paypalpayment", order);
	}

}
