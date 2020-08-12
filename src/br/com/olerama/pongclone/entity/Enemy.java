package br.com.olerama.pongclone.entity;

import br.com.olerama.pongclone.core.Game;

import java.awt.*;

public class Enemy {
    private double x, y;
    private int width, height;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.height = 5;
        this.width = 40;
    }

    public void tick(){
        x += (Game.ball.getX() - x - 10) * 0.1;
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, width, height);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
