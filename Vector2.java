
public class Vector2 
{
	//Properities
    private float x;
    private float y;
    
  //Constructor
    public Vector2(float x, float y)
    {
    	this.x=x;
    	this.y=y;
    }
    
    public Vector2()
    {
    	this.x=0;
    	this.y=0;
    }
    
    public Vector2(Vector2 other)
    {
    	this.x=other.x;
    	this.y=other.y;
    }
    
    public float getX()
    {
    	return x;
    }
    
    public float getY()
    {
    	return y;
    }
    
    public void setX(float x)
    {
    	 this.x=x;
    }
    
    public void setY(float y)
    {
    	 this.y=y;
    }
    
    public float getMagnitude()
    {
    	return (float)Math.sqrt(x*x + y*y);
    }
    
    public void normalize()
    {
    	Vector2 nV= this.normalized();
    	this.x= nV.x;
    	this.y=nV.y;
    }
    
    public Vector2 normalized()
    {
    	float magnitude;
    	magnitude = (float)Math.sqrt(x*x + y*y);
    	Vector2 normalizedVector = new Vector2();
    	
    	if(magnitude!=0)
    		normalizedVector= new Vector2(this.x/magnitude, this.y/magnitude);
    	
    	return normalizedVector;
    }
    
    public Vector2 difference(Vector2 other)
    {
    	Vector2 dV = new Vector2(this.x- other.x, this.y- other.y);
    	return dV;
    }
    
    public void add(Vector2 other)
    {
    	this.x = this.x+ other.x;
    	this.y = this.y+ other.y;
    }
    
    public void add(float x, float y)
    {
    	this.x = this.x + x;
    	this.y = this.y + y;
    }
    
    public Vector2 multiply(float n)
    {
    	this.x *= n;
    	this.y *= n;
    	return this;
    }
    
    public void multiply(int n)
    {
    	this.x *= n;
    	this.y *= n;
    }
}
