package deneme;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Bug extends Enemy 
{
	private final float BODY_DAMAGE = 7f;
	//-----------
	MultiBar m;
	//-----------
	
	public Bug(Vector2f pos, Vector2f dim, float speed, float maxHealth, 
			float bodyDamage, DynamicGameObject target)
	{
		super(pos, dim, speed, maxHealth, bodyDamage, target);
		//-----------
		m = new MultiBar(this, true);
		//-----------
	}
	
	public Bug(Vector2f pos, float speed, float maxHealth, float curHealth, 
			float bodyDamage, DynamicGameObject target)
	{
		super(pos, new Vector2f(20f,20f), speed, maxHealth, bodyDamage, target);
		//-----------
		m = new MultiBar(this, true);
		//-----------
	}
	
	public Bug(Vector2f pos, float maxHealth, float bodyDamage, DynamicGameObject target)
	{
		super(pos, new Vector2f(20f,20f), 3.2f, maxHealth, bodyDamage, target);
		//-----------
		m = new MultiBar(this, true);
		//-----------
	}
	public Bug(Vector2f pos, float speed, float maxHealth,
			float bodyDamage, DynamicGameObject target)
	{
		super(pos, new Vector2f(20f,20f), speed, maxHealth, bodyDamage, target);
		//-----------
		m = new MultiBar(this, true);
		//-----------
	}
	
	public Bug(Vector2f pos, float maxHealth, DynamicGameObject target)
	{
		super(pos, new Vector2f(20f,20f), 3.2f, maxHealth, target);
		super.getStats().setBodyDamage(BODY_DAMAGE);
		//-----------
		m = new MultiBar(this, true);
		//-----------
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Bug", this.getPosition().x - 3*4.6f ,
						this.getPosition().y + this.getDimentions().y);
		
		g.setColor(new Color(0.09f, 0.47f, 0.16f));
		super.draw(g);
		//-----------
		m.draw(g);
		//-----------
	}
	
	@Override
	void update() {
		super.update();
		//-----------
		m.update();
		//-----------
	}

}