/**
 * 
 * Author:Alper Þahýstan
 * 
 */
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class GameObject implements Drawable{
	private Vector2f position;
	private Vector2f dimentions;
	Shape shape;
	private boolean toBeRemoved = false;
	
	//Constructors
    public GameObject(Vector2f pos, Vector2f dim)
    {
    	this.position = pos;
    	this.dimentions = dim;
    }
    
    public GameObject(float x, float y, float l, float h)
    {
    	this.position = new Vector2f(x, y);
    	this.dimentions = new Vector2f(l, h);
    }
    
    public GameObject(float x, float y)
    {
    	this.position = new Vector2f(x, y);
    	this.dimentions = new Vector2f(1f, 1f);
    }
    
    
	public GameObject(Vector2f pos)
    {
    	this.position = pos;
    	this.dimentions = new Vector2f(1f,1f);
    }
    
    public GameObject()
    {
    	this.position = new Vector2f();
    	this.dimentions = new Vector2f(1f, 1f);
    }
    
    //Getters & setters
    public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f pos) {
		this.position = pos;
		//System.out.println(position.getY());
	}
	public Vector2f getDimentions() {
		return dimentions;
	}
	public void setDimentions(Vector2f dimentions) {
		this.dimentions = dimentions;
	}
	
    public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public boolean isToBeRemoved() {
		return toBeRemoved;
	}

	public void setToBeRemoved(boolean toBeRemoved) {
		this.toBeRemoved = toBeRemoved;
	}

	abstract void update();
    
	boolean collides(GameObject other) 
	{
		return shape.intersects(other.getShape());
		
	}
}
