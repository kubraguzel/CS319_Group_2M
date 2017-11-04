
public class Stats 
{
	private float maxHealth;
	private float curHealth;
	
	private boolean dead= false;
	
	private float fireRate;
	private float bulletSpeed;
	private float bulletDamage;

	//Constructors
	//For objects that don't shoot
	public Stats(float maxHealth, float curHealth)
	{
		this.maxHealth= maxHealth;
		this.curHealth= curHealth;
		this.bulletSpeed = 0;
		this.fireRate = 0;
		this.bulletDamage = 0;
	}
	
	public Stats(float maxHealth, float bulletSpeed, float bulletDamage, float fireRate)
	{
		this.maxHealth= maxHealth;
		this.curHealth= maxHealth;
		this.bulletSpeed = bulletSpeed;
		this.fireRate = fireRate;
		this.bulletDamage = bulletDamage;
	}
	
	public Stats(float maxHealth, float curHealth, float bulletSpeed, float bulletDamage, float fireRate)
	{
		this.maxHealth= maxHealth;
		this.curHealth= curHealth;
		this.bulletSpeed = bulletSpeed;
		this.fireRate = fireRate;
		this.bulletDamage = bulletDamage;
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
	
	public float getFireRate() {
		return fireRate;
	}

	public void setFireRate(float fireRate) {
		this.fireRate = fireRate;
	}

	public float getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(float bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}
	
	
	public float getBulletDamage() {
		return bulletDamage;
	}

	public void setBulletDamage(float bulletDamage) {
		this.bulletDamage = bulletDamage;
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
}
