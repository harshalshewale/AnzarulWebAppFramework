package in.harshalshewale.util;

import java.io.InputStream;
import java.util.Properties;

public class FileUtil {

	public static InputStream readFileFromResources(String filePath) {

		InputStream inputstream = null;

		try {

			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			inputstream = classloader.getResourceAsStream(filePath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return inputstream;

	}

	public static String readLocators(String fileName, String locatorName) {

		Properties properties = new Properties();
		String value = null;

		try {

			properties.load(readFileFromResources("data/locators/" + fileName + ".properties"));
			value = properties.getProperty(locatorName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;

	}

}
