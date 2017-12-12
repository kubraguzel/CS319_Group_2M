package deneme;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Layer extends UIObject{
	Vector2f position;
	Vector2f dimension;
	public Color color;
	//public Color color2;
	boolean boxLayer;
	boolean horizontal = true;
	float percentage;
	Enemy enemy;
	
	public Layer(Vector2f position, Vector2f dimension, Color color,  float percentage) {
		super(position, dimension);
		this.color = color;
		//this.color2 = color2;	
		this.percentage = percentage;
	}
	
	public Layer(Color color, float percentage, Enemy enemy, boolean boxLayer) {
		position = enemy.getPosition().add(new Vector2f(-15f, -15f));
		dimension = new Vector2f(1f,1f); 
		this.color = color;
		//this.color2 = color2;
		this.percentage = percentage;
		this.enemy = enemy;
		this.boxLayer = boxLayer;
	}
	
	@Override
	public void draw(Graphics g) 
	{
		if(horizontal){
			if(boxLayer){
				g.setColor(color);
				g.fillRect(position.getX(), position.getY(), dimension.getX()*100f, dimension.getY());
			}
			else{
				if(percentage>50){
					g.setColor(color);
					g.fillRect(position.getX()+50,position.getY(), (float) dimension.getX()*percentage/2, dimension.getY());
				}
				else{
					g.setColor(color);
					g.fillRect(position.getX(),position.getY(), (float) dimension.getX()*percentage, dimension.getY());
				}
			}
		}
		else{
			g.setColor(color);
			g.fillRect(position.getX(),position.getY(), dimension.getX(), (float) dimension.getY()*percentage);
		}
	}
	
	public void move() {
		if (enemy != null){
			setPosition(new Vector2f(enemy.getPosition().getX()-50f, enemy.getPosition().getY()-20f));
		}
		else{
			this.setToBeRemoved(true);
		}
	}

	public void setPosition(Vector2f pos) {
		this.position = pos;		
	}
	
	public void setPercentage(){
		this.percentage = (enemy.enemyStats.getCurHealth()/enemy.enemyStats.getMaxHealth())*100;
	}

	@Override
	public void update() 
	{
		setPercentage();
		if(enemy.isToBeRemoved()){
			setToBeRemoved(true);
		}
		else{
			move();
		}
	}
}