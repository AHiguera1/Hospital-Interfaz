package hosp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger implements Serializable {

	public static void createLogger() {
		try {
			File myObj = new File("C:\\Users\\Andrés\\Documents\\Logs\\log.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
				FileWriter myWriter = new FileWriter("C:\\Users\\Andrés\\Documents\\Logs\\log.txt");
				myWriter.write("");
				myWriter.close();
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void log(String msg) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			FileWriter myWriter = new FileWriter("C:\\Users\\Andrés\\Documents\\Logs\\log.txt", true);
			System.out.println(msg);
			myWriter.write(dtf.format(now) + " - " + msg + "\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
