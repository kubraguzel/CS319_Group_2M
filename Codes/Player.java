package deneme;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
//import org.newdawn.slick.geom.Ellipse;
//import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Player extends DynamicGameObject implements Shooter{
	
	//Fields about color changes
	private Color normal;
	private Color alternate;
	private Color curColor;
	
	//Fields about controls and directions
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	
	//Fields about shooting
	long nextTimeToShoot = 0;
	
	private Vector2f velocity;
	
	Stats playerStats;

	public Player(Vector2f pos, Vector2f dim, float speed, float maxHealth) {
		super(pos, dim, speed);
		
		//Constructing the shape to a specific Ellipse
		shape= new Circle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()/2));
		//Setting velocity
		velocity = new Vector2f(0f, 0f);
		
		normal = Color.blue;
		alternate = Color.red;
		curColor = normal;
		
		playerStats = new Stats(maxHealth, maxHealth);
	}
	
	public Player(Vector2f pos, Vector2f dim, float speed, 
					float maxHealth, float bulletSpeed,
					float bulletDamage, float fireRate) {
		super(pos, dim, speed);
		
		//Constructing the shape to a specific Circle
		shape= new Circle(super.getPosition().getX(), super.getPosition().getY(), 
							( super.getDimentions().getX()/2 ));
		//Setting velocity
		velocity = new Vector2f(0f, 0f);
		
		normal = Color.blue;
		alternate = Color.red;
		curColor = normal;
		
		playerStats = new Stats(maxHealth, bulletSpeed, bulletDamage, fireRate, 0f);
	}
	
	public Player(Vector2f pos, Vector2f dim, float speed, 
			float maxHealth, float bulletSpeed,
			float bulletDamage, float fireRate, float bodyDamage, float sW, float sH) {
		super(pos, dim, speed, sW, sH);
		
		//Constructing the shape to a specific Ellipse
		shape= new Circle(super.getPosition().getX(), super.getPosition().getY(), 
					(super.getDimentions().getX()/2));
		//Setting velocity
		velocity = new Vector2f(0f, 0f);
		
		normal = Color.blue;
		alternate = Color.red;
		curColor = normal;
		
		playerStats = new Stats(maxHealth, bulletSpeed, bulletDamage, fireRate, bodyDamage);
	}
	
	
	@Override
	public void draw(Graphics g) 
	{
		g.setColor(curColor);
		g.fill(shape);
	}

	@Override
	void move() 
	{
		//Controller sent signals are used to construct a vector velocity
		if (up)
			velocity.y = -1f;
		if (down)
			velocity.y = 1f;
		if(right)
			velocity.x = 1f;
		if(left)
			velocity.x = -1f;
		
		//velocity is is scaled to a unit vector
		velocity = velocity.normalise();
		//velocity is multiplied with speed
		velocity = velocity.scale(super.getSpeed());
		
		//a vector to hold the calculations of next point of move.
		Vector2f movePos = super.getPosition();
		//velocity is added to current point to become next point to move 
		movePos = movePos.add(velocity);	
		//Players position is set to next point to be moved
		super.setPosition (movePos);
		
		//velocity is assigned to (0,0) for next calculations
		velocity.x = 0f;
		velocity.y = 0f;
	}
	
	//The method to be called before every frame
	@Override
	void update() 
	{
		shape.setCenterX(getPosition().getX()); 
		shape.setCenterY(getPosition().getY());
		move();
	}
	@Override
	public Bullet shoot(Vector2f target)
	{
		if (System.currentTimeMillis() >= nextTimeToShoot)
		{
			nextTimeToShoot = (long)playerStats.getFireRate() + System.currentTimeMillis();
			Bullet bullet = new Bullet(new Vector2f(super.getPosition()), 
										target, playerStats.getBulletSpeed(), 
										playerStats.getBulletDamage());
			return bullet;
		}
		return null;
	}
	
	public BouncyBullet shootBouncy(Vector2f target)
	{
		if (System.currentTimeMillis() >= nextTimeToShoot)
		{
			nextTimeToShoot = (long)playerStats.getFireRate() + System.currentTimeMillis();
			BouncyBullet bullet = new BouncyBullet(new Vector2f(super.getPosition()), 
										target, playerStats.getBulletSpeed(), 
										playerStats.getBulletDamage(),
										super.screenWidth,
										super.screenHeight);
			return bullet;
		}
		return null;
	}
	
	public void takeDamage(float dmg)
	{
		this.playerStats.takeDamage(dmg);
		
		if(!(this.playerStats.getCurHealth()>0))
			System.out.println("player is killed!");
	}
	
	/*@Override
	boolean collides(GameObject other) 
	{
		return shape.intersects(other.getShape());
		
	}*/
	
	//getters & setters
	public Color getAlternate() {
		return alternate;
	}

	public void setAlternate(Color alternate) {
		this.alternate = alternate;
	}

	public Color getCurColor() {
		return curColor;
	}

	public void setCurColor(Color curColor) {
		this.curColor = curColor;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public Stats getStats() {
		return playerStats;
	}

	public void setStats(Stats playerStats) {
		this.playerStats = playerStats;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

		
}