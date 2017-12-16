package deneme;

/**
 * 
 * Author: Semih Teker
 * 
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class IconXAmount extends UIObject{
	Icon icon;
	int amount = 0; 
	Vector2f position;
	Vector2f dimension;
	
	public IconXAmount(Vector2f position, Vector2f dimension, Icon icon, int amount ) {
		this.position = position;
		this.dimension = dimension;
		//super(position, dimension);
		this.icon = icon;
		this.amount = amount;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(){
		
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.drawImage(icon.getImage(), position.x, position.y, Color.gray); // 25*90 pixels at x=0 and y=0
		g.drawString(amount + "", position.x+60, 3); // draw at x=60 and y=3
	}
	
	public Icon getIcon(){
		return icon;
	}
	
	public int getAmount(){
		return amount;
	}
}