import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthPack extends GameObject{

	private Handler handler;
	private int healthInPack;
	
	public HealthPack(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		healthInPack = 32;
		this.handler = handler;
		
	}

	@Override
	public void tick() {
		GameObject tempOb = handler.object.get(0);
	
		
		if(getBounds().intersects(tempOb.getBounds()) && ValueHandler.health <= (ValueHandler.health - healthInPack)){
			ValueHandler.addHealth 	(healthInPack);
			handler.removeObject(this);
		}else if(getBounds().intersects(tempOb.getBounds()) && ValueHandler.health < ValueHandler.ammoMax) {
			healthInPack -= (ValueHandler.healthMax - ValueHandler.health);
			ValueHandler.addHealth(ValueHandler.healthMax - ValueHandler.health);
		}
			
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.healthStuff, x, y, null);
		if(healthInPack < 32) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y + 34, healthInPack, 4);
		}
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 32, 32);
	}

}
