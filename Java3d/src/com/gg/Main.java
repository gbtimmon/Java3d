package com.gg;

import javax.swing.JFrame;

public class Main  {


    public static void main(String[] args)  {
        Game ga = new Game();
        ga.addKeyListener(new Input());
        JFrame frame = new JFrame ();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(ga);
        frame.setLocation(800, 50);
        frame.setSize( Game.WIDTH, Game.HEIGHT);
        frame.setVisible(true);
       
        
        
        ga.start();
    }


}