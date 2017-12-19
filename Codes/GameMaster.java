import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameMaster extends BasicGameState{
	
	Player player;
	ArrayList<Bullet> bulletList;
	ArrayList<Enemy> enemyList;
	ArrayList<Bonus> bonusList;
	
	Image background;
	int score=0;
	boolean isFinalSpawn = false;
	
	//Sound
	private Sound pew;
	private Sound boom;
	private Sound playerBoom;
	private boolean paused;
	
	//IconXAmounts
	IconXAmount keyXAmount;
	IconXAmount FreshmenChestXAmount;
	IconXAmount SophomoreChestXAmount;
	IconXAmount JuniorChestXAmount;
	IconXAmount SeniorChestXAmount;
	IconXAmount coinXAmount;

	//Bonus Constants 
	float keySpawnRate;
	float coinSpawnRate;
	float freshmenChestSpawnRate;
	float sophomoreChestSpawnRate ;
	float juniorChestSpawnRate;
	float seniorChestSpawnRate;
	final float BONUS_SPAWN = 2000f;
	private long nextTimeToSpawnBonus = 0;
	
	//Enemy Constants
	float bugSpawnRate;
	float quizSpawnRate;
	float labSpawnRate;
	float assignmentSpawnRate;
	float midtermSpawnRate;
	final float ENEMY_SPAWN = 5000f;
	private long nextTimeToSpawnEnemy = 0;
	
	//Number of Waves of Levels level1=6, level2=8, level3=10, level4=12 WAVE
	final int WAVES_LEVEL_1 = 6;
	final int WAVES_LEVEL_2 = 8;
	final int WAVES_LEVEL_3 = 10;
	final int WAVES_LEVEL_4 = 12;
	
	
	//level
	public int level;
	public int currentWave;
	public int numOfWave;
	public boolean isTimeToFinal;
	public boolean once;
	final float WAVE_SPAWN = 5000f;
	private long nextTimeToSpawnWave = 0;
	
	public static Color BACKGROUND = Color.gray;
	private static GameMaster gm = null;
	
	private GameMaster()
	{
		super();
	}
	
	public static GameMaster getGameMaster()
	{
		if(gm==null)
		{
			gm = new GameMaster();
		}
		return gm;
	}
	
	public void handleCollisions()
	{
		handleBulletCollisions();
		if(player!=null){
			handleEnemyPlayerCollisions();
			//*************************ST**************************
			handleBonusCollisions();
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
				}
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
					if(enemyList.get(j).isToBeRemoved()){
						score = (int) (score + enemyList.get(j).enemyStats.getMaxHealth());
						boom.play();
					}
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
	
	private void handleBonusCollisions()
	{
		for(int i=0; i<bonusList.size(); i++){
			if(player.collides(bonusList.get(i)))
			{
				bonusList.get(i).setToBeRemoved(true);
				if(bonusList.get(i) instanceof Key){
					player.getInventory().setNumOfKeys(player.getInventory().getNumOfKeys()+1);
					keyXAmount.amount = player.getInventory().getNumOfKeys();
				}
				if(bonusList.get(i) instanceof Coin){
					player.getInventory().setNumOfCoins(player.getInventory().getNumOfCoins()+1);
					coinXAmount.amount = player.getInventory().getNumOfCoins();
				}
				if(bonusList.get(i) instanceof FreshmenChest){
					player.getInventory().setNumOfFreshmenChests(player.getInventory().getNumOfFreshmenChests()+1);
					FreshmenChestXAmount.amount = player.getInventory().getNumOfFreshmenChests();
				}
				if(bonusList.get(i) instanceof SophomoreChest){
					player.getInventory().setNumOfSophomoreChests(player.getInventory().getNumOfSophomoreChests()+1);
					SophomoreChestXAmount.amount = player.getInventory().getNumOfSophomoreChests();
				}
				if(bonusList.get(i) instanceof JuniorChest){
					player.getInventory().setNumOfJuniorChests(player.getInventory().getNumOfJuniorChests()+1);
					JuniorChestXAmount.amount = player.getInventory().getNumOfJuniorChests();
				}
				if(bonusList.get(i) instanceof SeniorChest){	
					player.getInventory().setNumOfSeniorChests(player.getInventory().getNumOfSeniorChests()+1);
					SeniorChestXAmount.amount = player.getInventory().getNumOfSeniorChests();
				}
			}
		}
		handleRemovals((ArrayList)bonusList);
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
				pew.play();
			}
		}	
	  
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))
		{
			player.setPowerUpActive(!player.isPowerUpActive());	  
		}
		if (input.isKeyDown(Input.KEY_ESCAPE))
		{
			paused = true;
		}
		
	}

	private void spawnBonus(){
		
		float rnd = (float) (Math.random()*100f);
		
		float velX = (float) (Math.random()*1820f);
		float velY = (float) (Math.random()*1040f+40f);
	
		Key k;
		Coin c;
		FreshmenChest f;
		SophomoreChest sop;
		JuniorChest j;
		SeniorChest sen;
		
		if(rnd>=(100-seniorChestSpawnRate)){
			try {
				sen = new SeniorChest(new Vector2f(velX, velY), new Image("res/Chest/SeniorChest.png"), 800, 800);
				bonusList.add(sen); 
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		else if(rnd>=(100-seniorChestSpawnRate-juniorChestSpawnRate)){
			try {
				j = new JuniorChest(new Vector2f(velX, velY), new Image("res/Chest/JuniorChest.png"), 800, 800);
				bonusList.add(j); 
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		else if(rnd>=(100-seniorChestSpawnRate-juniorChestSpawnRate-sophomoreChestSpawnRate)){
			try {
				sop = new SophomoreChest(new Vector2f(velX, velY), new Image("res/Chest/SophomoreChest.png"), 800, 800);
				bonusList.add(sop); 
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		else if(rnd>=(100-seniorChestSpawnRate-juniorChestSpawnRate-sophomoreChestSpawnRate-freshmenChestSpawnRate)){
			try {
				f = new FreshmenChest(new Vector2f(velX, velY), new Image("res/Chest/FreshmenChest.png"), 800, 800);
				bonusList.add(f); 
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		else if(rnd>=(100-seniorChestSpawnRate-juniorChestSpawnRate-sophomoreChestSpawnRate-freshmenChestSpawnRate-coinSpawnRate)){
			try {
				c = new Coin(new Vector2f(velX, velY), new Image("res/coin.png"), 800, 800);
				bonusList.add(c); 
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		else if(rnd>=(100-seniorChestSpawnRate-juniorChestSpawnRate-sophomoreChestSpawnRate-freshmenChestSpawnRate-coinSpawnRate-keySpawnRate)){
			try {
				k = new Key(new Vector2f(velX, velY), new Image("res/key.png"), 800, 800);
				bonusList.add(k); 
			} catch (SlickException e) {
				e.printStackTrace();
			}	
		}
	}
	
	private void spawnEnemy(){
		
		float rnd = (float) (Math.random()*100f);
		
		float velX = (float) (Math.random()*1820f);
		float velY = (float) (Math.random()*1040f+40f);
	
		Bug b;
		Quiz q;
		Lab l;
		Assignment a;
		Midterm m;
		
		if(rnd>=(100-midtermSpawnRate)){
			m =  new Midterm(new Vector2f(velX, velY), 150f, player, bulletList);
			enemyList.add(m);
		}
		
		else if(rnd>=(100-midtermSpawnRate-assignmentSpawnRate)){
			a = new Assignment(new Vector2f(velX, velY), 1.6f,
					100f, player);
			enemyList.add(a);
		}
		
		else if(rnd>=(100-midtermSpawnRate-assignmentSpawnRate-labSpawnRate)){
			l = new Lab(new Vector2f(velX, velY), 
					100f, player, enemyList);
			enemyList.add(l);
		}
		else if(rnd>=(100-midtermSpawnRate-assignmentSpawnRate-labSpawnRate-quizSpawnRate)){
			q = new Quiz(new Vector2f(velX, velY), 
					100f, player, bulletList);
			enemyList.add(q);
		}
		else if(rnd>=(100-midtermSpawnRate-assignmentSpawnRate-labSpawnRate-quizSpawnRate-bugSpawnRate)){
			b = new Bug(new Vector2f(velX, velY), 
					100f, player);
			enemyList.add(b);
		}
	}
	
	private void spawnFinal(){
		
		float velX = (float) (Math.random()*1820f);
		float velY = (float) (Math.random()*1040f+40f);
		
		Final f = new Final(new Vector2f(velX, velY), 500f, player, bulletList);
		enemyList.add(f);
	}
	
	private void spawnWave(){
		int numOfEnemy = 2 + 2*level; // level1=8, level2=10, level3=12, level4=14 ENEMY
		for(int i=0; i<numOfEnemy; i++){
			spawnEnemy();
		}
	}
	
	private int getNumOfWave(int level){
		int num = 1;
		if(level==1){
			num =  WAVES_LEVEL_1;
		}
		if(level==2){
			num = WAVES_LEVEL_2;
		}
		if(level==3){
			num = WAVES_LEVEL_3;
		}
		if(level==4){
			num = WAVES_LEVEL_4;
		}
		return num;
	}
	
	public void reset(){
		keySpawnRate = 20f;
		coinSpawnRate = 25f;
		freshmenChestSpawnRate = 10f;
		sophomoreChestSpawnRate = 7.5f;
		juniorChestSpawnRate = 5f;
		seniorChestSpawnRate = 2.5f;
				
		bugSpawnRate = 25f;
		quizSpawnRate = 25f;
		labSpawnRate = 20f;
		assignmentSpawnRate = 20f;
		midtermSpawnRate = 15f;
		
		currentWave = 1;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		player = Player.getPlayer();
		player.getStats().setCurHealth(player.getStats().getMaxHealth());
		player.getStats().setDead(false);
		player.setPosition(new Vector2f( (float)width/2, (float)height/2));
		
		bulletList = new ArrayList<Bullet>();
		enemyList = new ArrayList<Enemy>();
		bonusList = new ArrayList<Bonus>();
	}

	@Override
	public void init(GameContainer container,  StateBasedGame game) throws SlickException 
	{
		pew = new Sound("res/Sound/pew.wav");
		boom = new Sound("res/Sound/kaboom.wav");
		playerBoom = new Sound("res/Sound/playerboom.wav");
		paused=false;
		
		container.setShowFPS(false); 
		
		background = new Image("res/Background.png");
		background = background.getScaledCopy(1.5f);
		
		player = Player.getPlayer();
		player.getStats().setCurHealth(player.getStats().getMaxHealth());
		player.getStats().setDead(false);
		player.setPosition(new Vector2f((float)container.getScreenWidth()/2, (float)container.getScreenHeight()/2));
		
		bulletList = new ArrayList<Bullet>();
		enemyList = new ArrayList<Enemy>();
		bonusList = new ArrayList<Bonus>();
				
		keyXAmount 			 = new IconXAmount(new Vector2f(5f,5f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/iconXAmount/keyX.png")), 
								0 ); 
		FreshmenChestXAmount = new IconXAmount(new Vector2f(125f,5f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/iconXAmount/FreshmenchestX.png")), 
								0 ); 

		SophomoreChestXAmount = new IconXAmount(new Vector2f(245f,5f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/iconXAmount/SophomoreChestX.png")), 
								0 ); 

		JuniorChestXAmount = new IconXAmount(new Vector2f(365f,5f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/iconXAmount/JuniorChestX.png")), 
								0 ); 

		SeniorChestXAmount = new IconXAmount(new Vector2f(485f,5f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/iconXAmount/SeniorChestX.png")), 
								0 );
		
		coinXAmount = new IconXAmount(new Vector2f(605f,5f), 
								new Vector2f(0f,0f), 
								new Icon(new Image("res/iconXAmount/coinX.png")), 
								0 ); 
		
		keySpawnRate = 20f;
		coinSpawnRate = 25f;
		freshmenChestSpawnRate = 10f;
		sophomoreChestSpawnRate = 7.5f;
		juniorChestSpawnRate = 5f;
		seniorChestSpawnRate = 2.5f;
				
		bugSpawnRate = 25f;
		quizSpawnRate = 25f;
		labSpawnRate = 20f;
		assignmentSpawnRate = 20f;
		midtermSpawnRate = 15f;
		
		level = 1;
		currentWave = 1;
		numOfWave = getNumOfWave(level);
		isTimeToFinal = false;
		//once = true;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2) throws SlickException 
	{
		if(container.getInput().isKeyPressed(Input.KEY_8))
			game.enterState(4);
		
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
		
		keyXAmount.update();
		FreshmenChestXAmount.update();
		SophomoreChestXAmount.update();
		JuniorChestXAmount.update();
		SeniorChestXAmount.update();
		coinXAmount.update();
		
		for (int i=0; i<bonusList.size(); i++){
			bonusList.get(i).update();
		}
				
		if(System.currentTimeMillis() > nextTimeToSpawnBonus){
			nextTimeToSpawnBonus =  (long)BONUS_SPAWN + System.currentTimeMillis();
			if(bonusList.size() < 3){
				spawnBonus();
			}
		}
		
		if(System.currentTimeMillis() > nextTimeToSpawnWave){
			nextTimeToSpawnWave =  (long)WAVE_SPAWN + System.currentTimeMillis();
			
			if((currentWave > numOfWave) && (enemyList.size() <= 0)){
				if(!isFinalSpawn){
					spawnFinal();
					isFinalSpawn = true;
				}
			}
			if(enemyList.size() <= 0 && !isFinalSpawn){
				spawnWave();
				currentWave++;
			}
		}
		
		if(isFinalSpawn){
			if(enemyList.size() <= 0){
				level++;
				if(level <= 4){
					game.enterState(4); //***** it will change with the upgrade screen *****4
				}
				else{
					game.enterState(5); //***** it will change with the success screen *****
				}
			}
		}
		
		if (player != null && player.getStats().isDead()){
			player = null;
			container.setMusicOn(false);
			game.getState(5).init(container, game);
			game.enterState(5);
			playerBoom.play();
		}
		
		if(paused)
		{
			game.enterState(3);
			paused= !paused;
		}	
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
	{
		//g.setBackground(BACKGROUND);
		background.draw();
		
		keyXAmount.draw(g);
		FreshmenChestXAmount.draw(g);
		SophomoreChestXAmount.draw(g);
		JuniorChestXAmount.draw(g);
		SeniorChestXAmount.draw(g);
		coinXAmount.draw(g);

		for (int i =0; i < bonusList.size(); i++){
			bonusList.get(i).draw(g);
		}

		g.setColor(Color.black);
		
		String scoreLabel = "";
		if(player != null){
			scoreLabel = "SCORE : " + score;	
		}
		//g.setFont( (org.newdawn.slick.Font) new Font( "TimesRoman" , Font.PLAIN, 18 ));
		g.drawString(scoreLabel, 1780f, 5f);
		
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
		
		if(enemyList.size()<=0){
			if(currentWave <= numOfWave){
				g.drawString("Wave: " + currentWave, container.getWidth()/2, container.getHeight()/2);
			}
			else{
				g.drawString("Final Time ;)", container.getWidth()/2, container.getHeight()/2);
			}
		}
	}
	
	@Override
	public int getID() {
		return 1;
	}

}