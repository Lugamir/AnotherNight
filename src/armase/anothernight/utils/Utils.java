package armase.anothernight.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// TODO : This file is an example, use it as a template or delete it
// LEAVE IT UNTIL ANOTHER FILE ARRIVES IN THIS PACKAGE (Git is whiny)

public class Utils {

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null)
				builder.append(line);
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
