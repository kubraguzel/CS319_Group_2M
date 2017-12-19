
public class HigherIQ extends Item {

	public HigherIQ() {
		super("Higher IQ", "You are better, smarter, faster but /n you are expected to do these in half the time.", 0);
		itemTier = Item.hacker;
	}

	@Override
	public void affect() {
		Player.getPlayer().getStats().setFireRate(Player.getPlayer().getStats().getFireRate()*0.8f);
		Player.getPlayer().getStats().setBulletSpeed(Player.getPlayer().getStats().getBulletSpeed()*1.4f);
		Player.getPlayer().getStats().setBulletDamage(Player.getPlayer().getStats().getBulletDamage()*1.3f);
		Player.getPlayer().getStats().setMaxHealth(Player.getPlayer().getStats().getMaxHealth()*0.5f);

	}

}
