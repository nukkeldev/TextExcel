package textExcel;

public class ValueCell implements Cell{

    private double value;
    public ValueCell(double value) {
        this.value = value;
    }
    @Override
    public String abbreviatedCellText() {
        String temp = String.valueOf(value);
        return temp.length() > 10 ? temp.substring(0, 10) : temp;
    }

    @Override
    public String fullCellText() {
        return String.valueOf(value);
    }
}
