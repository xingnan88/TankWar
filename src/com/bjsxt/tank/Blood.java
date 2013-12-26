package com.bjsxt.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blood
{
	private int x, y, w, h;
	private int step = 0;
	private TankWindow tw;
	private boolean live=true;
	private int[][] pos =
	{
	{ 300, 250 },
	{ 310, 260 },
	{ 320, 270 },
	{ 330, 280 },
	{ 340, 290 },
	{ 350, 300 },
	{ 360, 310 },
	{ 370, 320 } };

	public Blood()
	{
		x = pos[0][0];
		y = pos[0][1];
		w=h=15;
	}

	public void draw(Graphics g)
	{
		if (!live) return;
		Color c = g.getColor();
		g.setColor(Color.magenta);
		g.fillRect(x, y, w, h);
		g.setColor(c);

		move();
	}

	private void move()
	{
		step++;
		if (step >= pos.length)
		{
			step = 0;
		}
		x = pos[step][0];
		y = pos[step][1];
	}
	
	public Rectangle getRect()
	{
		return new Rectangle(x,y,w,h);
	}
	
	public boolean isLive()
	{
		return live;
	}

	public void setLive(boolean live)
	{
		this.live = live;
	}
}
