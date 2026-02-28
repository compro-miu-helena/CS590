package esb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class MastercardPaymentActivator {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MonitoringClient monitoringClient;

    public void pay(Order order) {
        String message = "Routing payment to MASTERCARD for order " + order.getOrderNumber();
        System.out.println(message);
        monitoringClient.logStep("ESB", order.getOrderNumber(), message);
        restTemplate.postForLocation("http://localhost:8085/payments", order);
    }
}
