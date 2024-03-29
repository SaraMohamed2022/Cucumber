package stepDefenitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.googleMaps.AddPlace;
import resources.testData.PayLoads;
import resources.testData.UtilsClass;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static stepDefenitions.CommonSteps.setRequestSpecification;
import static stepDefenitions.CommonSteps.userCallsApiWithRequest;


public class AddPlace_StepDef extends UtilsClass {

    RequestSpecification requestSpec;
    Response response;
    AddPlace addPlace = new AddPlace();
    PayLoads payLoads = new PayLoads();

    CommonSteps commonSteps = new CommonSteps();
        @Given("Add Place Payload")
        public RequestSpecification addPlacePayload() throws IOException {
             addPlace = payLoads.addPlacePayloadData();
            requestSpec= given().log().all()
                     .spec(requestSpecefication())
                     .body(addPlace);
            CommonSteps.setRequestSpecification(requestSpec);
            return requestSpec;
        }

    @Given("Add Place Payload with {string} , {string} , {string}")
    public void addPlacePayloadWith(String name, String adress, String language) throws IOException {
        addPlace = payLoads.addPlacePayloadData(name , adress , language);
        requestSpec= given().spec(requestSpecefication()).body(addPlace);
        setRequestSpecification(requestSpec);
    }

    @And("verify the generated Place_Id maps to {string} using {string} API")
    public void verifyTheGeneratedPlace_IdMapsToUsingGetPlaceAPI( String placeName, String endpoint) throws IOException
    {
        requestSpec =given().log().all()
                .spec(requestSpecefication())
                .queryParam("place_id",getJsonValue(CommonSteps.getResponse(),"place_id").toString())
                .log().all();
        setRequestSpecification(requestSpec);
        response = userCallsApiWithRequest(endpoint , "get");
        assertEquals(getJsonValue(response,"name"),placeName);
    }
}

