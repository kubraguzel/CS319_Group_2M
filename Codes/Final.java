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
	private final float FIRE_RATE= 700f;
	private final float BODY_DAMAGE = 60f;
	private final float BULLET_DAMAGE = 30f;
	private final float BULLET_SPEED = 7f;
	private long nextTimeToShoot =0;
	
	private boolean shieldUp=true;
	private final float DOWN_TIME = 10000f;
	private final float PROXIMITY = 500f;
	private long nextTimeToShieldUp = 0;
	private final float MAX_SHIELD = 60f;
	private float shieldHealth=MAX_SHIELD;
	private Shape shield = new Ellipse(super.getPosition().x , (super.getPosition().y), 
			super.getDimentions().x+ 2f, super.getDimentions().y+ 2f);
	private Vector2f velocity;
	

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
			((MorphShape) shape).updateMorphTime(0.03f);
		if(System.currentTimeMillis() >= nextTimeToShieldUp && !shieldUp)
		{
			shieldUp=true;
			shieldHealth = MAX_SHIELD;
		}
		
		super.update();
	}

}
