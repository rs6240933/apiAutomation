package swagger.controller;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Listeners;

import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import BaseUtils.BaseClass;
import BaseUtils.Reusables;

public class BooksController extends Reusables {

	protected static String getBooksEndPoint = "/api/v1/Activities";
	protected static String getSingleBooks = "/api/v1/Activities";

	protected static String validBookPayload = readJsonFromFile(
			System.getProperty("user.dir") + "/Input/BooksPayload/GetValidBook.json");

	public Response getAllBooks() {

		Response response = given().when().get(baseURI + getBooksEndPoint).andReturn();
		test.log(Status.INFO, "End Pointer ->" + getBooksEndPoint);
		test.log(Status.INFO, "Status Code ->" + response.getStatusCode());
		return response;
	}

	public Response getGivenPayloadInResponse() {

		Response response = given().header("Content-Type", "application/json").body(validBookPayload).when()
				.post(baseURI + getBooksEndPoint).andReturn();
		BaseClass.test.log(Status.INFO, "End Point ->" + getBooksEndPoint);
		BaseClass.test.log(Status.INFO, "Status Code ->" + response.getStatusCode());
		return response;
	}

	public Response SchemaValidation() {
		Response response = given().header("Content-Type", "application/json").body(validBookPayload).when()
				.post(baseURI + getBooksEndPoint).andReturn();
		return response;
	}

}
