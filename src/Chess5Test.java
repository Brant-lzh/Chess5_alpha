import alpha.*;
import java.awt.*;
import javax.swing.*;

public class Chess5Test {
	public static void main(String[] args) {
		JFrame frame = new JFrame("五子棋");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Chess5Panel c5 = new Chess5Panel();
		//Alpha alpha = new AlphaGo();
		Alpha alpha = new Alpha1740224170();
		alpha.setColor( Alpha.BLACK ); //机器执黑先手
		//alpha.setColor( Alpha.WHITE ); //机器执白后手
		c5.setAlpha( alpha );
		
		Student student = (Student)alpha;
		JOptionPane.showMessageDialog(null, student.getDesc());		
		frame.setTitle(student.getSID() + student.getName() 
						+ " " + student.getDept());
						
		frame.getContentPane().add( c5 );		
		frame.pack();		
		frame.setVisible(true);
	}
}