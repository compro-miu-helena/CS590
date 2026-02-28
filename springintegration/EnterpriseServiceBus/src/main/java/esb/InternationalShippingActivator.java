package esb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class InternationalShippingActivator {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MonitoringClient monitoringClient;

    public Order ship(Order order) {
        String message = "Routing to INTERNATIONAL shipping for order " + order.getOrderNumber();
        System.out.println(message);
        monitoringClient.logStep("ESB", order.getOrderNumber(), message);
        restTemplate.postForLocation("http://localhost:8084/orders", order);
        return order;
    }
}
