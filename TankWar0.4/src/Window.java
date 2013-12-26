import java.awt.*;
import java.awt.event.*;
import java.awt.*;

public class Window extends Frame
{
	public Window(int width,int highth,String title)
	{   
	  //���ô��ڵĻ�������
	  super.setTitle(title);
	  super.setSize(width,highth);
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
		
		y+=5;
	}
	
	//�߳�,ʵ��Runnable�ӿڣ����ػ�Բ
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
	
    //main����
	public static void main(String args[])
	{
		Window myFrame=new Window(800,800,"TankWar");
	}
}

  
