package armase.anothernight.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GFXwriter {
	private static int letterWidth = 16, letterHeight = 32;
	private static int scale = 1;

	public static void write(Graphics g, String msg, int x, int y, int msgWidth) {
		ArrayList<BufferedImage> msgImages = translateMsgToGfx(msg);
		
		letterWidth *= scale;
		letterHeight *= scale;
		int carriage = x;
		
		for(BufferedImage letter : msgImages) {
			g.drawImage(letter, carriage, y, letterWidth, letterHeight, null);
			
			carriage += letterWidth;
			
			if(x + carriage + letterWidth > x + msgWidth) {
				carriage = x;
				y += letterHeight;
			}
			// monospacing rules!
		}
	}
	
	public static void write(Graphics g, String msg, int x, int y, int msgWidth, int scaling) {
		ArrayList<BufferedImage> msgImages = translateMsgToGfx(msg);
		
		scale = scaling;
		letterWidth *= scale;
		letterHeight *= scale;
		int carriage = x;
		
		for(BufferedImage letter : msgImages) {
			g.drawImage(letter, carriage, y, letterWidth, letterHeight, null);
			
			carriage += letterWidth;
			
			if(x + carriage + letterWidth > x + msgWidth) {
				carriage = x;
				y += letterHeight;
			}
			// monospacing rules!
		}
		
		resetScaling();
	}
	
	private static ArrayList<BufferedImage> translateMsgToGfx(String msg) {
		ArrayList<BufferedImage> output = new ArrayList<>();
		char[] msgArray = msg.toLowerCase().toCharArray();
		
		for(int i = 0; i < msgArray.length; i++) {
			// TODO : catch unresolved keys
			output.add((BufferedImage) Assets.alphabetMap.get(msgArray[i]));
		}
		
		return output;
	}
	
	private static void resetScaling() {
		letterWidth = 16;
		letterHeight = 32;
		scale = 1;
	}
}
