package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
    private int x, y;
    private Cell cell;

    public SpreadsheetLocation(int x, int y) {
        this.x = x;
        this.y = y;
        this.cell = null;
    }

    @Override
    public int getRow()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCol()
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
    public SpreadsheetLocation(String cellName)
    {
        // TODO: Fill this out with your own code
    }

    public void clear() {
        cell = null;
    }
}
