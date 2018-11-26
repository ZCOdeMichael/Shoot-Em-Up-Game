import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Crate extends GameObject{
	private Handler handler;
	private Game game;
	private int ammoInCrate;
	
	public Crate(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.game = game;
		this.handler = handler;
		ammoInCrate = 32;
	}

	@Override
	public void tick() {
		
		GameObject tempOb = handler.object.get(0);
			
			if(getBounds().intersects(tempOb.getBounds()) && ValueHandler.ammo <= (ValueHandler.ammo - ammoInCrate)){
				ValueHandler.addAmmo(ammoInCrate);
				handler.removeObject(this);
			}else if(getBounds().intersects(tempOb.getBounds()) && ValueHandler.ammo < ValueHandler.ammoMax) {
				ammoInCrate -= (ValueHandler.ammoMax - ValueHandler.ammo);
				ValueHandler.addAmmo(ValueHandler.ammoMax - ValueHandler.ammo);
			}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.ammoStuff, x, y, null);
		if(ammoInCrate < 32) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y + 34, ammoInCrate, 4);
		}
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 32, 32);
	}

	@Override
	public Rectangle getRightBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getLeftBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getTopBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBottomBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
