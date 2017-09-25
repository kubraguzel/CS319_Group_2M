import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.util.ArrayList;
import javax.swing.Timer;

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
    private boolean mouseHeldDown=false;
    
    public GamePanel()
    {
    	super();
    	setPreferredSize(new Dimension (WIDTH, HEIGHT));
    	setFocusable(true);
    	requestFocus();
    	player = new Player(new Vector2((float)WIDTH/2, (float)HEIGHT/2), new Vector2(50f ,50f), 100f, 100f, 10f, 15000f);
    	bulletList = new ArrayList<Bullet>();
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
    	if (player.pos.getX()<0 + player.dimentions.getX()/2)
    		player.pos.setX(0+player.dimentions.getX()/2);
    	if (player.pos.getX()>WIDTH - player.dimentions.getX()/2)
    		player.pos.setX(WIDTH-player.dimentions.getX()/2);
    	if (player.pos.getY()<0 + player.dimentions.getY()/2)
    		player.pos.setY(0+player.dimentions.getY()/2);
    	if (player.pos.getY()>HEIGHT - player.dimentions.getY()/2)
    		player.pos.setY(HEIGHT-player.dimentions.getY()/2);
    	for(int i =0; i < bulletList.size(); i++)
    		bulletList.get(i).update();
    	
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
		System.out.println("mousepressed");
		if(e.getButton()==1)
		{
			mouseHeldDown=true;
		}
		if (mouseHeldDown)
		{
			Bullet bullet;
			bullet = player.shoot(new Vector2((float)(e.getX()), (float)(e.getY())));
			System.out.println("Posx: " + player.pos.getX());
			System.out.println((float)(e.getX()-WIDTH/2));
			System.out.println((float)(e.getY()- HEIGHT/2));
			if (bullet != null)
			{
				bulletList.add(bullet);
				//System.out.println("heyy");
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==0)
		{
			//mouseHeldDown=false;
		}
		
	}
}
