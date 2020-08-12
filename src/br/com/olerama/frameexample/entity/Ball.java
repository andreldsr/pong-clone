package br.com.olerama.frameexample.entity;

import java.awt.*;
import java.util.Random;

public class Ball extends Entity{
    private double dx, dy, speed;

    public Ball(int x, int y) {
        super(x, y);
        this.width = this.height = 4;
        dx = new Random().nextGaussian();
        dy = new Random().nextGaussian();
        speed = 1.2;
    }

    @Override
    public void tick() {
        x += dx * speed;
        y += dy * speed;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,y,width,height);
    }


}
