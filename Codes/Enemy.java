/**
 * 
 * Author:Alper Þahýstan
 * 
 */
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Enemy extends DynamicGameObject {
	
	Stats enemyStats;
	DynamicGameObject target;
	protected boolean stay;
	
	public Enemy(Vector2f pos, Vector2f dim, float speed, float maxHealth, float curHealth, float bodyDamage, DynamicGameObject target)
	{
		super(pos, dim, speed);
		enemyStats = new Stats(maxHealth, curHealth);
		enemyStats.setBodyDamage(bodyDamage);
		this.findTarget(target);
		shape= new Rectangle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()), (super.getDimentions().getY()));
	}
	
	public Enemy(Vector2f pos, Vector2f dim, float speed, float maxHealth, DynamicGameObject target)
	{
		super(pos, dim, speed);
		enemyStats = new Stats(maxHealth, maxHealth);
		this.target = target;
		shape= new Rectangle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()), (super.getDimentions().getY()));
	}
	
	public boolean findTarget(DynamicGameObject target)
	{
		this.target = target;
		if(this.target!= null)
			return true;
		return false;
	}
	
	public void takeDamage(float dmg)
	{
		this.enemyStats.takeDamage(dmg);
		
		if(!(this.enemyStats.getCurHealth()>0))
			setToBeRemoved(true);
	}

	@Override
	public void draw(Graphics g) 
	{
		g.setColor(new Color(0, 255, 100));
		
		//getting the object drawn from the center
		//System.out.println((int)(super.pos.getX()- (super.dimentions.getX()/2)));
		g.fill(shape);
		g.setColor(Color.black);
		//g.drawString("", super.getPosition().getX()-super.getDimentions().getX()/2, super.getPosition().getY());
	}

	@Override
	void move() {
		if (target != null)
		{
			Vector2f targetVector = new Vector2f(target.getPosition());
			targetVector.sub(getPosition()).normalise();
			targetVector.scale(super.getSpeed());
			super.setPosition(super.getPosition().add(targetVector));
		}
	}

	@Override
	void update() 
	{
		shape.setCenterX(getPosition().getX()); 
		shape.setCenterY(getPosition().getY());
		if(!stay)
			move();
	}

	public Stats getStats() {
		return enemyStats;
	}

	public boolean isStay() {
		return stay;
	}

	public void setStay(boolean stay) {
		this.stay = stay;
	}
	

	/*@Override
	boolean collides(GameObject other) {
		// TODO Auto-generated method stub
		return false;
	}*/

}
