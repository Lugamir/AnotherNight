package armase.anothernight.scobo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import armase.anothernight.utils.Utils;

public class ScoboManager {
	private static BufferedReader scoboReader;
	private static BufferedWriter scoboWriter;
	private static String scoboPath;
	private static File scoboFile;
	
	public static void initScobo() throws IOException {
		scoboPath = Utils.getGameFolderFile() + File.separator + "scores";
		scoboFile = new File(scoboPath);
		
		if(!scoboFile.exists())
			System.out.println(scoboFile.createNewFile());
		
		scoboWriter = new BufferedWriter(
                new FileWriter(scoboPath, true)); // true for append mode  
	}
	
	public static ArrayList<String[]> loadScoreboardMatrix() throws IOException {
		scoboReader = new BufferedReader(new FileReader(scoboFile)); // stays or breaks
		ArrayList<String[]> scoboMatrix = new ArrayList<String[]>();
		String line;
		while ((line = scoboReader.readLine()) != null) {
			scoboMatrix.add(line.split(";"));
		}
		scoboReader.close(); // stays or breaks
		return scoboMatrix;
	}
	
	public static void writeToScobo(String name, String score) throws IOException {
		scoboWriter.write(Utils.getDateTimeStamp() + ";" + name + ";" + score + ";" + "\n");
		scoboWriter.flush();
//		scoboWriter.close(); breaks stuff
	}
	
	public static boolean isScoboMatrixEmpty(ArrayList<String[]> scoboMatrix) {
		boolean onlyWhitespaces = true;
		for(String[] line : scoboMatrix) {
			for(int i = 0; i < line.length; i++) {
				if(line[i].trim().length() > 0) {
					onlyWhitespaces = false;
					break;
				}
			}
			if(line != null && !onlyWhitespaces)
				return false;
		}
		return true;
	}
	
	public static boolean scoboFileExists() {
		return scoboFile.exists();
	}
}
