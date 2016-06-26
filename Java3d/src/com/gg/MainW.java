package com.gg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MainW  {


    public static void main(String[] args)  {
        Raytrace ga = new Raytrace();
        ga.addKeyListener(new Input());
        JFrame frame = new JFrame ();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(ga);
        frame.setLocation(50, 50);
        frame.setSize( ga.WIDTH, ga.HEIGHT);
        frame.setVisible(true);
       
        
        
        ga.start();
    }


}