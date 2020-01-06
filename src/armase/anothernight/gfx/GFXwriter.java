package armase.anothernight.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		
		resetClassVariables();
	}
	
	public static BufferedImage createBufferedImageFromMsg(String msg, int width, int height) {
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		ArrayList<BufferedImage> msgImages = translateMsgToGfx(msg);
		
		for(int i = 0; i < msgImages.size(); i++) {
			
			joinBufferedImage(result, msgImages.get(i));
//			if(x > result.getWidth()){
//				x = 0;
//				y += bi.getHeight();
//			}
		}
		return result;
	}
	
	public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {

        //do some calculate first
        int wid = img1.getWidth() + img2.getWidth();
        int height = Math.max(img1.getHeight(), img2.getHeight());
        // create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, wid, height);
        //draw image
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth(), 0);
        g2.dispose();
        return newImage;
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
	
	private static void resetClassVariables() {
		letterWidth = 16;
		letterHeight = 32;
		scale = 1;
	}
}
