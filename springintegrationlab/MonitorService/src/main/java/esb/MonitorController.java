package esb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitorController {

    @PostMapping("/warehouse")
    public ResponseEntity<?> warehouse(@RequestBody Order order) {
        System.out.println("Check the warehouse with order: "+order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @PostMapping("/internationalshipping")
    public ResponseEntity<?> internationalshipping(@RequestBody Order order) {
        System.out.println("Ship with internationalshipping order: "+order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @PostMapping("/normalshipping")
    public ResponseEntity<?> normalshipping(@RequestBody Order order) {
        System.out.println("Ship with normalshipping order: "+order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @PostMapping("/nextdayshipping")
    public ResponseEntity<?> nextdayshipping(@RequestBody Order order) {
        System.out.println("Ship with nextdayshipping order: "+order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @PostMapping("/visapayment")
    public ResponseEntity<?> visapayment(@RequestBody Order order) {
        System.out.println("visapayment for order: "+order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @PostMapping("/mastercardpayment")
    public ResponseEntity<?> mastercardpayment(@RequestBody Order order) {
        System.out.println("mastercardpayment for order: "+order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @PostMapping("/paypalpayment")
    public ResponseEntity<?> paypalpayment(@RequestBody Order order) {
        System.out.println("paypalpayment for order: "+order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

}
