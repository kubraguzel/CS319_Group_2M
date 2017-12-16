package deneme;

import java.awt.Font;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class TestScreen extends BasicGame{
	Player player;
	ArrayList<Bullet> bulletList;
	ArrayList<Enemy> enemyList;

	
	//*************************ST**************************
	//Shape rectangle;
	IconXAmount keyXAmount;
	IconXAmount FreshmenChestXAmount;
	IconXAmount SophomoreChestXAmount;
	IconXAmount JuniorChestXAmount;
	IconXAmount SeniorChestXAmount;
	
	/*public static Color color1 = Color.white;
	public static Color color2 = Color.blue;
	public static Color color3 = Color.red;*/

	ArrayList<MultiBar> multiBarList;
	ArrayList<Key> keyList;
	//*************************ST**************************
	
	
	public static int FPS = 60;
	
	public static Color BACKGROUND = Color.gray;
	
	public TestScreen(String title)
	{
		super(title);
	}
	
	public void handleCollisions()
	{
		handleEnemyBulletCollisions();
		if(player!=null){
			handleEnemyPlayerCollisions();
			//*************************ST**************************
			handleKeyCollisions();
			//*************************ST**************************
		}
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
							enemyList.get(i).getDimentions().x/2);
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
		
		
		
		//*************************ST**************************
		//handleRemovals((ArrayList)layerList);
		handleRemovals((ArrayList)multiBarList);
		//*************************ST**************************
	}
	
	
	
	//*************************ST**************************
	private void handleKeyCollisions()
	{
		for(int i=0; i<keyList.size(); i++){
			if(player.collides(keyList.get(i)))
			{
				player.takeKey();
				keyList.get(i).setToBeRemoved(true);
				keyXAmount.amount++;	
			}
			
		}
		//System.out.println(myKey.isToBeRemoved());
		handleRemovals((ArrayList)keyList);
	}
	//*************************ST**************************
	
	
	
	private void handleRemovals(ArrayList<GameObject> list) {
		int size = list.size();
		for (int i = 0; i< size; i++)
		{
			if(list.get(i).isToBeRemoved())
			{
				list.remove(i);
				size--;
				System.out.println(list);
			}
		}
	}

	private void manageInput(GameContainer container) {
		
		Input input = container.getInput();
		
		//up
		if(input.isKeyDown(Input.KEY_W)){
			player.setUp(true);
		}
		else{
			player.setUp(false);
		}
	  
		//down
		if(input.isKeyDown(Input.KEY_S)){
			player.setDown(true);
		}
		else{
			player.setDown(false);
		}
	  
		//left
		if(input.isKeyDown(Input.KEY_A)){
			player.setLeft(true);
		}
		else{
			player.setLeft(false);
		}
	  
		//right
		if(input.isKeyDown(Input.KEY_D)){
			player.setRight(true);
		}
		else{
			player.setRight(false);
		}
	  
		//mouse
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
		{
			Bullet bullet = player.shoot(new Vector2f(input.getMouseX(), input.getMouseY()));
			if (bullet != null){
				bulletList.add(bullet);
			}
		}	
	  
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))
		{
			player.setPowerUpActive(!player.isPowerUpActive());	  
		}	  
	}

	
	
	//*************************ST**************************
	private void spawnKey(){
		Random rnd = new Random();
		float velX = rnd.nextFloat()*800f;
		float velY = rnd.nextFloat()*600f;
		Vector2f v = new Vector2f(velX, velY);
		Key k;
		try {
			k = new Key(v, 500, 500);
			keyList.add(k);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	//*************************ST**************************
	
	
	
	@Override
	public void init(GameContainer container) throws SlickException 
	{
		player = new Player (new Vector2f(400f, 400f), new Vector2f(30f, 30f), 
				3f, 100f, 6f, 5f, 
				200f, 0f, container.getWidth(), 
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
		//layerList = new ArrayList<Layer>();
		multiBarList = new ArrayList<MultiBar>();
		keyList = new ArrayList<Key>();
		
		multiBarList.add(new MultiBar(player, false)); //not horizontal
		
		//enemy1
		Enemy enemy1 = new Enemy(new Vector2f(300f, 300f), new Vector2f(30f, 30f), 
								2.5f, 100f, 3f, player);
		enemyList.add(enemy1);
		//MultiBar mb1 = new MultiBar(enemy1, true);
		multiBarList.add(new MultiBar(enemy1, true));
		//mb1.addLayer( enemy1);
		//mb1.addLayer( enemy1);
		//mb1.addLayer( enemy1);
/*      layerList.add(new Layer(color1, enemy1.enemyStats.getMaxHealth(), enemy1, true));
		layerList.add(new Layer(color2, enemy1.enemyStats.getCurHealth(), enemy1, false));*/
		
		//enemy2
		Enemy enemy2 = new Enemy(new Vector2f(900f, 300f), new Vector2f(30f, 30f), 
								2.5f, 100f, 3f, player);
		enemyList.add(enemy2);
		//MultiBar mb2 = new MultiBar(enemy2, true);
		multiBarList.add(new MultiBar(enemy2, true));
		//mb2.addLayer( enemy2);
		//mb2.addLayer( enemy2);
		//mb2.addLayer( enemy2);
/*		layerList.add(new Layer(color1, enemy2.enemyStats.getMaxHealth(), enemy2, true));
		layerList.add(new Layer(color2, enemy2.enemyStats.getCurHealth(), enemy2, false));*/
		
		//enemy3
		Enemy enemy3 =new Enemy(new Vector2f(100f, 100f), new Vector2f(30f, 30f),
								0.5f, 100f, 1f, player);
		enemyList.add(enemy3);
		//MultiBar mb3 = new MultiBar(enemy3, true);
		multiBarList.add(new MultiBar(enemy3, true));
		//mb3.addLayer( enemy3);
		//mb3.addLayer( enemy3);
		//mb3.addLayer( enemy3);
/*		layerList.add(new Layer(color1, enemy3.enemyStats.getMaxHealth(), enemy3, true));
		layerList.add(new Layer(color2, enemy3.enemyStats.getCurHealth(), enemy3, false));*/
	
		keyXAmount = new IconXAmount(new Vector2f(0f,0f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/keyX.png")), 
								0 ); //it will change with the amount of keys player has
		
		FreshmenChestXAmount = new IconXAmount(new Vector2f(100f,0f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/FreshmenchestX.png")), 
								0 ); //it will change with the amount of chest player has
		
		SophomoreChestXAmount = new IconXAmount(new Vector2f(200f,0f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/SophomoreChestX.png")), 
								0 ); //it will change with the amount of chest player has

		
		JuniorChestXAmount = new IconXAmount(new Vector2f(300f,0f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/JuniorChestX.png")), 
								0 ); //it will change with the amount of chest player has

		
		SeniorChestXAmount = new IconXAmount(new Vector2f(400f,0f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/SeniorChestX.png")), 
								0 ); //it will change with the amount of chest player has
		
		Random rnd = new Random();
		float velX = rnd.nextFloat()*800f;
		float velY = rnd.nextFloat()*600f;
		keyList.add(new Key(new Vector2f(velX, velY), 500, 500));
		
		//if(keyList.size()<1)
			//dkeyList.add(new Key(new Vector2f(60f,260f),400,400));
		/*keyList.add(new Key(new Vector2f(160f,160f)));
		keyList.add(new Key(new Vector2f(360f,260f)));
		keyList.add(new Key(new Vector2f(460f,60f)));*/
		
		//*************************ST**************************

	}
	
	
	
	@Override
	public void update(GameContainer container, int arg2) throws SlickException 
	{
		if(player !=null){
			manageInput(container);
			player.update();
		}
		
		for (int i =0; i < bulletList.size(); i++){
			bulletList.get(i).update();
		}
		
		for (int i =0; i < enemyList.size(); i++){
			enemyList.get(i).update();
		}
		
		//System.out.println(player.getStats().getCurHealth());
		/*if(player.collides(enemy))
			System.out.println("Wow");*/
		//System.out.println(player.getPosition().getX() + ", " + player.getPosition().getY());
		//enemy.findTarget(player);
		
		handleCollisions();
		
		
		
		//*************************ST**************************
		
		keyXAmount.update();
		FreshmenChestXAmount.update();
		SophomoreChestXAmount.update();
		JuniorChestXAmount.update();
		SeniorChestXAmount.update();
		
		for (int i=0; i<multiBarList.size(); i++){
			multiBarList.get(i).update();
		}
		
		for (int i=0; i<keyList.size(); i++){
			keyList.get(i).update();
		}
		
		if(keyList.size() < 1){
			spawnKey();
		}
		
		//*************************ST**************************
		
		
		
		if (player != null && player.getStats().isDead()){
			player= null;
		}

	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException 
	{
		g.setBackground(BACKGROUND);
		
		
		
		//*************************ST**************************
		keyXAmount.draw(g);
		FreshmenChestXAmount.draw(g);
		SophomoreChestXAmount.draw(g);
		JuniorChestXAmount.draw(g);
		SeniorChestXAmount.draw(g);
		
		/*for (int i =0; i < layerList.size(); i++)
			layerList.get(i).draw(g);*/
		
		for (int i =0; i < multiBarList.size(); i++){
			multiBarList.get(i).draw(g);
		}

		for (int i =0; i < keyList.size(); i++){
			keyList.get(i).draw(g);
		}

		g.setColor(Color.white);
		
		String scoreLabel = "SCORE : " + player.score;	
		//g.setFont( (org.newdawn.slick.Font) new Font( "TimesRoman" , Font.PLAIN, 18 ));
		g.drawString(scoreLabel, 980f, 5f);
		
		//*************************ST**************************
		
		
		
		if(player !=null){
			player.draw(g);
		}
		if(player == null){
			g.drawString("GAME OVER!", container.getWidth()/2, container.getHeight()/2);
		}
		for (int i =0; i < bulletList.size(); i++){
			bulletList.get(i).draw(g);
		}
		for (int i =0; i < enemyList.size(); i++){
			enemyList.get(i).draw(g);
		}
	
	}

	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new TestScreen ("Test"));
		
		app.setDisplayMode(1080, 720, false);
		app.setTargetFrameRate(FPS);
		app.start();
	}
	
}