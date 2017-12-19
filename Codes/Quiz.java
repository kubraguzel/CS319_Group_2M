import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Vector2f;

public class Quiz extends Enemy implements Shooter {
	
	private long nextTimeToShoot = 0;
	private final float FIRE_RATE = 900f;
	private final float BULLET_SPEED = 9f;
	private final float PROXIMITY = 120f;
	private final float BULLET_DAMAGE = 5f;
	private ArrayList<Bullet> bulletList;
	
	//-----------
	MultiBar m;
	//-----------

	public Quiz(Vector2f pos, Vector2f dim, float speed, float maxHealth, float bodyDamage, DynamicGameObject target, ArrayList<Bullet> bulletList) {
		super(pos, dim, speed, maxHealth, bodyDamage, target);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.shape = new Ellipse(super.getPosition().x,super.getPosition().y,
				super.getDimentions().x, super.getDimentions().y);
		this.bulletList = bulletList;
		super.proximityDistance = PROXIMITY;
		//-----------
		m = new MultiBar(this, true);
		//-----------
	}

	public Quiz(Vector2f pos, Vector2f dim, float speed, float maxHealth, DynamicGameObject target, ArrayList<Bullet> bulletList) {
		super(pos, dim, speed, maxHealth, target);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.shape = new Ellipse(super.getPosition().x,super.getPosition().y,
				super.getDimentions().x, super.getDimentions().y);
		this.bulletList = bulletList;
		super.proximityDistance = PROXIMITY;
		//-----------
		m = new MultiBar(this, true);
		//-----------
	}
	
	public Quiz(Vector2f pos, float speed, float maxHealth, DynamicGameObject target, ArrayList<Bullet> bulletList) {
		super(pos, new Vector2f(13f, 13f), speed, maxHealth, target);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.shape = new Ellipse(super.getPosition().x,super.getPosition().y,
				super.getDimentions().x, super.getDimentions().y);
		this.bulletList = bulletList;
		super.proximityDistance = PROXIMITY;
		//-----------
		m = new MultiBar(this, true);	
		//-----------
	}
	
	public Quiz(Vector2f pos, float maxHealth, DynamicGameObject target, ArrayList<Bullet> bulletList) {
		super(pos, new Vector2f(13f, 13f), 4f, maxHealth, target);
		super.getStats().setFireRate(FIRE_RATE);
		super.getStats().setBulletSpeed(BULLET_SPEED);
		super.getStats().setBulletDamage(BULLET_DAMAGE);
		super.shape = new Ellipse(super.getPosition().x,super.getPosition().y,
				super.getDimentions().x, super.getDimentions().y);
		this.bulletList = bulletList;
		super.proximityDistance = PROXIMITY;
		//-----------
		m = new MultiBar(this, true);	
		//-----------
	}

	@Override
	public Bullet shoot(Vector2f target) {
		if (System.currentTimeMillis() >= nextTimeToShoot)
		{
			nextTimeToShoot = (long)super.getStats().getFireRate() + System.currentTimeMillis();
			Vector2f currentPos = new Vector2f(super.getPosition());
			Bullet bullet = new Bullet (currentPos, target, super.getStats().getBulletSpeed(),
					super.getStats().getBulletDamage(), true);
			return bullet;
		}
		return null;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Quiz", this.getPosition().x - 3*4.6f ,
				this.getPosition().y + this.getDimentions().y );
		
		g.setColor(new Color(0.9f,0.64f, 0.4f));
		super.draw(g);
		//-----------
		m.draw(g);
		//-----------
	}

	@Override
	void update() 
	{
		super.update();
		
		Vector2f targetPos = new Vector2f(super.target.getPosition());
		Bullet bullet = shoot(targetPos);
		if (bullet!=null)
			bulletList.add(bullet);
		
		//-----------
		m.update();
		//-----------
	}
}
