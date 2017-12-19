import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Enemy extends DynamicGameObject {
	
	Stats enemyStats;
	DynamicGameObject target;
	protected boolean stay;
	protected float proximityDistance = 0f;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//*************************ST**************************
	int numOfLayer;
	//*************************ST**************************
	
	public Enemy(Vector2f pos, Vector2f dim, float speed, float maxHealth, float bodyDamage, DynamicGameObject target)
	{
		super(pos, dim, speed);
		enemyStats = new Stats(maxHealth, maxHealth);
		enemyStats.setBodyDamage(bodyDamage);
		this.findTarget(target);
		shape= new Rectangle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()), (super.getDimentions().getY()));
		super.setScreenWidth((float) screenSize.getWidth()); 
		super.setscreenHeight((float)screenSize.getHeight());
		//*************************ST**************************
		numOfLayer = 2;
		//*************************ST**************************		
	}
	
	public Enemy(Vector2f pos, Vector2f dim, float speed, float maxHealth, DynamicGameObject target)
	{
		super(pos, dim, speed);
		enemyStats = new Stats(maxHealth, maxHealth);
		this.target = target;
		enemyStats.setBodyDamage(5f);
		shape= new Rectangle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()), (super.getDimentions().getY()));
		super.setScreenWidth((float) screenSize.getWidth()); 
		super.setscreenHeight((float)screenSize.getHeight());
		//*************************ST**************************
		numOfLayer = 2;
		//*************************ST**************************
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
		g.fill(shape);
		g.setColor(Color.black);
		//g.drawString("", super.getPosition().getX()-super.getDimentions().getX()/2, super.getPosition().getY());
	}

	@Override
	public void move()
	{
		if (target != null && 
				this.getPosition().distance(target.getPosition())>= proximityDistance)
		{
			Vector2f targetVector = new Vector2f(target.getPosition());
			targetVector.sub(getPosition()).normalise();
			targetVector.scale(super.getSpeed());
			targetVector.add(super.getPosition());
			
			super.setPosition(new Vector2f (clamp(0+this.getDimentions().getX()/2, 
					screenWidth-this.getDimentions().getX()/2, targetVector.getX()), 
					clamp(0+this.getDimentions().getY()/2, screenHeight- this.getDimentions().getY()/2, targetVector.getY())));
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
