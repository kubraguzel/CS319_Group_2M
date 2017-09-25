import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameObject implements Drawable {
	
	Vector2 velocity;
	float speed;
	
	public Bullet(Vector2 pos, Vector2 dim, Vector2 velocity)
	{
		super(pos, dim);
		this.velocity= velocity;
	}
	
	public Bullet(Vector2 pos, Vector2 target, float speed)
	{
		super(pos, new Vector2(15f, 15f));
		this.velocity= (target.difference(pos).normalized()).multiply(speed);
		System.out.print("Bullet");
	}
	@Override
	void update() 
	{
		super.pos.add(velocity);
	}
	@Override
	public void draw(Graphics2D g) 
	{
		g.setColor(Color.RED);
		
		//getting the object drawn from the center
		//System.out.println((int)(super.pos.getX()- (super.dimentions.getX()/2)));
		g.fillOval((int)(super.pos.getX()- (super.dimentions.getX()/2)),
				(int)(super.pos.getY()- (super.dimentions.getY()/2)),
				(int)super.dimentions.getX(),
				(int)super.dimentions.getY());		
	}

}
