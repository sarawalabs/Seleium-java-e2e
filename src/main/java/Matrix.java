import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private double[][] data;

    public Matrix(double[][] data) {
        int r = data.length;
        int c = data[0].length;
        System.out.println(r);
        System.out.println(c);
        this.data = new double[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }

    /* convenience method for getting a
       string representation of matrix */
    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        for (double[] row : this.data) {
            for (double val : row) {
                sb.append(val);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return (sb.toString());
    }

    public void removeRowsWithValue(final double value) {
        /* Use an array list to track of the rows we're going to want to
           keep...arraylist makes it easy to grow dynamically so we don't
           need to know up front how many rows we're keeping */
        List<double[]> rowsToKeep = new ArrayList<double[]>(this.data.length);
        for (double[] row : this.data) {
            /* If you download Apache Commons, it has built-in array search
              methods so you don't have to write your own */
            boolean found = false;
            for (double testValue : row) {
                /* Using == to compares doubles is generally a bad idea
                   since they can be represented slightly off their actual
                   value in memory */
                if (Double.compare(value, testValue) == 0) {
                    found = true;
                    break;
                }
            }

            /* if we didn't find our value in the current row,
              that must mean its a row we keep */
            if (!found) {
                rowsToKeep.add(row);
            }
        }

        /* now that we know what rows we want to keep, make our
           new 2D array with only those rows */
        this.data = new double[rowsToKeep.size()][];
        for (int i = 0; i < rowsToKeep.size(); i++) {
            this.data[i] = rowsToKeep.get(i);
        }
    }

    public static void main(String[] args) {
        double[][] test = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {6, 2, 7, 2, 9, 6, 8, 10, 5},
                {2, 6, 4, 7, 8, 4, 3, 2, 5},
                {9, 8, 7, 5, 9, 7, 4, 1, 10},
                {5, 3, 6, 8, 2, 7, 3, 7, 2}};

        //make the original array and print it out
        Matrix m = new Matrix(test);
        System.out.println(m);

        //remove rows with the value "10" and then reprint the array
        m.removeRowsWithValue(10);
        System.out.println(m);
    }
}

