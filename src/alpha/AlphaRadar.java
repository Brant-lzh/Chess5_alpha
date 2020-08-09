package alpha;
import util.Monitor;
import java.awt.Point;

public class AlphaRadar extends AlphaAdapter{
	private final int R = 4; //雷达扫描半径
	private Monitor monitor;
	
	public String getName(){return "雷达";}
	public String getDesc(){
		return "雷达策略。附近棋子愈多愈近则愈有价值";
	}
	public void setData(int[][] data){
		monitor = new Monitor(data);
		super.setData(data);
	}
	public Point next(){
		int color = getColor()==Alpha.BLACK?Alpha.WHITE:Alpha.BLACK;//对方颜色
		System.out.println( monitor.getLast(color) );
		return super.next();
	}
	
	/** 求(x1,y1)与(x2, y2)两点之间的距离 */
	
	private double distance(double x1, double y1, double x2, double y2){
		return Math.sqrt( Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2) );
	}
	
	public double weight(int x, int y){	
		int[][] data = getData();		
		if(data[x][y] != Alpha.KONG ) return 0; 	
		return weight1(x, y)*0.7 + weight2(x, y)*0.3;
	}
	public double weight1(int x, int y){		
		int[][] data = getData();		
		if(data[x][y] != Alpha.KONG ) return 0; 		
		double rtn = 0;
		for(int i=0; i<data.length; i++) {
			for(int j=0; j<data[i].length; j++) {
				if( data[i][j] == Alpha.KONG) continue; 
				double dist = distance(x, y, i, j);
				if(dist <= R){
					rtn += 1/dist;
				}
			}
		}
		return rtn;		
	}
	//离中心位置越近越佳
	public double weight2(int x, int y){
		int[][] data = getData();
		if(data[x][y] != Alpha.KONG ) return 0;		
		return 1/distance(x, y, data.length/2, data.length/2);
	}
}