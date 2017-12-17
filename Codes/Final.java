import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Final extends Enemy implements Shooter {
	
	private ArrayList<Bullet> bulletList;
	private final float FIRE_RATE= 800f;
	private final float BODY_DAMAGE = 60f;
	private final float BULLET_DAMAGE = 40f;
	private final float BULLET_SPEED = 7f;
	private long nextTimeToShoot =0;
	private final float THETA = 10f;
	
	private boolean shieldUp=true;
	private final float DOWN_TIME = 9500f;
	private final float PROXIMITY = 700f;
	private long nextTimeToShieldUp = 0;
	private final float MAX_SHIELD = 150f;
	private float shieldHealth=MAX_SHIELD;
	private Shape shield = new Ellipse(super.getPosition().x , (super.getPosition().y), 
			super.getDimentions().x+ 2f, super.getDimentions().y+ 2f);
	private Vector2f velocity;
	private boolean shootSingle = false;
	

	public Final(Vector2f pos, Vector2f dim, float speed, float maxHealth, float bodyDamage, 
			DynamicGameObject target, ArrayList<Bullet> bulletList) {
		super(pos, dim, speed, maxHealth, bodyDamage, target);
		this.bulletList=bulletList;
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.proximityDistance =PROXIMITY;
		setShape();
		//*************************ST**************************
		//numOfLayer = 3;
		//*************************ST**************************
	}

	public Final(Vector2f pos, Vector2f dim, float speed, float maxHealth, 
			DynamicGameObject target, ArrayList<Bullet> bulletList) {
		super(pos, dim, speed, maxHealth, target);
		this.bulletList=bulletList;
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.proximityDistance =PROXIMITY;
		setShape();
		//*************************ST**************************
		//numOfLayer = 3;
		//*************************ST**************************
	}
	
	public Final(Vector2f pos, float speed, float maxHealth, 
			DynamicGameObject target, ArrayList<Bullet> bulletList) {
		super(pos, new Vector2f(80f,80f), speed, maxHealth, target);
		this.bulletList=bulletList;
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.proximityDistance =PROXIMITY;
		setShape();
		//*************************ST**************************
		//numOfLayer = 3;
		//*************************ST**************************
	}
	
	public Final(Vector2f pos, float maxHealth, 
			DynamicGameObject target, ArrayList<Bullet> bulletList) {
		super(pos, new Vector2f(80f,80f), 1.5f, maxHealth, target);
		this.bulletList=bulletList;
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.proximityDistance =PROXIMITY;
		setShape();
		//*************************ST**************************
		//numOfLayer = 3;
		//*************************ST**************************
	}
	
	private void setShape()
	{
		float x = super.getDimentions().x;
		float y = super.getDimentions().y;
		float[] points = {0,y, 0.21f*x,0.46f*y, 0.71f*x,0.7f*y,
				0.46f*x,0.2f*y, x,0, 0.46f*x,-0.2f*y, 0.71f*x,-0.71f*y,
				0.21f*x,-0.46f*y, 0,-y, -0.21f*x,-0.46f*y, 
				-0.71f*x,-0.71f*y, -0.46f*x,-0.2f*y, -x,0, 
				-0.46f*x, 0.2f*y, -0.71f*x,0.71f*y, -0.21f*x,0.46f*y};
		Polygon eigthAnointedStar = new Polygon(points);
		shape = new MorphShape(eigthAnointedStar);
		
		float[] points2 = {0,y, 0.36f*x,0.93f*y, 0.71f*x,0.7f*y,
				0.92f*x,0.38f*y, x,0, 0.92f*x,-0.38f*y, 0.71f*x,-0.71f*y,
				0.36f*x,-0.93f*y, 0,-y, -0.38f*x,-0.92f*y, 
				-0.71f*x,-0.71f*y, -0.93f*x,-0.38f*y, -x,0, 
				-0.93f*x, 0.38f*y, -0.71f*x,0.71f*y, -0.36f*x,0.93f*y};
		Polygon closedShape = new Polygon(points2);
		((MorphShape)shape).addShape(closedShape);
	}

	@Override
	public Bullet shoot(Vector2f target) 
	{
		if(System.currentTimeMillis() > nextTimeToShoot)
		{
			Bullet bullet1;
			nextTimeToShoot = System.currentTimeMillis() 
					+ (long)super.getStats().getFireRate();
			
			if(shootSingle)
			{
				Vector2f curPos = new Vector2f(super.getPosition());
				Vector2f targetPos = new Vector2f(target);
				
				//Creating 3 bullets with different angles
				//Directly aimed bullet(will be returned)
				bullet1 = new Bullet(curPos,new Vector2f(20f,20f), targetPos, getStats().getBulletSpeed()*0.8f,
						2*getStats().getBulletDamage(), true);
				//bullet1.setDimentions(new Vector2f(90f,90f));
			}
			else
			{
				
				Vector2f curPos = new Vector2f(super.getPosition());
				Vector2f targetPos = new Vector2f(target);
				
				//Creating 3 bullets with different angles
				//Directly aimed bullet(will be returned)
				bullet1 = new BouncyBullet(curPos, targetPos, getStats().getBulletSpeed(),
						getStats().getBulletDamage(), true);
				
				/*Bullet with +10degrees off set*/
				Vector2f targetPos2 = new Vector2f (target);
				Vector2f curPos2 = new Vector2f (super.getPosition());
				
				targetPos2.sub(curPos2);
				targetPos2.add(THETA);
				targetPos2.add(curPos2);
				
				Bullet bullet2 = new BouncyBullet(curPos2, targetPos2, getStats().getBulletSpeed(),
						getStats().getBulletDamage() ,true);
				
				//Bullet with +20 offset
				Vector2f targetPos3 = new Vector2f (target);
				Vector2f curPos3 = new Vector2f (super.getPosition());
				
				targetPos3.sub(curPos3);
				targetPos3.add(2*THETA);
				targetPos3.add(curPos3);
				
				Bullet bullet3 = new BouncyBullet(curPos3, targetPos3, getStats().getBulletSpeed(),
						getStats().getBulletDamage() ,true);
				
				//Bullet with -10 offset
				Vector2f targetPos4 = new Vector2f (target);
				Vector2f curPos4 = new Vector2f (super.getPosition());
				
				targetPos4.sub(curPos4);
				targetPos4.sub(THETA);
				targetPos4.add(curPos4);
				
				Bullet bullet4 = new BouncyBullet(curPos4, targetPos4, getStats().getBulletSpeed(),
						getStats().getBulletDamage(), true);
				
				//Bullet with -20 offset
				Vector2f targetPos5 = new Vector2f (target);
				Vector2f curPos5 = new Vector2f (super.getPosition());
				
				targetPos5.sub(curPos5);
				targetPos5.sub(2*THETA);
				targetPos5.add(curPos5);
				
				Bullet bullet5 = new BouncyBullet(curPos5, targetPos5, getStats().getBulletSpeed(),
						getStats().getBulletDamage(), true);
				
				bulletList.add(bullet5);
				bulletList.add(bullet4);
				bulletList.add(bullet3);
				bulletList.add(bullet2);
			}
			shootSingle=!shootSingle;
			bulletList.add(bullet1);
			return bullet1;
		}
		else
			return null;
	}

	@Override
	public void takeDamage(float dmg) {
		
		if(!shieldUp)
			super.takeDamage(dmg);
		else
		{
			shieldHealth = shieldHealth - dmg;
			if (shieldHealth <= 0)
			{
				lowerShield();
			}
		}
	}

	private void lowerShield() 
	{
		shieldUp= false;
		nextTimeToShieldUp =  (System.currentTimeMillis() + (long)DOWN_TIME);
		((MorphShape) shape).setMorphTime(0f);
		
		velocity = new Vector2f(target.getPosition());
		velocity.sub(getPosition());
		velocity.normalise();
		velocity.scale(getSpeed()*10f);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		super.draw(g);
		if(shieldUp)
		{
			g.setColor(new Color(1f, 1f, 0f, 0.2f));
			g.fill(shield);
		}
	}

	@Override
	public void move() {
		if(shieldUp)
			super.move();
		else
		{
			super.setPosition((super.getPosition().add(velocity)));
			if(super.getPosition().getX()<0 || super.getPosition().getX()>super.screenWidth)
				bounce(false);
			else if(super.getPosition().getY()<0 || super.getPosition().getY()>super.screenHeight)
				bounce(true);
		}
	}
	
	private void bounce (boolean hitToAYBoundary)
	{
		if((!hitToAYBoundary)){
			velocity.set(-velocity.x, velocity.y);
		}
		else if(hitToAYBoundary){
			velocity.set(velocity.x, -velocity.y);
		}
	}
	
	@Override
	void update() {
		
		shield.setCenterX(getPosition().getX()); 
		shield.setCenterY(getPosition().getY());
		
		if(shieldUp)
		{
			((MorphShape) shape).updateMorphTime(0.03f);
			shoot(target.getPosition());
		}
		
		if(System.currentTimeMillis() >= nextTimeToShieldUp && !shieldUp)
		{
			shieldUp=true;
			shieldHealth = MAX_SHIELD;
		}
		
		super.update();
		
	}

}