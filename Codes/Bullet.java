package deneme;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Bullet extends DynamicGameObject implements Drawable{
	
	private float damage;
	private Vector2f target;
	Vector2f velocity;
	
	public Bullet(Vector2f pos, Vector2f target, float speed, float damage)
	{
		super(pos, new Vector2f(15f, 15f), speed);
		this.target = target;
		this.damage = damage;
		velocity = ((target.sub(super.getPosition())).normalise()).scale(speed);

		//super.setSpeed( speed );
		//super.setSpeed( (target.distance(pos))*speed );
		//System.out.print("Bullet");
	}
	
	@Override
	void update() 
	{
		move();
	}
	
	public void draw(Graphics g) 
	{
		g.setColor(Color.red);
		
		//getting the object drawn from the center
		//System.out.println((int)(super.pos.getX()- (super.dimentions.getX()/2)));
		g.fillOval((int)(super.getPosition().getX() - ((super.getDimentions()).getX())/2),
					(int)(super.getPosition().getY()- (super.getDimentions().getY()/2)),
					(int)super.getDimentions().getX(),
					(int)super.getDimentions().getY());		
	}
	
	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	@Override
	void move() {
		super.setPosition((super.getPosition().add(velocity)));
	}

}
