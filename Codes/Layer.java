
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
	float rate;
	Enemy enemy;
	Player player;
	boolean isPlayer = false;
	
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
	
	public Layer(Color color, float percentage, Player player, boolean boxLayer) {
		position =  new Vector2f(1050f, 550f);
		dimension = new Vector2f(5f,-5f);
		this.color = color;
		//this.color2 = color2;
		this.percentage = percentage;
		this.player = player;
		this.boxLayer = boxLayer;
		isPlayer = true;
		horizontal = false;
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
				g.setColor(color);
				g.fillRect(position.getX(),position.getY(), (float) dimension.getX()*percentage, dimension.getY());
			}
		}
		else{
			if(boxLayer){
				g.setColor(color);
				g.fillRect(position.getX(),position.getY(), dimension.getX(), (float) dimension.getY()*100f);
			}
			else{
				g.setColor(color);
				g.fillRect(position.getX(),position.getY(), dimension.getX(), (float) dimension.getY()*percentage);
			}
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
		
		if(isPlayer){
			rate = (player.playerStats.getCurHealth()/player.playerStats.getMaxHealth())*100;
			this.percentage = rate;
		}
		else{
			rate = (enemy.enemyStats.getCurHealth()/enemy.enemyStats.getMaxHealth())*100;
			if(rate>50){
				this.percentage = ((rate-50)/50)*100;
			}
			else{
				this.percentage = (rate/50)*100;
			}
		}

		if(this.percentage == 0){
			setToBeRemoved(true);
		}
	}

	@Override
	public void update() 
	{
		setPercentage();
		if(isPlayer){
			if(player.isToBeRemoved()){
				setToBeRemoved(true);
			}
		}
		else{
			if(enemy.isToBeRemoved()){
				setToBeRemoved(true);
			}
			else{
				move();
			}
		}
	}
}