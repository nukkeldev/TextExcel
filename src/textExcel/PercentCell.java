package textExcel;

public class PercentCell implements Cell{
    //TODO: Do we want a setter for this?
    private final double value;

    public PercentCell(double value) {
        this.value = value / 100;
    }

    @Override
    public String abbreviatedCellText() {
        return String.valueOf((int)(value * 100.0));
    }

    @Override
    public String fullCellText() {
        return String.valueOf(value);
    }
}
