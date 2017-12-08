package sysc3010;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class alarm implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Toolkit.getDefaultToolkit().beep();
	}
		
	public static void CallAlarm()
	{
		ActionListener alarm = new alarm();
		Timer t = new Timer(1000,alarm);
		t.start();
		JOptionPane.showMessageDialog(null,"Alarm starts, press 'OK' to cancel ");
		System.exit(0);
	}
}
