import org.newdawn.slick.geom.Vector2f;

public class Quiz extends Enemy implements Shooter {

	public Quiz(Vector2f pos, Vector2f dim, float speed, float maxHealth, float bodyDamage, DynamicGameObject target) {
		super(pos, dim, speed, maxHealth, bodyDamage, target);
		// TODO Auto-generated constructor stub
	}

	public Quiz(Vector2f pos, Vector2f dim, float speed, float maxHealth, DynamicGameObject target) {
		super(pos, dim, speed, maxHealth, target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Bullet shoot(Vector2f target) {
		// TODO Auto-generated method stub
		return null;
	}

}
