import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public abstract class UIObject extends GameObject implements Drawable {

	Vector2f position;
	Vector2f dimension;
	
	public UIObject(Vector2f position, Vector2f dimension){
		this.position = position;
		this.dimension = dimension;
	}
	
	public UIObject(){
    	this.position = new Vector2f();
    	this.dimension = new Vector2f(1f, 1f);
	}
	
	public void update(){
		
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}