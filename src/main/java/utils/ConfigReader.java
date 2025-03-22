package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class to read configuration properties from an external file. This
 * allows for easy modification of URLs, API keys, and other configurations
 * without modifying the code.
 */
public class ConfigReader {

	private static Properties properties = new Properties();

	static {
		try (InputStream input = new FileInputStream("resources/config.properties")) {
			// Load the properties file
			properties.load(input);
		} catch (IOException ex) {
			System.out.println("Error reading config.properties file");
			ex.printStackTrace();
		}
	}

	/**
	 * Retrieves the value for a given key from the properties file.
	 * 
	 * @param key The key whose value is to be fetched.
	 * @return The value associated with the given key.
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

}
