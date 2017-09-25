
public class Stats 
{
	private float maxHealth;
	private float curHealth;
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

	/*public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}*/
	
	
}
