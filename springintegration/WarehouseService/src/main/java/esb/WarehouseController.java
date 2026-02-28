package esb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WarehouseController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/orders")
    public ResponseEntity<?> receiveOrder(@RequestBody Order order) {
        String message = "Warehouse Application receiving order: " + order;
        System.out.println(message);
        try {
            restTemplate.postForLocation("http://localhost:8088/events",
                    new MonitorEvent("WarehouseService", order.getOrderNumber(), message));
        } catch (Exception ignored) {
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}
