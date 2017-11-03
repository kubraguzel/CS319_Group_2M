import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class testScreen extends BasicGame{
	
	public testScreen(String title)
	{
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(GameContainer container, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawOval(20f, 50f, 40f, 60f);
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new testScreen ("Test"));
		
		app.setDisplayMode(800, 600, false);
		app.start();
		
	}

}