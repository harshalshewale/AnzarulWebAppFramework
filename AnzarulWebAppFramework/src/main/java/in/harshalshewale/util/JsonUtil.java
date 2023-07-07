package in.harshalshewale.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jayway.jsonpath.JsonPath;

public class JsonUtil {

	private static final Gson gson = new Gson();

	public static String readTestDataJsonFile(String jsonFile) throws ParseException, IOException {
		Object jsonObject = null;
		JSONParser jsonParser = new JSONParser();
		String filename = "src/test/resources/data/testdata/" + jsonFile;
		FileReader reader = new FileReader(filename);
		jsonObject = jsonParser.parse(reader);
		return jsonObject.toString();
	}

	public static boolean isValidJson(String jsonInString) {

		try {

			gson.fromJson(jsonInString, Object.class);

		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public static String getJsonValue(String jsonInString, String jsonPath) throws ParseException {

		isValidJson(jsonInString);
		JSONParser jsonParser = new JSONParser();
		Object jsonObject = jsonParser.parse(jsonInString);
		return JsonPath.read(jsonObject, jsonPath).toString();

	}

	public static String readJsonFromURL(URL url) throws IOException {
		try (InputStream input = url.openStream()) {
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader reader = new BufferedReader(isr);
			StringBuilder json = new StringBuilder();
			int c;
			while ((c = reader.read()) != -1) {
				json.append((char) c);
			}
			return json.toString();
		}
	}

}
