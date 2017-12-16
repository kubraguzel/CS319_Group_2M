import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameMaster extends BasicGameState{
	Player player;
	ArrayList<Bullet> bulletList;
	ArrayList<Enemy> enemyList;
	private String playerName;
	Image background;
	
	//*************************ST**************************
	int score=0;
	
	IconXAmount keyXAmount;
	IconXAmount FreshmenChestXAmount;
	IconXAmount SophomoreChestXAmount;
	IconXAmount JuniorChestXAmount;
	IconXAmount SeniorChestXAmount;

	ArrayList<MultiBar> multiBarList;
	ArrayList<Key> keyList;
	//*************************ST**************************
	
	public static Color BACKGROUND = Color.gray;
	
	public GameMaster()
	{
		super();
	}
	
	public void handleCollisions()
	{
		handleBulletCollisions();
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
					//*************************ST**************************
					if(enemyList.get(j).isToBeRemoved()){
						score = (int) (score + enemyList.get(j).enemyStats.getMaxHealth());
					}
					//*************************ST**************************
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
		
		//*************************ST**************************
		handleRemovals((ArrayList)multiBarList);
		//*************************ST**************************
	}
	
	//*************************ST**************************
	private void handleKeyCollisions()
	{
		for(int i=0; i<keyList.size(); i++){
			if(player.collides(keyList.get(i)))
			{
				//player.takeKey();
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
		for (int i = 0; i< list.size(); i++)
		{
			if(list.get(i).isToBeRemoved())
			{
				list.remove(i);
				size--;
				//System.out.println(list);
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
	private void spawnKey() {
		Random rnd = new Random();
		float velX = rnd.nextFloat()*2000f;
		float velY = rnd.nextFloat()*2000f;
		
		Key k;
		try {
			k = new Key(new Vector2f(velX, velY), new Image("res/key.png"), 800, 800);
			keyList.add(k); 
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	//*************************ST**************************
	
	@Override
	public void init(GameContainer container,  StateBasedGame game) throws SlickException {
		
		background = new Image("res/Background.png");
		background = background.getScaledCopy(1.5f);
		
		player = Player.getPlayer();
		player.setPlayerName(playerName);
		
		bulletList = new ArrayList<Bullet>();
		enemyList = new ArrayList<Enemy>();
		
		//*************************ST**************************
		multiBarList = new ArrayList<MultiBar>();
		keyList = new ArrayList<Key>();
		
		multiBarList.add(new MultiBar(player, false)); //not horizontal
				
		/*Enemy enemy1 = new Bug(new Vector2f(300f, 300f), 50f, 3f, player);
		enemyList.add(enemy1);*/
		
		/*Enemy enemy2 = new Quiz(new Vector2f(900f, 300f), 100f, player, bulletList);
		enemyList.add(enemy2);
		multiBarList.add(new MultiBar(enemy2, true));
		//System.out.println(enemyList==null);
		
		Enemy enemy3 =new Lab(new Vector2f(400f, 500f), 
				100f, player, enemyList);
		enemyList.add(enemy3);
		multiBarList.add(new MultiBar(enemy3, true));
		
		Enemy enemy4 =new Assignment(new Vector2f(400f, 500f), 1.6f,
				100f, player);
		enemyList.add(enemy4);
		multiBarList.add(new MultiBar(enemy4, true));
		
		Enemy enemy5= new Midterm(new Vector2f(1000f, 600f), 150f, player, bulletList);
		enemyList.add(enemy5);
		multiBarList.add(new MultiBar(enemy5, true));
		
		Enemy enemy6= new Final(new Vector2f(1200f, 200f), 500f, player, bulletList);
		enemyList.add(enemy6);
		multiBarList.add(new MultiBar(enemy6, true));*/
		
		keyXAmount 			 = new IconXAmount(new Vector2f(5f,5f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/iconXAmount/keyX.png")), 
								0 ); //it will change with the amount of keys player has
		FreshmenChestXAmount = new IconXAmount(new Vector2f(125f,5f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/iconXAmount/FreshmenchestX.png")), 
								0 ); //it will change with the amount of chest player has

		SophomoreChestXAmount = new IconXAmount(new Vector2f(245f,5f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/iconXAmount/SophomoreChestX.png")), 
								0 ); //it will change with the amount of chest player has

		JuniorChestXAmount = new IconXAmount(new Vector2f(365f,5f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/iconXAmount/JuniorChestX.png")), 
								0 ); //it will change with the amount of chest player has

		SeniorChestXAmount = new IconXAmount(new Vector2f(485f,5f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/iconXAmount/SeniorChestX.png")), 
								0 ); //it will change with the amount of chest player has

		for(int i=0; i<10; i++){
			Random rnd = new Random();
			float velX = rnd.nextFloat()*1800f;
			float velY = rnd.nextFloat()*1600f;

			keyList.add(new Key(new Vector2f(velX, velY), new Image("res/key.png"), 800, 800));
		}
		
		//*************************ST**************************
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2) throws SlickException 
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
		
		handleCollisions();
		
		//*************************ST**************************
		// it will change with the if(player collides any key on the game screen)
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
		
		if(keyList.size() < 4){
			spawnKey();
		}
		
		//*************************ST**************************
		
		if (player != null && player.getStats().isDead()){
			player= null;
		}
		
		if (container.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
	    	  game.enterState(0);	  
		 }
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
	{
		//g.setBackground(BACKGROUND);
		background.draw();
		
		//*************************ST**************************
		keyXAmount.draw(g);
		FreshmenChestXAmount.draw(g);
		SophomoreChestXAmount.draw(g);
		JuniorChestXAmount.draw(g);
		SeniorChestXAmount.draw(g);
		
		for (int i =0; i < multiBarList.size(); i++){
			multiBarList.get(i).draw(g);
		}

		for (int i =0; i < keyList.size(); i++){
			keyList.get(i).draw(g);
		}

		g.setColor(Color.black);
		
		String scoreLabel = "";
		if(player != null){
			scoreLabel = "SCORE : " + score;	
		}
		//g.setFont( (org.newdawn.slick.Font) new Font( "TimesRoman" , Font.PLAIN, 18 ));
		g.drawString(scoreLabel, 1780f, 5f);
		
		//*************************ST**************************
		
		if(player != null){
			player.draw(g);
		}
		if(player == null){
			g.drawString("GAME OVER!", container.getWidth()/2, container.getHeight()/2);
		}
		for (int i=0; i<bulletList.size(); i++){
			bulletList.get(i).draw(g);
		}
		for (int i=0; i<enemyList.size(); i++){
			enemyList.get(i).draw(g);
		}
		
	}
	
	@Override
	public int getID() {
		return 1;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}