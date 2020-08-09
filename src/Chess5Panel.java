import alpha.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chess5Panel extends JPanel {
	private int x0=30, y0=30; //棋盘左上角的位置	
	//private int N = 14; //N*N的棋盘
	private int N = 9; //N*N的棋盘
	private int k = 60; //方格宽（高）度	
	private int r = 20; //棋子（圆）半径	
	private int[][] data; //棋盘状态：0-无子（默认），1-黑子，2-白字
	
	//阿尔法对象
	private Alpha alpha;
	public void setAlpha(Alpha alpha){
		alpha.setData( data );
		this.alpha = alpha;
		//机器执黑先手
		if(alpha.getColor()==Alpha.BLACK) {
			Point p = alpha.next();
			data[p.x][p.y] = alpha.getColor();
		}
	}
	
	public Chess5Panel() {
		setPreferredSize( new Dimension(600, 600) );
		data = new int[N+1][N+1];		
		addMouseListener( new ChessMouseListener() );
	}
	public void paint(Graphics g){
		for(int i=0; i<=N; i++) {
			g.drawLine(x0, y0+i*k, x0+N*k, y0+i*k);	
		}
		for(int i=0; i<=N; i++) {
			g.drawLine(x0+i*k, y0, x0+i*k, y0+N*k);
		}
		for(int i=0; i<=N; i++){
			for(int j=0; j<=N; j++){
				drawChess(i, j, data[i][j], g);
			}
		}		
	}
	/** 
	*  在指定棋盘坐标绘制指定颜色的棋子（圆）
	 * @param x 棋盘横坐标
	 * @param y 棋盘纵坐标
	 * @param color 棋子颜色，1为黑子，2为白子
	 * @param g 图形设备上下文
	 */
	public void drawChess(int x, int y, int color, Graphics g){
		if(color==Alpha.BLACK)
			g.fillOval(x0+x*k-r, y0+y*k-r, 2*r, 2*r);
		else if(color==Alpha.WHITE)
			g.drawOval(x0+x*k-r, y0+y*k-r, 2*r, 2*r);
	}
	/**
	 * 内部类，鼠标事件监听器。
	 */
	
	private class ChessMouseListener implements MouseListener {	
		public void mouseClicked(MouseEvent e){
			int x = Math.round( (e.getX()-x0)/(k+0.0f) );
			int y = Math.round( (e.getY()-y0)/(k+0.0f) );
			if(data[x][y] > 0) return; //非空，无效！						
			//人下子颜色与机器相反
			data[x][y] = alpha.getColor()==Alpha.BLACK? Alpha.WHITE : Alpha.BLACK; 
			Point p = alpha.next();
			System.out.println(p.x+"     "+p.y);
			data[p.x][p.y] = alpha.getColor(); 
			repaint(); //重绘
			
		}
		public void mousePressed(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
	}
}