import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends GameObject implements Drawable
{
	Stats enemyStats;
	Bar healthBar;
	GameObject target;
	
	public Enemy(Vector2 pos, Vector2 dim, float maxHealth, float curHealth, Vector2 velocity, GameObject target)
	{
		super(pos, dim, velocity);
		enemyStats = new Stats(maxHealth, curHealth);
		this.findTarget(target);
		Vector2 hbPos = super.getPos().difference(new Vector2(0f, getDimentions().getY() + 5f));
		hbPos.setX(hbPos.getX() - super.getDimentions().getX());
		healthBar = new Bar(hbPos, super.getPos().getX()*2, 3f);
		System.out.println("Enemy Spawned" + super.getPos().getX()+ " " +super.getPos().getY());
	}
	
	public Enemy(Vector2 pos, Vector2 dim, float maxHealth, float curHealth, Vector2 velocity)
	{
		super(pos, dim, velocity);
		enemyStats = new Stats(maxHealth, curHealth);
	}
	
	public boolean findTarget(GameObject target)
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
			System.out.println("Enemy is killed!");
	}
	
	@Override
	void update() 
	{
		if (target != null)
		{
			Vector2 targetVector = (target.getPos().difference(super.getPos())).normalized();
			targetVector.multiply(super.getVelocity().getX());
			targetVector.add(super.getPos());
			super.setPos(targetVector);
			Vector2 hbPos = super.getPos().difference(new Vector2(0f, getDimentions().getY() + 5f));
			hbPos.setX(hbPos.getX() - super.getDimentions().getX());
			healthBar.setPos(hbPos);
			healthBar.setPercentage((enemyStats.getCurHealth()/enemyStats.getMaxHealth()));
			
		}
		
		
	}

	@Override
	public void draw(Graphics2D g) 
	{
		g.setColor(new Color(0, 255, 100));
		
		//getting the object drawn from the center
		//System.out.println((int)(super.pos.getX()- (super.dimentions.getX()/2)));
		g.fillRect((int)(super.getPos().getX() - ((super.getDimentions()).getX())/2),
				(int)(super.getPos().getY()- (super.getDimentions().getY()/2)),
				(int)super.getDimentions().getX(),
				(int)super.getDimentions().getY());
		//System.out.println("Enemy is drawn");
		healthBar.draw(g);
	}

}
