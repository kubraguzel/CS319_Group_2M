/**
 * Author: Alper Þahýstan, Semih Teker
 */

import java.awt.Dimension;
import java.awt.Toolkit;

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
	
	private boolean powerUpActive=false;
	
	private String playerName;
	
	private final float ALTERNATE_TIME = 90f;
	private long nextTimeToNormalise = 0;
	
	private static Player player = null;
	
	//*************************ST**************************
	int numberOfKey;
	int score;
	//*************************ST**************************	
	
	public static Player getPlayer()
	{
		if (player==null)
		{
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			player = new Player (new Vector2f(30f, 30f), new Vector2f(30f, 30f), 
					4f, 100f, 6f, 10f, 
					400f, 0f, (float)width, 
					(float)height);
		}
		return player;
	}

	private Player(Vector2f pos, Vector2f dim, float speed, float maxHealth) {
		super(pos, dim, speed);
		
		//Constructing the shape to a specific Ellipse
		shape= new Circle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()/2));
		//Setting velocity
		velocity = new Vector2f(0f, 0f);
		
		normal = Color.blue;
		alternate = new Color(1f, 0f, 0f, 0.6f);
		curColor = normal;
		
		playerStats = new Stats(maxHealth, maxHealth);
		
		//*************************ST**************************
		numberOfKey=0;
		score=0;
		//*************************ST**************************
	}
	
	private Player(Vector2f pos, Vector2f dim, float speed, 
			float maxHealth, float bulletSpeed,
			float bulletDamage, float fireRate) {
		super(pos, dim, speed);
		
		//Constructing the shape to a specific Circle
		shape= new Circle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()/2));
		//Setting velocity
		velocity = new Vector2f(0f, 0f);
		
		normal = Color.blue;
		alternate = new Color(1f, 0f, 0f, 0.4f);
		curColor = normal;
		
		playerStats = new Stats(maxHealth, bulletSpeed, bulletDamage, fireRate, 0f);
		
		//*************************ST**************************
		numberOfKey=0;
		score=0;
		//*************************ST**************************
	}
	
	private Player(Vector2f pos, Vector2f dim, float speed, 
			float maxHealth, float bulletSpeed,
			float bulletDamage, float fireRate, float bodyDamage, float sW, float sH) {
		super(pos, dim, speed, sW, sH);
		
		//Constructing the shape to a specific Ellipse
		shape= new Circle(super.getPosition().getX(), super.getPosition().getY(), 
					(super.getDimentions().getX()/2));
		//Setting velocity
		velocity = new Vector2f(0f, 0f);
		
		normal = Color.blue;
		alternate = new Color(1f, 0f, 0f, 0.4f);
		curColor = normal;
		
		playerStats = new Stats(maxHealth, bulletSpeed, bulletDamage, fireRate, bodyDamage);
		
		//*************************ST**************************
		numberOfKey=0;
		score=0;
		//*************************ST**************************
	}
	
	
	@Override
	public void draw(Graphics g) 
	{
		g.setColor(curColor);
		g.fill(shape);
		if(playerName != null)
		{
			g.setColor(new Color (0.14f, 0.25f, 0.6f));
			g.drawString(playerName, this.getPosition().x /*- this.getDimentions().x*/ - playerName.length()*4.6f ,
					this.getPosition().y + this.getDimentions().y/2 + 10f);
		}	
		
	}

	@Override
	void move() 
	{
		//Controller sent signals are used to construct a vector velocity
		if (up && 
				(this.getPosition().getY() - (super.getDimentions().y/2)> 0f))
			velocity.y = -1f;
		if (down && 
				(this.getPosition().getY() + (super.getDimentions().y/2) < this.screenHeight))
			velocity.y = 1f;
		if(right && 
				(this.getPosition().getX() + (super.getDimentions().x/2) < this.screenWidth ))
			velocity.x = 1f;
		if(left &&
				(this.getPosition().getX() - (super.getDimentions().x/2) > 0f))
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
		
		if(System.currentTimeMillis() > nextTimeToNormalise)
			curColor = normal;
	}
	
	@Override
	public Bullet shoot(Vector2f target)
	{
		if (System.currentTimeMillis() >= nextTimeToShoot)
		{
			nextTimeToShoot = (long)playerStats.getFireRate() + System.currentTimeMillis();
			Bullet bullet;
			if(powerUpActive)
			{
					bullet = new BouncyBullet(new Vector2f(super.getPosition()), 
					target, playerStats.getBulletSpeed(), 
					playerStats.getBulletDamage(),
					super.screenWidth,
					super.screenHeight);
			}
			else
			{
				bullet = new Bullet(new Vector2f(super.getPosition()), 
					target, playerStats.getBulletSpeed(), 
					playerStats.getBulletDamage());
			}
			return bullet;
		}
		return null;
	}
	
	public void takeDamage(float dmg)
	{
		this.playerStats.takeDamage(dmg);
		
		curColor = alternate;
		nextTimeToNormalise = System.currentTimeMillis() + (long)ALTERNATE_TIME;
	}
	
	//*************************ST**************************
	void takeKey(){
		numberOfKey++;
		//System.out.println(numberOfKey);
	}
	//*************************ST**************************
	
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

	public boolean isPowerUpActive() {
		return powerUpActive;
	}

	public void setPowerUpActive(boolean powerUpActive) {
		this.powerUpActive = powerUpActive;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
}
