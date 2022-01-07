import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject{

	private Handler handler;
	private final int SPEED = 2;
	private int choose;
	int hp = 100;
	Random r = new Random();
	private final int DETECT_RADIUS = 10;
	private final int HIT_COOL_DOWN_NUM = 500;
	private int hitCoolDown;

	private Animations enemy_anime;
	
	Enemy(int x, int y, ID id, Handler handler, int hp){
		super(x, y, id);
		this.handler = handler;
		hitCoolDown = HIT_COOL_DOWN_NUM;
		this.hp = hp;
		enemy_anime = new Animations(200, Assets.enemy_anime);
	}

	@Override
	public void tick() {
		enemy_anime.tick();
		collision();
		
		x += velX;
		y += velY;
		
		if(hitCoolDown < HIT_COOL_DOWN_NUM)
			hitCoolDown++;
		
		
		GameObject tempOb0 = handler.object.get(0);
		if(tempOb0.getBounds().intersects(getAUXBounds())){
			double diffX = tempOb0.getX() - x;
			double diffY = tempOb0.getY() - y;
			double disX = (double) (diffX) / Math.sqrt((Math.pow(diffX, 2) + Math.pow(diffY, 2)));
			double disY = (double) (diffY) / Math.sqrt((Math.pow(diffX, 2) + Math.pow(diffY, 2)));
			
			velX = (int) (SPEED * disX);
			velY = (int) (SPEED * disY);
			
		}else{
			velX = 0;
			velY = 0;
		}
		
		 
		 choose = r.nextInt(20);
		 
		 if(choose == 0){
			 velX = (r.nextInt(2*5+1) - 5);
			 velY = (r.nextInt(2*5+1) - 5);
		 }
		 

		 for(int i = 0; i < handler.object.size(); i++)
		 {
			 GameObject tempObject = handler.object.get(i);
			 
			 if(tempObject.getId() == ID.Bullet){
				 if(getBounds().intersects(tempObject.getBounds())){
					 hp -= 50;
					 handler.removeObject(tempObject);
				 }
			 }
			 if(tempObject.getId() == ID.Boolit){
				 if(getBounds().intersects(tempObject.getBounds())){
					 hp -= 50;
					 handler.removeObject(tempObject);
				 }
					 
			 }
			
		 }
		 
		 if(hp <= 0) {
			 handler.removeObject(this);
			 ValueHandler.addScore(1);
		 }
	}
	
	private void collision() {
		  for(int i = 0; i < handler.object.size(); i++) {
			  if(handler.object.size() == i)
					break;
			  
		   GameObject tempObject = handler.object.get(i);
		   
		   if(tempObject.getId() == ID.Player) {
		    if(!place_free((int) (x + velX), y, getBounds(), tempObject.getBounds())) {
		    	if(hitCoolDown == HIT_COOL_DOWN_NUM) {
		    	ValueHandler.subHealth(1);
		    		hitCoolDown = 0;
		    	}
		    	velX= 0;
		    }
		    if(tempObject.getId() == ID.OpenDoor) {
				if(!place_free((int) (x + velX), y, getBounds(), tempObject.getBounds())) {
					velX= 0;
				}
		    
				if(!place_free(x, (int) (y + velY), getBounds(), tempObject.getBounds())) {
					velY= 0;
				}
		   }
		    if(tempObject.getId() == ID.BreakAble) {
				if(!place_free((int) (x + velX), y, getBounds(), tempObject.getBounds())) {
					velX= 0;
				}
		    
				if(!place_free(x, (int) (y + velY), getBounds(), tempObject.getBounds())) {
					velY= 0;
				}
		   }
		    if(!place_free(x, (int) (y + velY), getBounds(), tempObject.getBounds())) {
		    	if(hitCoolDown == HIT_COOL_DOWN_NUM) {
			    	ValueHandler.subHealth(1);
			    	hitCoolDown = 0;
			    }
		    	velY= 0;
		    }
		   }
		   
		   if(tempObject.getId() == ID.Block) {
			    if(!place_free((int) (x + velX), y, getBounds(), tempObject.getBounds())) {
			     velX= 0;
			    }
			    
			    if(!place_free(x, (int) (y + velY), getBounds(), tempObject.getBounds())) {
			     velY= 0;
			    }
			}
		   
		  }
	}
	
	public boolean place_free(int x, int y, Rectangle myRect, Rectangle otherRect) {
		  myRect.x = x;
		  myRect.y = y;
		  if(myRect.intersects(otherRect)) {
		   return false;
		  }
		  return true;
	}
	

	@Override
	public void render(Graphics g) {
		g.drawImage(enemy_anime.getCurrentFrame(), x, y, null);
		
		//Graphics2D g2d = (Graphics2D) g;
		//g2d.setColor(Color.cyan);
		//g2d.draw(getAUXBounds());
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	private Rectangle getAUXBounds(){
		return new Rectangle(x - 32*DETECT_RADIUS, y - 32*DETECT_RADIUS, 32*DETECT_RADIUS*2+32, 32*DETECT_RADIUS*2+32);
	}
	
}
