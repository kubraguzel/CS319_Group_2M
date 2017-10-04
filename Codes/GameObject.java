/**
 * 
 */

/**
 * @author Alper Þahýstan
 *
 */


public abstract class GameObject 
{
	//Properities
    private Vector2 pos;
    private Vector2 dimentions;   
    private Vector2 velocity;
    
	//Constructors
    public GameObject(Vector2 pos, Vector2 dim, Vector2 velocity)
    {
    	this.pos = pos;
    	this.dimentions = dim;
    	this.velocity= velocity;
    }
    public GameObject(Vector2 pos, Vector2 dim)
    {
    	this.pos = pos;
    	this.dimentions = dim;
    	this.velocity= new Vector2(0,0);
    }
    
    public GameObject(float x, float y, float l, float h, Vector2 velocity)
    {
    	pos = new Vector2(x, y);
    	dimentions = new Vector2(l, h);
    	this.velocity= velocity;
    }
    public GameObject(float x, float y, float l, float h)
    {
    	pos = new Vector2(x, y);
    	dimentions = new Vector2(l, h);
    	this.velocity= new Vector2(0,0);
    }
    
    public GameObject(float x, float y, Vector2 velocity)
    {
    	this.pos = new Vector2(x, y);
    	this.dimentions = new Vector2(1f, 1f);
    	this.velocity= velocity;
    }
    
    public GameObject(float x, float y)
    {
    	this.pos = new Vector2(x, y);
    	this.dimentions = new Vector2(1f, 1f);
    	this.velocity = new Vector2(0,0);
    }
    
	public GameObject(Vector2 pos)
    {
    	this.pos = pos;
    	this.dimentions = new Vector2(1f,1f);
    	this.velocity= new Vector2(0,0);
    }
    
    public GameObject()
    {
    	this.pos = new Vector2();
    	this.dimentions = new Vector2(1f, 1f);
    	this.velocity= new Vector2(0,0);
    }
    
    //Getters & setters
    public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	public Vector2 getDimentions() {
		return dimentions;
	}
	public void setDimentions(Vector2 dimentions) {
		this.dimentions = dimentions;
	}
	public Vector2 getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
    
    abstract void update();
     
}
