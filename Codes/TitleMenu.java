import java.awt.Font;
import java.util.jar.Attributes.Name;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TitleMenu extends BasicGameState {
	//public String playerName;
	
	Image playButton;
	Image exitButton;
	Image background;
	TextField nameField;
	private boolean flag = false;
	private GameMaster gm ;

	public TitleMenu(GameMaster gm) 
	{
		this.gm=gm;
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException 
	{
		playButton = new Image("res/Buttons/PlayButton.png");
		playButton = playButton.getScaledCopy(0.6f);
		exitButton = new Image("res/Buttons/ExitButton.png");
		exitButton = exitButton.getScaledCopy(0.6f);
		
		background = new Image ("res/BilkentVector2.jpg");
		background = background.getScaledCopy(0.6f);
		
		nameField = new TextField(container, new TrueTypeFont (new Font("arial", Font.BOLD, 55), false),
				(int)(container.getScreenWidth()/2 - 250f),
				(int)(1.3f*container.getScreenHeight()/3)-2, 500, 70);
		nameField.setFocus(true);
		nameField.setBorderColor(new Color (0.15f, 0.15f, 0.15f));
		nameField.setBackgroundColor(new Color (0f, 0.65f, 0.9f));
		nameField.setTextColor(new Color (0.15f, 0.15f, 0.15f));
		nameField.setCursorVisible(false);
		nameField.setMaxLength(15);
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException 
	{	
		background.draw();
		g.drawString("Welcome To Bilkent \nThis will be your end...", container.getScreenWidth()/2 - 80f, container.getScreenHeight()/3);
		playButton.draw(container.getScreenWidth()/2 - playButton.getWidth()/2, 3*container.getScreenHeight()/5- playButton.getHeight()/2);
		exitButton.draw(container.getScreenWidth()/2-exitButton.getWidth()/2, 3*container.getScreenHeight()/4- exitButton.getHeight()/2);
		nameField.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException 
	{
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();
		
		if(((mouseX > container.getScreenWidth()/2 - playButton.getWidth()/2)
						&& (mouseX < container.getScreenWidth()/2 + playButton.getWidth()/2)) 
				&& ((mouseY > 2*container.getScreenHeight()/5- playButton.getHeight()/2)
						&&(mouseY < 2*container.getScreenHeight()/5+ playButton.getHeight()/2)))
		{
			playButton.setImageColor(1f, 1f, 1f, 1f);
			if(Mouse.isButtonDown(0))
			{
				sbg.enterState(1);
				gm.setPlayerName(nameField.getText());
			}
		}
		else
		{
			playButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
		}
		
		if(((mouseX > container.getScreenWidth()/2 - exitButton.getWidth()/2)
				&& (mouseX < container.getScreenWidth()/2 + exitButton.getWidth()/2)) 
		&& ((mouseY > container.getScreenHeight()/4 - exitButton.getHeight()/2)
				&&(mouseY < container.getScreenHeight()/4 + exitButton.getHeight()/2)))
		{
			exitButton.setImageColor(1f, 1f, 1f, 1f);
			if(Mouse.isButtonDown(0))
				System.exit(0);
		}
		else
		{
			exitButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
