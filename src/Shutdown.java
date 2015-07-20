import java.io.IOException;

public class Shutdown {
	public Shutdown() throws InterruptedException {
		try {
			new ProcessBuilder("cmd.exe", "/c", "shutdown -p -f").start();
			//System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
