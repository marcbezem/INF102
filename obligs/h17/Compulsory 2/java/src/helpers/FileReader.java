package helpers;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.io.IOUtils;

/**
 * @author knutandersstokke
 * This file reader simply reads content from files inside the resource folder
 * If the reader doesn't work for your setup, feel free to read using another approach.
 */
public class FileReader {

	public String getFile(String filename) {
		if (filename.charAt(0) != '/')
			filename = getAbsolutePathToResFolder() + filename;

		String result = "";
		try {
			InputStream inStream = new FileInputStream(filename);
			result = IOUtils.toString(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}
	
	public String getAbsolutePathToResFolder() {
		String result = "";
		try {
			result = URLDecoder.decode(getClass().getResource("..").getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
