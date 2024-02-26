package TDD;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class APIFramework extends Utils {
    static RequestSpecification res;
    static ResponseSpecification resspec;
    static Response response;
    static TestDataBuild data = new TestDataBuild();
    static String place_id;
    public static WebDriver driver;
    String name = "AAhouse";
    String language = "German";
    String address = "walton";
    String keyvalue="status";
    String expectedValue="OK";

    @Test(priority = 1)
    public void postCall() throws IOException {
        String resource = "AddPlaceAPI";
        String method = "POST";
        String call = "post";
        String keyvalue="status";
        String expectedValue="OK";
        String scopeName="scope";
        String scopevalue="APP";
        calls_with_http_request(resource, method);
        with_http_request(resource, method);
        get_response_body_is(keyvalue,expectedValue);
        get_response_body_is(scopeName,scopevalue);


    }

    @Test(priority = 2)
    public void getCall() throws IOException {
        String resourceGet = "getPlaceAPI";
        String name = "AAhouse";
        verify_place_Id(name, resourceGet);

    }


    @Test(priority = 3)
    public void deleteCall() throws IOException {
        String call = "delete";
        String resourceDel = "deletePlaceAPI";
        String method = "POST";
        int status = 200;
        String statusname = "status";
        String statusvalue = "OK";

        delete_place_Payload();
        with_http_request(resourceDel, method);
        got_success_with_status_code(status);
        get_response_body_is(statusname,statusvalue);


    }


}















