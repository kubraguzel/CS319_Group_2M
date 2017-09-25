import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameObject implements Drawable {
	
	
	public Bullet(Vector2 pos, Vector2 dim, Vector2 velocity)
	{
		super(pos, dim, velocity);
	}
	
	public Bullet(Vector2 pos, Vector2 target, float speed)
	{
		super(pos, new Vector2(15f, 15f));
		super.setVelocity( (target.difference(pos).normalized()).multiply(speed));
		//System.out.print("Bullet");
	}
	@Override
	void update() 
	{
		Vector2 v = super.getPos();
		v.add(super.getVelocity());
		super.setPos(v);
	}
	@Override
	public void draw(Graphics2D g) 
	{
		g.setColor(Color.RED);
		
		//getting the object drawn from the center
		//System.out.println((int)(super.pos.getX()- (super.dimentions.getX()/2)));
		g.fillOval((int)(super.getPos().getX() - ((super.getDimentions()).getX())/2),
				(int)(super.getPos().getY()- (super.getDimentions().getY()/2)),
				(int)super.getDimentions().getX(),
				(int)super.getDimentions().getY());		
	}

}
