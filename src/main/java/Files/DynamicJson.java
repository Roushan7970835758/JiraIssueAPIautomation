package Files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
//not successd
	@Test
	public void addBook() {
		RestAssured.baseURI ="http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json").body(Payload.addBook("adsfs","6464"))
		.when()
		.post("Library/Addbook.php")
		.then().log().all()
		.assertThat().statusCode(200).extract().asString();
		
		JsonPath js = ReusableMethod.rawToJson(response);
		String id =js.get("ID");
		
		System.out.println("the response is: " +id);
	}
	
	@DataProvider
	public Object[][] getData(){
		return  new Object[][]{{"oner","2343"},{"seref","2342"},{"ewifcn","23493"}};
	}
}
