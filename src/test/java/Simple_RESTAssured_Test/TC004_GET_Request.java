package Simple_RESTAssured_Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TC004_GET_Request {

    @Test
    void printAllHeaders(){
        //Specify base URI
        RestAssured.baseURI = "https://maps.googleapis.com";

        //Request Object
        RequestSpecification httpRequest = RestAssured.given();

        //Response Object
        Response response = httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jyls");

        //Print Response in Console window
        String responseBody = response.getBody().asString();
        System.out.println("Response body is "+ responseBody);

        //Capture all the headers from response
        Headers allHeaders = response.headers();

        for(Header header:allHeaders){
            System.out.println(header.getName()+" :  "+header.getValue());

        }


    }
}
