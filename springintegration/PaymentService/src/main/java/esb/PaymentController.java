package esb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/mastercard")
    public ResponseEntity<?> payMastercard(@RequestBody Order order) {
        return pay(order, "Mastercard Payment Service");
    }

    @PostMapping("/visa")
    public ResponseEntity<?> payVisa(@RequestBody Order order) {
        return pay(order, "Visa Payment Service");
    }

    @PostMapping("/paypal")
    public ResponseEntity<?> payPaypal(@RequestBody Order order) {
        return pay(order, "PayPal Payment Service");
    }

    private ResponseEntity<?> pay(Order order, String serviceName) {
        String message = serviceName + " paid order: " + order;
        System.out.println(message);
        try {
            restTemplate.postForLocation("http://localhost:8088/events",
                    new MonitorEvent("PaymentService", order.getOrderNumber(), message));
        } catch (Exception ignored) {
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}
