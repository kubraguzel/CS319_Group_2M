import java.awt.Font;
import java.util.jar.Attributes.Name;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TitleMenu extends BasicGameState {
	//public String playerName;
	
	Image playButton;
	Image exitButton;
	Image background;
	TextField nameField;
	boolean flag = true;
	Image logo;

	public TitleMenu(){
		super();
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException 
	{
		logo = new Image("res/logo2.png");
		logo = logo.getScaledCopy(0.4f);
		
		playButton = new Image("res/Buttons/PlayButton.png");
		playButton = playButton.getScaledCopy(0.6f);
		exitButton = new Image("res/Buttons/ExitButton.png");
		exitButton = exitButton.getScaledCopy(0.6f);
		
		background = new Image ("res/BilkentVector4.jpg");
		background = background.getScaledCopy(0.57f);
		
		nameField = new TextField(container, new TrueTypeFont (new Font("arial", Font.BOLD, 50), true),
				(int)(container.getScreenWidth()/2 - 230f),
				(int)(1.3f*container.getScreenHeight()/3) + 10, 470, 65);
		nameField.setFocus(true);
		nameField.setBorderColor(Color.transparent);
		nameField.setBackgroundColor(Color.transparent);
		nameField.setTextColor(new Color (0.15f, 0.15f, 0.6f));
		nameField.setCursorVisible(true);
		nameField.setMaxLength(15);
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException 
	{	
		background.draw();
		
		g.setColor(Color.blue);
		nameField.render(container, g);
		g.setColor(Color.black);
		g.setFont(new TrueTypeFont (new Font("Rockwell Extra Bold", Font.PLAIN, 30), true));
		
		g.drawString("Welcome to Bilkent!", container.getScreenWidth()/2 - 150f, container.getScreenHeight()/3);
		g.drawString("This will be your end...", container.getScreenWidth()/2 - 160f, container.getScreenHeight()/3 + 40f);
		g.drawString("Please Enter your name:", container.getScreenWidth()/2 - 170f, container.getScreenHeight()/3 + 80f);
		
		playButton.draw(container.getScreenWidth()/2 - playButton.getWidth()/2, 3*container.getScreenHeight()/5- playButton.getHeight()/2);
		exitButton.draw(container.getScreenWidth()/2-exitButton.getWidth()/2, 3*container.getScreenHeight()/4- exitButton.getHeight()/2);
		logo.draw(container.getScreenWidth()/2 - logo.getWidth()/2, 1*container.getScreenHeight()/5- logo.getHeight()/2);
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
				Player.getPlayer().setPlayerName(nameField.getText());
				sbg.enterState(1);
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
		
		nameField.setCursorVisible(flag);
		flag = !flag;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
