
/**
 * 
 * Author: Semih Teker
 * 
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Icon extends UIObject{
	Image icon;
	static Vector2f position;
	static Vector2f dimension;
	
	public Icon(Image icon) {
		super(position, dimension);
		this.icon = icon;
	}
	
	/*public Icon(Vector2f position, Vector2f dimension, Image icon) {
		//super(position, dimension);
		this.position = position;
		this.dimension = dimension;
		this.icon = icon;
	}*/
	
	public Icon(Vector2f position, Image icon) {
		//super(position, dimension);
		this.position = position;
		//this.dimension = dimension;
		this.icon = icon;
	}
	
	public Image getImage(){
		return icon;
	}
	
	@Override
	public void update(){
		
	}
	
	@Override
	public void draw(Graphics g){
		icon.draw(this.position.x, this.position.y);
		//g.drawImage(icon, this.position.x, this.position.y, Color.gray); // 8*18 pixels at x=0 and y=0
	}
}