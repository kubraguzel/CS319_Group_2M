import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SurvivalInBilkent extends StateBasedGame {
	public static int FPS = 60;
	private String playerName;
	
	public SurvivalInBilkent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException 
	{
		addState(new UpgradeScreen());
		addState(new TitleMenu());
		addState(GameMaster.getGameMaster());
		addState(new CreditsMenu());
		addState(new PauseMenu());
		addState(new GameOverScreen());
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new SurvivalInBilkent ("Survival in Bilkent v0.8 beta"));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		app.setDisplayMode((int)width, (int)height, true);
		app.setTargetFrameRate(FPS);
		app.start();
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	

}
