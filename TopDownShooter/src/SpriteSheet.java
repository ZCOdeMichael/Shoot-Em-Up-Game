import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
		
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height){
		return image.getSubimage((col*32)-32 + (col-1), (row*32)-32 + (row-1), width, height);
	}
	
	public BufferedImage grabImageOG(int col, int row, int width, int height){
		return image.getSubimage((col*32)-32, (row*32)-32, width, height);
	}
	
	public BufferedImage grabImageG(int col, int row, int width, int height){
		return image.getSubimage((col*20)-20, (row*20)-20, width, height);
	}
}
