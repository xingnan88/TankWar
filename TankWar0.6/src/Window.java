import java.awt.*;
import java.awt.event.*;
import java.awt.*;

import com.sun.corba.se.impl.protocol.giopmsgheaders.KeyAddr;

public class Window extends Frame
{   
	public int width,heigth;
	public Window(int width,int heigth,String title)
	{   
	  //设置窗口的基本属性
	  this.width=width;
	  this.heigth=heigth;
	  super.setTitle(title);
	  super.setSize(width,heigth);
	  super.setVisible(true );
	  this.setResizable(false);
	  this.setBackground(Color.gray);
	  
	  //添加菜单
	  MenuBar menuBar=new MenuBar();
	  this.setMenuBar(menuBar);
	  Menu menuFile=new Menu("文件");
	  MenuItem begin=new MenuItem("开始游戏");
	  MenuItem exit=new MenuItem("退出游戏");
	  menuFile.add(begin);
	  menuFile.add(exit);
	  menuBar.add(menuFile);
	  this.addKeyListener(new KeyMonitor());
	  
	  //关闭窗口
	  this.addWindowListener(
              new WindowAdapter()
              {
             	 public void windowClosing(WindowEvent e)
             	 {
             		 System.exit(0);
             	 }
              }
      	);
	  
	  new Thread(new PaintThread()).start();
	}
	
	//画出一个圆形
	int x=50,y=150;
	public void paint(Graphics g)
	{   
		Color c=g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x,y,30,30);
		g.setColor(c);
		
	}
	
	//处理闪烁现象，把东西先画在一张图片上，然后再一起画出来，重写update方法
	Image offScreamImage=null;
	public void update(Graphics g) 
	{
		if (offScreamImage==null)
		{
			offScreamImage=this.createImage(width,heigth);
		}
		Graphics graphics=offScreamImage.getGraphics();
		//刷屏，画一个和屏幕一样大的矩形
		Color c=graphics.getColor();
		graphics.setColor(Color.gray);
		graphics.fillRect(0, 0,width, heigth);
		graphics.setColor(c);
		
		paint(graphics);
		g.drawImage(offScreamImage, 0, 0, null);
	}
	
	//线程,实现Runnable接口，来重画圆,内部类
	private class PaintThread implements Runnable
	{
		public void run() 
		{
			while(true)
			{
				repaint();
				try
				{
					Thread.sleep(50);
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//内部类，实现KeyAdapter接口
	private class KeyMonitor extends KeyAdapter
	{

		public void keyPressed(KeyEvent e) 
		{
			int key=e.getKeyCode();
			/*if (key==KeyEvent.VK_RIGHT)
			{
				x+=5;
			}
			if (key==KeyEvent.VK_DOWN)
			{
				y+=5;
			}
			if (key==KeyEvent.VK_LEFT)
			{
				x-=5;
			}
			if (key==KeyEvent.VK_UP)
			{
				y-=5;
			}*/
			switch(key)
			{
				case KeyEvent.VK_RIGHT:
					x+=5;
					break;
				case KeyEvent.VK_DOWN:
					y+=5;
					break;
				case KeyEvent.VK_LEFT:
					x-=5;
					break;
				case KeyEvent.VK_UP:
					y-=5;
					break;
			}
		}
		
	}
	
    //main方法
	public static void main(String args[])
	{
		Window myFrame=new Window(800,800,"TankWar");
	}
}

  
