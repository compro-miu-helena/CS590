package esb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class VisaPaymentActivator {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MonitoringClient monitoringClient;

    public void pay(Order order) {
        String message = "Routing payment to VISA for order " + order.getOrderNumber();
        System.out.println(message);
        monitoringClient.logStep("ESB", order.getOrderNumber(), message);
        restTemplate.postForLocation("http://localhost:8085/payments/visa", order);
    }
}
