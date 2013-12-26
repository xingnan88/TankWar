import java.awt.*;
import java.awt.event.*;
import java.awt.*;

import com.sun.corba.se.impl.protocol.giopmsgheaders.KeyAddr;

public class Window extends Frame
{   
	public int width,heigth;
	public Window(int width,int heigth,String title)
	{   
	  //���ô��ڵĻ�������
	  this.width=width;
	  this.heigth=heigth;
	  super.setTitle(title);
	  super.setSize(width,heigth);
	  super.setVisible(true );
	  this.setResizable(false);
	  this.setBackground(Color.gray);
	  
	  //��Ӳ˵�
	  MenuBar menuBar=new MenuBar();
	  this.setMenuBar(menuBar);
	  Menu menuFile=new Menu("�ļ�");
	  MenuItem begin=new MenuItem("��ʼ��Ϸ");
	  MenuItem exit=new MenuItem("�˳���Ϸ");
	  menuFile.add(begin);
	  menuFile.add(exit);
	  menuBar.add(menuFile);
	  this.addKeyListener(new KeyMonitor());
	  
	  //�رմ���
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
	
	//����һ��Բ��
	int x=50,y=150;
	public void paint(Graphics g)
	{   
		Color c=g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x,y,30,30);
		g.setColor(c);
		
	}
	
	//������˸���󣬰Ѷ����Ȼ���һ��ͼƬ�ϣ�Ȼ����һ�𻭳�������дupdate����
	Image offScreamImage=null;
	public void update(Graphics g) 
	{
		if (offScreamImage==null)
		{
			offScreamImage=this.createImage(width,heigth);
		}
		Graphics graphics=offScreamImage.getGraphics();
		//ˢ������һ������Ļһ����ľ���
		Color c=graphics.getColor();
		graphics.setColor(Color.gray);
		graphics.fillRect(0, 0,width, heigth);
		graphics.setColor(c);
		
		paint(graphics);
		g.drawImage(offScreamImage, 0, 0, null);
	}
	
	//�߳�,ʵ��Runnable�ӿڣ����ػ�Բ,�ڲ���
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
	
	//�ڲ��࣬ʵ��KeyAdapter�ӿ�
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
	
    //main����
	public static void main(String args[])
	{
		Window myFrame=new Window(800,800,"TankWar");
	}
}

  
