import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

public class Midterm extends Enemy implements Shooter 
{
	private ArrayList<Bullet> bulletList;
	private final float FIRE_RATE= 600f;
	private final float BODY_DAMAGE = 60f;
	private final float BULLET_DAMAGE = 20f;
	private final float BULLET_SPEED = 10f;
	private long nextTimeToShoot =0;
	private final float THETA = 10f;
	private final float PROXIMITY = 500f;
	
	
	public Midterm(Vector2f pos, Vector2f dim, float speed, float maxHealth, float bodyDamage,
			DynamicGameObject target, ArrayList<Bullet> bulletList) {
		super(pos, dim, speed, maxHealth, bodyDamage, target);
		this.bulletList=bulletList;
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.proximityDistance =PROXIMITY;
		super.shape = makeShape(super.getDimentions().x, super.getDimentions().y);
	}

	public Midterm(Vector2f pos, Vector2f dim, float speed, float maxHealth, DynamicGameObject target,
			ArrayList<Bullet> bulletList) {
		super(pos, dim, speed, maxHealth, target);
		this.bulletList=bulletList;
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.proximityDistance =PROXIMITY;
		super.shape = makeShape(super.getDimentions().x, super.getDimentions().y);
	}
	
	public Midterm(Vector2f pos, float speed, float maxHealth, DynamicGameObject target,
			ArrayList<Bullet> bulletList) {
		super(pos, new Vector2f (60f, 60f), speed, maxHealth, target);
		this.bulletList=bulletList;
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.proximityDistance =PROXIMITY;
		super.shape = makeShape(super.getDimentions().x, super.getDimentions().y);
	}
	
	public Midterm(Vector2f pos, float maxHealth, DynamicGameObject target,
			ArrayList<Bullet> bulletList) {
		super(pos, new Vector2f (60f, 60f), 1.9f, maxHealth, target);
		this.bulletList=bulletList;
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.proximityDistance =PROXIMITY;
		super.shape = makeShape(super.getDimentions().x, super.getDimentions().y);
	}
	
	private Polygon makeShape(float x, float y)
	{
		float[] points = {0,0,x,y,0,y*0.5f,-x,y};
		return new Polygon(points);
	}
	
	//Due to design constraints this method only returns the middle bullet but adds all 3 bullets it shoots to the bulletList
	@Override
	public Bullet shoot(Vector2f target) 
	{
		nextTimeToShoot = System.currentTimeMillis() 
				+ (long)super.getStats().getFireRate();
		
		Vector2f curPos = new Vector2f(super.getPosition());
		Vector2f targetPos = new Vector2f(target);
		
		//Creating 3 bullets with different angles
		//Directly aimed bullet(will be returned)
		Bullet bullet1 = new Bullet(curPos, targetPos, getStats().getBulletSpeed(),
				getStats().getBulletDamage(), true);
		/*Bullet with +30degrees off set
		X'= X*cos(THETA)-Y*sin(THETA)
		Y'= X*sin(THETA)+Y*cos(THETA)*/
		
		Vector2f targetPos2 = new Vector2f (target);
		Vector2f curPos2 = new Vector2f (super.getPosition());
		float distance = targetPos2.distance(curPos2);
		
		targetPos2.sub(curPos2);
		targetPos2.add(THETA);
		targetPos2.add(curPos2);
		
		Bullet bullet2 = new Bullet(curPos2, targetPos2, getStats().getBulletSpeed(),
				getStats().getBulletDamage() ,true);
		
		//Bullet with -30 degrees angle
		Vector2f targetPos3 = new Vector2f (target);
		Vector2f curPos3 = new Vector2f (super.getPosition());
		
		targetPos3.sub(curPos3);
		targetPos3.sub(THETA);
		targetPos3.add(curPos3);
		
		Bullet bullet3 = new Bullet(curPos3, targetPos3, getStats().getBulletSpeed(),
				getStats().getBulletDamage(), true);
		bulletList.add(bullet3);
		bulletList.add(bullet2);
		bulletList.add(bullet1);
		return bullet1;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0.7059f,0.175f,1f, 0.6f));
		super.draw(g);
	}

	@Override
	void update() {
		if (System.currentTimeMillis() >= nextTimeToShoot)
		{
			Vector2f targetPos = new Vector2f(super.target.getPosition());
			this.shoot(targetPos);
		}
		
		super.update();
	}
	
	

}
