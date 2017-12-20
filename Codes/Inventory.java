import java.util.ArrayList;

public class Inventory {
	
	private final int ITEM_CAPACITY = 5;
	
	private ArrayList<Item> itemList;
	private int numOfCoins;
	private int numOfKeys;
	private int numOfFreshmenChests;
	private int numOfSophomoreChests;
	private int numOfJuniorChests;
	private int numOfSeniorChests;
	
	public Inventory() 
	{
		itemList = new ArrayList<Item>();
		numOfCoins = 0;
		numOfKeys = 0;
		numOfFreshmenChests = 0;
		numOfSophomoreChests = 0;
		numOfJuniorChests = 0;
		numOfSeniorChests = 0;
	}
	
	public boolean addItem(Item item)
	{
		if(itemList.size() < ITEM_CAPACITY)
		{
			itemList.add(item);
			return true;
		}
		return false;
			
	}
	
	public boolean discardItem(int i)
	{
		if (i >= 0 
				&& !itemList.isEmpty()
				&& i < itemList.size() )
		{
			itemList.remove(i);
			return true;
		}
		return false;
	}
	
	public boolean openChest(int chestType)
	{
		Chest chest;
		if (itemList.size() <= ITEM_CAPACITY)
		{
			if(chestType == 0 
					&& numOfFreshmenChests > 0
					&& numOfKeys >= 1)
			{
				chest = new FreshmenChest();
				itemList.add(chest.unlock());
				numOfFreshmenChests--;
				numOfKeys--;
				return true;
			}
			
			else if(chestType == 1
					&& numOfSophomoreChests > 0
					&& numOfKeys >= 2)
			{
				chest = new SophomoreChest();
				itemList.add(chest.unlock());
				numOfSophomoreChests--;
				numOfKeys= numOfKeys -2;
				return true;
			}
			
			else if(chestType == 2
					&& numOfJuniorChests > 0
					&& numOfKeys >= 3)
			{
				chest = new JuniorChest();
				itemList.add(chest.unlock());
				numOfJuniorChests--;
				numOfKeys= numOfKeys -3;
				return true;
			}
			
			else if(chestType == 3
					&& numOfSeniorChests > 0
					&& numOfKeys >= 4)
			{
				chest = new SeniorChest();
				itemList.add(chest.unlock());
				numOfSeniorChests--;
				numOfKeys= numOfKeys -4;
				return true;
			}
			else 
				return false;
		}
		else
		{
			return false;
		}
	}

	public int getNumOfCoins() {
		return numOfCoins;
	}

	public void setNumOfCoins(int numOfCoins) {
		this.numOfCoins = numOfCoins;
	}

	public int getNumOfKeys() {
		return numOfKeys;
	}

	public void setNumOfKeys(int numOfKeys) {
		this.numOfKeys = numOfKeys;
	}

	public int getNumOfFreshmenChests() {
		return numOfFreshmenChests;
	}

	public void setNumOfFreshmenChests(int numOfFreshmenChests) {
		this.numOfFreshmenChests = numOfFreshmenChests;
	}

	public int getNumOfSophomoreChests() {
		return numOfSophomoreChests;
	}

	public void setNumOfSophomoreChests(int numOfSophomoreChests) {
		this.numOfSophomoreChests = numOfSophomoreChests;
	}

	public int getNumOfJuniorChests() {
		return numOfJuniorChests;
	}

	public void setNumOfJuniorChests(int numOfJuniorChests) {
		this.numOfJuniorChests = numOfJuniorChests;
	}

	public int getNumOfSeniorChests() {
		return numOfSeniorChests;
	}

	public void setNumOfSeniorChests(int numOfSeniorChests) {
		this.numOfSeniorChests = numOfSeniorChests;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
}
