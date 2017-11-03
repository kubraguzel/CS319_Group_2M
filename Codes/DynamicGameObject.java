import org.newdawn.slick.geom.Vector2f;

public abstract class DynamicGameObject extends GameObject {
	float speed;
	
	public DynamicGameObject(Vector2f pos, Vector2f dim, float speed)
    {
    	super(pos, dim);
    	this.speed= speed;
    }
    
    public DynamicGameObject(float x, float y, float l, float h, float speed)
    {

    	super(new Vector2f(x, y), new Vector2f(l, h));
    	this.speed= speed;
    }
    
    public DynamicGameObject(float x, float y, float speed)
    {
    	super(new Vector2f(x, y), new Vector2f(1f, 1f));
    	this.speed= speed;
    }
    
    //Getters & setters
	public float getVelocity() {
		return speed;
	}
	public void setVelocity(float speed) {
		this.speed = speed;
	}
	
	abstract void move();
}
