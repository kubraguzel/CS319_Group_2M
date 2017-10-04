import javax.swing.JFrame;

public class GameTest {

	public static void main(String[] args) 
	{
		JFrame window = new JFrame ("Hello");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setContentPane(new GamePanel());
		
		window.pack();
		window.setVisible(true);
	}

}
