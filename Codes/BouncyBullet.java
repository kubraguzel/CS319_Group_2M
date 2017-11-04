package deneme;

import org.newdawn.slick.geom.Vector2f;

public class BouncyBullet extends Bullet implements Drawable{

	private int numOfBounces = 0;
	private final int NUM_OF_MAX_BOUNCES = 3;
	//private Vector2f velocity2;
	
	public BouncyBullet(Vector2f pos, Vector2f target, float speed, float damage, float width, float height) {
		super(pos, target, speed, damage);
		super.setScreenWidth(width);
		super.setscreenHeight(height);
	}
	
	@Override
	public void move()
	{
		super.setPosition((super.getPosition().add(velocity)));
		if(super.getPosition().getX()<0 || super.getPosition().getX()>super.screenWidth)
			bounce(false);
		else if(super.getPosition().getY()<0 || super.getPosition().getY()>super.screenHeight)
			bounce(true);
	}
	private void bounce (boolean hitToAYBoundary)
	{
		if((!hitToAYBoundary) && canBounce()){
			velocity.set(-super.velocity.x, super.velocity.y);
			numOfBounces++;
		}
		else if(hitToAYBoundary && canBounce()){
			velocity.set(super.velocity.x, -super.velocity.y);
			numOfBounces++;
		}
	}
	
	private boolean canBounce(){
		return (NUM_OF_MAX_BOUNCES >= numOfBounces);
	}
}