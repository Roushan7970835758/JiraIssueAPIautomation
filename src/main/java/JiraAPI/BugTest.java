package JiraAPI;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static  io.restassured.RestAssured.*;
import java.io.File;
import Files.JiraApiData;

public class BugTest {	
	public static void main(String[] args) {
		 RestAssured.baseURI = JiraApiData.baseURI();
		 
		 //post issue
		 String createIssueresponse = given()
		.header("Content-Type","application/json")
		.header("Authorization",JiraApiData.authenticationHeader())
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Created by API automation \",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}\r\n"
				+ "")
		.post("rest/api/3/issue").then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(createIssueresponse);
		String issueId = js.getString("id");
		String issueKey = js.getString("key");
		
		
		
		//add Attachments
		given()
		.header("X-Atlassian-Token"," no-check").pathParam("key", issueKey)
		.header("Authorization",JiraApiData.authenticationHeader())
		.multiPart("file",new File("C:/Users/User/Downloads/6194961103360999063-removebg-preview.png"))
		.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		//get an issue
		given()
		.header("Content-Type","application/json/")
		.header("Authorization",JiraApiData.authenticationHeader())
		.get("rest/api/3/issue/"+issueId+"").then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println("The newly created issue id is: " + issueId + " key is: " + issueKey);

	}

}
