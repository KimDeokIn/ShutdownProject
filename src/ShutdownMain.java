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
					"��ȣ ���α׷��� ���� ������� ���߽��ϴ�", "���!! ���α׷��� �����մϴ�",
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
			countdown = new MenuItem("ī��Ʈ �ٿ� ����");
			inform = new MenuItem("���α׷� ����");
			mItemExit = new MenuItem("��ȣ �Է�");
			
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
			showMessage("Info", "����� �㰡���� ���� ������Դϴ�.\n��ȣ�� �Է��Ͽ� �����㰡�� ��������.");
		} else if (ae.getSource() == mItemExit) {
			intputMessage("���!!", "��ȣ�� �Է��ϼ���.");
		}
	}

	private void intputMessage(String title, String message) {
		String s = JOptionPane.showInputDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
		if(s != null && s.equals(password)){
			showMessage(title,"����� �㰡�Ǿ����ϴ�.");
			System.exit(0);
		} else {
			showMessage(title,"��ȣ�� �߸� �Է��ϼ̽��ϴ�!\n�ð��� �� ���� �ʾҽ��ϴ�!!!!");
		}
	}

	private void showMessage(String title, String message) {
		JOptionPane.showMessageDialog(null,  message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String[] args){
		new ShutdownMain();
	}
}
