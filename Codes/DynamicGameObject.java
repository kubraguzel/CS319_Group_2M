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
    
    //returns a clamped value of the given float between min and max
    public float clamp(float min, float max, float value)
    {
    	return Math.min(max,(Math.max(min, value)));
    }
    
    
    //Pushes 2 DynamicGameObjects away from eachother
    public void bounceOff(DynamicGameObject other, float bounceValue)
    {
    	Vector2f bounceOffVector = new Vector2f(this.getPosition());
    	//initializes an vector that is the difference between 2 colliding objects
		bounceOffVector.sub(other.getPosition());
		//Make the vector a unit vector
		bounceOffVector.normalise();
		//bounce value times that unit vector
		bounceOffVector.scale(bounceValue);
		Vector2f pos = new Vector2f(this.getPosition());
		pos.add(bounceOffVector);
		//pushing this away from the other
		pos.set(clamp(0+this.getDimentions().getX()/2, screenWidth-this.getDimentions().getX()/2, pos.getX()), 
				clamp(0+this.getDimentions().getY()/2, screenHeight- this.getDimentions().getY()/2, pos.getY()));
		this.setPosition(pos);
		
		pos = new Vector2f (other.getPosition());
		pos.sub(bounceOffVector);
		//pushing other away from the this
		pos.set(clamp(0+this.getDimentions().getX()/2, screenWidth-this.getDimentions().getX()/2, pos.getX()), 
				clamp(0+this.getDimentions().getY()/2, screenHeight- this.getDimentions().getY()/2, pos.getY()));
		other.setPosition(pos);
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
