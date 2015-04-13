package util;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.JsonPath;

public class JsonParser {

	static String fileName = "1.json";

	static String jsonString = "{     \"firstName\": \"John\",     \"lastName\": \"Smith\",     \"age\": 25,     \"address\": {         \"streetAddress\": \"21 2nd Street\",         \"city\": \"New York\",         \"state\": \"NY\",         \"postalCode\": 10021     },     \"phoneNumbers\": [         {             \"type\": \"home\",             \"number\": \"212 555-1234\"         },         {             \"type\": \"fax\",             \"number\": \"646 555-4567\"          }     ]  }";

	public static void main(String args[]) throws JSONException {
		parser(jsonString);
	}

	public static void parser(String jsonString) throws JSONException {
		JSONObject obj = new JSONObject(jsonString);

		// Object jsonPathResult=JsonPath.read(jsonString,"firstName");

		System.out.println(JsonPath.read(jsonString, "firstName"));
		System.out.println(JsonPath.read(jsonString, "address.streetAddress"));
		try {
			System.out.println(JsonPath.read(jsonString, "address.notExist"));
		} catch (InvalidPathException e) {
			System.out.println("path not found");
		}

		System.out.println(obj.getJSONObject("address").getString(
				"streetAddress"));
		System.out.println(obj.getString("firstName"));
	}

}
