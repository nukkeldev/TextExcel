package textExcel;

public class TextCell implements Cell{

    private String value = "";

    public TextCell(){
        value = "\"\"";
    }
    public TextCell(String value) {
        this.value = value;
    }

    @Override
    public String abbreviatedCellText() {
        String thing = value.substring(1, value.length() - 1);
        return thing.length() > 10 ? thing.substring(0, 10) : String.format("%-10s", thing);
    }

    @Override
    public String fullCellText() {
        return value;
    }
}
