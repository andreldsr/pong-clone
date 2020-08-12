package br.com.olerama.pongclone.entity;

import br.com.olerama.pongclone.core.Game;

import java.awt.*;

public class Player extends Entity{
    private boolean right, left;

    public Player(int x, int y) {
        super(x, y);
        this.width = 40;
        this.height = 5;
    }

    public void tick(){
        if(right)
            x++;
        if(left)
            x--;

        if(x + width > Game.WIDTH)
            x = Game.WIDTH - width;
        if(x < 0)
            x = 0;

    }

    public void render(Graphics g){
        g.setColor(Color.orange);
        g.fillRect(x,y,width,height);
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
