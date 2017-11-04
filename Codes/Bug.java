/**
 * 
 * Author:Alper Þahýstan
 * 
 */
import org.newdawn.slick.geom.Vector2f;

public class Bug extends Enemy 
{
	public Bug(Vector2f pos, Vector2f dim, float speed, float maxHealth, float curHealth, 
			float bodyDamage, DynamicGameObject target)
	{
		super(pos, dim, speed, maxHealth, curHealth, bodyDamage, target);
	}
	
}
