import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CreditsMenu extends BasicGameState {
	
	private Image backButton;

	public CreditsMenu() 
	{
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException 
	{
		backButton = new Image ("res/back.png");
		backButton = backButton.getScaledCopy(0.2f);
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException 
	{
		g.setColor(Color.white);
		g.setBackground(Color.black);
		backButton.draw(20f, container.getScreenHeight()- backButton.getHeight() - 20f);
		
		g.setFont(new TrueTypeFont (new Font("Pixeled Regular", Font.PLAIN, 35), true));
		g.setColor(Color.orange);
		g.drawString("CREDITS", container.getScreenWidth()/2 - 200f, container.getScreenHeight()/10);
		g.setColor(new Color (0.3f, 0.4f, 0.6f));
		g.setFont(new TrueTypeFont (new Font("Pixeled Regular", Font.PLAIN, 30), true));
		g.drawString("KUBRA NUR GUZEL", 30f, 3*container.getScreenHeight()/10);
		g.drawString("SEMIH TEKER", 450f, 4*container.getScreenHeight()/10);
		g.drawString("PELIN ELBIN GUNAY", 800f, 5*container.getScreenHeight()/10);
		g.drawString("ALPER SAHISTAN", 1270f, 6*container.getScreenHeight()/10);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException 
	{
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();
		
		if(((mouseX > 20f)
				&& (mouseX < 20f + backButton.getWidth())) 
				&&(mouseY < backButton.getHeight() + 20f
						&&(mouseY >20f )))
		{
			if (Mouse.isButtonDown(0))
			{
				sbg.enterState(0);
			}
			backButton.setImageColor(1f, 1f, 1f);
			
		}
		else
			backButton.setImageColor(0.5f, 0.5f, 0.5f);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
