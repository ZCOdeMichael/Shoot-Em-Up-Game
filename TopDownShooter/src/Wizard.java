import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Wizard extends GameObject{

	Handler handler;
	private KeyInputList keyList;
	private final int COOL_DOWN_NUM = 500;
	private final int DASH_RANGE = 100;
	private int coolDown;
	private boolean dashUsed;
	private final int SPEED = 1;
	private final int HIT_COOL_DOWN_NUM = 100;
	private int hitCoolDown;
	
	private int right;
	private int left;
	private int top;
	private int bottom;
	
	private Animations animeDown, animeUp, animeLeft, animeRight;
	
	public Wizard(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.keyList = new KeyInputList();
		coolDown = COOL_DOWN_NUM ;
		hitCoolDown = HIT_COOL_DOWN_NUM;
		dashUsed = false;
		
		animeDown = new Animations(100, Assets.player_down);
		animeUp = new Animations(100, Assets.player_up);
		animeLeft = new Animations(100, Assets.player_left);
		animeRight = new Animations(100, Assets.player_right);
		
	}


	
	public void tick() {
		animeDown.tick();
		animeUp.tick();
		animeLeft.tick();
		animeRight.tick();
		
		if(ValueHandler.dash) {
			dashAbility();
		}
		collision();
		
		if(hitCoolDown < HIT_COOL_DOWN_NUM)
			hitCoolDown++;
			
		
		x += velX;
		y += velY;

		
		/// Movement ///
		if (keyList.isUp()){ 
			velY = -SPEED;
		}
		else if(!keyList.isDown()){ 
			velY = 0;
		}
		
		if (keyList.isDown()){ 
			velY = SPEED;
		}
		else if(!keyList.isUp()) {
			velY = 0;
		}	
		
		if (keyList.isRight()){ 
			velX = SPEED;
		}
		else if(!keyList.isLeft()) {
			velX = 0;
		}
		if (keyList.isLeft()){
			velX = -SPEED;
		}
		else if(!keyList.isRight()) {
			velX = 0;
		}
		
		if(ValueHandler.health <= 0 || ValueHandler.score == 67)
			handler.object.remove(0);
	}
	
	private void dashAbility(){
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Block){
				if(tempObject.getBounds().intersects(getRightBounds())){
					right = ((tempObject.getX() - 10)  - (x+32));
				}
				if(tempObject.getBounds().intersects(getLeftBounds())){
					left = (x - (tempObject.getX() + 10)) - 32;
				}
				if(tempObject.getBounds().intersects(getTopBounds())){
					top = (y - (tempObject.getY() + 10)) - 32;
				}
				if(tempObject.getBounds().intersects(getBottomBounds())){
					bottom = ((tempObject.getY() - 10) - (y+32));
				}	
			}
			if(tempObject.getId() == ID.BreakAble){
				if(tempObject.getBounds().intersects(getRightBounds())){
					right = ((tempObject.getX() - 10)  - (x+32));
				}
				if(tempObject.getBounds().intersects(getLeftBounds())){
					left = (x - (tempObject.getX() + 10)) - 32;
				}
				if(tempObject.getBounds().intersects(getTopBounds())){
					top = (y - (tempObject.getY() + 10)) - 32;
				}
				if(tempObject.getBounds().intersects(getBottomBounds())){
					bottom = ((tempObject.getY() - 10) - (y+32));
				}	
			}
			if(tempObject.getId() == ID.OpenDoor){
				if(tempObject.getBounds().intersects(getRightBounds())){
					right = ((tempObject.getX() - 10)  - (x+32));
				}
				if(tempObject.getBounds().intersects(getLeftBounds())){
					left = (x - (tempObject.getX() + 10)) - 32;
				}
				if(tempObject.getBounds().intersects(getTopBounds())){
					top = (y - (tempObject.getY() + 10)) - 32;
				}
				if(tempObject.getBounds().intersects(getBottomBounds())){
					bottom = ((tempObject.getY() - 10) - (y+32));
				}	
			}
			
			if(coolDown == COOL_DOWN_NUM  && dashUsed == false){
				
				
				if(keyList.isShift() && keyList.isUp()){
					y -= top;
					dashUsed = true;
					System.out.println("Dash Used Type: Up");
				}
				if(keyList.isShift() && keyList.isDown()){
					y += bottom;
					dashUsed = true;
					System.out.println("Dash Used Type: Down");
				}
				if(keyList.isShift() && keyList.isLeft()){
					x -= left;
					dashUsed = true;
					System.out.println("Dash Used Type: Left");
				}
				if(keyList.isShift() && keyList.isRight()){
					x += right;
					dashUsed = true;
					System.out.println("Dash Used Type: Right");
				}
				
				
			}
		}
		if(right < DASH_RANGE)
			right ++;
		if(left < DASH_RANGE)
			left ++;
		if(top < DASH_RANGE)
			top ++;
		if(bottom < DASH_RANGE)
			bottom ++;
	
		if(dashUsed == true)
			coolDown--;
				//System.out.println(coolDown--);
		
		if(coolDown <= 0){
			dashUsed = false;
			coolDown = COOL_DOWN_NUM ;
			System.out.println("Dash Cooldown Reset");
		}
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
		   
			if(tempObject.getId() == ID.Block) {
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
		if(tempObject.getId() == ID.OpenDoor) {
				if(!place_free((int) (x + velX), y, getBounds(), tempObject.getBounds())) {
					velX= 0;
				}
		    
				if(!place_free(x, (int) (y + velY), getBounds(), tempObject.getBounds())) {
					velY= 0;
				}
		   }
			if(tempObject.getId() == ID.Enemy) {
				if(!place_free((int) (x + velX), y, getBounds(), tempObject.getBounds())) {
					velX= 0;
					if(hitCoolDown == HIT_COOL_DOWN_NUM) {
						ValueHandler.subHealth(1);
						hitCoolDown = 0;
					}
				}
			    
				if(!place_free(x, (int) (y + velY), getBounds(), tempObject.getBounds())) {
					velY= 0;
					if(hitCoolDown == HIT_COOL_DOWN_NUM) {
						ValueHandler.subHealth(1);
						hitCoolDown = 0;
					}
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
	
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimeationFrame(), x, y, null);
		
		//Graphics2D g2d = (Graphics2D) g;
		//g2d.setColor(Color.orange);
		//g2d.draw(getRightBounds());
		//g2d.draw(getLeftBounds());
		//g2d.draw(getTopBounds());
		//g2d.draw(getBottomBounds());
		
	}
	
	private BufferedImage getCurrentAnimeationFrame(){
		if(velX < 0){
			return animeLeft.getCurrentFrame();
		}
		else if(velX > 0){
			return animeRight.getCurrentFrame();
		}
		else if(velY < 0){
			return animeUp.getCurrentFrame();
		}
		else if(velY > 0){
			return animeDown.getCurrentFrame();
		}
		else
			return Assets.player;
	}
	

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 32, 32);
	}

	@Override
	public Rectangle getRightBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x + 32, y, right, 32);
	}

	@Override
	public Rectangle getLeftBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x - left, y, left, 32);
	}

	@Override
	public Rectangle getTopBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y - top, 32, top);
	}

	@Override
	public Rectangle getBottomBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y + 32, 32, bottom);
	}

}
