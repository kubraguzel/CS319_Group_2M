
public class Stats 
{
	private float maxHealth;
	private float curHealth;
	private boolean dead= false;
	
	//private float speed;

	//Constructor
	public Stats(float maxHealth, float curHealth)
	{
		this.maxHealth= maxHealth;
		this.curHealth= curHealth;
		//this.speed= speed;
	}

	public float getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}

	public float getCurHealth() {
		return curHealth;
	}

	public void setCurHealth(float curHealth) {
		this.curHealth = curHealth;
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public void takeDamage(float dmg)
	{
		this.setCurHealth(this.getCurHealth()-dmg);
		
		if(!(this.getCurHealth()>0))
		{
			this.setDead(true);
			//System.out.println("Enemy is killed!");
		}
	}

	/*public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}*/
	
	
}
