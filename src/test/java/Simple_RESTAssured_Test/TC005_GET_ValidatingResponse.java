package Simple_RESTAssured_Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC005_GET_ValidatingResponse {

    @Test
    void validatingResponseBody(){
        //Specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        //Request Object
        RequestSpecification httpRequest = RestAssured.given();

        //Response Object
        Response response = httpRequest.request(Method.GET,"/Dhaka");

        //Print Response in Console window
        String responseBody = response.getBody().asString();
        System.out.println("Response body is "+ responseBody);

        SoftAssert softAssert = new SoftAssert();


        softAssert.assertEquals(responseBody.contains("Dhaka"),true);

        //Validating Node value in response Body by using JsonPath
        JsonPath jsonpath = response.jsonPath();
        softAssert.assertEquals(jsonpath.get("Temperature"), "14.23 Degree celsius");




        //Extract value from Each Node
        System.out.println(jsonpath.get("City"));
        System.out.println(jsonpath.get("Temperature"));
        System.out.println(jsonpath.get("Humidity"));
        System.out.println(jsonpath.get("WeatherDescription"));
        System.out.println(jsonpath.get("WindSpeed"));

        softAssert.assertAll();
    }
}
