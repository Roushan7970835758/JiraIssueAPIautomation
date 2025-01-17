package Intro;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Files.Payload;

public class Intro {

	public static void main(String[] args) throws IOException {
		// validate if add place API is working as Expected
		
		//given :- all input details
		//when :- Submit the api -resource, http method
		//then :- Validate the response
		//content of the file to string -> content of file can convert into Byte -> Byte to string
		
		//Add place by post --> update place by put --> varify place added by get
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
//		String response =given().log().all().queryParams("key","qaclick123").header("content-type","application/json")
//		.body(Payload.addPlace()).when().post("maps/api/place/add/json").then()
//		.log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
//		.header("Server","Apache/2.4.52 (Ubuntu)").extract().asString();
		
//		String response =given().log().all().queryParams("key","qaclick123").header("content-type","application/json")
//				.body(Payload.addPlace()).when().post("maps/api/place/add/json").then()
//				.assertThat().statusCode(200).body("scope", equalTo("APP"))
//				.header("Server","Apache/2.4.52 (Ubuntu)").extract().asString();
		String response =given().log().all().queryParams("key","qaclick123").header("content-type","application/json")
				.body(new String (Files.readAllBytes(Paths.get("C:\\Users\\User\\eclipse-workspace\\TODO\\RestAsuredAPI\\src\\main\\java\\dataJson\\JsonData.json"))))
				.when().post("maps/api/place/add/json").then()
				.assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server","Apache/2.4.52 (Ubuntu)").extract().asString();
		
		//validate the response
		System.out.println("The Response is: "+ response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println("place_id from response is : " + placeId);
		
		//update place
		
		String newAddress = "Patna,Bihar";
		
		given().log().all().queryParam("key", "qaclick123").header("content-type","application/json")
		.body(Payload.updatePlace(placeId,newAddress)).when().put("/maps/api/place/update/json").then()
		.assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"))
		.header("Server","Apache/2.4.52 (Ubuntu)");
		
		//varify place
		
		String response_get = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("/maps/api/place/get/json").then().log().all()
		.assertThat().statusCode(200).body("address", equalTo(newAddress)).header("Server","Apache/2.4.52 (Ubuntu)").extract().asString();
		
//		System.out.println("Get response is : "+ response_get);
		JsonPath js_get = new JsonPath(response_get);
		String get_Address = js_get.getString("address");
		System.out.println("address Got form get req is : "+ get_Address);
		
		
		
		
		
		
		

	}

}
