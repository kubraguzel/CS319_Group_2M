import org.newdawn.slick.geom.Vector2f;

public abstract class DynamicGameObject extends GameObject {
	Vector2f velocity;
	
	public DynamicGameObject(Vector2f pos, Vector2f dim, Vector2f velocity)
    {
    	super(pos, dim);
    	this.velocity= velocity;
    }
    
    public DynamicGameObject(float x, float y, float l, float h, Vector2f velocity)
    {

    	super(new Vector2f(x, y), new Vector2f(l, h));
    	this.velocity= velocity;
    }
    
    public DynamicGameObject(float x, float y, Vector2f velocity)
    {
    	super(new Vector2f(x, y), new Vector2f(1f, 1f));
    	this.velocity= velocity;
    }
    
    //Getters & setters
	public Vector2f getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}
	
	abstract void move();
}
