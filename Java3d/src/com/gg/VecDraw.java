package com.gg;

import java.awt.Color;
import java.awt.Graphics;

import com.gg.Thing.Vec;

public class VecDraw {
	public static void Line( Graphics g, Vec s, Vec e ){
		g.drawLine((int)s.x(), (int)s.y(), (int)e.x(), (int)e.y());
	}
	
	public static void Rect( Graphics g, Vec s, Vec e ){
		g.drawRect((int)s.x(), (int)s.y(), (int) e.x(), (int)e.y());
	}
	
	public static void Circle( Graphics g, Vec s, Vec e){
		g.drawArc((int)s.x(), (int)s.y(), (int) e.x(), (int) e.y(), 0, 360);
	}
	
	public static void Arc( Graphics g, Vec s, Vec e, int rs, int re){
		g.drawArc((int)s.x(), (int)s.y(), (int) e.x(), (int)e.y(), rs, re);
	}
	
	public static void Tri( Graphics g, Vec a, Vec b, Vec c, Color d){
		g.setColor(d);
		g.fillPolygon(new int[] { (int) a.x(), (int) b.x(), (int) c.x()}, 
				      new int[] { (int) a.y(), (int) b.y(), (int) c.y()}, 
				      3);
	}
	
	public static void Tri( Graphics g, Vec a, Vec b, Vec c ){
		g.setColor(Color.WHITE);
		g.fillPolygon(new int[] { (int) a.x(), (int) b.x(), (int) c.x()}, 
				      new int[] { (int) a.y(), (int) b.y(), (int) c.y()}, 
				      3);
	}
}
