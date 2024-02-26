package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;


	TestDataBuild data =new TestDataBuild();
	static String place_id;

	public static RequestSpecification req;
	static String name = "AAhouse";
	static String language = "German";
	static String address = "walton";
	String resource = "AddPlaceAPI";
	String method = "POST";
	static String keyValue = "scope";
	static String Expectedvalue = "APP";

	String valueGet = "getPlaceAPI";

	public  RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;


	}


	public  String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("/Users/ansjamil/Downloads/APIFramework/src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);


	}


	public  String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}


	public  void calls_with_http_request(String resource, String method) throws IOException {
			res = given().spec(requestSpecification())
					.body(data.addPlacePayLoad(name, language, address));

			APIResources resourceAPI = APIResources.valueOf(resource);
			System.out.println(resourceAPI.getResource());


			resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


//			response = res.when().post(resourceAPI.getResource());
			if (method.equalsIgnoreCase("POST")) {
				response = res.when().post(resourceAPI.getResource());
				String value = response.asString();
				System.out.println(value);
				assertEquals(response.getStatusCode(), 200);
			assertEquals(getJsonPath(response,keyValue),Expectedvalue);

			} else if (method.equalsIgnoreCase("GET")) {
				response = res.when().get(resourceAPI.getResource());
				String value = response.asString();
				System.out.println(value);
;
			}


	}

	public  void verify_place_Id(String expectedName, String resource) throws IOException {

		// requestSpec
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		with_http_request(resource, "GET");
		String actualName=getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}

	public  void with_http_request(String resource, String method) throws IOException {


		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());


		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


//		response = res.when().post(resourceAPI.getResource());
		if (method.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceAPI.getResource());



		} else if (method.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceAPI.getResource());


		}


	}





	public void delete_place_Payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions

		res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));


	}
	public void got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);


	}
	public void get_response_body_is(String keyValue, String Expectedvalue) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	}

}
