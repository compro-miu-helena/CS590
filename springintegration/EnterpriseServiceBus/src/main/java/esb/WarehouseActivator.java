package esb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class WarehouseActivator {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MonitoringClient monitoringClient;

    public Order checkStock(Order order) {
        String message = "Warehouse step for order " + order.getOrderNumber();
        System.out.println(message);
        monitoringClient.logStep("ESB", order.getOrderNumber(), message);
        restTemplate.postForLocation("http://localhost:8081/orders", order);
        return order;
    }
}
