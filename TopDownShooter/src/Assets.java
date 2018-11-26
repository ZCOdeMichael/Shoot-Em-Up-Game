import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage block, floor, player, healthPack, ammoPack, openDoor, breakable, powerUp;
	public static BufferedImage level, blocks_sheet, player_sheet, floorV2, fireBall, healthStuff, ammoStuff, ghost_sheet;
	
	public static BufferedImage[] player_down;
	public static BufferedImage[] player_up;
	public static BufferedImage[] player_left;
	public static BufferedImage[] player_right;
	
	public static BufferedImage[] enemy_anime;
	
	public static BufferedImage[] fireBall_anime;
	
	private static BufferedImageLoader loader = null;
	private static SpriteSheet sheet = null;
	private static SpriteSheet playerTex = null;
	private static SpriteSheet floor2 = null;
	private static SpriteSheet fire = null;
	private static SpriteSheet ammo = null;
	private static SpriteSheet health = null;
	private static SpriteSheet ghost = null;
	
	public static void init(){
		
		loader = new BufferedImageLoader();
		level = loader.loadImage("/Level1.png");
		blocks_sheet = loader.loadImage("/Tileset.png");
		player_sheet = loader.loadImage("/bard.png");
		floorV2 = loader.loadImage("/Floor.png");
		fireBall = loader.loadImage("/projectile_fireball.png");
		ammoPack = loader.loadImage("/Ammo.png");
		healthPack = loader.loadImage("/Potion.png");
		ghost_sheet = loader.loadImage("/Ghost.png");
		
		ghost = new SpriteSheet(ghost_sheet);
		ammo = new SpriteSheet(ammoPack);
		health = new SpriteSheet(healthPack);
		sheet = new SpriteSheet(blocks_sheet);
		playerTex = new SpriteSheet(player_sheet);
		floor2 = new SpriteSheet(floorV2);
		fire = new SpriteSheet(fireBall);
		
		fireBall_anime = new BufferedImage[3];
		fireBall_anime[0] = fire.grabImageOG(1, 1, 16, 16);
		fireBall_anime[1] = fire.grabImageOG(2, 1, 16, 16);
		fireBall_anime[2] = fire.grabImageOG(3, 1, 16, 16);
		
		player_down = new BufferedImage[3];
		player_down[0] = playerTex.grabImageOG(1, 1, 32, 32);
		player_down[1] = playerTex.grabImageOG(2, 1, 32, 32);
		player_down[2] = playerTex.grabImageOG(3, 1, 32, 32);
		
		player_up = new BufferedImage[3];
		player_up[0] = playerTex.grabImageOG(1, 4, 32, 32);
		player_up[1] = playerTex.grabImageOG(2, 4, 32, 32);
		player_up[2] = playerTex.grabImageOG(3, 4, 32, 32);
		
		player_left = new BufferedImage[3];
		player_left[0] = playerTex.grabImageOG(1, 2, 32, 32);
		player_left[1] = playerTex.grabImageOG(2, 2, 32, 32);
		player_left[2] = playerTex.grabImageOG(3, 2, 32, 32);
		
		player_right = new BufferedImage[3];
		player_right[0] = playerTex.grabImageOG(1, 3, 32, 32);
		player_right[1] = playerTex.grabImageOG(2, 3, 32, 32);
		player_right[2] = playerTex.grabImageOG(3, 3, 32, 32);
		
		player = playerTex.grabImageOG(2, 1, 32, 32);
		
		enemy_anime = new BufferedImage[2];
		enemy_anime[0] = ghost.grabImageOG(1, 1, 32, 32);
		enemy_anime[1] = ghost.grabImageOG(2, 1, 32, 32);
		
		floor = floor2.grabImageOG(2, 11, 32, 32);
		block = sheet.grabImage(16, 2, 32, 32);
		ammoStuff = ammo.grabImageG(1, 1, 20, 20);
		healthStuff = health.grabImageOG(1, 1, 20, 20);
		openDoor = sheet.grabImage(9, 2, 32, 32);
		breakable = sheet.grabImage(2, 2, 32, 32);
		powerUp = health.grabImageG(1, 2, 20, 20);
	}

}
