package com.employeeApi.testCases;

import com.employeeApi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001_Get_All_Employees extends TestBase {

    @BeforeClass
    void getAllEmployees() throws InterruptedException {
        logger.info("===================Started TC001_Get_All_Employees========================");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest=RestAssured.given();
        response=httpRequest.request(Method.GET,"/employees");
        Thread.sleep(7000);
    }

    @Test
    void checkResponseBody(){
        logger.info("===================Checking Response Body========================");
        String responseBody = response.getBody().asString();
        logger.info("Response Body : "+responseBody);
        Assert.assertTrue(responseBody!=null);
    }

    @Test
    void checkStatusCode(){
        logger.info("===================Checking Status Code========================");
        int statusCode = response.getStatusCode();
        logger.info("Status code : "+statusCode);
        Assert.assertEquals(statusCode,200);
    }

    @Test
    void  checkResponseTime(){
        logger.info("===================Checking Response Time========================");
        long responseTime = response.getTime();
        logger.info("Response Time : "+responseTime);
        if(responseTime>2000)
            logger.warn("Response time greater than 2000 ms");
        Assert.assertTrue(responseTime<2000);
    }

    @Test
    void checkStatusLine(){
        logger.info("===================Checking Status Line========================");
        String statusLine = response.getStatusLine();
        logger.info("Status Line : "+statusLine);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
    }

    @Test
    void checkContentType(){
        logger.info("===================Checking Content Type========================");
        String contentType = response.getContentType();
        logger.info("Content Type  : "+contentType);
        Assert.assertEquals(contentType,"text/html; charset=UTF-8");
    }

    @Test
    void checkServerType(){
        logger.info("===================Checking Sever Type========================");
        String serverType = response.header("Server");
        logger.info("Server Type  : "+serverType);
        Assert.assertEquals(serverType,"nginx/1.14.1");
    }
    @Test
    void checkContentEncoding(){
        logger.info("===================Checking Content Encoding========================");
        String contentEncoding = response.header("Content-Encoding");
        logger.info("Content Encoding  : "+contentEncoding);
        Assert.assertEquals(contentEncoding,"gzip");
    }

    @Test
    void CheckContentLength(){
        logger.info("===================Checking Content Length========================");
        String contentLength = response.header("Content-Length");
        logger.info("Content Length  : "+contentLength);
        if(Integer.parseInt(contentLength) <100 )
            logger.warn("Content Length less than 100");
        Assert.assertTrue(Integer.parseInt(contentLength)>100);
    }

    @Test
    void checkCookies(){
        logger.info("===================Checking Cookies========================");
        String checkCookies = response.getCookie("PHPSESSID");
    }

    @AfterClass
    void tearDown(){
        logger.info("=================== Finished TC001_Get_All_Employees ========================");
    }

}
