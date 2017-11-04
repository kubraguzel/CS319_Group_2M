/**
 * 
 * Author:Alper Þahýstan
 * 
 */
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class TestScreen extends BasicGame{
	Player player;
	ArrayList<Bullet> bulletList;
	ArrayList<Enemy> enemyList;
	
	public static int FPS = 60;
	
	public static Color BACKGROUND = Color.gray;
	
	public TestScreen(String title)
	{
		super(title);
	}
	
	public void handleCollisions()
	{
		handleEnemyBulletCollisions();
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
					enemyList.get(i).setStay(true);
				}
				else
					enemyList.get(i).setStay(false);
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
				System.out.println(enemyList.get(i).getStats().getBodyDamage());
				enemyList.get(i).takeDamage(player.getStats().getBodyDamage());
			}
		}
		
		handleRemovals((ArrayList)enemyList);
	}

	private void handleEnemyBulletCollisions() 
	{
		for (int i = 0; i< bulletList.size(); i++)
		{
			for (int j = 0; j< enemyList.size(); j++)
			{
				if(bulletList.get(i).collides(enemyList.get(j)) && !bulletList.get(i).isEnemyBullet())
				{
					enemyList.get(j).takeDamage(bulletList.get(i).getDamage());
					bulletList.get(i).setToBeRemoved(true);
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

	private void manageInput(GameContainer container)
	{
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
	      
	      if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
		  {
	    	  Bullet bullet = player.shoot(new Vector2f(input.getMouseX(), input.getMouseY()));
	    	  if (bullet != null)
	    		  bulletList.add(bullet);
		  }
	    	  
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		player = new Player (new Vector2f(30f, 30f), new Vector2f(30f, 30f), 
				3f, 100f, 6f, 10f, 
				200f, 0f, container.getWidth(), 
				container.getHeight());
		bulletList = new ArrayList<Bullet>();
		enemyList = new ArrayList<Enemy>();
		enemyList.add(new Enemy(new Vector2f(300f, 30f), new Vector2f(30f, 30f), 
				4f, 100f, 100f, 3f, player));
		enemyList.add(new Enemy(new Vector2f(400f, 30f), new Vector2f(30f, 30f), 
				4f, 100f, 100f, 3f, player));
	}
	
	@Override
	public void update(GameContainer container, int arg2) throws SlickException 
	{
		manageInput(container);
		player.update();
		for (int i =0; i < bulletList.size(); i++)
			bulletList.get(i).update();
		
		for (int i =0; i < enemyList.size(); i++)
			enemyList.get(i).update();
		
			//System.out.println(player.getStats().getCurHealth());
		/*if(player.collides(enemy))
			System.out.println("Wow");*/
		//System.out.println(player.getPosition().getX() + ", " + player.getPosition().getY());
		//enemy.findTarget(player);
		handleCollisions();
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException 
	{
		g.setBackground(BACKGROUND);
		player.draw(g);
		for (int i =0; i < bulletList.size(); i++)
			bulletList.get(i).draw(g);
		for (int i =0; i < enemyList.size(); i++)
			enemyList.get(i).draw(g);
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new TestScreen ("Test"));
		
		app.setDisplayMode(1080, 720, false);
		app.setTargetFrameRate(FPS);
		app.start();
		
	}

}
