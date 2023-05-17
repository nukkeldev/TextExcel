package textExcel;

import java.util.Scanner;
// Update this file with your own code.

public class TextExcel {
	public static void main(String... args) {
		Spreadsheet spreadsheet = new Spreadsheet();
		Scanner scanner = new Scanner(System.in);
		System.out.print(spreadsheet.getGridText());
		String cmd = scanner.nextLine();
		while (!cmd.equals("quit")) {
			try {
				String output = spreadsheet.processCommand(cmd);
				System.out.println(output);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			cmd = scanner.nextLine();
		}
	}
}
