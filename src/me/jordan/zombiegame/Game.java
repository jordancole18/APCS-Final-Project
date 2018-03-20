package me.jordan.zombiegame;

import java.awt.Toolkit;

import javafx.scene.input.KeyCode;
import javafx.stage.StageStyle;
import me.jordan.zombiegame.entity.EntityType;
import me.jordan.zombiegame.utils.Utils;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;

@SuppressWarnings("restriction")
public class Game extends GameApplication{

	public static final double PLAYER_SPEED = 3.2;
	public static final String OS = System.getProperty("os.name").toString().toLowerCase();
	public static final int SW = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int SH = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final int HALFW = SW / 2;
	public static final int HALFH = SH / 2;
	public static final String GAME_VERSION = "0.1";
	
	public static void main(String[] args){
		launch(args);
	}
	
	private Entity player;
//	private boolean isHost = true;
//	private boolean isConnected = false;
	
	public Game(){
		
	}
	
	@Override
	protected void initGame(){
		player = Entities.builder()
				.at(200, 200)
				.viewFromTexture("player.png")
				.rotate(90)
				.type(EntityType.PLAYER)
				.build();
		
		getGameWorld().addEntities(player);
	}
	
	@Override
	public void initUI(){
		
	}
	
	@Override
	public void initInput(){
		
		Input input = getInput();
		
	    input.addAction(new UserAction("Move Up") {
	        @Override
	        protected void onAction() {
	            player.translateY(-PLAYER_SPEED);
	        }
	    }, KeyCode.W);
		
	    input.addAction(new UserAction("Move Down") {
	        @Override
	        protected void onAction() {
	            player.translateY(PLAYER_SPEED);
	        }
	    }, KeyCode.S);
	    
	    input.addAction(new UserAction("Move Left") {
	        @Override
	        protected void onAction() {
	            player.translateX(-PLAYER_SPEED);
	        }
	    }, KeyCode.A);
	    
	    input.addAction(new UserAction("Move Right") {
	        @Override
	        protected void onAction() {
	            player.translateX(PLAYER_SPEED);
	        }
	    }, KeyCode.D);
	}
	
	@Override
	public void onUpdate(double tfp){
		player.setRotation(-Utils.calculateAngle(player.getX(), player.getY(), getInput().getMouseXWorld(), getInput().getMouseYWorld()) + 95);
	}
	
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setFullScreenAllowed(true);
		settings.setTitle("Zombie Game");
		settings.setWidth(SW);
		settings.setHeight(SH);
		settings.setVersion(GAME_VERSION);
		settings.setIntroEnabled(true);
		settings.setMenuEnabled(true);
		settings.setStageStyle(StageStyle.UNDECORATED);
	}
	
	public Entity getPlayer(){
		return player;
	}
	
}
