import java.util.ArrayList;

public class KeyInputList {
	
	final int numberOfInputs = 10;
	static ArrayList<Boolean> keyController = new ArrayList<>();
	
	
	public KeyInputList()
	{
		for(int i = 0; i <= numberOfInputs; i++)
			keyController.add(false);
	}
	//////////////////////////////////////////////////////////////////////////
	public boolean isUp() {
		return keyController.get(0);
	}
	public static void setUp(boolean up) {
		keyController.set(0, up);
	}
	
	//////////////////////////////////////////////////////////////////////////
	public boolean isDown() {
		return keyController.get(1);
	}
	public static void setDown(boolean down) {
		keyController.set(1, down);
	}
	
	//////////////////////////////////////////////////////////////////////////
	public boolean isRight() {
		return keyController.get(2);
	}
	public static void setRight(boolean right) {
		keyController.set(2, right);
	}
	
	//////////////////////////////////////////////////////////////////////////
	public boolean isLeft() {
		return keyController.get(3);
	}
	public static void setLeft(boolean left) {
		keyController.set(3, left);
	}
	
	//////////////////////////////////////////////////////////////////////////
	public static void setShift(boolean shift){
		keyController.set(4, shift);
	}
	public boolean isShift() {
		return keyController.get(4);
	}
	
	//////////////////////////////////////////////////////////////////////////

	
}
