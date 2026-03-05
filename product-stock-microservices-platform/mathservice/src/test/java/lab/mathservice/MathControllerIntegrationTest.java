package lab.mathservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.aiche:evenoddservice:+:stubs:8090"
)
class MathControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenWhenPassEvenNumberInQueryParamThenReturnEven() throws Exception {
        mockMvc.perform(get("/calculate?number=2").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Even"));
    }

    @Test
    void givenWhenPassOddNumberInQueryParamThenReturnOdd() throws Exception {
        mockMvc.perform(get("/calculate?number=1").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Odd"));
    }
}
