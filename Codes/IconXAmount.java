import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class IconXAmount extends UIObject{
	Icon icon;
	int amount = 0; 
		
	public IconXAmount(Vector2f position, Vector2f dimension, Icon icon, int amount ) {
		super(position, dimension);
		this.icon = icon;
		this.amount = amount;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(){
		amount++;
	}
	
	@Override
	public void draw(Graphics g){
		g.drawImage(icon.getImage(), 0, 0, Color.yellow); // 25*80 pixels at x=0 and y=0
		g.drawString(amount+"", 60, 3); // draw at x=60 and y=3
	}
	
	public Icon getIcon(){
		return icon;
	}
	
	public int getAmount(){
		return amount;
	}
}