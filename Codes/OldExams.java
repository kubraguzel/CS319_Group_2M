
public class OldExams extends Item {

	public OldExams() {
		super("Old Exams", "The blessing of seniors, curse of Midterms", 0);
		itemTier = Item.rare;
	}

	@Override
	public void affect() 
	{
		Player.getPlayer().getStats().setBulletSpeed(Player.getPlayer().getStats().getBulletSpeed()*1.7f);
		GameMaster.getGameMaster().juniorChestSpawnRate = GameMaster.getGameMaster().juniorChestSpawnRate * 1.3f; 
		GameMaster.getGameMaster().midtermSpawnRate = GameMaster.getGameMaster().midtermSpawnRate *1.5f;
	}

}
