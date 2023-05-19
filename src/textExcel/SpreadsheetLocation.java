package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location {
    private final int row, col;

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    public SpreadsheetLocation(String cellName) {
        col = cellName.charAt(0) - 'A';
        row = Integer.parseInt(cellName.substring(1)) - 1;
    }

    public SpreadsheetLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
