package RestAssuredGroup.RestAssuredArtifact;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;

import net.minidev.json.JSONObject;

public class restAssuredTest {
	
	@Test(enabled=true, priority=0)
	public void getTest() {

		given().
		when().
			get("https://reqres.in/api/users?page=2").
			prettyPeek().
		then().
			statusCode(200).
			body("data.findAll{it.id > 4}.last_name",hasItems("Morris","Ramos"));
				
		given().
			when().
			get("https://reqres.in/api/users/2").
			prettyPeek().
		then().
			statusCode(200).
			body("data.id", equalTo(2)).
			body("data.first_name", equalTo("Janet"));

	}
	
	@Test(enabled=true, priority=1)
	public void postTest(){
		
		Map<String, Object>  jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", "morpheus");
		jsonAsMap.put("job", "leader");
		
		given().
			//contentType(ContentType.JSON).
			header("Content-Type", "application/json").
			body(jsonAsMap).
		when().
			post("https://reqres.in/api/users").
			prettyPeek().
		then().
			statusCode(201).
			body("name", equalTo("morpheus"));
		
	}
	
	@Test(enabled=true, priority=2)
	public void putTest(){
		Map<String, Object> jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", "Morpheus");
		jsonAsMap.put("job", "Manager");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "morpheus");
		jsonObject.put("job", "Manager");
		
		given().
			//content(ContentType.JSON).
			header("Content-Type", "application/json").
			body(jsonAsMap).
			pathParam("id", 237).
		when().
			put("https://reqres.in/api/users/{id}").
			prettyPeek().
		then().
			statusCode(200).
			body("name", equalTo("Morpheus")).
			and().
			body("job", equalTo("Manager"));
	}
	
	@Test(enabled=true, priority=3)
	public void deleteTest(){
		given().
		when().
			delete("https://reqres.in/api/users/2").
			prettyPeek().
		then().
			statusCode(204);
	}
}
