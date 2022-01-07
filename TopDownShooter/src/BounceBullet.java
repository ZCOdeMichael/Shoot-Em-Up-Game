import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BounceBullet extends GameObject{
	
	private Handler handler;
	private final int SPEED = 2;
	
	private Animations fire;
	
	public BounceBullet(int x, int y, ID id, Handler handler, int mx , int my) {
		super(x, y, id);
		this.handler = handler;
		
		 int dist = (int) Math.hypot((mx - x), (my - y));
		 dist/=SPEED; 
		 velX = (mx);
		 velY = (my);
		 
		 fire = new Animations(100, Assets.fireBall_anime);
	}

	@Override
	public void tick() {
		fire.tick();
		x += velX;
		y += velY;
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			if (handler.object.size() == i)
				break;
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Block){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
				}
			}
			if(tempObject.getId() == ID.OpenDoor){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
				}
			}
			
		}

		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(fire.getCurrentFrame(), x, y, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
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
