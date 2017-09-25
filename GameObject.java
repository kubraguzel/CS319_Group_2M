/**
 * 
 */

/**
 * @author Alper Þahýstan
 *
 */


public abstract class GameObject 
{
	//Properities
    Vector2 pos;
    Vector2 dimentions;
    
  //Constructors
    public GameObject(Vector2 pos, Vector2 dim)
    {
    	this.pos = pos;
    	this.dimentions = dim;
    }
    
    public GameObject(float x, float y, float l, float h)
    {
    	pos = new Vector2(x, y);
    	dimentions = new Vector2(l, h);
    }
    
    public GameObject(float x, float y)
    {
    	this.pos = new Vector2(x, y);
    	this.dimentions = new Vector2(1f, 1f);
    }
    
    public GameObject(Vector2 pos)
    {
    	this.pos = pos;
    	this.dimentions = new Vector2(1f,1f);
    }
    
    public GameObject()
    {
    	this.pos = new Vector2();
    	this.dimentions = new Vector2(1f, 1f);
    }
    
    abstract void update();
     
}
