package DataDrivenTesting_AddNewEmployee;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class DataDrivenTest_AddEmployee {

    @Test(dataProvider = "employeeDataProvider")
    void postNewEmployees(String eName,String eSal, String eAge){
        //Specify base URI
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

        //Request Object
        RequestSpecification httpRequest = RestAssured.given();

        //Here Created data which we can send along with the post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("name",eName);
        requestParams.put("age",eAge);
        requestParams.put("salary",eSal);

        //Add a header Stating the request body is a json
        httpRequest.header("Content-Type","application/json");

        //Add the json to the body of the request
        httpRequest.body(requestParams.toJSONString());

        //Response Object-Post Request
        Response response = httpRequest.request(Method.POST,"/create");

        //Print Response in Console window
        String responseBody = response.getBody().asString();
        System.out.println("Response body is "+ responseBody);


        //Validation
        Assert.assertEquals(responseBody.contains(eName),true);
        Assert.assertEquals(responseBody.contains(eAge),true);
        Assert.assertEquals(responseBody.contains(eSal),true);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);

    }

    @DataProvider(name="employeeDataProvider")
     Object [][] getEmpData () throws IOException

    {
        //Read data from Excel
        String path = System.getProperty("user.dir")+"/src/test/java/DataDrivenTesting_AddNewEmployee/empData.xlsx";
        System.out.println(path);
       int rowNum =  XLUtils.getRowCount(path,"Sheet1");
        System.out.println(rowNum);
       int colNum = XLUtils.getCellCount(path,"Sheet1",1);
        System.out.println(colNum);

       String empDatax [][] = new String[rowNum][colNum];

       for(int i = 1;i<=rowNum;i++){
           for(int j=0;j<colNum;j++){
               empDatax[i-1][j] = XLUtils.getCellData(path,"Sheet1",i,j);
               System.out.println(empDatax[i-1][j]);
           }
        }
        return(empDatax);

    }
}
