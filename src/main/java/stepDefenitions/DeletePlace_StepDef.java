package stepDefenitions;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.testData.UtilsClass;
import resources.testData.PayLoads;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class DeletePlace_StepDef extends UtilsClass {
    RequestSpecification requestSpec;
    String place_id;
    Response response;
    PayLoads payLoads = new PayLoads();
    AddPlace_StepDef addPlace= new AddPlace_StepDef();
    CommonSteps commonSteps;
    @Given("Delete Place Payload")
    public void deletePlacePayload() throws IOException {
        place_id =getJsonValue(sendAddPlaceRequest(),"place_id").toString();
        System.out.println(" delete payload " +payLoads.deletePlacePayloadData(place_id) );

        requestSpec= given().log().all()
                .spec(requestSpecefication())
                .body(payLoads.deletePlacePayloadData(place_id));
    }

    private Response sendAddPlaceRequest() throws IOException {
        requestSpec = addPlace.addPlacePayload();
        commonSteps=new CommonSteps(requestSpec);
        response=commonSteps.userCallsApiWithRequest("AddPlaceAPI","post");
        return response;
    }
}
