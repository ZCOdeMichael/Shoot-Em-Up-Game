import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	
	ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	
	public void addObject(GameObject tempObject){
		object.add(tempObject);
	}
	
	public void removeObject(GameObject tempObject){
		object.remove(tempObject);
	}
	
	public void addObjectSpec(int i, GameObject tempObject){
		object.add(i, tempObject);
	}
	
}
