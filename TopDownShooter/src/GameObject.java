import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x, y;
	protected float velX = 0, velY = 0;
	protected ID id;
	
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	
	public abstract void tick();//Defines the function of the "Object"
	public abstract void render(Graphics g);//Draws the image of the "Object"
	public abstract Rectangle getBounds();//Returns the bounding box of the "Object"
	
	//Returns the individual sides of the bounding box
	public Rectangle getLeftBounds(){
		return new Rectangle(x, y, 1, 32);
	}
	public Rectangle getRightBounds(){
		return new Rectangle(x + 31, y, 1, 32);
	}
	public Rectangle getTopBounds(){
		return new Rectangle(x, y, 32, 1);
	}
	public Rectangle getBottomBounds(){
		return new Rectangle(x, y + 31, 32, 1);
	}
	
	//Identifies the object by assigning its ID
	public ID getId() {
		return id;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
	//A bunch of get and set methods
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	
	
}
