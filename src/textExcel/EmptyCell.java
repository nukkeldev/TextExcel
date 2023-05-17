package textExcel;

public class EmptyCell implements Cell {
    @Override
    public String abbreviatedCellText() {
        return " ".repeat(10);
    }

    @Override
    public String fullCellText() {
        return "";
    }
}
