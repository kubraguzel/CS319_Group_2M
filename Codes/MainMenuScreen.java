
//package test;


import org.newdawn.slick.gui.MouseOverArea;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;



/**
* MainMenuScreen class creates the main  menu screen's components
* @param
* @return 
* This method extends BasicGameState
*/

public class MainMenuScreen extends BasicGameState
{

	Image world;
	
	/**
	* MainMenuScreen constructor
	* 
	* @param startmenu
	* @return 
	*/
	
	public MainMenuScreen(int startmenu)
	{



	}
	
	/**
	* init method
	* 
	* @param gc The GameContainer object
	* @param sbg  The StateBasedGame object
	* @return 
	* This method throws SlickException
	*/
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException
			{

		world= new Image("libraries/resim.jpg");
	}

	
	/**
	* render method draws the buttons and screen
	* 
	* @param gc The GameContainer object
	* @param sbg  The StateBasedGame object
	* @param g The Graphics object
	* @return 
	* This method throws SlickException
	*/
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException 
			{

		//designate the game screen's sizes
		world.draw(0,0,700,700);

		//designate the play button's location
		g.drawString("Play", 500, 400);	
		
		//designate the exit button's location
		g.drawString("Exit", 500, 500);	
		
	}
	

	/**
	* update method
	* 
	* @param gc The GameContainer object
	* @param sbg  The StateBasedGame object
	* @param delta
	* @return 
	* This method throws SlickException
	*/

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException 
			{

	//getting the mouse's x coordinate
	int posX = Mouse.getX();

	//getting the mouse's y coordinate
	int posY = Mouse.getY();

	//System.out.println("x:"+posX+"y:"+posY);



	//play button
	//coordinate the play button with locations
	if((posX>500 && posX<541)&&(posY>278&& posY<300))
	{

		//player press the play button
		if(Mouse.isButtonDown(0))
		{

			//stateBasedGame keeps the game plays
			sbg.enterState(1);
		}
	}


	//exit button
	//coordinate the exit button with locations
	if((posX>500 && posX<541)&&(posY>180&& posY<195))
	{

		//player press the exit button
		if(Mouse.isButtonDown(0))

		{
			//stateBasedGame exits the game screen
			//stops the game
			System.exit(0);
		}

	}

	}
	

	/**
	* getId method
	* 
	* @param
	* @return 0
	*/
	
	public int getID()
	{
		return 0;
	}
	








}