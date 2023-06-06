package textExcel;

import java.util.Scanner;

public class TextExcel {
	public static void main(String... args) {
		Spreadsheet spreadsheet = new Spreadsheet();
		Scanner scanner = new Scanner(System.in);
		System.out.print(spreadsheet.getGridText());
		System.out.print("> ");
		String cmd = scanner.nextLine();
		while (!cmd.equalsIgnoreCase("quit")) {
			try {

				String output = spreadsheet.processCommand(cmd);
				if (!output.equals("")) System.out.println(output);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
			System.out.print("> ");
			cmd = scanner.nextLine();
		}
	}
}
