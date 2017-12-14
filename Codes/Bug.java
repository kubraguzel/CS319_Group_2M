/**
 * 
 * Author:Alper Þahýstan
 * 
 */
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
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
		super(pos, new Vector2f(20f,20f), speed, maxHealth, bodyDamage, target);
	}
	
	public Bug(Vector2f pos, float maxHealth, float bodyDamage, DynamicGameObject target)
	{
		super(pos, new Vector2f(20f,20f), 3.2f, maxHealth, bodyDamage, target);
	}
	public Bug(Vector2f pos, float speed, float maxHealth,
			float bodyDamage, DynamicGameObject target)
	{
		super(pos, new Vector2f(20f,20f), speed, maxHealth, bodyDamage, target);
	}


	@Override
	public void draw(Graphics g) {
		g.setColor(Color.green);
		super.draw(g);
	}
	
	
	
}
