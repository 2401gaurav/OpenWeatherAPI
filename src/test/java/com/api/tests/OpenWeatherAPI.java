package com.api.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OpenWeatherAPI {

	ArrayList<String> id = new ArrayList<String>();

	@Test(priority=0)
	public void tokenPostAPIValidateKeyTest1() {

		RestAssured.baseURI = "http://api.openweathermap.org";
		RequestSpecification request = RestAssured.given().log().all();

		String payload = "{\"external_id\":\"SF_TEST001\",\"name\":\"San Francisco Test Station\",\"latitude\":37.76,\"longitude\":-122.43,\"altitude\":150}";

		request.contentType("application/json");
		request.body(payload);
		Response response = request.post("/data/3.0/stations");

		System.out.println(response.statusCode());

		JsonPath js = response.jsonPath();

		Assert.assertEquals(401, response.statusCode());
		Assert.assertEquals("Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.",
				js.get("message").toString());

		// POJO or DTO

	}

	@Test(priority=1)
	public void tokenPostRegisterAPI1Test2() {

		RestAssured.baseURI = "http://api.openweathermap.org";
		RequestSpecification request = RestAssured.given().log().all();

		String payload = "{\"external_id\":\"DEMO_TEST001\",\"name\":\"Interview Station 1\",\"latitude\":33.33,\"longitude\":-111.44,\"altitude\":444}";

		request.contentType("application/json");
		request.queryParam("appid", "f16901f2800dc74819238c93cde54925");
		request.body(payload);
		Response response = request.post("/data/3.0/stations");

		System.out.println(response.statusCode());

		JsonPath js = response.jsonPath();

		id.add(js.getJsonObject("ID").toString());

		System.out.println(js.get());

		// POJO or DTO

	}

	@Test(priority=2)
	public void tokenPostRegisterAPI2Test3() {

		RestAssured.baseURI = "http://api.openweathermap.org";
		RequestSpecification request = RestAssured.given().log().all();

		String payload = "{\"external_id\":\"DEMO_TEST001\",\"name\":\"Interview Station 2\",\"latitude\":33.34,\"longitude\": -12.44,\"altitude\":444}";

		request.contentType("application/json");
		request.queryParam("appid", "f16901f2800dc74819238c93cde54925");
		request.body(payload);
		Response response = request.post("/data/3.0/stations");

		System.out.println(response.statusCode());

		JsonPath js = response.jsonPath();
		id.add(js.getJsonObject("ID").toString());

		System.out.println(js.get());

		// POJO or DTO

	}

	@Test(priority=3)
	public void tokenGetStationTest4() {

		RestAssured.baseURI = "http://api.openweathermap.org";
		RequestSpecification request = RestAssured.given().log().all();

		request.contentType("application/json");
		request.queryParam("appid", "f16901f2800dc74819238c93cde54925");

		Response response = request.get("/data/3.0/stations");

		System.out.println(response.statusCode());

		JsonPath js = response.jsonPath();
		List<Object> list = js.getList("id");

		Assert.assertEquals(id.get(0), list.get(1));
		Assert.assertEquals(id.get(1), list.get(2));
		// POJO or DTO

	}

	@Test(priority=4)
	public void tokenDeleteStationTest5() {

		RestAssured.baseURI = "http://api.openweathermap.org";
		RequestSpecification request = RestAssured.given().log().all();
	//	for (String stationId : id) {

			request.contentType("application/json");
			request.queryParam("appid", "f16901f2800dc74819238c93cde54925");
			request.queryParam("id", "5f1bce33cca8ce0001ef4c41");
			Response response = request.delete("/data/3.0/stations" );

			System.out.println(response.statusCode());

			System.out.println(response.getStatusCode());

			// POJO or DTO
		}

	//}

}
