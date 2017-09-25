import java.awt.*;

public class Player extends GameObject implements Drawable
{
	Stats playerStats;
	float fireRate;
	float dX=0;
	float dY=0;
	
	private float nextTimeToShoot = 0;
	
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	
	public Player(Vector2 pos, Vector2 dim, float maxHealth, float curHealth, float speed, float fireRate)
	{
		super(pos, dim);
		playerStats = new Stats(maxHealth, curHealth, speed);
		this.fireRate =fireRate;
	}
	//TODO: ADD MORE CONTRUCTORS
	
	public void takeDamage(float dmg)
	{
		this.playerStats.setCurHealth(this.playerStats.getCurHealth()-dmg);
		
		if(!(this.playerStats.getCurHealth()>0))
			System.out.println("Player is killed!");
	}

	public void update() 
	{
		if (up)
			dY=-1;
		if (down)
			dY=1;
		if(right)
			dX=1;
		if(left)
			dX=-1;
		
		Vector2 velocity = new Vector2(dX, dY);
		//System.out.println(velocity.normalized());
		velocity = (velocity.normalized());
		velocity.multiply( playerStats.getSpeed());
		
		//System.out.println(velocity.getX());
		//System.out.println(velocity.getY());
		
		
		pos.add(velocity);
		
		/*System.out.println(dX);
		System.out.println(dY);
		System.out.println(pos.getX());*/	
		
		dX=0;
		dY=0;
		
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLUE);
		
		//getting the object drawn from the center
		//System.out.println((int)(super.pos.getX()- (super.dimentions.getX()/2)));
		g.fillOval((int)(super.pos.getX()- (super.dimentions.getX()/2)),
				(int)(super.pos.getY()- (super.dimentions.getY()/2)),
				(int)super.dimentions.getX(),
				(int)super.dimentions.getY());
	}
	
	public Bullet shoot(Vector2 target)
	{
		if ((float) System.currentTimeMillis() >= nextTimeToShoot)
		{
			nextTimeToShoot = fireRate + (float) System.currentTimeMillis();
			Bullet bullet = new Bullet(new Vector2(super.pos), target, 20f);
			return bullet;
		}
		System.out.println("Null");
		return null;
	}
	
	
}
