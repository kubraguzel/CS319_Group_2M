import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

public class Assignment extends Enemy {
	
	private final float BODY_DAMAGE = 12f;
	private final float[] points = {20f,0f,40f,20f,20f,40f,0f,20f};
	
	private boolean shieldUp = true;
	private final float DOWN_TIME = 3000f;
	private long nextTimetoShieldUp =0;
	private Polygon shield = makeDiamond(super.getDimentions().x + 5f);
	private int hitCounter =0;
	private final int SHIELD_STRENGHT = 3;
	//private final float SPEED = 8f;

	public Assignment(Vector2f pos, Vector2f dim, float speed, float maxHealth, float bodyDamage,
			DynamicGameObject target) {
		super(pos, dim, speed, maxHealth, bodyDamage, target);
		super.getStats().setFireRate(DOWN_TIME);//firerate is repurposed to act as a shield down time
		super.shape = makeDiamond(super.getDimentions().x);
	}

	public Assignment(Vector2f pos, Vector2f dim, float speed, float maxHealth, DynamicGameObject target) {
		super(pos, dim, speed, maxHealth, target);
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setFireRate(DOWN_TIME);//firerate is repurposed to act as a shield down time
		super.shape = makeDiamond(super.getDimentions().x);
	}
	
	public Assignment(Vector2f pos, float speed, float maxHealth, DynamicGameObject target) {
		super(pos, new Vector2f(25f, 25f), speed, maxHealth, target);
		super.getStats().setBodyDamage(BODY_DAMAGE);
		super.getStats().setFireRate(DOWN_TIME);//firerate is repurposed to act as a shield down time
		super.shape = makeDiamond(super.getDimentions().x);
	}
	
	private Polygon makeDiamond(float scale)
	{
		float[] points = {scale,0,2*scale,scale,scale,2*scale,0,scale};
		Polygon poly = new Polygon(points);
		return poly;
	}
	private void lowerShield()
	{
		shieldUp=false;
		nextTimetoShieldUp = System.currentTimeMillis() 
				+ (long)super.getStats().getFireRate();//firerate is repurposed to act as a shield down time
	}

	@Override
	public void takeDamage(float dmg) {
		if(!shieldUp)
			super.takeDamage(dmg);
		else
		{
			hitCounter++;
			if(hitCounter >= SHIELD_STRENGHT)
			{
				hitCounter=0;
				lowerShield();
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		if(shieldUp)
		{
			g.setColor(Color.cyan);
			g.draw(shield);
		}
		
		g.setColor(Color.white);
		super.draw(g);
	}

	@Override
	void update() {
		shield.setCenterX(getPosition().getX()); 
		shield.setCenterY(getPosition().getY());
		if(!shieldUp && System.currentTimeMillis() >= nextTimetoShieldUp)
			shieldUp=true;
		super.update();
	}
	
	
	
	
	
	
	

}
