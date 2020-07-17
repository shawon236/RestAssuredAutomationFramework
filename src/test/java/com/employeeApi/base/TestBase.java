package com.employeeApi.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class TestBase {
    public static RequestSpecification httpRequest;
    public static Response response;
    public String empId ="228965"; //Hard coded - Input for details of single Employee & update Employee

    public Logger logger;
    @BeforeClass
    public void setup(){
        logger=Logger.getLogger("EmployeesRestApi"); //added logger
        PropertyConfigurator.configure("Log4j.properties"); //Added Logger
        logger.setLevel(Level.DEBUG);
    }
}
