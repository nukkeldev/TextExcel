package textExcel;

// Update this file with your own code.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Spreadsheet implements Grid {
    private static final Pattern CELL = Pattern.compile("[A-Z]\\d+");

    private List<List<Cell>> cells;

    public Spreadsheet() {
        clearGrid();
    }

    private void clearGrid() {
        cells = new ArrayList<>() {{
            for (int i = 0; i < 20; i++) {
                add(new ArrayList<>() {{
                    for (int j = 0; j < 12; j++)
                        add(null);
                }});
            }
        }};
    }

    @Override
    public String processCommand(String command) {
        Iterator<String> segments = Arrays.stream(command.split(" ")).iterator();

        String segment = segments.next();
        switch (segment) {
            case "clear" -> {
                if (segments.hasNext()) {
					segment = segments.next();
					if (!CELL.matcher(segment).matches()) throw new InvalidCellException("Cell '" + segment + "' is not properly formatted.");
                    Location loc = new SpreadsheetLocation(segments.next());
                    cells.get(loc.getRow()).set(loc.getCol(), null);
                } else {
                    clearGrid();
                }
            }
            case "save" -> {
                if (!segments.hasNext()) throw new InvalidCommandException("'save' requires a file path argument.");
                // Save
            }
            case "open" -> {
                if (!segments.hasNext()) throw new InvalidCommandException("'open' requires a file path argument.");
                // Open
            }
            default -> {
                if (CELL.matcher(segment).matches()) {
					Location loc = new SpreadsheetLocation(segment);
                    if (segments.hasNext() && segments.next().equals("=")) {
//                        cells.get(loc.getRow()).set(loc.getCol(), )
                    } else {
                        return getCell(loc).fullCellText();
                    }
                } else {
                    throw new InvalidCommandException("Unknown command '" + segment + "'.");
                }
            }
        }
        return null;
    }

    @Override
    public int getRows() {
        return cells.size();
    }

    @Override
    public int getCols() {
        return cells.get(0).size();
    }

    @Override
    public Cell getCell(Location loc) {
        return cells.get(loc.getRow()).get(loc.getCol());
    }

    @Override
    public String getGridText() {
        StringBuilder builder = new StringBuilder();
        builder.append("   ");
        for (int i = 'A'; i < 'A' + 12; i++) {
            builder.append("|").append((char) i).append("         |");
        }
        builder.append("\n");
        for (int i = 0; i < 20; i++) {
            builder.append(String.format("%-3d", i + 1));
            for (int j = 0; j < 12; j++) {
                builder.append(String.format("|%-10s", ""));
            }
        }
        return null;
    }

}
