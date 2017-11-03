import org.newdawn.slick.Renderable;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class GameObject implements Renderable{
	private Vector2f position;
	private Vector2f dimentions;
	Shape shape;
	
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
	}
	public Vector2f getDimentions() {
		return dimentions;
	}
	public void setDimentions(Vector2f dimentions) {
		this.dimentions = dimentions;
	}
	
    abstract void update();
}
