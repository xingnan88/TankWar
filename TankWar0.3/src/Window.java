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
	}
  
	public void paint(Graphics g)
	{   
		Color c=g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(50,50,30,30);
		g.setColor(c);
	}
  
	public static void main(String args[])
	{
		Window myFrame=new Window(800,800,"TankWar");
	}
}

  
