package lab.mathservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    private final EvenOddClient evenOddClient;

    public MathController(EvenOddClient evenOddClient) {
        this.evenOddClient = evenOddClient;
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam("number") Integer number) {
        return evenOddClient.validate(number);
    }
}
