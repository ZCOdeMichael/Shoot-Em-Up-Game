import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class OpenDoor extends GameObject{

	private int temp;
	private int score;
	
	public OpenDoor(int x, int y, ID id, int score) {
		super(x, y, id);
		temp = 32;
		this.score = score;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		if(ValueHandler.score >= score)
			temp = 0;
		
	}

	@Override
	public void render(Graphics g) {
		
		
		
		if(ValueHandler.score >= score) {}
		else 
			g.drawImage(Assets.openDoor, x, y, null);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		if(ValueHandler.score >= score)
			return new Rectangle(x, y, 0, 32);
		else
			return new Rectangle(x, y, 32, 32);
	}

}
