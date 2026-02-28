package esb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MonitoringClient {

    @Autowired
    private RestTemplate restTemplate;

    public void logStep(String source, String orderNumber, String step) {
        try {
            restTemplate.postForLocation("http://localhost:8088/events", new MonitorEvent(source, orderNumber, step));
        } catch (Exception ignored) {
            // Monitoring must never break order processing.
        }
    }
}
