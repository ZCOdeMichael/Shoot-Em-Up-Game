import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject{
	
	public Block(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id);
		

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.block, x, y, null);
		
		//Graphics2D g2d = (Graphics2D) g;
		//g2d.setColor(Color.GREEN);
		//g2d.draw(getBounds());
	}

	
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 32, 32);
	}

}
