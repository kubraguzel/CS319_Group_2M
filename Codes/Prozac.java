
public class Prozac extends Item {

	public Prozac() {
		super("Prozac 20mg", "A stress-free life with a cost...", 0);
		itemTier = Item.ultraRare;
	}

	@Override
	public void affect() 
	{
		Player.getPlayer().getStats().setMaxHealth(Player.getPlayer().getStats().getMaxHealth()*1.5f);
		Player.getPlayer().getStats().setBulletDamage(Player.getPlayer().getStats().getBulletDamage()*1.5f);
		Player.getPlayer().getStats().setFireRate(Player.getPlayer().getStats().getFireRate()*1.2f);
	}

}
