package lab.evenoddservice;

import lab.evenoddservice.BaseTestClass;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;

import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@SuppressWarnings("rawtypes")
public class ContractVerifierTest extends BaseTestClass {

	@Test
	public void validate_add_two_numbers() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();


		// when:
			ResponseOptions response = given().spec(request)
					.queryParam("value1","2")
					.queryParam("value2","5")
					.get("/add");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);

		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			String responseBody = response.getBody().asString();
			assertThat(responseBody).isEqualTo("7");
	}

	@Test
	public void validate_validate_should_return_even() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();


		// when:
			ResponseOptions response = given().spec(request)
					.queryParam("number1","2")
					.queryParam("number2","2")
					.get("/validate");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);

		// and:
			String responseBody = response.getBody().asString();
			assertThat(responseBody).isEqualTo("Even");
	}

	@Test
	public void validate_validate_should_return_odd() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();


		// when:
			ResponseOptions response = given().spec(request)
					.queryParam("number1","1")
					.queryParam("number2","1")
					.get("/validate");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);

		// and:
			String responseBody = response.getBody().asString();
			assertThat(responseBody).isEqualTo("Odd");
	}

}
