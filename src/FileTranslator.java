import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FileTranslator {
	
	private Scanner file;
	private String password;
	private int time;
	
	public FileTranslator(String text) {
		try {
			file = new Scanner(new File(text));	
			setPassword(text);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,  
					"파일 오류!!", text + " 파일을 확인해주세요!!",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	private void setPassword(String text) throws IOException {
		StreamTokenizer in = new StreamTokenizer(
				new BufferedReader(new FileReader(text)));
		Interpreter ip = new Interpreter(in);
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			in.pushBack();
			ip.parseStatement();
		}
		password = ip.getWord("password");
		time = Integer.parseInt(ip.getWord("time"));
		file.close();
	}

	public String getPassword() {
		return password;
	}
	
	public int getTime() {
		return time;
	}
}

