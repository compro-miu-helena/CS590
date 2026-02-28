package esb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class VisaPaymentController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/payments")
    public ResponseEntity<?> pay(@RequestBody Order order) {
        String message = "Visa Payment Service paid order: " + order;
        System.out.println(message);
        try {
            restTemplate.postForLocation("http://localhost:8088/events",
                    new MonitorEvent("VisaPaymentService", order.getOrderNumber(), message));
        } catch (Exception ignored) {
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}
