import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.util.ArrayList;
import javax.swing.Timer;

import org.newdawn.slick.geom.Vector2f;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener
{
	 //Properities
	
    static final int AMOUNT = 100;
    static final int WIDTH = 1920;
    static final int HEIGHT = 1080;
    //static final int MAX_ELAPSED_TIME = 250;
    static final int UPDATE_PERIOD = 100;
    
    private boolean running;
    private Player player;
    
    private Graphics2D g;
    private BufferedImage image;
    private Thread thread;
    
    private int FPS = 30;
    private float averageFPS;
    
    private ArrayList<Bullet> bulletList;
    private ArrayList<Enemy> enemyList;
    private boolean mouseHeldDown=false;
    
    int mouseX;
    int mouseY;
    
    public GamePanel()
    {
    	super();
    	setPreferredSize(new Dimension (WIDTH, HEIGHT));
    	setFocusable(true);
    	requestFocus();
    	player = new Player(new Vector2((float)WIDTH/2, (float)HEIGHT/2), 
    						new Vector2(30f, 30f), 
    						100f, 100f, 
    						new Vector2(15f, 15f), 
    						300f);
    	
    	bulletList = new ArrayList<Bullet>();
    	
    	enemyList = new ArrayList<Enemy>();
    	enemyList.add(new Enemy(new Vector2(10f,10f), 
				new Vector2(20f, 20f),
				100f, 100f,
				new Vector2(7f, 7f), player));
    	
    	enemyList.add(new Enemy(new Vector2(600f,600f),
				new Vector2(20f, 20f), 
				100f, 100f,
				new Vector2(7f, 7f), player));
    	enemyList.add(new Enemy(new Vector2(1000f,900f), 
				new Vector2(20f, 20f),
				100f, 100f,
				new Vector2(7f, 7f), player));
    }
    
    public void addNotify()
    {
    	super.addNotify();
    	if(thread==null)
    		thread= new Thread(this);
    	thread.start();
    	addKeyListener(this);
    	addMouseListener(this);
    }
    
    public void run()
    {
    	running=true;
    	image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    	g= (Graphics2D) image.getGraphics();
    	
    	long startTime;
    	long URDTimeMS;
    	long waitTime;
    	long totalTime = 0;
    	
    	int frameCount=0;
    	int maxFrameCount=30;
    	
    	long targetTime= 1000/FPS;
    	
    	//Game Loop
    	while(running)
    	{
    		startTime= System.nanoTime();
    		
    		gameUpdate();
    		gameRender();
    		gameDraw();
    		
    		URDTimeMS = (System.nanoTime()-startTime)/1000000;
    		waitTime= targetTime-URDTimeMS;
    		
    		try
    		{
    			thread.sleep(waitTime);
    		}catch (Exception e){}
    		
    		totalTime += System.nanoTime() - startTime;
    		frameCount++;   		
    		if(frameCount==maxFrameCount)
    		{
    			averageFPS = 1000f / ( (totalTime / frameCount)/ 1000000f);
    			frameCount=0;
    			totalTime=0;
    		}
    	}
    }
    
    private void gameUpdate()
    {
    	player.update();
    	
    	//Border Check
    	if (player.getPos().getX()<0 + player.getDimentions().getX()/2)
    		player.getPos().setX(0+player.getDimentions().getX()/2);
    	if (player.getPos().getX()>WIDTH - player.getDimentions().getX()/2)
    		player.getPos().setX(WIDTH-player.getDimentions().getX()/2);
    	if (player.getPos().getY()<0 + player.getDimentions().getY()/2)
    		player.getPos().setY(0+player.getDimentions().getY()/2);
    	if (player.getPos().getY()>HEIGHT - player.getDimentions().getY()/2)
    		player.getPos().setY(HEIGHT-player.getDimentions().getY()/2);
    	
    	if (mouseHeldDown)
		{
			Bullet bullet;
			bullet = player.shoot(new Vector2((float)(MouseInfo.getPointerInfo().getLocation().getX()), 
					(float)MouseInfo.getPointerInfo().getLocation().getY()));
			/*System.out.println("Posx: " + player.getPos().getX());
			System.out.println((float)(e.getX()-WIDTH/2));
			System.out.println((float)(e.getY()- HEIGHT/2));*/
			if (bullet != null)
			{
				bulletList.add(bullet);
				//System.out.println("heyy");
			}
		
		}
    	
    	for(int j =0; j < enemyList.size(); j++)
    	{ 
    		//System.out.println("hiii");
			for(int i = bulletList.size()-1; i >= 0; i--)
    		{
				bulletList.get(i).update();
    			//System.out.println(bulletList.get(i));
    			//System.out.println(enemyList.get(j));
    			//TODO: Write a contains method for game objects
        		if( bulletList.get(i).getPos().getX() <= enemyList.get(j).getPos().getX() 
        				+ enemyList.get(j).getDimentions().getX() &&
        				bulletList.get(i).getPos().getX() >= enemyList.get(j).getPos().getX() 
        				- enemyList.get(j).getDimentions().getX() &&
        				bulletList.get(i).getPos().getY() <= enemyList.get(j).getPos().getY() 
        				+ enemyList.get(j).getDimentions().getY() &&
        				bulletList.get(i).getPos().getY() >= enemyList.get(j).getPos().getY() 
        				- enemyList.get(j).getDimentions().getY())
        		{
        			enemyList.get(j).takeDamage(bulletList.get(i).getDamage());
        			//bulletList.get(i).setHit(true);
        			bulletList.remove(i);
        			//system.out.println(i);
        			
        		}
        		/*if (bulletList.get(i).isHit())
        		{
        			
        			System.out.println("byeee");
        		}*/
        			
    		}
    	}
    	for(int i = bulletList.size()-1; i >= 0; i--)
			bulletList.get(i).update();
    	
    	for(int i =0; i < enemyList.size(); i++)
    	{
    		enemyList.get(i).update();
    		if(enemyList.get(i).enemyStats.isDead())
				enemyList.remove(i);
    	}
    }
    
    private void gameRender()
    {
    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, WIDTH, HEIGHT);
    	g.setColor(Color.BLACK);
    	g.drawString("Average FPS: "+ averageFPS, 10, 10);
    	
    	player.draw(g);
    	
    	for(int i =0; i < bulletList.size(); i++)
    		bulletList.get(i).draw(g);
    	
    	for(int i =0; i < enemyList.size(); i++)
    		enemyList.get(i).draw(g);
    }
    
    private void gameDraw()
    {
    	Graphics g2= this.getGraphics();
    	g2.drawImage(image, 0, 0, null);
    	g2.dispose();
    }

	@Override
	public void keyPressed(KeyEvent key) 
	{
		int keyCode = key.getKeyCode();
		if (keyCode == KeyEvent.VK_W)
			player.setUp(true);
		if (keyCode == KeyEvent.VK_S)
			player.setDown(true);
		if (keyCode == KeyEvent.VK_D)
			player.setRight(true);
		if (keyCode == KeyEvent.VK_A)
			player.setLeft(true);
		
		//System.out.println(keyCode);
	}

	@Override
	public void keyReleased(KeyEvent key) 
	{
		int keyCode = key.getKeyCode();
		if (keyCode == KeyEvent.VK_W)
			player.setUp(false);
		if (keyCode == KeyEvent.VK_S)
			player.setDown(false);
		if (keyCode == KeyEvent.VK_D)
			player.setRight(false);
		if (keyCode == KeyEvent.VK_A)
			player.setLeft(false);	
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println("mousepressed");
		if(e.getButton()==1)
		{
			mouseHeldDown=true;
			mouseX = e.getX();
			mouseY = e.getY();
		}
		/*if (mouseHeldDown)
		{
			Bullet bullet;
			bullet = player.shoot(new Vector2((float)(e.getX()), (float)(e.getY())));
			//System.out.println("Posx: " + player.getPos().getX());
			//System.out.println((float)(e.getX()-WIDTH/2));
			System.out.println((float)(e.getY()- HEIGHT/2));
			if (bullet != null)
			{
				bulletList.add(bullet);
				//System.out.println("heyy");
			}
		
		} */
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==1)
		{
			mouseHeldDown=false;
		}
		
	}
}
