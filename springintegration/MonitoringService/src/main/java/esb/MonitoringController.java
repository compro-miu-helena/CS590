package esb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {

    @PostMapping("/events")
    public ResponseEntity<?> receiveEvent(@RequestBody MonitorEvent event) {
        System.out.println("[MONITOR] source=" + event.getSource() +
                " order=" + event.getOrderNumber() +
                " step=" + event.getStep());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
