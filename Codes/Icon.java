import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Icon extends UIObject{
	Image icon;
	static Vector2f position;
	static Vector2f dimension;
	
	public Icon(Image icon) {
		super(position, dimension);
		this.icon = icon;
		// TODO Auto-generated constructor stub
	}
	
	public Icon(Vector2f position, Vector2f dimension, Image icon) {
		super(position, dimension);
		this.icon = icon;
		// TODO Auto-generated constructor stub
	}
	
	public Image getImage(){
		return icon;
	}
}