package textExcel;

// Update this file with your own code.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Spreadsheet implements Grid {
    private static final Pattern CELL = Pattern.compile("[A-Z]\\d+");

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private List<List<Cell>> cells;

    public Spreadsheet() {
        clearGrid();
    }

    private void clearGrid() {
        cells = new ArrayList<>() {{
            for (int i = 0; i < 20; i++) {
                add(new ArrayList<>() {{
                    for (int j = 0; j < 12; j++)
                        add(new EmptyCell());
                }});
            }
        }};
    }

    @Override
    public String processCommand(String command) {
        var presentments = command.split(" ");
        Iterator<String> segments = Arrays.stream(presentments).iterator();

        String segment = segments.next();
        switch (segment) {
            case "clear" -> {
                if (segments.hasNext()) {
                    segment = segments.next();
                    if (!CELL.matcher(segment).matches())
                        throw new InvalidCellException("Cell '" + segment + "' is not properly formatted.");
                    Location loc = new SpreadsheetLocation(segment);
                    cells.get(loc.getRow()).set(loc.getCol(), new EmptyCell());
                    System.out.print(getGridText());
                    return "Cleared " + segment;
                } else {
                    clearGrid();
                    System.out.print(getGridText());
                    return "Cleared the grid";
                }
            }
            case "save" -> {
                if (!segments.hasNext()) throw new InvalidCommandException("'save' requires a file path argument.");
                String pathString = segments.next();

                try (FileWriter writer = new FileWriter(pathString)) {
                    for (int i = 0; i < cells.size(); i++) {
                        var row = cells.get(i);
                        for (int j = 0; j < row.size(); j++) {
                            String text = row.get(j).fullCellText();
                            if (text.equals("\"\"")) continue;
                            writer.write(String.format("%s,%s,%s\n", String.valueOf((char)('A' + j)) + (i + 1), row.get(j).getClass().getSimpleName(), text));
                        }
                    }
                } catch (IOException e) {
                    System.out.println("An error has occurred when saving!");
                    e.printStackTrace();
                }
                System.out.print(getGridText());
                System.out.println("Saved " + pathString);
            }
            case "open" -> {
                if (!segments.hasNext()) throw new InvalidCommandException("'open' requires a file path argument.");
                String pathString = segments.next();
                try{
                    Scanner scanner = new Scanner(new File(pathString));
                    while (scanner.hasNext()) {
                        String line = scanner.nextLine();
                        String[] args = line.split(",");
                        SpreadsheetLocation loc = new SpreadsheetLocation(args[0]);
                        switch (args[1]) {
                            case "PercentCell" -> cells.get(loc.getRow()).set(loc.getCol(), new PercentCell(Double.parseDouble(args[2])));
                            case "TextCell" -> cells.get(loc.getRow()).set(loc.getCol(), new TextCell(args[2]));
                        }

                    }
                } catch (IOException e) {
                    System.out.println("An error has occurred when opening!");
                }

                // Open

                System.out.print(getGridText());
                System.out.println("Opened " + pathString);
            }
            default -> {
                segment = segment.toUpperCase();
                if (CELL.matcher(segment).matches()) {
                    Location loc = new SpreadsheetLocation(segment);
                    if (segments.hasNext() && segments.next().equals("=")) {
                        StringBuilder segB = new StringBuilder(segments.next());
                        while (segments.hasNext()) segB.append(" ").append(segments.next());
                        segment = segB.toString();
                        cells.get(loc.getRow()).set(loc.getCol(), switch (segment.charAt(segment.length() - 1)) {
                            case '%' -> {
                                try {
                                    double value = Double.parseDouble(segment.substring(0, segment.length() - 1));
                                    yield new PercentCell(value);
                                } catch (RuntimeException e) {
                                    throw new RuntimeException("Invalid percent format.");
                                }
                            }
                            case '"' -> {
                                if (segment.charAt(0) != '"')
                                    throw new RuntimeException("Quote not found at beginning of text value.");
                                yield new TextCell(segment);
                            }
                            default -> {
                                try {
                                    double value = Double.parseDouble(segment);
                                    yield new ValueCell(value);
                                } catch (RuntimeException e) {
                                    throw new RuntimeException("Invalid value.");
                                }
                            }
                        });
                        System.out.print(getGridText());
                        return "";
                    } else {
                        return getCell(loc).fullCellText();
                    }
                } else {
                    throw new InvalidCommandException("Unknown command '" + segment + "'.");
                }
            }
        }
        return "";
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

    private String getAbbreviatedCellValue(Location loc) {
        return getCell(loc).abbreviatedCellText();
    }

    @Override
    public String getGridText() {
        StringBuilder builder = new StringBuilder();
        builder.append("   |");
        for (int i = 'A'; i < 'A' + 12; i++) {
            builder.append((char) i).append("         |");
        }
        builder.append("\n");
        for (int i = 0; i < 20; i++) {
            builder.append(String.format("%-3d|", i + 1));
            for (int j = 0; j < 12; j++) {
                builder.append(getAbbreviatedCellValue(new SpreadsheetLocation(j, i))).append("|");// TODO: Why reverse????
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}