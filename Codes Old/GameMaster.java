
package test2;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import test2.GameMaster;

import org.newdawn.slick.Animation;

import java.awt.Font;
import java.util.jar.Attributes.Name;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.BigImage;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.TextField;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.UnicodeFont;
//import org.newdawn.slick.Color;
public class GameMaster extends BasicGameState
{
	Player player;
	ArrayList<Bullet> bulletList;
	ArrayList<Enemy> enemyList;
	
	
	//control for paused******************************************
	
	public static boolean PAUSED = false;///
	
	
	//*************************ST**************************
	
	Icon key;
	IconXAmount keyXAmount;
	
	public static Color color1 = Color.green;
	public static Color color2 = Color.red;
	
	ArrayList<Layer> layerList;
	private String playerName;
	Image resumeGameButton;
	Image settingsButton;
	Image background;

	
	//*************************ST**************************
	
	public static Color BACKGROUND = Color.gray;
	
	public GameMaster()
	{
		super();
	}
	
	public void handleCollisions()
	{
		handleBulletCollisions();
		if(player!=null)
			handleEnemyPlayerCollisions();
		handleEnemyEnemyCollisions();
	}
	
	
	//To avoid the enemies to go through each other.
	private void handleEnemyEnemyCollisions() 
	{
		int j = 0;
		while(j<enemyList.size())
		{
			for (int i=j+1; i< enemyList.size(); i++)
			{
				if(enemyList.get(i).collides(enemyList.get(j)))
				{
					enemyList.get(i).bounceOff(enemyList.get(j), 
							enemyList.get(i).getDimentions().x/10);
					//enemyList.get(i).setStay(true);
				}
				/*else
					enemyList.get(i).setStay(false);*/
			}
			j++;
		}
	}

	private void handleEnemyPlayerCollisions() 
	{
		for (int i = 0; i< enemyList.size(); i++)
		{
			if(player.collides(enemyList.get(i)))
			{
				player.takeDamage(enemyList.get(i).getStats().getBodyDamage());
				enemyList.get(i).takeDamage(player.getStats().getBodyDamage());
				
				player.bounceOff(enemyList.get(i), 30f);
			}
		}
		
		handleRemovals((ArrayList)enemyList);
	}

	private void handleBulletCollisions() 
	{
		for (int i = 0; i< bulletList.size(); i++)
		{
			Bullet curBullet = bulletList.get(i);
			for (int j = 0; j< enemyList.size(); j++)
			{
				if(curBullet.collides(enemyList.get(j)) && !curBullet.isEnemyBullet())
				{
					enemyList.get(j).takeDamage(curBullet.getDamage());
					curBullet.setToBeRemoved(true);
				}
			}
			
			if (player != null)
			{
				if (curBullet.collides(player) && curBullet.isEnemyBullet())
				{
					player.takeDamage(curBullet.getDamage());
					curBullet.setToBeRemoved(true);
				}
			}
		}
		
		handleRemovals((ArrayList)bulletList);
		handleRemovals((ArrayList)enemyList);
	}

	private void handleRemovals(ArrayList<GameObject> list)
	{
		int size = list.size();
		
		for (int i = 0; i< list.size(); i++)
		{
			if(list.get(i).isToBeRemoved())
			{
				list.remove(i);
				size--;
			}
		}
	}

	private void manageInput(GameContainer container)
	{
		
		Input input = container.getInput();
		
	      //up
	      if(input.isKeyDown(Input.KEY_W))
	      {
	         player.setUp(true);
	      }
	      else
	    	  player.setUp(false);
	      
	      
	      //down
	      if(input.isKeyDown(Input.KEY_S))
	      {
	    	  player.setDown(true);
	      }
	      else
	    	  player.setDown(false);
	      
	      
	      //left
	      if(input.isKeyDown(Input.KEY_A))
	      {
	    	  player.setLeft(true);
	      }
	      else
	    	  player.setLeft(false);
	      
	      
	      //right
	      if(input.isKeyDown(Input.KEY_D))
	      {
	    	  player.setRight(true);
	      }
    	  else
    		  player.setRight(false);
	      
	      
	      if(input.isKeyDown(Input. KEY_ESCAPE))
	      {		
	    	  //System.out.println("try1");
	    	  
	    	  player.setRight(true);
	    	  
	    	//System.out.println("try2");
	    	  PAUSED = true;
	    	  
	    	  //System.out.println("try3");
	      }
	      else
    		  player.setRight(false);
	      
	      
	      
	      //mouse
	      if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
		  {
	    	  Bullet bullet = player.shoot(new Vector2f(input.getMouseX(), input.getMouseY()));
	    	  if (bullet != null)
	    		  bulletList.add(bullet);
		  }	
	      
	      if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))
		  {
	    	  player.setPowerUpActive(!player.isPowerUpActive());
	    		  
		  }	 
	      
	}

	
	@Override
	public void init(GameContainer container,  StateBasedGame game) throws SlickException 
	{
		if (!PAUSED) 
		{
			
		player = new Player (new Vector2f(30f, 30f), new Vector2f(30f, 30f), 
				4f, 100f, 6f, 10f, 
				400f, 0f, container.getWidth(), 
				container.getHeight());
		
		System.out.println("GameMaster: "+ playerName);
		player.setPlayerName(playerName);
		
		bulletList = new ArrayList<Bullet>();
		enemyList = new ArrayList<Enemy>();
		
		
		/*
		Enemy enemy1 = new Enemy(new Vector2f(300f, 300f), new Vector2f(30f, 30f), 
				2f, 100f, 100f, 3f, player);
		enemyList.add(enemy1);
		
		Enemy enemy2 = new Enemy(new Vector2f(900f, 300f), new Vector2f(30f, 30f), 
				2f, 100f, 100f, 3f, player);
		enemyList.add(enemy2);
		*/
		
		
		
		//*************************ST**************************
		
		/*Enemy enemy1 = new Bug(new Vector2f(300f, 300f), 50f, 3f, player);
		enemyList.add(enemy1);*/
		
		Enemy enemy2 = new Quiz(new Vector2f(900f, 300f), 100f, player, bulletList);
		enemyList.add(enemy2);
		
		//System.out.println(enemyList==null);
		
		Enemy enemy3 =new Lab(new Vector2f(400f, 500f), 
				100f, player, enemyList);
		enemyList.add(enemy3);
		
		Enemy enemy4 =new Assignment(new Vector2f(400f, 500f), 1.6f,
				100f, player);
		enemyList.add(enemy4);
		
		Enemy enemy5= new Midterm(new Vector2f(1000f, 600f), 150f, player, bulletList);
		enemyList.add(enemy5);
		Enemy enemy6= new Final(new Vector2f(1200f, 200f), 150f, player, bulletList);
		enemyList.add(enemy6);
		keyXAmount = new IconXAmount(new Vector2f(0f,0f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/keyX.png")), 
								0 ); //it will change with the amount of keys player has
		//*************************ST**************************
		
		
	}
	else
	{
		
		//
		Color trans = new Color(0f,0f,0f,0.5f);
        graphics.setColor(trans);
        //graphics.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);
        
        
        // draw any pause menu buttons.
		
		Animation a=new Animation();
		
		background = new Image ("res/pauseMenuImage.jpg");
		background = background.getScaledCopy(0.6f);
		
		resumeGameButton = new Image("res/Buttons/resumeGameButton.png");
		resumeGameButton = resumeGameButton.getScaledCopy(0.6f);
		
		settingsButton = new Image("res/Buttons/settingsButton.png");
		settingsButton = settingsButton.getScaledCopy(0.6f);
		
		
		//make it unclickable??
		settingsInfoButton = new Image("res/Buttons/settingsInfoButton.png");
		settingsInfoButton = settingsInfoButton.getScaledCopy(0.6f);
		
		a.addFrame("res/pauseMenuImage.jpg", 10);
		a.addFrame("res/Buttons/resumeGameButton.png", 10);
		a.addFrame("res/Buttons/settingsButton.png", 10);
		a.getFrame();
		
		
		a.draw();
		
		//Draw the animation at a specific location
		a.draw(100,100);
		//Draw the animation at a specific location
		a.draw(100,100,a.getFrame());
		//Draw the animation
		a.draw(10,10,5,5);
		//Draw the animation with color
		a.draw(10,10,5,5,trans);
		
		
		
	}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2) throws SlickException 
	{
		
		if (!PAUSED)
		{
			
			if(player !=null)
			{
				manageInput(container);
				player.update();
			}
			for (int i =0; i < bulletList.size(); i++)
			{
				bulletList.get(i).update();
			}
			
			for (int i =0; i < enemyList.size(); i++){
				enemyList.get(i).update();
			}
			
		
		//*************************ST**************************
		// it will change with the if(player collides any key on the game screen)
			
		keyXAmount.update();
		
		handleCollisions();
		
		
		if (player != null && player.getStats().isDead())
			player= null;
		} 
		else
		{
			
			
		// check for unpause button press, etc.
			float mouseX = Mouse.getX();
			float mouseY = Mouse.getY();
			
			if(((mouseX > container.getScreenWidth()/2 - resumeGameButton.getWidth()/2)
							&& (mouseX < container.getScreenWidth()/2 + resumeGameButton.getWidth()/2)) 
					&& ((mouseY > 2*container.getScreenHeight()/5- resumeGameButton.getHeight()/2)
							&&(mouseY < 2*container.getScreenHeight()/5+ resumeGameButton.getHeight()/2)))
			{
				resumeGameButton.setImageColor(1f, 1f, 1f, 1f);
				
				if(Mouse.isButtonDown(0))
				{
					//game.enterState(1);
					//this.setPlayerName(nameField.getText());
					
					BasicGameState prevState = GameMaster.prevState;
					GameMaster.prevState = this;
					game.enterState(prevState.getID());
				}
				
			}
			else
			{
				resumeGameButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
			}
			
			if(((mouseX > container.getScreenWidth()/2 - settingsButton.getWidth()/2)
					&& (mouseX < container.getScreenWidth()/2 + settingsButton.getWidth()/2)) 
			&& ((mouseY > container.getScreenHeight()/4 - settingsButton.getHeight()/2)
					&&(mouseY < container.getScreenHeight()/4 + settingsButton.getHeight()/2)))
			{
				settingsButton.setImageColor(1f, 1f, 1f, 1f);
				if(Mouse.isButtonDown(0))
					//game.enterState(1);
					
				Animation b = new Animation();
				
				background = new Image ("res/settingsMenuImage.jpg");
				background = background.getScaledCopy(0.6f);
				
				
				//textfield 1
				nameField2 = new TextField(container, new TrueTypeFont (new Font("arial", Font.BOLD, 55), false),
						(int)(container.getScreenWidth()/2 - 250f),
						(int)(1.3f*container.getScreenHeight()/3)-2, 500, 70);
				nameField2.setFocus(true);
				nameField2.setBorderColor(new Color (0.15f, 0.15f, 0.15f));
				nameField2.setBackgroundColor(new Color (0f, 0.65f, 0.9f));
				nameField2.setTextColor(new Color (0.15f, 0.15f, 0.15f));
				nameField2.setCursorVisible(false);
				nameField2.setMaxLength(20);
				
				
				//textfield 2
				nameField3 = new TextField(container, new TrueTypeFont (new Font("arial", Font.BOLD, 55), false),
						(int)(container.getScreenWidth()/2 - 250f),
						(int)(1.3f*container.getScreenHeight()/3)-2, 500, 70);
				nameField3.setFocus(true);
				nameField3.setBorderColor(new Color (0.15f, 0.15f, 0.15f));
				nameField3.setBackgroundColor(new Color (0f, 0.65f, 0.9f));
				nameField3.setTextColor(new Color (0.15f, 0.15f, 0.15f));
				nameField3.setCursorVisible(false);
				nameField3.setMaxLength(15);
				
				/*resumeGameButton = new Image("res/Buttons/resumeGameButton.png");
				resumeGameButton = resumeGameButton.getScaledCopy(0.6f);
				settingsButton = new Image("res/Buttons/settingsButton.png");
				settingsButton = settingsButton.getScaledCopy(0.6f);
				*/
				
				b.addFrame(nameField2);
				//b.addFrame("res/Buttons/resumeGameButton.png", 10);
			//	a.addFrame("res/Buttons/settingsButton.png", 10);
				b.getFrame();
				
				
				/////////////////////////////////draw deneme2
				
				b.draw();
				System.out.println("try");
				
				//Draw the animation at a specific location
				b.draw(100,100);
				System.out.println("try1");
				
				//Draw the animation at a specific location
				b.draw(100,100,a.getFrame());
				System.out.println("try2");
				
				//Draw the animation
				b.draw(10,10,5,5);
				System.out.println("try3");
				//Draw the animation with color
				b.draw(10,10,5,5,trans);
				System.out.println("try4");
				
				/////////////////////////////////draw deneme2
				
			}
			else
			{
				
				settingsButton.setImageColor(0.8f, 0.8f, 0.8f, 1f);
			}
			
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		if (!PAUSED) 
		{
		g.setBackground(BACKGROUND);
		if(player !=null)
		{
			player.draw(g);
		}
		if(player == null)
		{
			g.drawString("GAME OVER!", container.getWidth()/2, container.getHeight()/2);
		}
		for (int i =0; i < bulletList.size(); i++)
		{
			bulletList.get(i).draw(g);
		}
		for (int i =0; i < enemyList.size(); i++)
		{
			enemyList.get(i).draw(g);
		}
		
		
		//*************************ST**************************
		keyXAmount.draw(g);
		
		//*************************ST**************************
		}
		else
		{

			background.draw();
			
			//g.drawString("Welcome To Bilkent \nThis will be your end...", container.getScreenWidth()/2 - 80f, container.getScreenHeight()/3);
			resumeGameButton.draw(container.getScreenWidth()/2 - resumeGameButton.getWidth()/2, 3*container.getScreenHeight()/5- resumeGameButton.getHeight()/2);
			settingsButton.draw(container.getScreenWidth()/2-settingsButton.getWidth()/2, 3*container.getScreenHeight()/4- settingsButton.getHeight()/2);
			//nameField.render(container, g);
			
			
			////////////////////////////////////draw deneme2
			
			b.draw();
			System.out.println("try");
			
			//Draw the animation at a specific location
			b.draw(100,100);
			System.out.println("try1");
			
			//Draw the animation at a specific location
			b.draw(100,100,a.getFrame());
			System.out.println("try2");
			//Draw the animation
			b.draw(10,10,5,5);
			System.out.println("try3");
			//Draw the animation with color
			b.draw(10,10,5,5,trans);
			System.out.println("try4");
			
			///////////////////////////////////////draw deneme2
		
		}
		
	}
	
	
	@Override
	public int getID()
	{
		return 1;
	}

	public String getPlayerName()
	{
		return playerName;
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;	
	}
	
	
	
	
	
	

}