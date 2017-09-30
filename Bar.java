import java.awt.Graphics2D;
import java.awt.*;

public class Bar implements Drawable {
	
	private Vector2 pos;
	private float width;
	private float height;
	private float percentage;
	
	private boolean horizontal;
	private Color layer1;
	private Color layer2;
	
	public Bar(Vector2 pos, float height, float width, Color layer1, Color layer2)
	{
		this.pos = pos;
		this.height = height;
		this.width =width;
		this.horizontal= true;
		this.layer1 = layer1;
		this.layer2 = layer2;
	}
	public Bar(Vector2 pos, float width, float height, Color layer1, Color layer2, boolean horizontal)
	{
		this.pos = pos;
		this.width = width;
		this.height =height;
		this.horizontal=horizontal;
		this.layer1 = layer1;
		this.layer2 = layer2;
	}
	
	public Bar(Vector2 pos, float width, float height, boolean horizontal)
	{
		this.pos = pos;
		this.width = width;
		this.height =height;
		this.horizontal=horizontal;
		this.layer1 = Color.RED;
		this.layer2 = Color.GREEN;
	}
	
	public Bar(Vector2 pos, float width, float height)
	{
		this.pos = pos;
		this.width = width;
		this.height =height;
		this.horizontal= true;
		this.layer1 = Color.RED;
		this.layer2 = Color.GREEN;
	}
	
	
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	public float getWidth() {
		return width;
	}

	public void setWength(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	

	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	public Color getLayer1() {
		return layer1;
	}
	public void setLayer1(Color layer1) {
		this.layer1 = layer1;
	}
	public Color getLayer2() {
		return layer2;
	}
	public void setLayer2(Color layer2) {
		this.layer2 = layer2;
	}
	@Override
	public void draw(Graphics2D g) 
	{
		g.setColor(layer1);
		g.fillRect(((int)pos.getX()), (int)(pos.getY()), (int)width, (int)height);
		
		g.setColor(layer2);
		if(horizontal)
		{
			g.fillRect(((int)pos.getX()), (int)(pos.getY()), (int)(width*percentage), (int)height);
		}
		
		else
		{
			g.fillRect(((int)pos.getX()), (int)(pos.getY()), (int)width, (int)(height*percentage));
		}
	}

}
