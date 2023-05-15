package textExcel;

// Update this file with your own code.

import jdk.jfr.ContentType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Spreadsheet implements Grid {

	private final List<List<SpreadsheetLocation>> cells;

	public Spreadsheet() {
		cells = new ArrayList<>() {{
			for (int i  = 0; i < 20; i++) {
				int _i = i;
				add(new ArrayList<>() {{
					for (int j = 0 ;  j < 12 ; j++)
						add(new SpreadsheetLocation(_i, j)); }});
			}
		}};
	}

	@Override
	public String processCommand(String command)
	{
		Iterator<String> segs = Arrays.stream(command.split(" ")).iterator();

		switch (segs.next()) {
			case "clear" -> {
				if (segs.hasNext()) {

				} else {
					cells.forEach(cellRow -> cellRow.forEach(SpreadsheetLocation::clear));
				}
			}
		}
		return null;
	}

	@Override
	public int getRows()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCols()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cell getCell(Location loc)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGridText()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
