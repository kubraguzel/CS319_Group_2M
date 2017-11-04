import org.newdawn.slick.geom.Vector2f;

public abstract class DynamicGameObject extends GameObject {
	private float speed;
	protected float screenWidth;
	protected float screenHeight;
	
	public DynamicGameObject(Vector2f pos, Vector2f dim, float speed)
    {
    	super(pos, dim);
    	this.speed= speed;
    	this.screenWidth = 1920f;
    	this.screenHeight = 1080f;
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
    
    
    
    public DynamicGameObject(Vector2f pos, Vector2f dim, float speed, float screenWidth, float screenHeight) {
		super(pos, dim);
		this.speed = speed;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	//Getters & setters
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	

	public float getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(float screenWidth) {
		this.screenWidth = screenWidth;
	}

	public float getscreenHeight() {
		return screenHeight;
	}

	public void setscreenHeight(float screenHeight) {
		this.screenHeight = screenHeight;
	}

	abstract void move();
}
