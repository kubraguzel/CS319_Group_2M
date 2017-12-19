import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class UpgradeScreen extends BasicGameState {

	private boolean success;
	private float alphaS;
	private float alphaF;
	private ArrayList<Item> inv;

	public UpgradeScreen() {
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException 
	{
		inv = Player.getPlayer().getInventory().getItemList();
		Player.getPlayer().getInventory().setNumOfKeys(5);
		Player.getPlayer().getInventory().setNumOfFreshmenChests(6);
		Player.getPlayer().getInventory().setNumOfSeniorChests(2);
		Player.getPlayer().getInventory().addItem(new Prozac());
	}

	@Override
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.setFont(new TrueTypeFont (new Font("Pixeled Regular", Font.PLAIN, 20), true));
		g.drawString("Press 1 to open Freshmen Chest (cost 1 key)", container.getScreenWidth()/2- 250f, 10*container.getScreenHeight()/15f);
		g.drawString("Press 2 to open Sophomore Chest (cost 2 keys)", container.getScreenWidth()/2- 250f, 8*container.getScreenHeight()/15f);
		g.drawString("Press 3 to open Junior Chest (cost 3 keys)", container.getScreenWidth()/2- 250f, 6*container.getScreenHeight()/15f);
		g.drawString("Press 4 to open Senior Chest (cost 4 keys)", container.getScreenWidth()/2- 250f, 4*container.getScreenHeight()/15f);
		g.setColor(Color.orange);
		g.drawString("Press Enter when you are done", container.getScreenWidth()/2- 250f, 12*container.getScreenHeight()/15f);
		
		g.drawString("Inventory:",50f, 3*container.getScreenHeight()/15f);
		
		g.setColor(new Color (0f, 1f, 0f, alphaS));
		g.drawString("Success! Chest has opened.", 50f, 2*container.getScreenHeight()/15f);
		
		g.setColor(new Color (1f, 0f, 0f, alphaF));
		g.drawString("Failed to open the chest", 50f, 2*container.getScreenHeight()/15f);
		
		g.setColor(new Color(0.3f, 0.84f, 0.46f));
		
		
		
		for (int i =0; i < inv.size(); i++)
		{
			g.setFont(new TrueTypeFont (new Font("Pixeled Regular", Font.PLAIN, 15), true));
			g.drawString( "- [ " + inv.get(i).name +" ]", 60f, 8 + (3+ (i+1.7f))*container.getScreenHeight()/15f);
			g.drawString(inv.get(i).description, 60f, (3+ (i+2.2f))*container.getScreenHeight()/15f);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2) throws SlickException 
	{
		inv = Player.getPlayer().getInventory().getItemList();
		
		if(container.getInput().isKeyPressed(Input.KEY_ENTER))
		{
			GameMaster.getGameMaster().reset();
			Player.getPlayer().applyInventory();
			game.enterState(1);
		}
		
		if (alphaS <= 0 && alphaF <= 0 )
		{
			if(container.getInput().isKeyPressed(Input.KEY_1))
			{
				success = Player.getPlayer().getInventory().openChest(0);
				if (success)
				{
					alphaS = 1f;
				}
				else
				{
					alphaF = 1f;
				}
			}
			
			if(container.getInput().isKeyPressed(Input.KEY_2))
			{
				success = Player.getPlayer().getInventory().openChest(1);
				if (success)
				{
					alphaS = 1f;
				}
				else
				{
					alphaF = 1f;
				}
				
			}
			
			if(container.getInput().isKeyPressed(Input.KEY_3))
			{
				success = Player.getPlayer().getInventory().openChest(2);
				if (success)
				{
					alphaS = 1f;
				}
				else
				{
					alphaF = 1f;
				}
				
			}
			
			if(container.getInput().isKeyPressed(Input.KEY_4))
			{
				success = Player.getPlayer().getInventory().openChest(3);
				if (success)
				{
					alphaS = 1f;
				}
				else
				{
					alphaF = 1f;
				}
			}
		}
		success = false;
		
		if (alphaS > 0)
			alphaS = alphaS - 0.17f;
		if (alphaF > 0)
			alphaF = alphaF - 0.17f;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}
