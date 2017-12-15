import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

public class Final extends Enemy implements Shooter {
	
	private ArrayList<Bullet> bulletList;
	private final float FIRE_RATE= 700f;
	private final float BODY_DAMAGE = 60f;
	private final float BULLET_DAMAGE = 30f;
	private final float BULLET_SPEED = 7f;
	private long nextTimeToShoot =0;
	private final float PROXIMITY = 500f;

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
	public Bullet shoot(Vector2f target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void takeDamage(float dmg) {
		// TODO Auto-generated method stub
		super.takeDamage(dmg);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		super.draw(g);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
	}

	@Override
	void update() {
		((MorphShape) shape).updateMorphTime(0.03f);
		super.update();
	}
	
	

}
