package deneme;

import org.newdawn.slick.geom.Vector2f;

public class BouncyBullet extends Bullet implements Drawable{

	private int numOfBounces = 0;
	private final int NUM_OF_MAX_BOUNCES = 10;
	Vector2f velocity2;
	
	public BouncyBullet(Vector2f pos, Vector2f target, float speed, float damage) {
		super(pos, target, speed, damage);
		velocity2 = super.velocity;
		//this.numOfBounces = numOfBounces;
	}
	
	void bounce()
	{
		System.out.println("++++X++++"+super.getPosition().getX()+"+++++++++");
		System.out.println("++++Y++++"+super.getPosition().getY()+"+++++++++");
		if((super.getPosition().getX()<0 || super.getPosition().getX()>800f) && canBounce()){
			velocity2.set(-super.velocity.x, super.velocity.y);
			numOfBounces++;
			System.out.println("***"+super.velocity.x+"***");
			System.out.println("***"+super.velocity.x+"***");
			System.out.println(numOfBounces+"-----(1)");
		}
		else if((super.getPosition().getY()<0 || super.getPosition().getY()>600f) && canBounce()){
			velocity2.set(super.velocity.x, -super.velocity.y);
			numOfBounces++;
			System.out.println("***"+super.velocity.x+"***");
			System.out.println("***"+super.velocity.x+"***");
			System.out.println(numOfBounces+"-----(2)");
		}
		super.setPosition((super.getPosition().add(velocity2)));
	}
	
	boolean canBounce(){
		return (NUM_OF_MAX_BOUNCES >= numOfBounces);
	}
	
	@Override
	void update() 
	{
		bounce();
	}
}
