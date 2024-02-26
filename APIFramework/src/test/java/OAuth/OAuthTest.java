package OAuth;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import pojo.GetCourse;
import pojo.WebAutomation;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthTest {

@Test
        public void oathTest() {


    //Hit this url and you will see Email ids witch which ypu are already signed in you need to use another account option give the username and password  as shown below press Enter

//    URL: https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
//    UserName: srinath19830
//     password: password

//you need to repeat the above procedure after some minutes to get the access code as the code generated can expire after a while
    //Once you REPEAT THE ABOVE PROCEDURE it will take you to the another page and ypu will get url as shown below this URL ypu will copy paste here in your framework
    String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWgavdcBfTHQWiSsHo7elmw1kGOQ6v3H3gCZAc7wNpbTFNwsTuObqzR51n3JFMl-37LsZQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
    ;
    //To extract the part of code that starts with code till the end we need the Split method of Java
    String partialCode = url.split("code=")[1];
    String code = partialCode.split("&scope")[0];
    //This is our access code
    System.out.println(code);

// To get Access Token we need following query params to be sent along with the access code generated above
    String accessTokenmResponse = given().urlEncodingEnabled(false).queryParam("code", code)
            .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
            .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
            .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
            .queryParam("grant_type", "authorization_code").when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
//Here we will parse the Json by extracting the Accesstoken from it and then append it to our get request
    JsonPath js = new JsonPath(accessTokenmResponse);
    String respoinse = js.getString("access_token");
    //This is our request to get the course Details and its necessary to have accesstoken appended with this request
    GetCourse gc = given().queryParam("access_token", respoinse).expect().defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
    System.out.println(gc.getInstructor());
    System.out.println(gc.getUrl());
    List<WebAutomation> course = gc.getCourses().getWebAutomation();
    for (
            int i = 0; i < course.size(); i++) {

        System.out.println(course.get(i).getPrice());


    }





}
}

