import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseMenu extends BasicGameState {
	
	private Image resumeButton;
	private Image exitButton;
	private Image soundButton;
	private Image musicButton;
	
	private boolean mousePressed;

	public PauseMenu() 
	{
		
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException 
	{
		resumeButton = new Image("res/Buttons/ResumeButton.png");
		resumeButton = resumeButton.getScaledCopy(0.7f);
		
		exitButton =new Image("res/Buttons/ExitButton.png");
		exitButton = exitButton.getScaledCopy(0.35f);
		
		soundButton = new Image("res/Buttons/soundbutton.png");
		soundButton = soundButton.getScaledCopy(0.7f);
		
		musicButton = new Image("res/Buttons/musicbutton.png");
		musicButton = musicButton.getScaledCopy(0.9f);
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.orange);
		g.setFont(new TrueTypeFont (new Font("Pixeled Regular", Font.PLAIN, 30), true));
		g.drawString("PAUSED!", container.getScreenWidth()/2-90f, container.getScreenHeight()/10);
		
		resumeButton.draw(container.getScreenWidth()/2- resumeButton.getWidth()/2,
				3f*container.getScreenHeight()/10, new Color(0.8f, 0.8f, 0.8f, 1f));
		exitButton.draw(container.getScreenWidth()/2- exitButton.getWidth()/2,
				5f*container.getScreenHeight()/10, new Color(0.8f, 0.8f, 0.8f, 1f));
		
		soundButton.draw(container.getScreenWidth()/2 - 1.6f*soundButton.getWidth(),
				7f*container.getScreenHeight()/10);
		
		if(!container.isSoundOn())
		{
			g.setColor(Color.white);
			g.setLineWidth(10f);
			g.drawLine(container.getScreenWidth()/2 - 0.5f*soundButton.getWidth(), 
					7f*container.getScreenHeight()/10 - soundButton.getHeight()/5, 
					container.getScreenWidth()/2 - 1.6f*soundButton.getWidth(), 
					7f*container.getScreenHeight()/10 + soundButton.getHeight());
		}
		
		musicButton.draw(container.getScreenWidth()/2 + 0.8f* musicButton.getWidth(),
				7.2f*container.getScreenHeight()/10);
		
		if(!container.isMusicOn())
		{
			g.setColor(Color.white);
			g.setLineWidth(10f);
			g.drawLine(container.getScreenWidth()/2+ 1.9f*musicButton.getWidth(), 
					7f*container.getScreenHeight()/10 - musicButton.getHeight()/5, 
					container.getScreenWidth()/2 + 0.8f*musicButton.getWidth(), 
					7f*container.getScreenHeight()/10 + musicButton.getHeight());
		}
		

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2) throws SlickException 
	{
		float x = Mouse.getX();
		float y = Mouse.getY();
		
		if(((x <= container.getScreenWidth()/2+ resumeButton.getWidth()/2)
				&&(x >= container.getScreenWidth()/2- resumeButton.getWidth()/2))
				&& ((y >= 7f*container.getScreenHeight()/10 - resumeButton.getHeight()) 
						&&(y <= 7f*container.getScreenHeight()/10)))
		{
			resumeButton.setImageColor(1f, 1f, 1f,1f);
			if(Mouse.isButtonDown(0))
			{
				game.enterState(1);
			}
		}
		else
		{
			resumeButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
		}
		
		if(((x <= container.getScreenWidth()/2+ exitButton.getWidth()/2)
				&&(x >= container.getScreenWidth()/2- exitButton.getWidth()/2))
				&& ((y >= 5f*container.getScreenHeight()/10 - exitButton.getHeight()) 
						&&(y <= 5f*container.getScreenHeight()/10)))
		{
			exitButton.setImageColor(1f, 1f, 1f,1f);
			if(Mouse.isButtonDown(0))
			{
				System.exit(0);
			}
		}
		else
		{
			exitButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
		}
		
		if(((x >= container.getScreenWidth()/2 - 1.7f*soundButton.getWidth())
				&& x <= container.getScreenWidth()/2 - 0.5f*soundButton.getWidth())
				&&(( y >=  3f*container.getScreenHeight()/10 - soundButton.getHeight()) 
						&& (y <=  3f*container.getScreenHeight()/10)))
		{
			soundButton.setImageColor(1f, 1f, 1f, 1f);
			if(Mouse.isButtonDown(0) && !mousePressed)
			{
				mousePressed = true;
				container.setSoundOn(!container.isSoundOn());
			}
		}
		else
		{
			soundButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
		}
		
		if(((x >= container.getScreenWidth()/2 + 0.8f* musicButton.getWidth())
				&& x <= container.getScreenWidth()/2 + 2f*musicButton.getWidth())
				&&(( y >=  2.8f*container.getScreenHeight()/10 - musicButton.getHeight()) 
						&& (y <=  2.8f*container.getScreenHeight()/10)))
		{
			musicButton.setImageColor(1f, 1f, 1f, 1f);
			if(Mouse.isButtonDown(0) && !mousePressed)
			{
				mousePressed = true;
				container.setMusicOn(!container.isMusicOn());
			}
		}
		else
		{
			musicButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
		}
		
		if(!Mouse.isButtonDown(0))
			mousePressed = false;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
