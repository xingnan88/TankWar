import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.protocol.giopmsgheaders.KeyAddr;

public class TankWindow extends Frame
{   
	public int width,heigth;
	Tank myTank=new Tank(150,150,true,this);
	Tank enemyTank=new Tank(100,100,false,this);
	List<Missile> missiles=new ArrayList<Missile>();
	
	public TankWindow(int width,int heigth,String title)
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
	
	//注意面向对象的思想，画坦克是Tank自己的事情，交给Tank自己去做，这里直接调它的引用就是
	public void paint(Graphics g)
	{   
		myTank.draw(g);
		enemyTank.draw(g);
		for (int i=0;i<missiles.size();i++)
		{
			Missile m=missiles.get(i);
			if (enemyTank.isLive())//敌人活着的时候，才有必要去调hitTank方法
			m.hitTank(enemyTank);
			m.draw(g);
		}
		g.drawString("容器 累计:"+missiles.size(), 30, 80);
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
		public void keyReleased(KeyEvent e)
		{
			myTank.keyReleased(e);
		}

		//直接调用keyPress
		public void keyPressed(KeyEvent e) 
		{
			myTank.keyPress(e);
		}
		
	}
	
    //main方法
	public static void main(String args[])
	{
		TankWindow m=new TankWindow(800,800,"TankWar");
	}
}

  
