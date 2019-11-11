package armase.anothernight;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;

// This is the launcher for the console line version
// This class must be specified as Main in build when building console version
// IF PATH OR FILE NAME HAVE WHITESPACES IT WON't WORK

public class ConsoleLauncher {
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		Console console = System.console();
		if (console == null && !GraphicsEnvironment.isHeadless()) {
			String filename = ConsoleLauncher.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
			Runtime.getRuntime()
					.exec(new String[] { "cmd", "/c", "start", "cmd", "/k", "java -jar \"" + filename + "\"" });
		} else {
			Launcher.main(new String[0]);
			System.out.println("Program has ended, please type 'exit' to close the console");
		}
	}
}