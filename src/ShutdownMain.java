import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ShutdownMain implements ActionListener{
	private SystemTray systemTray;
	private PopupMenu mPopup;
	private MenuItem countdown, inform, mItemExit;
	private String password;
	private WallClock clock;
	private FileTranslator configFile;
	
	ShutdownMain() {
		try{
			initSystemTrayIcon();
		} catch(AWTException awte) {
			JOptionPane.showMessageDialog(null,  
					"보호 프로그램이 정상 실행되지 못했습니다", "경고!! 프로그램을 종료합니다",
					JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

	private void initSystemTrayIcon() throws AWTException {
		if (SystemTray.isSupported()) {
			configFile = new FileTranslator("config.txt");
			password = configFile.getPassword();
			
			int time = configFile.getTime();
			int hour = time/3600, minute = time/60, second = time%60;
			clock = new WallClock(hour, minute, second);
			
			mPopup = new PopupMenu();
			countdown = new MenuItem("카운트 다운 보기");
			inform = new MenuItem("프로그램 정보");
			mItemExit = new MenuItem("암호 입력");
			
			countdown.addActionListener(this);
			inform.addActionListener(this);
			mItemExit.addActionListener(this);
			
			mPopup.add(countdown);
			mPopup.addSeparator();
			mPopup.add(inform);
			mPopup.addSeparator();
			mPopup.add(mItemExit);
			
			Image image = Toolkit.getDefaultToolkit().getImage("icon.png");
			TrayIcon trayIcon = new TrayIcon(image, "java Test", mPopup);
			trayIcon.setImageAutoSize(true);
			
			systemTray = SystemTray.getSystemTray();
			systemTray.add(trayIcon);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == countdown) {
			clock.setVisible(true);
		} else if (ae.getSource() == inform) {
			JOptionPane.showMessageDialog(null,  "당신은 허가되지 않은 사용자입니다.\n암호를 입력하여 실행허가를 받으세요.", "Infomation", JOptionPane.INFORMATION_MESSAGE);
		} else if (ae.getSource() == mItemExit) {
			new PasswordFrame(password);
		}
	}
	
	public static void main(String[] args){
		new ShutdownMain();
	}
}

