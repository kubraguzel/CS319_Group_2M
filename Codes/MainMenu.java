


package test;



//importing slick library

import org.newdawn.slick.gui.MouseOverArea;

//importing mouse input

import org.lwjgl.input.Mouse;

//importing slick.GameContainer

import org.newdawn.slick.GameContainer;

//importing slick.Graphics

import org.newdawn.slick.Graphics;

//importing slick.Imagery

import org.newdawn.slick.Image;

//importing slick.SlickException

import org.newdawn.slick.SlickException;


//importing sstate.BasicGameState

import org.newdawn.slick.state.BasicGameState;

//importing state.StateBasedGame

import org.newdawn.slick.state.StateBasedGame;

//importing slick library

import org.newdawn.slick.*;

//importing slick.state

import org.newdawn.slick.state.*;






//MainMenu class

//this class extends BasicGameState class


public class MainMenu extends BasicGameState 

{


	//creating Image

	Image world;


	//MainMenu method

	public MainMenu(int startmenu)

	{



	}


	//init method 

	//this method throws SlickException

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException

	{

		//assigning the image

		world= new Image("libraries/resim.jpg");

	}



	//render method 

	//this method throws SlickException


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException 

	{


		//conforms the window's sizes

		world.draw(0,0,700,700);


		//conforms the play button's sizes

		g.drawString("Play", 500, 400);	


		//conforms the exit button's sizes

		g.drawString("Exit", 500, 500);	
		


	}



	//update method

	//this method throws SlickException

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException 

	{


	//getting position of x

	int posX = Mouse.getX();


	//getting position of x

	int posY = Mouse.getY();



	//System.out.println("x:"+posX+"y:"+posY);



	//play button

	if((posX>500 && posX<541)&&(posY>278&& posY<300))

	{

		//if mouse is clicked

		if(Mouse.isButtonDown(0))

		{


			//activate the play button

			sbg.enterState(1);

		}

	}


	//exit button

	if((posX>500 && posX<541)&&(posY>180&& posY<195))

	{
	

		//if mouse is clicked

		if(Mouse.isButtonDown(0))

		{

			//activate the exit button

			System.exit(0);


		}

	}


	}




	//getId method

	public int getID() 

	{

		return 0;

	}



	
}