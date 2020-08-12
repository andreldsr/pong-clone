package br.com.olerama.pongclone.entity;

import br.com.olerama.pongclone.core.Game;

import java.awt.*;
import java.util.Random;

public class Ball extends Entity{
    private double x,y, dx, dy, speed;

    public Ball(int x, int y) {
        super(x, y);
        this.width = this.height = 4;

        int angle = new Random().nextInt(120 - 45) + 45 + 1;
        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
    }

    @Override
    public void tick() {
       if(x+(dx*speed) + width >= Game.WIDTH ) {
                dx*=-1;
            }else if(x+(dx*speed) < 0) {
                dx*=-1;
            }
            
            if(y >= Game.HEIGHT)
            {
                //Ponto do inimigo.
                System.out.println("Ponto do inimigo!");
                new Game();
                return;
            }else if(y < 0) {
                //Ponto do jogador.
                System.out.println("Ponto nosso! YAYY!");
                new Game();
                return;
            }
            
            Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
            
            Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
            Rectangle boundsEnemy = new Rectangle((int)Game.enemy.getX(),(int)Game.enemy.getY(),Game.enemy.getWidth(),Game.enemy.getHeight());
            
            if(bounds.intersects(boundsPlayer)) {
                int angle = new Random().nextInt(120 - 45) + 45 + 1;
                dx = Math.cos(Math.toRadians(angle));
                dy = Math.sin(Math.toRadians(angle));
                if(dy > 0)
                    dy*=-1;
            }else if(bounds.intersects(boundsEnemy)) {
                int angle = new Random().nextInt(120 - 45) + 45 + 1;
                dx = Math.cos(Math.toRadians(angle));
                dy = Math.sin(Math.toRadians(angle));
                if(dy < 0)
                    dy*=-1;
            }
            
            
            x+=dx*speed;
            y+=dy*speed;
            
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x,(int)y,width,height);
    }


}
