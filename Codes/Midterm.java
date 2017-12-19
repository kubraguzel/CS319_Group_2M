import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.MorphShape;

public class Midterm extends Enemy implements Shooter 
{
	private ArrayList<Bullet> bulletList;
	private final float FIRE_RATE= 600f;
	private final float BODY_DAMAGE = 60f;
	private final float BULLET_DAMAGE = 20f;
	private final float BULLET_SPEED = 9f;
	private long nextTimeToShoot =0;
	private final float THETA = 10f;
	private final float PROXIMITY = 500f;
	private boolean inCriticalState=false;
	private final Color standart = new Color(0.7059f,0.175f,1f, 0.3f);
	private final Color critical = new Color(1f, 0f, 0.5f);
	private Color curColor = standart;
	
	//-----------
	MultiBar m;
	//-----------
	
	public Midterm(Vector2f pos, Vector2f dim, float speed, float maxHealth, float bodyDamage,
			DynamicGameObject target, ArrayList<Bullet> bulletList) {
		super(pos, dim, speed, maxHealth, bodyDamage, target);
		this.bulletList=bulletList;
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.proximityDistance =PROXIMITY;
		setShape();
		//-----------
		m = new MultiBar(this, true);
		//-----------
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
		setShape();
		//-----------	
		m = new MultiBar(this, true);
		//-----------
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
		setShape();
		//-----------
		m = new MultiBar(this, true);
		//-----------
	}
	
	public Midterm(Vector2f pos, float maxHealth, DynamicGameObject target,
			ArrayList<Bullet> bulletList) {
		super(pos, new Vector2f (40f, 40f), 1.9f, maxHealth, target);
		this.bulletList=bulletList;
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.proximityDistance =PROXIMITY;
		setShape();
		//-----------
		m = new MultiBar(this, true);
		//-----------
	}
	
	private void setShape()
	{
		float x = super.getDimentions().x;
		float y = super.getDimentions().y;
		float[] points = {0.2f*x,0, x,y, 0,y*0.5f, -x,y, -0.2f*x,0, -x,-y, 0,-0.5f*y, x,-y};
		Polygon firstShape = new Polygon(points);
		super.shape = new MorphShape((Shape)firstShape);
		//points for 8 vertex square
		float[] points2 = {0.5f*x,0, 0.5f*x,0.5f*y, 0,0.5f*y, -0.5f*x,0.5f*y,
				-0.5f*x,0, -0.5f*x,-0.5f*y, 0,-0.5f*y, 0.5f*x,-0.5f*y};
		Polygon secondShape = new Polygon(points2);
		((MorphShape) shape).addShape(secondShape);
		
	}
	
	//Due to design constraints this method only returns the middle bullet but adds all 3 bullets it shoots to the bulletList
	@Override
	public Bullet shoot(Vector2f target) 
	{
		if (!inCriticalState && System.currentTimeMillis() >= nextTimeToShoot)
		{
			nextTimeToShoot = System.currentTimeMillis() 
					+ (long)super.getStats().getFireRate();
			
			Vector2f curPos = new Vector2f(super.getPosition());
			Vector2f targetPos = new Vector2f(target);
			
			//Creating 3 bullets with different angles
			//Directly aimed bullet(will be returned)
			Bullet bullet1 = new Bullet(curPos, targetPos, getStats().getBulletSpeed(),
					getStats().getBulletDamage(), true);
			/*Bullet with +30degrees off set*/
			Vector2f targetPos2 = new Vector2f (target);
			Vector2f curPos2 = new Vector2f (super.getPosition());
			
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
		else if(inCriticalState)
		{

			Vector2f curPos = new Vector2f(super.getPosition());
			Vector2f targetPos = new Vector2f(target);
			Bullet bullet1 = new Bullet(curPos, targetPos, getStats().getBulletSpeed(),
					getStats().getBulletDamage(), true);
			
			((MorphShape) shape).setExternalFrame(shape);
			bulletList.add(bullet1);
			return bullet1;
			
		}
		return null;
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Midterm", this.getPosition().x - 7*4.6f ,
					this.getPosition().y + this.getDimentions().y + 5f);
		
		g.setColor(curColor);
		super.draw(g);
		
		//-----------
		m.draw(g);
		//-----------
	}

	@Override
	void update() {
		Vector2f targetPos = new Vector2f(super.target.getPosition());
		this.shoot(targetPos);
		
		if(getStats().getCurHealth()<50 && !inCriticalState)
			enterCriticalState();
		else
			((MorphShape) shape).updateMorphTime(0.04f);
		super.update();
		
		//-----------
		m.update();
		//-----------
	}

	private void enterCriticalState() 
	{
		inCriticalState=true;
		curColor = critical;
		
		((MorphShape) shape).setMorphTime(0f);
	}
}