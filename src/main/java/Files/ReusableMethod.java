package Files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethod {

	public static JsonPath rawToJson(String res) {
		 JsonPath js1 = new  JsonPath(res);
		 return js1;
	}

}
