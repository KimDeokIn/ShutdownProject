import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WallClock extends JFrame 
implements ActionListener, WindowListener {

	private JLabel clockDisplay;
	private Clock clock;
	private Timer timer;
	private final int DELAY = 1000;
	
	/**
	 * Create a WallClock.
	 */
	public WallClock(int hour, int minute, int second) {
		setTitle("WallClock");
		clock = new Clock(hour, minute, second);
		clockDisplay = new JLabel(clock.getTime());
		clockDisplay.setFont(clockDisplay.getFont().deriveFont(64f));
		clockDisplay.setBorder(new EmptyBorder(10, 20, 10, 20));
		add(clockDisplay);

		timer = new Timer(DELAY, this);
		timer.start();

		pack();
		setLocationRelativeTo(null);
		addWindowListener(this);
	}

	public void setVisible(boolean b) {
		super.setVisible(b);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(!clock.timeTick()) {
			try {
				new Shutdown();
			} catch (InterruptedException e1) {
				JOptionPane.showMessageDialog(null, "명령어 에러", 
						"자동 종료 명령어를 실행하지 못했습니다", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
		clockDisplay.setText(clock.getTime());
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		this.setVisible(false);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}