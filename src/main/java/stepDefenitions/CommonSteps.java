package stepDefenitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.testData.UtilsClass;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class CommonSteps extends UtilsClass
{
    public static Response response;
    public static RequestSpecification requestSpec;
    public CommonSteps(RequestSpecification requestSpec) {
        this.requestSpec=requestSpec;
    }

    @Then("the API call success with status code {string}")
    public static void theAPICallSuccessWithStatusCode( String expectedStatusCode)
    {
        System.out.println(response.asString() + " response in status code");
        assertEquals(response.getStatusCode(),Integer.parseInt(expectedStatusCode));
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value) {
        System.out.println(response.asString() + " Response ");
        assertEquals(getJsonValue(response,key), value);
    }

    @When("user calls {string} api with {string} request")
    public static Response userCallsApiWithRequest(String endPoint, String requestType) throws IOException {
        System.out.println("End point " + endPoint);
        System.out.println("Request type " + requestType);

        if (requestType.equalsIgnoreCase( "post"))
            response = requestSpec.when()
                    .post(getProperty(endPoint))
                    .then()
                    .spec(responseSpecification())
                    .extract().response();

        else if (requestType.equalsIgnoreCase("get"))
            response = requestSpec.when()
                    .get(getProperty(endPoint))
                    .then().log().all()
                    .spec(responseSpecification())
                    .extract().response();

        else if (requestType.equalsIgnoreCase("delete"))
            response = requestSpec.when()
                    .delete(getProperty(endPoint))
                    .then().log().all().spec(responseSpecification()).extract().response();

        else
            assertFalse(true , "You entered invalid request type");

        System.out.println(response.asString() + " Response ");
        return response;
    }

    public static Response getResponse() {
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
