package Simple_RESTAssured_Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC006_GET_BasicAuthenticationTest {

    @Test
    void getAuthenticationTest(){

        //Specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

        //Basic Authentication
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("ToolsQA");
        authScheme.setPassword("TestPassword");

        RestAssured.authentication = authScheme;

        //Request Object
        RequestSpecification httpRequest = RestAssured.given();

        //Response Object
        Response response = httpRequest.request(Method.GET,"/");

        //Print Response in Console window
        String responseBody = response.getBody().asString();
        System.out.println("Response body is "+ responseBody);

        //Status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is : "+statusCode);
        Assert.assertEquals(statusCode,200);
    }

}
