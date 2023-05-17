package textExcel;

public class TextCell implements Cell{
    private String value = "";

    public TextCell(){

    }
    public TextCell(String value) {
        this.value = value;
    }
    @Override
    public String abbreviatedCellText() {
        return value.substring(1, value.length() - 1);
    }

    @Override
    public String fullCellText() {
        return value;
    }
}
