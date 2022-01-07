import java.awt.Graphics;
import java.awt.Rectangle;

public class BreakableBlock extends GameObject{

	private final int health = 100;
	private Handler handler;
	private int healthBlock;
	
	public BreakableBlock(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		healthBlock = health;
	}

	@Override
	public void tick() {
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.size() == i)
				break;
			
			GameObject tempObject = handler.object.get(i);
			 
			if(tempObject.getId() == ID.Bullet) {
				if(tempObject.getBounds().intersects(getBounds())) {
					healthBlock -= 50;
					handler.removeObject(tempObject);
				}
			}
			
		}
		if(healthBlock <= 0)
			handler.removeObject(this);
		
		
	}
		   

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.breakable, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
