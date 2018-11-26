import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FireSplitPowerUp extends GameObject{

	private Handler handler;
	
	public FireSplitPowerUp(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick() {
		GameObject temp = handler.object.get(0);
		if(temp.getBounds().intersects(getBounds())) {
			ValueHandler.changeFireSplit(true);
			handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.powerUp, x, y, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
