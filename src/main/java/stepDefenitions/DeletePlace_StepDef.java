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
    Response response;
    PayLoads payLoads = new PayLoads();
    AddPlace_StepDef addPlace= new AddPlace_StepDef();
    CommonSteps commonSteps = new CommonSteps();
    @Given("Delete Place Payload")
    public void deletePlacePayload() throws IOException {
        requestSpec= given().log().all()
                .spec(requestSpecefication())
                .body(payLoads.deletePlacePayloadData(getJsonValue(sendAddPlaceRequest(),"place_id").toString()));
        CommonSteps.setRequestSpecification(requestSpec);
    }

    private Response sendAddPlaceRequest() throws IOException {
        requestSpec = addPlace.addPlacePayload();
        commonSteps=new CommonSteps(requestSpec);
        response=commonSteps.userCallsApiWithRequest("AddPlaceAPI","post");
        return response;
    }
}
