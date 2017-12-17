import java.awt.Font;
import java.util.jar.Attributes.Name;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TitleMenu extends BasicGameState {
	//public String playerName;
	
	Image playButton;
	Image exitButton;
	Image creditsButton;
	Image background;
	TextField nameField;
	boolean flag = true;
	Image logo;
	
	private long nextBlinkTime = 0;
	private final float BLINK_DURATION = 300f;
	private float alpha;
	
	Music music;

	public TitleMenu(){
		super();
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException 
	{
		music = new Music("res/Music/title.wav");
		music.loop();
		music.setVolume(0.5f);
		
		logo = new Image("res/logo2.png");
		logo = logo.getScaledCopy(0.4f);
		logo.setAlpha(alpha);
		
		playButton = new Image("res/Buttons/PlayButton.png");
		playButton = playButton.getScaledCopy(0.25f);
		creditsButton = new Image("res/Buttons/CreditsButton.png");
		creditsButton = creditsButton.getScaledCopy(0.25f);
		exitButton = new Image("res/Buttons/ExitButton.png");
		exitButton = exitButton.getScaledCopy(0.25f);
		
		background = new Image ("res/BilkentVector4.jpg");
		background = background.getScaledCopy(0.57f);
		
		nameField = new TextField(container, new TrueTypeFont (new Font("Pixeled Regular", Font.BOLD, 24), true),
				(int)(container.getScreenWidth()/2 - 230f),
				(int)(1.4f*container.getScreenHeight()/3) + 10, 470, 60);
		nameField.setFocus(true);
		nameField.setBorderColor(Color.transparent);
		nameField.setBackgroundColor(new Color(0.9f, 0.9f, 0.9f, 1f));
		nameField.setTextColor(Color.cyan);
		nameField.setCursorVisible(true);
		nameField.setMaxLength(25);
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException 
	{	
		background.draw();
		g.setColor(Color.black);
		g.setFont(new TrueTypeFont (new Font("Pixeled Regular", Font.PLAIN, 20), true));
		
		g.drawString("Welcome to Bilkent!", container.getScreenWidth()/2- 161f, container.getScreenHeight()/3);
		g.drawString("This will be your end...", container.getScreenWidth()/2- 175f , container.getScreenHeight()/3 + 40f);
		
		g.setFont(new TrueTypeFont (new Font("Pixeled Regular", Font.PLAIN, 21), true));
		g.drawString("ENTER YOUR NAME:", container.getScreenWidth()/2 -150f, container.getScreenHeight()/3 + 85f);
		
		playButton.draw(container.getScreenWidth()/2 - playButton.getWidth()/2, 3*container.getScreenHeight()/5- playButton.getHeight()/2);
		creditsButton.draw(container.getScreenWidth()/2 - creditsButton.getWidth()/2, 7.5f*container.getScreenHeight()/10- creditsButton.getHeight()/2);
		exitButton.draw(container.getScreenWidth()/2-exitButton.getWidth()/2, 9*container.getScreenHeight()/10- exitButton.getHeight()/2);
		logo.draw(container.getScreenWidth()/2 - logo.getWidth()/2, 1*container.getScreenHeight()/5- logo.getHeight()/2);
		
		g = new Graphics();
		nameField.setBorderColor(Color.transparent);
		nameField.setBackgroundColor(new Color(0.5f, 0.5f, 0.5f, 0.8f));
		nameField.setTextColor(Color.black);
		nameField.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException 
	{
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();
		
		if(alpha < 1f)
		{
			alpha = alpha + 0.03f;
			logo.setAlpha(alpha);
		}
		
		/*play button*/
		if(((mouseX > container.getScreenWidth()/2 - playButton.getWidth()/2)
						&& (mouseX < container.getScreenWidth()/2 + playButton.getWidth()/2)) 
				&& ((mouseY > 2*container.getScreenHeight()/5- playButton.getHeight()/2)
						&&(mouseY < 2*container.getScreenHeight()/5+ playButton.getHeight()/2)))
		{
			playButton.setImageColor(1f, 1f, 1f, 1f);
			if(Mouse.isButtonDown(0))
			{
				Player.getPlayer().setPlayerName(nameField.getText());
				sbg.getState(1).init(container, sbg);
				sbg.enterState(1);
			}
		}
		else
		{
			playButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
		}
		/*play button*/
		/*credits button*/
		if(((mouseX > container.getScreenWidth()/2 - creditsButton.getWidth()/2)
				&& (mouseX < container.getScreenWidth()/2 + creditsButton.getWidth()/2)) 
		&& ((mouseY > 2.5f*container.getScreenHeight()/10- creditsButton.getHeight()/2)
				&&(mouseY < 2.5f*container.getScreenHeight()/10+ creditsButton.getHeight()/2)))
		{
			creditsButton.setImageColor(1f, 1f, 1f, 1f);
			if(Mouse.isButtonDown(0))
			{
				sbg.enterState(2);
			}
		}
		else
		{
			creditsButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
		}
		/*credits button*/
		/*exit button*/
		if(((mouseX > container.getScreenWidth()/2 - exitButton.getWidth()/2)
				&& (mouseX < container.getScreenWidth()/2 + exitButton.getWidth()/2)) 
		&& ((mouseY > container.getScreenHeight()/10 - exitButton.getHeight()/2)
				&&(mouseY < container.getScreenHeight()/10 + exitButton.getHeight()/2)))
		{
			exitButton.setImageColor(1f, 1f, 1f, 1f);
			if(Mouse.isButtonDown(0))
				System.exit(0);
		}
		else
		{
			exitButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
		}
		/*exit button*/
		
		//cursorBliker
		nameField.setCursorVisible(flag);
		if(System.currentTimeMillis() > nextBlinkTime )
		{
			nextBlinkTime =System.currentTimeMillis() + (long) BLINK_DURATION;
			flag = !flag;
		}
		//CursorBlinker
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
