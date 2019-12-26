package Simple_RESTAssured_Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_GET_Request {

    @Test
    void validatingHeaderGoogleMapApi(){
        //Specify base URI
        RestAssured.baseURI = "https://maps.googleapis.com";

        //Request Object
        RequestSpecification httpRequest = RestAssured.given();

        //Response Object
        Response response = httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jyls");

        //Print Response in Console window
        String responseBody = response.getBody().asString();
        System.out.println("Response body is "+ responseBody);

        // Capture details of Headers from Response
        String contentType = response.header("Content-Type");
        System.out.println("Content type is :"+ contentType);
        Assert.assertEquals(contentType,"application/xml; charset=UTF-8");

        String contentEncoding = response.header("Content-Encoding");
        System.out.println("Content Encoding is :"+ contentEncoding);
        Assert.assertEquals(contentEncoding,"gzip");


    }
}
