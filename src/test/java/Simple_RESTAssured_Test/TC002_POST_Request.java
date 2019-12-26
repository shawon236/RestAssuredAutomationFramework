package Simple_RESTAssured_Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_POST_Request {

    @Test
    void registerCustomer(){

        //Specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";

        //Request Object
        RequestSpecification httpRequest = RestAssured.given();

        //Request payload sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName","SteveRoss1");
        requestParams.put("LastName","RossSteve1");
        requestParams.put("UserName","SteveRoss1231");
        requestParams.put("Password","SteveRoss1");
        requestParams.put("Email","SteveRoss1@gmailcom");

        httpRequest.header("Content-Type","application/json");

        //Attache above data to the request
        httpRequest.body(requestParams.toJSONString());

        //Response Object
        Response response = httpRequest.request(Method.POST,"/register");

        //Print Response in Console window
        String responseBody = response.getBody().toString();
        System.out.println("Response body is "+ responseBody);

        //Status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is : "+statusCode);
        Assert.assertEquals(statusCode,201);

        //Success code validation

        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode,"OPERATION_SUCCESS");
    }
}
