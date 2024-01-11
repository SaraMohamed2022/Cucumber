package stepDefenitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import resources.testData.UtilsClass;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CommonSteps extends UtilsClass
{
    @Getter
    public static Response response;
    public static RequestSpecification requestSpec;
    public CommonSteps() {

    }
    public CommonSteps(RequestSpecification requestSpec) {
        CommonSteps.requestSpec =requestSpec;
    }

    @Then("the API call success with status code {string}")
    public static void theAPICallSuccessWithStatusCode( String expectedStatusCode)
    {
        assertEquals(response.getStatusCode(),Integer.parseInt(expectedStatusCode));
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value) {
        assertEquals(getJsonValue(response,key), value);
    }

    @When("user calls {string} api with {string} request")
    public static Response userCallsApiWithRequest(String endPoint, String requestType) throws IOException {
        if (requestType.equalsIgnoreCase( "post"))
            response = requestSpec.when()
                    .post(getProperty(endPoint))
                    .then().log().all()
                    .spec(responseSpecification())
                    .extract().response();

        else if (requestType.equalsIgnoreCase("get"))
            response = requestSpec.when()
                    .get(getProperty(endPoint))
                    .then()
                    .spec(responseSpecification())
                    .extract().response();

        else if (requestType.equalsIgnoreCase("delete"))
            response = requestSpec.when()
                    .delete(getProperty(endPoint))
                    .then().spec(responseSpecification()).extract().response();

        else
            fail("You entered invalid request type");

        return response;
    }

    public static void setResponse(Response response) {
        CommonSteps.response = response;
    }

    public static RequestSpecification getRequestSpecification() {
        return requestSpec;
    }

    public static void setRequestSpecification(RequestSpecification requestSpec) {
        CommonSteps.requestSpec = requestSpec;
    }
}
