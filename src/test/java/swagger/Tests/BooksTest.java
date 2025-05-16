package swagger.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.response.Response;
import swagger.controller.BooksController;

public class BooksTest extends BooksController {

	Response response;

	@Test(priority = 1)
	public void verifyBooksResponsseIsNotNull() {
		try {
			response = getAllBooks();
			Assert.assertTrue(!response.getBody().asString().equals(null));
		} catch (Throwable e) {
			String error = e.getMessage();
			printer("*********** " + e.getMessage());
			printer("Base URL -> " + baseURI + " EndPoint ->" + getBooksEndPoint);
			response.then().log().all().extract().response();
			throw new AssertionError(error, e);
		    
		}

	}
	
	@Test(priority = 1)
	public void verifyResponseByFiltering() {
		try {
			response = getGivenPayloadInResponse();
			JsonObject payload = jsonparser(validBookPayload).getAsJsonObject();
			Assert.assertEquals((int)response.then().extract().path("id"), payload.get("id").getAsInt());
		}
		catch(Throwable e) {
			String error = e.getMessage();
			printer("*********** " + e.getMessage());
			printer("Base URL -> " + baseURI + " EndPoint ->" + getSingleBooks);
			response.then().log().all().extract().response();
			throw new AssertionError(error, e);
		}
		
	}
	@Test(priority = 1)
	public void schemaValidationOfResponseFiltering() {
		response = SchemaValidation();
		SchemaValidatorJson(response, "./Input/BooksPayload/getRecords.json");
	}

}
