
//package test;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


/**
* Game class which holds the main menu informations
* 
* @param
* @return 
* This class extends StateBasedGame
*/

public class Game extends StateBasedGame
{

	//keeps the game's name
	public static final String gameName="Survival in Bilkent";

	//control for starting menu
	public static final int startMenu=0;

	//control for worldMap
	public static final int worldMap=1;

	
	/**
	* constructor
	* creates the game/constructor
	* 
	* @param gameName The name of the game
	* @return 
	*/

	public Game(String gameName)
	{

		//upper class
		super(gameName);

		//create main menu
		this.addState(new MainMenuScreen(startMenu));

		//
		this.addState(new WorldMap(worldMap));
	}


	/**
	* initStatesList method
	* It inits the states
	* @param gc The GameContainer object
	* @return 
	*/

	public void initStatesList(GameContainer gc) throws SlickException
	{

		//adds startMenu
		this.getState(startMenu).init(gc,this);

		//adds worldMap
		this.getState(worldMap).init(gc,this);

		// begins with startMenu
		this.enterState(startMenu);

	}


	/**
	* Main method
	* 
	* @param args
	* @return 
	*/

	public static void main(String[] args) throws SlickException
	{
		AppGameContainer agc;

		try
		{

			//creating game
			agc= new AppGameContainer(new Game(gameName));

			//creating game screen's sizes
			agc.setDisplayMode(700, 700, false);

			//opens the main game screen
			agc.start();

		}catch(SlickException e)
		{

			e.printStackTrace();	
		}
	}
	










}