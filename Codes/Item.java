
public abstract class Item {

	protected int cost = 0;
	protected String name;
	protected String description;
	
	public Item(String name, String description, int cost) 
	{
		this.cost = cost;
		this.name =name;
		this.description = description;
	}
	
	public Item(String name, String description) 
	{
		this.cost = 0;
		this.name =name;
		this.description = description;
	}
	
	public abstract void affect(GameMaster gm, Player player);


}
