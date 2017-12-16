
package test2;

import org.newdawn.slick.gui.MouseOverArea;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;


import org.newdawn.slick.state.*;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;
import java.io.InputStream;
import org.newdawn.slick.Font;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
//import java.util.Scanner;

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
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;


//import java.awt.Font;



public class TitleMenu extends BasicGameState 
{
	
	TextField textfield;
	Font font;
	Font font2;

	 
	Image world;
	Image play;
	Image exit;
	
	/*
	  private String value = "";
	  private Font font;
	    private Color border = Color.white;
	    private Color text = Color.white;
	    private Color background = new Color(0, 0, 0, 0.5f);
	    private int cursorPos;
	    private boolean visibleCursor = true;
	    
	
	*/
	
	public TitleMenu(int startmenu) 
	{
		
		
		
	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException 
	{
		
		world= new Image("native/resim.jpg");
		//play= new Image("native/play.jpg");
		//exit= new Image("native/exit.jpg");
		
		///Font font = new Font('Verdana', Font.BOLD, 32);
		//TrueTypeFont ttf = new TrueTypeFont(font, true);
		textfield = new TextField(gc, gc.getDefaultFont(), 250, 100, 200, 30);
		
		/*Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, antiAlias);
 
		// load font from file
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("myfont.ttf");
 
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(24f); // set font size
			font2 = new TrueTypeFont(awtFont2, antiAlias);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		//font = new UnicodeFont(new java.awt.Font(java.awt.Font.SANS_SERIF, java.awt.Font.ITALIC, 26));
	    //textField = new TextField(gc, [color=#4000FF]gc.getDefaultFont(), [/color]400, 300, 300, 50); 
	    //textField.setText("AWSOME");
	     */
		/* Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
         awtFont2 = awtFont2.deriveFont(24f); // set font size
         font2 = new TrueTypeFont(awtFont2, antiAlias);
         
	 */    
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException 
	{
		
		world.draw(0,0,700,700);
		
		//play.draw(500, 400,50,50);
		//exit.draw(500, 500,50,50);
		
		g.drawString("write your name", 100, 100);	
		g.drawString("Play", 500, 400);	
		g.drawString("Exit", 500, 500);	
		textfield.render(gc, g);
		
		//textfield.setText("dfdf");
		//g.drawString(textField, 500, 500);	

		
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException 
	{
	
	System.out.println(textfield.getText());
	//textfield=new TextField(gc, new TrueTypeFont((java.awt.Font) new Font("Comic Sans MS", 0, 45), false),  250, 100, 200, 30);
	
	int posX = Mouse.getX();
	int posY = Mouse.getY();
 
	
	//username = new TextField(gc,  new TrueTypeFont(new Font("Comic Sans MS", 0, 45), false), (int) (width*(0.28)), (int) (height*0.325), title.getWidth()*2/3, 60);
    //password = new TextField(gc,  new TrueTypeFont(new Font("Comic Sans MS", 0, 45), false), (int) (width*(0.28)), (int) (height*0.575), title.getWidth()*2/3, 60);

	//System.out.println("x:"+posX+"y:"+posY);
	//Input input=gc.getInput();
	
	//Scanner scanner = new Scanner(System.in);
	//String name = scanner.nextLine();
	
	/*Input input = gc.getInput();
    int xpos = Mouse.getX();
    int ypos = Mouse.getY();


    if ((xpos > 200 && xpos < 250) && (ypos > 230 && ypos < 260))
{

        if (input.isMousePressed(0)) 
        {
            g.drawImage("res/abc.png", 200, 400)
        }

	*/
	
	//play button
	if((posX>500 && posX<541)&&(posY>278&& posY<300))
	{
		if(Mouse.isButtonDown(0))
		{
			
			sbg.enterState(1);
		}
	}
	
	//exit button
	if((posX>500 && posX<541)&&(posY>180&& posY<195))
	{
		if(Mouse.isButtonDown(0))
		{
			
			System.exit(0);
		}
	}
	}
	
	/*public UnicodeFont getNewFont(String fontName , int fontSize)
	 * {
		Font font = new Font(fontName, Font., fontSize);
		
        UnicodeFont returnFont = new UnicodeFont ();
        returnFont.addAsciiGlyphs();
        returnFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (returnFont);
    }
    */
	
	
/*	public TextField(GameContainer gc, Font font, int x, int y, int width, int height, ComponentListener listener) 
 * {
 * 
        this(gc, font, x, y, width, height);
        addListener(listener);
    }
	
	*/
	
	public int getID() 
	{
		return 0;
	}
	

	
	
	

	
	
	
	
}


