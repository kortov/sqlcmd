package juja.sqlcmd;

import java.util.Arrays;

public class DataSet {
    private final String[] row;
    private final int rowSize;

    public DataSet(int rowSize) {
        this.rowSize = rowSize;
        row = new String[rowSize];
    }

    public void setValue(int index, String string) {
        row[index] = string;
        // TODO add check arguments
    }

    public String getValue(int index, Object string) {
        return row[index];
        // TODO add check args
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataSet dataSet = (DataSet) o;

        if (rowSize != dataSet.rowSize) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(row, dataSet.row);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(row);
        result = 31 * result + rowSize;
        return result;
    }
}
