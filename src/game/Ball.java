package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	public double x, y;
	public int width;
	public int height;
	public double dx, dy;
	public double speed = 1.4;
	public static int playerPoint = 0;
	public static int enemyPoint = 0;
	public static String playerPointTxt;
	public static String enemyPointTxt;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 3;
		this.height = 3;
		
		int angle = new Random().nextInt(120-45) + 25;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		//lógica de colisão
		if(x+(dx*speed) + width >= Game.WIDTH) {
			dx *= -2;
		}
		else if(x + (dx*speed)-width < 0) {
			dx *= -2;
		}
		
		if(y >= Game.HEIGHT) {
			//ponto do inimigo
			enemyPointTxt = "Ponto do inimigo";
			enemyPoint++;
			new Game();
			return;
		}
		else if(y <= 0) {
			//ponto do jogador
			playerPointTxt = "Ponto nosso, caraio!!!";
			playerPoint++;
			new Game();
			return;
		}
		
		if(enemyPoint == 10) {
			enemyPointTxt = "Game Over";
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, (int)Game.enemy.width, (int)Game.enemy.height);
		if(bounds.intersects(boundsPlayer)) {
			dy *= -1.1;
		}
		else if(bounds.intersects(boundsEnemy)) {
			dy *= -1.1;
		}
		
		
		x += dx*speed;
		y += dy*speed;
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int)x, (int)y, width, height);
	}

}
