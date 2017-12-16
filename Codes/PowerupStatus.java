
import org.newdawn.slick.geom.Vector2f;

public class PowerupStatus extends UIObject{
	
	Icon icon;
	boolean activated;
	boolean deployed;
	String powerUpName; 
	
	public PowerupStatus(Vector2f position, Vector2f dimension,
			Icon icon, String powerUpName) {
		super(position, dimension);
		this.icon = icon;
		this.powerUpName = powerUpName;
		// TODO Auto-generated constructor stub
	}
	
	public boolean activate(){
		return false;
		
	}
	
	public boolean deploy(){
		return false;
		
	}
}