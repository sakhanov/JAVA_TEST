package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;

import static javax.imageio.ImageIO.read;


public class GameWindow extends JFrame {
    private static  GameWindow  gameWindow;
    private  static long last_frame_time;
    private static Image background;
    private static Image drop;
    private static Image game_over;
    private static float drop_left = 200;
    private  static float drop_top = -100;
    private  static float drop_v = 200;
    private  static  int score = 0;
    private static  float rectX = 10;
    private static float deltaX = 1;
    private static int rectY  = 100;
    private static int rectW = 100;
    private static int rectH = 5;

    private static float ballX = 100; // положение мяча по горизонту
    private static float ballY = 50;
    private static float speedX = 50;
    private static float speedY = 50;
    private static int ballR = 5; // радиус мяча
    public GameWindow(){
        MyKey gamer = new MyKey();
        addKeyListener(gamer);
        setFocusable(true);


    }
    private  class MyKey implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {
            int k =0;

        }

        public void keyPressed(KeyEvent e) {
            int location = e.getKeyCode();
            int k = KeyEvent.VK_RIGHT;
           // rectX += 1;
            if (location == KeyEvent.VK_RIGHT) {
                rectX += 10;
            }
            if (location == KeyEvent.VK_LEFT) {
                rectX -= 10;
            }
           if ( rectX < 0) rectX =0;
            if (gameWindow.getWidth() - rectW < rectX) rectX = gameWindow.getWidth() - rectW;
//        }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int k =0;

        }
    }
    public static void main(String[] args) throws IOException  {
	// write your code here

        try {
            background = ImageIO.read(GameWindow.class.getResourceAsStream("background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            game_over = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            drop = ImageIO.read(GameWindow.class.getResourceAsStream("drop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameWindow = new GameWindow();



        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(100,200);
        gameWindow.setSize(906,478);
        gameWindow.setResizable(false);
        GameField game_filed = new GameField();

        game_filed.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                float drop_right = drop_left + drop.getWidth(null);
                float drop_botton = drop_top + drop.getHeight(null);
                boolean is_drop  = x >= drop_left && x <= drop_right && y >= drop_top && y <= drop_botton;
                if (is_drop){
                    drop_top = -100;
                    drop_left = (float)(Math.random()* (game_filed.getWidth() - drop.getWidth(null)) );
                    drop_v +=  10;
                    score ++;
                    gameWindow.setTitle("Score" + score);
                }
            }
        });
        last_frame_time = System.nanoTime();
        gameWindow.add(game_filed);
        gameWindow.setVisible(true);
    }
    private static void onRepaint(Graphics g){
        g.drawImage(background,0,0,null);

        // g.fillOval(10,10,200,100);
        int testY = gameWindow.getHeight() - rectH - rectY;
        g.fillOval((int)ballX, (int)ballY, ballR, ballR);
        g.fillRect((int)rectX,gameWindow.getHeight() - rectH - rectY,rectW,rectH);
        long current_time = System.nanoTime();
        float delta_time = (current_time - last_frame_time) * 0.000000001f;
        last_frame_time = current_time;
        drop_top += delta_time * drop_v * 0;
        ballX += delta_time * speedX;
        ballY += delta_time * speedY;

        // проверяем попали ли мы в ракетку
        if (ballY >= testY){
            if (ballX >= rectX && ballX <= rectX + rectW){
                speedY *= -1;
                //speedX *= -1;
            }

        }
        if (ballY <= 0) {
            speedY *=-1;
            //speedX *= -1;
        }
        if (ballX <= 0 || ballX >= gameWindow.getWidth()){
            //speedY *=-1;
            speedX *= -1;
        }
        if (ballY > gameWindow.getHeight()) {
            g.drawImage(game_over, 280, 120, null);
        }

    }
    private static class GameField extends JPanel{

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}
