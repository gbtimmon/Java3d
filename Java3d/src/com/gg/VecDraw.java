package com.gg;

import java.awt.Graphics;

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
}
