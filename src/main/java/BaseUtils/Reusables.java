package BaseUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import BaseUtils.BaseClass;

public class Reusables extends BaseClass{
	
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";
	

	

	public static void printer(String message) {
		
		System.out.println(ANSI_RED + " " + message + " " + ANSI_RESET);
	}

	public static String readJsonFromFile(String filePath) {
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	public static JsonObject jsonparser(String payload) {

		return new JsonParser().parse(payload).getAsJsonObject();
	}
	
	public void SchemaValidatorJson(Response response , String SchemaPath) {
		 File schemaFile = new File(SchemaPath);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
	}

}
