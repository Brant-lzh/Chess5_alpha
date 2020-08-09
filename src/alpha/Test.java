package alpha;

public class Test {
	
	public static void main(String[] args) {
		int[][][] x = new int[10][10][];  
	    int[][][] y = new int[10][10] [];
	    int icount=0;
		// TODO Auto-generated method stub
         for(int i=0;i<10;i++)  
             for(int j=0;j<(10-4);j++){  
                 icount++;  
             }  
         System.out.println(icount);
         //竖  
         for(int i=0;i<10;i++)  
             for(int j=0;j<(10-4);j++){  
                 icount++;  
             }  
         //右斜  
         for(int i=0;i<(10-4);i++)  
             for(int j=0;j<(10-4);j++){  
                 icount++;  
             }  
         //左斜  
         for(int i=0;i<(10-4);i++)  
             for(int j=(10-1);j>=4;j--){  //改了
                 icount++;  
             }  
        System.out.println("赢法"+icount);
	}
}
