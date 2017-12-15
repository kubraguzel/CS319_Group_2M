import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class GameMaster extends BasicGame{
	Player player;
	ArrayList<Bullet> bulletList;
	ArrayList<Enemy> enemyList;
	
	
	//*************************ST**************************
	Icon key;
	IconXAmount keyXAmount;
	
	public static Color color1 = Color.green;
	public static Color color2 = Color.red;
	
	ArrayList<Layer> layerList;
	
	//*************************ST**************************
	
	
	public static int FPS = 60;
	
	public static Color BACKGROUND = Color.gray;
	
	public GameMaster(String title)
	{
		super(title);
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

	private void handleRemovals(ArrayList<GameObject> list) {
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

	private void manageInput(GameContainer container) {
		
		Input input = container.getInput();
		
	      //up
	      if(input.isKeyDown(Input.KEY_W)){
	         player.setUp(true);
	      }
	      else
	    	  player.setUp(false);
	      
	      //down
	      if(input.isKeyDown(Input.KEY_S)){
	    	  player.setDown(true);
	      }
	      else
	    	  player.setDown(false);
	      
	      //left
	      if(input.isKeyDown(Input.KEY_A)){
	    	  player.setLeft(true);
	      }
	      else
	    	  player.setLeft(false);
	      
	      //right
	      if(input.isKeyDown(Input.KEY_D)){
	    	  player.setRight(true);
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
	public void init(GameContainer container) throws SlickException {
		player = new Player (new Vector2f(30f, 30f), new Vector2f(30f, 30f), 
				4f, 100f, 6f, 10f, 
				400f, 0f, container.getWidth(), 
				container.getHeight());
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
	
	@Override
	public void update(GameContainer container, int arg2) throws SlickException 
	{
		if(player !=null)
		{
			manageInput(container);
			player.update();
		}
		for (int i =0; i < bulletList.size(); i++)
			bulletList.get(i).update();
		
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

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException 
	{
		g.setBackground(BACKGROUND);
		if(player !=null)
			player.draw(g);
		if(player == null)
			g.drawString("GAME OVER!", container.getWidth()/2, container.getHeight()/2);
		for (int i =0; i < bulletList.size(); i++)
			bulletList.get(i).draw(g);
		for (int i =0; i < enemyList.size(); i++)
			enemyList.get(i).draw(g);
		
		
		//*************************ST**************************
		keyXAmount.draw(g);
		
		//*************************ST**************************
	}
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new GameMaster ("Survival in Bilkentv0.1 beta"));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		app.setDisplayMode((int)width, (int)height, false);
		app.setTargetFrameRate(FPS);
		app.start();
	}
}