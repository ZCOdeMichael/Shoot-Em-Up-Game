import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SpawnerBlock extends GameObject{

	private final int SPAWN_DELAY = 500;
	private int spawnTimer;
	private Handler handler;
	private final int DETECT_RADIUS = 10;
	private int enemyCount;
	
	public SpawnerBlock(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		spawnTimer = 0;
		enemyCount = 0;
	}
	
	@Override
	public void tick() {
		GameObject temp = handler.object.get(0);
		
		if(temp.getBounds().intersects(getBoundsAux()) && spawnTimer == SPAWN_DELAY) {
			handler.addObject(new Enemy(x, y, ID.Enemy, handler, 100));
			spawnTimer = 0;
			enemyCount++;
		}
		else if(temp.getBounds().intersects(getBoundsAux()))
			spawnTimer++;
		
		if(enemyCount == 5)
			handler.removeObject(this);
		
		
	}

	@Override
	public void render(Graphics g) {
		//Graphics2D g2d = (Graphics2D) g;
		//g2d.setColor(Color.green);
		//g2d.draw(getBoundsAux());
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
	
	private Rectangle getBoundsAux() {
		return new Rectangle(x - 32*DETECT_RADIUS, y - 32*DETECT_RADIUS, 32*DETECT_RADIUS*2+32, 32*DETECT_RADIUS*2+32);

	}

}
