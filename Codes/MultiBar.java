
/**
 * 
 * Author: Semih Teker
 * 
 */

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class MultiBar extends UIObject {
	Vector2f position;
	Vector2f dimension;
	ArrayList<Layer> layerList;
	boolean horizontal;
	Enemy enemy;
	Player player;
	
	public static Color color1 = Color.white;
	public static Color color2 = Color.orange;
	public static Color color3 = Color.red;
	public static Color color4 = Color.blue;
	
	/*public MultiBar(Vector2f position, Vector2f dimension, Enemy enemy, boolean horizontal) {
		super(position, dimension);
		this.enemy = enemy;
		layerList = new ArrayList<Layer>();
		//this.layerList = layerList;
	}*/
	
	public MultiBar(Enemy enemy, boolean horizontal) {
		position = enemy.getPosition().add(new Vector2f(-20f, -20f));
		dimension = new Vector2f(2f,2f); 
		layerList = new ArrayList<Layer>();
		this.enemy = enemy;
		//this.layerList = layerList;
		addLayer(enemy, enemy.numOfLayer);
	}
	
	public MultiBar(Player player, boolean horizontal) {
		position =  new Vector2f(0f, 0f);
		dimension = new Vector2f(0f, 0f); 
		this.player = player;
		layerList = new ArrayList<Layer>();
		addLayer(player);
	}
			
	public void addLayer(Enemy enemy, int num){
		
		layerList.add(new Layer(color1, enemy.enemyStats.getMaxHealth(), enemy, true));
		if(num <= 2){
			layerList.add(new Layer(color3, enemy.enemyStats.getCurHealth(), enemy, false));
		}
		else if(num > 2){
			layerList.add(new Layer(color3, enemy.enemyStats.getCurHealth(), enemy, false));
			layerList.add(new Layer(color2, enemy.enemyStats.getCurHealth(), enemy, false));
		}
	}
	
	public void addLayer(Player player){
		layerList.add(new Layer(color1, player.playerStats.getMaxHealth(), player, true));
		//layerList.add(new Layer(color2, player.playerStats.getCurHealth(), player, false));
		layerList.add(new Layer(color4, player.playerStats.getCurHealth(), player, false));
	}
	
	public void removeLayer(int index){
		layerList.remove(index);
	}
	
	public void setLayerColor(int index, Color color){
		
	}
	
	public void setLayerPercentage(int index, float percentage, int num){
		/*	
		if(num<=2){
			rate = (player.playerStats.getCurHealth()/player.playerStats.getMaxHealth())*100;
			layerList.get(index).percentage = percentage;
		}
		else{
			rate = (enemy.enemyStats.getCurHealth()/enemy.enemyStats.getMaxHealth())*100;
			if(rate>50){
				layerList.get(index).percentage = percentage; //((rate-50)/50)*100;
			}
			else{
				layerList.get(index).percentage = (rate/50)*100;
			}
		}*/
	}
	
	@Override
	public void draw(Graphics g) 
	{
		if(layerList.size()>=1){
			for(int i=0; i<layerList.size(); i++){				
				if(layerList.get(i).percentage > 0){
					layerList.get(i).draw(g);
				}
				else{
					layerList.remove(i);
				}
			}
		}
		if(layerList.size()>2){
			if(layerList.get(2).isPlayer){
				if(((layerList.get(2).player.playerStats.getCurHealth()/layerList.get(2).player.playerStats.getMaxHealth())*100)<=50){
					layerList.remove(2);
				}
			}else{
				if(((layerList.get(2).enemy.enemyStats.getCurHealth()/layerList.get(2).enemy.enemyStats.getMaxHealth())*100)<=50){
					layerList.remove(2);
				}
			}
		}
	}
	
	public void move() 
	{	
		if(layerList.size()>=1){
			for(int i=0; i<layerList.size(); i++){
				layerList.get(i).move();
			}
		}
		else{
			this.setToBeRemoved(true);
		}
	}
	
	public void update() 
	{
		//setLayerPercentage();
		for(int i=0; i<layerList.size(); i++){
			layerList.get(i).update();
		}
		
		for(int i=0; i<layerList.size(); i++){
			if(layerList.get(i).isPlayer){
				if(player.isToBeRemoved()){
					setToBeRemoved(true);
				}
			}
			else{
				if(enemy.isToBeRemoved()){
					setToBeRemoved(true);
				}
				else{
					move();
				}
			}
		}
	}
}
