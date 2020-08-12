package br.com.olerama.pongclone.core;

import br.com.olerama.pongclone.entity.Ball;
import br.com.olerama.pongclone.entity.Enemy;
import br.com.olerama.pongclone.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {
    public static final int WIDTH = 160;
    public static final int HEIGHT = 120;
    public static final int SCALE = 7;
    private static boolean isRunning = false;
    private static Thread thread;
    private static JFrame frame;
    private BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public Game(){
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        configureFrame();
        addKeyListener(this);
        requestFocus();
        set();
    }

    public static void set(){
        player = new Player(WIDTH/2 - 20, HEIGHT - 5);
        enemy = new Enemy(WIDTH/2 - 20, 0);
        ball = new Ball(WIDTH / 2, HEIGHT / 2);
    }

    private void configureFrame(){
        frame = new JFrame("Frame Example");
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        var game = new Game();
        game.start();
    }

    private void start(){
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    private void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tick(){
        player.tick();
        enemy.tick();
        ball.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = layer.getGraphics();
        g.setColor(new Color(83, 135, 24));
        g.fillRect(0,0,WIDTH, HEIGHT);
        player.render(g);
        enemy.render(g);
        ball.render(g);

        g = bs.getDrawGraphics();
        g.drawImage(layer, 0,0,WIDTH * SCALE, HEIGHT * SCALE, null);
        bs.show();
    }

    public void run() {
        var lastTime = System.nanoTime();
        var frameRate = 60.0;
        var ns = Math.pow(10,9) / frameRate;
        var delta = 0d;
        var timer = System.currentTimeMillis();
        var frames = 0;
        while(isRunning){
            var now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                render();
                frames++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >= 1000){
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }

        stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.setRight(true);
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.setRight(false);
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.setLeft(false);
        }

    }
}
