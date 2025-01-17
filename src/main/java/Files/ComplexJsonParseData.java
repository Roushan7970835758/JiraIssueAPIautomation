package Files;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParseData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(Payload.coursePrice());
		
		String temp = js.getString("dashboard");
		System.out.println(temp);
		
		//print no. of courses
		System.out.println(js.getInt("courses.size()"));
		
		//print purchase amount
		System.out.println("Purchase amt is : " + js.getInt("dashboard.purchaseAmount"));
		
		//print title of the first course
		
		System.out.println("Title of the course is : " + js.getString("courses[0].title"));
		
		//print all the courses title and their respective prices
		
		for(int i=0;i<js.getInt("courses.size()"); i++) {
			System.out.println("course:  "+ js.getString("courses["+ i +"].title") + "  price: "+ 
		js.getInt("courses["+ i +"].price"));
		}
		
		//print no. of copies sold by RPA course
		
		System.out.println("RPA copies sold: " + js.getInt("courses[0].copies"));
		
		//varify if sum of all courses price matches with purchase amount
		int sum =0;
		for(int i=0; i<js.getInt("courses.size()"); i++) {
			sum+= js.getInt("courses["+ i + "].price")* js.getInt("courses["+ i + "].copies");
		}
		System.out.println(sum);
		System.out.println(js.getInt("dashboard.purchaseAmount"));
		Assert.assertEquals(sum, js.getInt("dashboard.purchaseAmount"));
		
		
		
		

	}

}
