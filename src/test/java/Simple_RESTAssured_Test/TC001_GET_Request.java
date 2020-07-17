package Simple_RESTAssured_Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_GET_Request {

    @Test
    void getWeatherDetails(){

        //Specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city/";

        //Request Object
        RequestSpecification httpRequest = RestAssured.given();

        //Response Object
        Response response = httpRequest.request(Method.GET,"/Dhaka");

        //Print Response in Console window
        String responseBody = response.getBody().asString();
        System.out.println("Response body is "+ responseBody);

        //Status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is : "+statusCode);
        Assert.assertEquals(statusCode,300);

        //Status line verification
        String statusLine = response.getStatusLine();
        System.out.println("Status line is "+ statusLine);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

    }
}
