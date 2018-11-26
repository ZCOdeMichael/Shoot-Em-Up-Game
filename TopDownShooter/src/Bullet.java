import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{
	
	private Handler handler;
	private final int SPEED = 5;
	private int TimeDie;
	private int mx;
	private int my;
	private int playerX;
	private int playerY;

	
	private Animations animeFireBall;
	
	public Bullet(int x, int y, ID id, Handler handler, int mx , int my) {
		super(x, y, id);
		this.handler = handler;
		TimeDie = 600000;
		
		this.mx = mx;
		this.my = my;
		velX = (mx - x) / 30;
		velY = (my - y) / 30;
		
		animeFireBall = new Animations(10, Assets.fireBall_anime);

	}

	@Override
	public void tick() {
		animeFireBall.tick();
		
		GameObject tempOb0 = handler.object.get(0);
		x += velX;
		y += velY;
		/*
		double diffX = mx - tempOb0.getX();
		double diffY = my - tempOb0.getY();
		
		
		
		if(Math.sqrt((Math.pow(diffX, 2) + Math.pow(diffY, 2))) == 0){}
		else{
			double disX = (double) (diffX) / Math.sqrt((double)(Math.pow(diffX, 2) + Math.pow(diffY, 2)));
			double disY = (double) (diffY) / Math.sqrt((double)(Math.pow(diffX, 2) + Math.pow(diffY, 2)));
			
			velX = (int) (SPEED * disX);
			velY = (int) (SPEED * disY);
			
			x += velX;
			y += velY;
		}
		*/
		
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			
			GameObject tempOb = handler.object.get(i);
			
			if(tempOb.getId() == ID.OpenDoor) {
				if(tempOb.getBounds().intersects(getBounds())) {
					handler.removeObject(this);
				}
			}
			
			if(!(ValueHandler.fireSplit)) {
			if(tempOb.getId() == ID.Block) {
				if(tempOb.getBounds().intersects(getBounds())) {
					handler.removeObject(this);
				}
			}
			}
			
			if(ValueHandler.fireSplit) {
			if(tempOb.getId() == ID.Block){

				if(getBounds().intersects(tempOb.getLeftBounds())){
					handler.addObject(new BounceBullet(x - 10, y, ID.Boolit, handler, -1, -1));
					handler.addObject(new BounceBullet(x - 10, y, ID.Boolit, handler, -1, 1));
					handler.removeObject(this);
				}
				
				else if(getBounds().intersects(tempOb.getRightBounds())){
					handler.addObject(new BounceBullet(x + 10, y, ID.Boolit, handler, 1, 1));
					handler.addObject(new BounceBullet(x + 10, y, ID.Boolit, handler, 1, -1));
					handler.removeObject(this);
				}
				
				else if(getBounds().intersects(tempOb.getTopBounds())){
					handler.addObject(new BounceBullet(x, y - 10, ID.Boolit, handler, -1, -1));
					handler.addObject(new BounceBullet(x, y - 10, ID.Boolit, handler, 1, -1));
					handler.removeObject(this);
				}
				
				else if(getBounds().intersects(tempOb.getBottomBounds())){
					handler.addObject(new BounceBullet(x, y + 10, ID.Boolit, handler, -1, 1));
					handler.addObject(new BounceBullet(x, y + 10, ID.Boolit, handler, 1, 1));
					handler.removeObject(this);
				}

				if(getBounds().intersects(tempOb.getBottomBounds())){
					handler.removeObject(this);
				}
				
			}
			}
			if(TimeDie <= 0){
				handler.removeObject(this);

			}
			TimeDie--;
			
			
		}
			
	}

		
		


	@Override
	public void render(Graphics g) {
		g.drawImage(animeFireBall.getCurrentFrame(), x, y, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 10, 10);
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
