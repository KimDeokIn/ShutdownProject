import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


public class PasswordFrame extends WindowAdapter{

	private JFrame frame;
	private JPasswordField passwordField;
	private String password;
	/**
	 * Create the application.
	 */
	public PasswordFrame(String password) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		this.password = password;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("경고!!!");
		frame.setBounds(100, 100, 350, 104);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(this);
		
		JLabel label = new JLabel("암호를 입력하세요.");
		label.setBounds(12, 10, 140, 15);
		frame.getContentPane().add(label);
		
		JCheckBox chBox = new JCheckBox("입력한 암호 보기");
		chBox.setBounds(196, 6, 130, 23);
		frame.getContentPane().add(chBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(12, 35, 205, 21);
		frame.getContentPane().add(passwordField);
		passwordField.requestFocus();
		
		JButton btn = new JButton("확인");
		btn.setBounds(229, 34, 97, 23);
		frame.getContentPane().add(btn);
		
		char echoChar = passwordField.getEchoChar();
		chBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent iev) {
				if(passwordField.getEchoChar() == echoChar) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar(echoChar);
				}
			}
		});
		
		btn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (password.equals(passwordField.getText())) {
					showMessage("확인!", "사용이 허가되었습니다.");
					System.exit(0);
				} else {
					showMessage("경고!", "암호를 잘못 입력하였습니다!\n시간이 얼마 남지 않았습니다!!");
				}
			}
		});
		
		passwordField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btn.doClick();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void showMessage(String title, String message) {
		JOptionPane.showMessageDialog(null,  message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		JOptionPane.showMessageDialog(null, "암호 입력 후 확인 버튼을 눌러주세요!\n시간이 얼마 남지 않았습니다!!!!", "경고!!", JOptionPane.INFORMATION_MESSAGE);
		frame.dispose();
	}
}
