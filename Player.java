import java.awt.*;

public class Player extends GameObject implements Drawable, Shooter
{
	private Stats playerStats;
	private float fireRate;
	private float dX=0;
	private float dY=0;
	
	private float nextTimeToShoot = 0;
	
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	
	
	public Player(Vector2 pos, Vector2 dim, float maxHealth, float curHealth, Vector2 velocity, float fireRate)
	{
		super(pos, dim, velocity);
		playerStats = new Stats(maxHealth, curHealth);
		this.fireRate =fireRate;
	}
	
	public Player(Vector2 pos, float maxHealth, float curHealth, Vector2 velocity, float fireRate)
	{
		super(pos, new Vector2(15f, 15f), velocity);
		playerStats = new Stats(maxHealth, curHealth);
		this.fireRate =fireRate;
	}
	
	//Getters & Setters
	public Stats getPlayerStats() {
		return playerStats;
	}

	public void setPlayerStats(Stats playerStats) {
		this.playerStats = playerStats;
	}

	public float getFireRate() {
		return fireRate;
	}

	public void setFireRate(float fireRate) {
		this.fireRate = fireRate;
	}
	
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

	public void takeDamage(float dmg)
	{
		this.playerStats.takeDamage(dmg);
		
		if(!(this.playerStats.getCurHealth()>0))
			System.out.println("player is killed!");
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
		
		//System.out.println(velocity.normalized());
		
		//System.out.println(velocity.getX());
		//System.out.println(velocity.getY());
		
		Vector2 p = super.getPos();
		Vector2 v = new Vector2(super.getVelocity().getX()*dX,
							super.getVelocity().getY()*dY);
		
		//Limits the speed to the magnitude
		if(v.getMagnitude()> v.getX() || v.getMagnitude()> v.getY())
		{
			v.setX(v.getX()/1.4142f);
			v.setY(v.getY()/1.4142f);
			//System.out.println("hello");
		}
		p.add(v);
		
		super.setPos(p);
		
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
		g.fillOval((int)(super.getPos().getX() - ((super.getDimentions()).getX())/2),
				(int)(super.getPos().getY()- (super.getDimentions().getY()/2)),
				(int)super.getDimentions().getX(),
				(int)super.getDimentions().getY());
	}
	
	public Bullet shoot(Vector2 target)
	{
		if ((float) System.currentTimeMillis() >= nextTimeToShoot)
		{
			nextTimeToShoot = fireRate + (float) System.currentTimeMillis();
			Bullet bullet = new Bullet(new Vector2(super.getPos()), target, 20f, 10f);
			return bullet;
		}
		System.out.println("Null");
		return null;
	}
	
	
}
