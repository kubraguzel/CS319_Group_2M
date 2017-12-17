import java.awt.Container;
import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverScreen extends BasicGameState {
	private Sound gameOver;
	private long time = System.currentTimeMillis();
	private float alpha1 =0f;
	private boolean flag = true;
	private float alpha2 = 0f;

	public GameOverScreen() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException 
	{
		gameOver = new Sound("res/Sound/Gameoversound.wav");
		alpha2 = 0f;
		alpha1 =0f;
		flag = true;
		time = System.currentTimeMillis();
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException 
	{
		g.setFont(new TrueTypeFont (new Font("Pixeled Regular", Font.PLAIN, 30), true));
		g.setColor(new Color(1f, 1f, 1f, alpha1));
		alpha1 = alpha1 + 0.02f;
		g.drawString("GAME",
				container.getScreenWidth()/2 - 170f, container.getScreenHeight()/2);
		g.setColor(new Color(1f, 1f, 1f, alpha2));
		if(alpha1>=1f)
			alpha2 = 0.02f + alpha2;
		g.drawString("OVER",
				container.getScreenWidth()/2 , container.getScreenHeight()/2);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException 
	{
		if(flag)
		{
			gameOver.play();
			flag =false;
		}
		
		if(alpha2 > 1f)
		{
			sbg.getState(0).init(container, sbg);
			sbg.enterState(0);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 5;
	}

}
