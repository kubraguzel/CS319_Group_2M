/**
 * 
 * Author: Semih Teker
 * 
 */

public abstract class Bonus extends GameObject implements Collectable {

	boolean pickedUp;
	
	public Bonus() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collect() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void update(){
		
	}

}