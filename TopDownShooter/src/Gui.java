/* WIP
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Gui extends GameObject{
	
	Handler handler;
	private int coolDown;
	
	public Gui(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		
		this.handler = handler;
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				x = tempObject.getX();
				y = tempObject.getY();
			}
			
				
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x - 450, y - 220, 200, 32);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
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

*/
