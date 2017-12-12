/**
 * 
 * Author:Alper Þahýstan
 * 
 */
import org.newdawn.slick.geom.Vector2f;

public class Bug extends Enemy 
{
	public Bug(Vector2f pos, Vector2f dim, float speed, float maxHealth, 
			float bodyDamage, DynamicGameObject target)
	{
		super(pos, dim, speed, maxHealth, bodyDamage, target);
	}
	
	public Bug(Vector2f pos, float speed, float maxHealth, float curHealth, 
			float bodyDamage, DynamicGameObject target)
	{
		super(pos, new Vector2f(25f,25f), speed, maxHealth, bodyDamage, target);
	}
	
	public Bug(Vector2f pos, float maxHealth, float bodyDamage, DynamicGameObject target)
	{
		super(pos, new Vector2f(25f,25f), 2.5f, maxHealth, bodyDamage, target);
	}
	
}
