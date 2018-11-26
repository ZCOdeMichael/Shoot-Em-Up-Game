import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{
	
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	private SpriteSheet ss;
	
	public static int hp = 100;
	
	private void init() {
		handler = new Handler(); System.out.println("Handler initialized");
		camera = new Camera(0, 0); System.out.println("Camera initialized");
		
		
		this.addKeyListener(new KeyInput(handler)); System.out.println("Key listener initialized");
		
		
		
		this.addMouseListener(new MouseInput(handler, camera, this, ss)); System.out.println("Mouse input initialized");
		
		loadLevel(Assets.level); System.out.println("level loaded");
	}
	
	public Game () {
		new Window(1000, 563, "Shooter", this);
		Assets.init();
		
		start();
		init();
		//handler.addObject(new Gui(0, 0, ID.GUI, handler));	WIP
		
	}

	//Starts the thread
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//Stops the thread
	public void stop (){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Runs the game loop that makes sure that the game updates 60 times per second
	//I have no idea how it works so don't bother asking!
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 100000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning) {
			long now = System.nanoTime();
		    delta += (now - lastTime) / ns;
		    lastTime = now;
		    	while(delta >= 1) {
		    		tick();
		    		//updates++;
		    		delta--;
		    	}
		   render();
		   frames++;
		   
		   if(System.currentTimeMillis() - timer > 1000) {
		    timer += 1000;
		    frames = 0;
		    //updates = 0;
		   }
		}
		stop();
	}
	
	/*
	 * This method is called in the run() method which calls this method 60 times per second. 
	 * The tick method calls all the other tick methods in classes that extend GameObject.
	 * These classes that extend GameObject are stored in an array in the handler class.
	*/
	public void tick(){
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() ==ID.Player){
				camera.tick(handler.object.get(0));
			}
		}
		
	}
	
	/*
	 * This method is called in the run() method which calls this method 60 times per second. 
	 * The render method calls all the other render methods in classes that extend GameObject.
	 * These classes that extend GameObject are stored in an array in the handler class.
	 * 
	 * This also renders the floor, renders the hud, and creates the camera
	*/
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		//////////////////////////////////
		
		
		
		g2d.translate(-camera.getX(),  -camera.getY());
		
		for(int xx = -640; xx < 3840; xx += 32){
			for(int yy = -640; yy < 3840; yy += 32){
				g.drawImage(Assets.floor, xx, yy, null);
			}
		}
		
		handler.render(g);
		

		g2d.translate(camera.getX(),  camera.getY());
		
		
		//////////////////////////////////
		
		g.setColor(Color.green);
		g.fillRect(5, 5, ValueHandler.health*2, 32);
		g.setColor(Color.gray);
		g.fillRect(5, 38, ValueHandler.ammo*2, 32);
		g.setColor(Color.CYAN);
		g.drawString("Score: " + ValueHandler.score, 5, 80);
		
		if(ValueHandler.health <= 0) {
			g.setColor(Color.red);
			g.drawString("HA! U DED", 100, 100);
		}
		if(ValueHandler.score == 67) {
			g.setColor(Color.GREEN);
			g.drawString("YEAH! YOU WON!", 100, 100);
		}
		
		g.dispose();
		bs.show();
		
	}
	
	/*
	 * This method loads the level!
	 * It goes through a BufferedImage pixel by pixel and replaces the pixels with a GameObject that corresponds with a color
	 * For example the wizard class or the player corresponds to the color blue
	 * This is essentially a level maker
	 */
	private void loadLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		
		for(int xx = 0; xx< w; xx++){
			for(int yy = 0; yy < h; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(blue == 255)
					handler.addObjectSpec(0, new Wizard(xx*32, yy*32, ID.Player, handler));
				if(red == 255 && green == 255 && blue == 0)
					handler.addObject(new Crate(xx*32, yy*32, ID.Crate, handler, this));
				if(red == 255 && green == 0 && blue == 0)
					handler.addObject(new Block(xx*32, yy*32, ID.Block, ss));
				if(green == 255 && red == 0 && blue == 0)
					handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler, 100));
				if(green == 200 && red == 0 && blue == 0)
					handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler, 1000));
				
				if(red == 200 && green == 200 && blue == 200) 
					handler.addObject(new SpawnerBlock(xx*32, yy*32, ID.Spawner, handler));
				if(red == 150 && green == 150 && blue == 150)
					handler.addObject(new OpenDoor(xx*32, yy*32, ID.OpenDoor, 20));
				if(red == 120 && green == 120 && blue == 120)
					handler.addObject(new OpenDoor(xx*32, yy*32, ID.OpenDoor, 40));
				if(red == 140 && green == 140 && blue == 140)
					handler.addObject(new OpenDoor(xx*32, yy*32, ID.OpenDoor, 60));
				if(red == 100 && green == 100 && green == 100)
					handler.addObject(new HealthPack(xx*32, yy*32, ID.Healing, handler));
				if(red == 90 && green == 90 && green == 90)
					handler.addObject(new BreakableBlock(xx*32, yy*32, ID.BreakAble, handler));
				if(red == 80 && green == 80 && green == 80)
					handler.addObject(new DashPowerUp(xx*32, yy*32, ID.Dash, handler));
				if(red == 70 && green == 70 && green == 70)
					handler.addObject(new FireSplitPowerUp(xx*32, yy*32, ID.BannaSplit, handler));
			}
				
		}
		
	}
	
	/*
	 * This method just starts the game!
	 * It just makes an instance of this class to start the game
	 */
	public static void main(String args[]){
		new Game();
	}
}
