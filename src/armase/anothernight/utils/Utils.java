package armase.anothernight.utils;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import armase.anothernight.properties.Props;

public class Utils {
	private static String gameFolderPath = Props.GAME_FOLDER_PATH;
	private static File gameFolderFile = new File(gameFolderPath);

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
	
	public static boolean gameFolderExists() {
		return gameFolderFile.exists();
	}
	
	public static void createGameFolder() {
		if(!gameFolderExists())
			gameFolderFile.mkdirs();
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int generateRandomMinMax(int min, int max) {
		max++;
		Random rnd = new Random();
		return rnd.nextInt(max - min) + min;
	}
	
	public static String readConsoleInput() {
		Scanner scanner = new Scanner(System.in);
		String outString = scanner.next().toString();
//		scanner.close(); breaks stuff
		return outString;
	}
	
	public static void waitInMs(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void redirectTo(String urlString) {
		try {
			Desktop.getDesktop().browse(new URL(urlString).toURI());
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	// ### GETTERS & SETTERS
	
	public static String getDateTimeStamp() {
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return formatter.format(today);
	}

	public static String getGameFolderPath() {
		return gameFolderPath;
	}

	public static File getGameFolderFile() {
		return gameFolderFile;
	}
}
