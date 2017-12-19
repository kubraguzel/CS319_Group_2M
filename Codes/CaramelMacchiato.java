
public class CaramelMacchiato extends Item {

	public CaramelMacchiato() {
		super("Caramel Macchiato", "Live faster with less money", 20);
		itemTier = Item.standart;
	}

	@Override
	public void affect() {
		Player.getPlayer().setSpeed(Player.getPlayer().getSpeed()*1.5f);
		GameMaster.getGameMaster().coinSpawnRate = GameMaster.getGameMaster().coinSpawnRate* 0.6f;
	}

}
