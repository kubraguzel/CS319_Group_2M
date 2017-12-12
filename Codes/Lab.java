/**
 * 
 * Author:Alper Þahýstan
 * 
 */
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Lab extends Enemy 
{
	private float spawnRate =3000f;
	private long nextTimeToSpawn = System.currentTimeMillis();
	private ArrayList<Enemy> enemyList;
	
	
	public Lab(Vector2f pos, Vector2f dim, float speed, float maxHealth, DynamicGameObject target, ArrayList<Enemy> enemyList) {
		super(pos, dim, speed, maxHealth, target);
		super.shape = new Rectangle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()), (super.getDimentions().getY()));
		this.enemyList = enemyList;
		super.proximityDistance = 500f;
	}
	
	public Lab(Vector2f pos, float speed, float maxHealth, DynamicGameObject target, ArrayList<Enemy> enemyList) {
		super(pos, new Vector2f(70f,50f) , speed, maxHealth, target);
		super.shape = new Rectangle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()), (super.getDimentions().getY()));
		this.enemyList = enemyList;
		super.proximityDistance = 500f;
	}
	
	public Lab(Vector2f pos, float maxHealth, DynamicGameObject target, ArrayList<Enemy> enemyList) {
		super(pos, new Vector2f(70f,50f) , 1.5f, maxHealth, target);
		super.shape = new Rectangle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()), (super.getDimentions().getY()));
		this.enemyList = enemyList;
		super.proximityDistance = 500f;
	}
	
	public void spawnBug()
	{
			
		Vector2f spawnPos= new Vector2f(this.getPosition());
		spawnPos.sub(target.getPosition());
		spawnPos.normalise();
		spawnPos.scale(-this.getDimentions().getX());
		spawnPos.add(this.getPosition());
		
		Bug bug = new Bug(spawnPos, 2.5f, 100, 100, 3f, target);
		
		enemyList.add(bug);
	}
	
	/*public void move()
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
	}*/
	
	@Override
	public void draw(Graphics g) 
	{
		g.setColor(new Color(150, 255, 150));
		g.fill(shape);
		//g.drawString("", super.getPosition().getX()-super.getDimentions().getX()/2, super.getPosition().getY());
	}
	
	@Override
	void update() 
	{
		shape.setCenterX(getPosition().getX()); 
		shape.setCenterY(getPosition().getY());
		
		if(!stay)
			move();
		

		if (System.currentTimeMillis() >= nextTimeToSpawn)
		{
			nextTimeToSpawn = (long)spawnRate + System.currentTimeMillis();
			spawnBug();
			
		}
	}
	
	public float getSpawnRate() {
		return spawnRate;
	}
	
	public void setSpawnRate(float spawnRate) {
		this.spawnRate = spawnRate;
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
	
}

