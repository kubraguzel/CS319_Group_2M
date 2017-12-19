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
	private long nextTimeToSpawn = System.currentTimeMillis();
	private ArrayList<Enemy> enemyList;
	private final float SPAWN_RATE = 3000f;
	
	//-----------
	MultiBar m;
	//-----------
	
	
	public Lab(Vector2f pos, Vector2f dim, float speed, float maxHealth, DynamicGameObject target, ArrayList<Enemy> enemyList) {
		super(pos, dim, speed, maxHealth, target);
		super.shape = new Rectangle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()), (super.getDimentions().getY()));
		this.enemyList = enemyList;
		super.proximityDistance = 500f;
		super.getStats().setFireRate(SPAWN_RATE);
		//-----------
		m = new MultiBar(this, true);
		//-----------
	}
	
	public Lab(Vector2f pos, float speed, float maxHealth, DynamicGameObject target, ArrayList<Enemy> enemyList) {
		super(pos, new Vector2f(70f,50f) , speed, maxHealth, target);
		super.shape = new Rectangle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()), (super.getDimentions().getY()));
		this.enemyList = enemyList;
		super.proximityDistance = 500f;
		super.getStats().setFireRate(SPAWN_RATE);
		//-----------
		m = new MultiBar(this, true);	
		//-----------
	}
	
	public Lab(Vector2f pos, float maxHealth, DynamicGameObject target, ArrayList<Enemy> enemyList) {
		super(pos, new Vector2f(70f,50f) , 1.5f, maxHealth, target);
		super.shape = new Rectangle(super.getPosition().getX(), super.getPosition().getY(), 
				(super.getDimentions().getX()), (super.getDimentions().getY()));
		this.enemyList = enemyList;
		super.proximityDistance = 500f;
		super.getStats().setFireRate(SPAWN_RATE);
		//-----------
		m = new MultiBar(this, true);	
		//-----------
	}
	
	public void spawnBug()
	{
			
		Vector2f spawnPos= new Vector2f(this.getPosition());
		spawnPos.sub(target.getPosition());
		spawnPos.normalise();
		spawnPos.scale(-this.getDimentions().getX());
		spawnPos.add(this.getPosition());
		
		Bug bug = new Bug(spawnPos, 30, target);
		enemyList.add(bug);			
	}
	
	@Override
	public void draw(Graphics g) 
	{
		g.setColor(new Color(150, 255, 150));
		super.draw(g);
		
		g.setColor(Color.black);
		g.drawString("Lab", this.getPosition().x - 3*4.6f ,
					this.getPosition().y + this.getDimentions().y -5f);
		//-----------
		m.draw(g);
		//-----------
	}
	
	@Override
	void update() 
	{
		super.update();

		if (System.currentTimeMillis() >= nextTimeToSpawn)
		{
			nextTimeToSpawn = (long)super.getStats().getFireRate() + System.currentTimeMillis();
			spawnBug();
		}
		//-----------
		m.update();
		//-----------
	}
	
	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
	
}
