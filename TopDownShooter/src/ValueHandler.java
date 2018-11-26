
public class ValueHandler {
	
	public static final int ammoMax = 100;
	public static final int healthMax = 100;
	public static int health = 100;
	public static int score = 0;
	public static int ammo = 100;
	public static int kills = 0;
	public static int spawnersKills = 0;
	public static boolean dash = false;
	public static boolean fireSplit = false;

	
	public static void changeDash(boolean cd) {
		dash = cd;
	}
	
	public static void changeFireSplit(boolean cfs) {
		fireSplit = cfs;
	}
	
	public static void addSpawnerKills(int k) {
		spawnersKills += k;
	}
	 
	public static void addKills(int k) {
		kills += k;
	}
	
	public static void addHealth(int h) {
		health += h;
	}
	
	public static void subHealth(int h) {
		health -= h;
	}
	
	public static void addScore(int s) {
		score += s;
	}
	
	public static void addAmmo(int a) {
		ammo += a;
	}
	
	public static void subAmmo(int a) {
		ammo -= a;
	}
	
}
