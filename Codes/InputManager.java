import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;

public class InputManager {

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int input, char arg1) {
		if(input (Input.KEY_W)){
	         player.setUp(true);
	      }
	      else
	    	  player.setUp(false);
	      //down
	      if(input.isKeyDown(Input.KEY_S)){
	    	  player.setDown(true);
	      }
	      else
	    	  player.setDown(false);
	      //left
	      if(input.isKeyDown(Input.KEY_A)){
	    	  player.setLeft(true);
	      }
	      else
	    	  player.setLeft(false);
	      //right
	      if(input.isKeyDown(Input.KEY_D)){
	    	  player.setRight(true);
	      }
   	  else
   		  player.setRight(false);

	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub

	}

}
