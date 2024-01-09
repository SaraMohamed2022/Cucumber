package resources.testData;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class UtilsClass
{
    public static RequestSpecification requestSpecification;
    public RequestSpecification requestSpecefication() throws IOException {
        if(requestSpecification == null ) { // For more optimization not to create request specification instance with each TC
            PrintStream printStream = new PrintStream(new FileOutputStream("logging.txt", true));
            requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(getProperty("BaseURL"))
                    .addQueryParam("key", "qaclick123")
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .build();
            return requestSpecification;
        }
        return requestSpecification;
    }

    public static ResponseSpecification responseSpecification()
    {
        return new ResponseSpecBuilder()
                .expectStatusCode(anyOf(is(200),is(201)))
                .expectHeader("Server","Apache/2.4.52 (Ubuntu)").build();
    }

    public static String getProperty(String property) throws IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/testData/GlobalProperties.properties");
        properties.load(inputStream);
        return properties.getProperty(property);
    }

    public Object getJsonValue(Response response , String key)
    {
        System.out.println("Response in getJson value "+ response.asString());
        JsonPath responseJsonPath = new JsonPath(response.asString());
        return responseJsonPath.get(key);
    }
}
