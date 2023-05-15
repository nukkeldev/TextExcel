package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
    private final int x;
    private final int y;

    @Override
    public int getRow()
    {
        return y;
    }

    @Override
    public int getCol()
    {
        return x;
    }
    
    public SpreadsheetLocation(String cellName)
    {
        x = cellName.charAt(0) - 'A';
        y = Integer.parseInt(cellName.substring(1)) - 1;
    }
}
