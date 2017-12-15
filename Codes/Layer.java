import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Layer extends UIObject{
	Vector2f position;
	Vector2f dimension;
	public Color color1;
	public Color color2;
	boolean horizontal = true;
	float percentage;
	Enemy enemy;
	
	public Layer(Vector2f position, Vector2f dimension, Color color1, Color color2,  float percentage) {
		super(position, dimension);
		this.color1 = color1;
		this.color2 = color2;	
		this.percentage = percentage;
		// TODO Auto-generated constructor stub
	}
	
	public Layer(Color color1, Color color2,  float percentage, Enemy enemy) {
		position = enemy.getPosition().add(new Vector2f(-15f, -15f));
		dimension = new Vector2f(1f,1f); 
		this.color1 = color1;
		this.color2 = color2;
		this.percentage = percentage;
		this.enemy = enemy;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw(Graphics g) 
	{
		g.setColor(color1);
		g.fillRect(position.getX(), position.getY(), dimension.getX(), dimension.getY());
		g.setColor(color2);
		
		if(horizontal){
			g.fillRect(position.getX(),position.getY(), (float) dimension.getX()*percentage, dimension.getY());
		}
		else{
			g.fillRect(position.getX(),position.getY(), dimension.getX(), (float) dimension.getY()*percentage);
		}
	}
	
	public void move() {
		if (enemy != null){
			setPosition(new Vector2f(enemy.getPosition().getX()-50f, enemy.getPosition().getY()-20f));
		}
		else
			this.setToBeRemoved(true);
	}

	public void setPosition(Vector2f pos) {
		this.position = pos;
		// TODO Auto-generated method stub
		
	}
	
	public void setPercentage(){
		this.percentage = enemy.enemyStats.getCurHealth();
	}

	public void update() 
	{
		setPercentage();
		if(enemy.isToBeRemoved())
			setToBeRemoved(true);
		else
			move();
	}
}