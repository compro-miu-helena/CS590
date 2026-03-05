package lab.mathservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class EvenOddClient {

    private final RestClient restClient;

    public EvenOddClient(RestClient.Builder builder,
                         @Value("${evenodd.service-url:http://localhost:8090}") String evenOddServiceUrl) {
        this.restClient = builder.baseUrl(evenOddServiceUrl).build();
    }

    public String validate(int number) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/validate")
                        .queryParam("number1", number)
                        .queryParam("number2", number)
                        .build())
                .retrieve()
                .body(String.class);
    }
}
