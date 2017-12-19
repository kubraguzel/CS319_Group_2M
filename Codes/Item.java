
public abstract class Item {

	protected int cost = 0;
	protected String name;
	protected String description;
	protected int itemTier;
	
	protected static int standart = 0;
	protected static int rare = 1;
	protected static int ultraRare = 2;
	protected static int hacker = 3;
	
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
	
	public abstract void affect();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getItemTier() {
		return itemTier;
	}
}
