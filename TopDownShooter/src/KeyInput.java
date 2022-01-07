import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	Handler handler;
	private KeyInputList keyList;
	
	public KeyInput(Handler handler){
		this.handler = handler;
		keyList = new KeyInputList();
	}
	
	//Add new key inputs here
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			if (handler.object.size() == i)
				break;
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_W) 
					KeyInputList.setUp(true);
				if(key == KeyEvent.VK_S) 
					KeyInputList.setDown(true);
				if(key == KeyEvent.VK_A) 
					KeyInputList.setLeft(true);
				if(key == KeyEvent.VK_D) 
					KeyInputList.setRight(true);
				if(key == KeyEvent.VK_SHIFT)
					KeyInputList.setShift(true);
			}
		}
	}

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			if (handler.object.size() == i)
				break;
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_W) 
					KeyInputList.setUp(false);
				if(key == KeyEvent.VK_S) 
					KeyInputList.setDown(false);
				if(key == KeyEvent.VK_A) 
					KeyInputList.setLeft(false);
				if(key == KeyEvent.VK_D) 
					KeyInputList.setRight(false);
				if(key == KeyEvent.VK_SHIFT)
					KeyInputList.setShift(false);

			}
		}
	}
}
