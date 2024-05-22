public class MatrixOperations {
    public double addNumbers(double a, double b) {
        return a + b;
    }

    public double subtractNumbers(double a, double b) {
        return a - b;
    }

    public double multiplyNumbers(double a, double b) {
        return a * b;
    }

    public double divideNumbers(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }

        return a / b;
    }

    public boolean isPositive(double a) {
        return a > 0;
    }

    public boolean isNegative(double a) {
        return a < 0;
    }

    public boolean canMultiply(double[][] a, double[][] b) {
        return a[0].length == b.length;
    }

    public boolean canAddOrSubtract(double[][] a, double[][] b) {
        return a.length == b.length && a[0].length == b[0].length;
    }

    public double[][] multiply(double[][] a, double[][] b) {
        if (!canMultiply(a, b)) {
            throw new IllegalArgumentException("Matrices cannot be multiplied.");
        }

        int aRows = a.length;
        int aColumns = a[0].length;
        int bColumns = b[0].length;

        double[][] result = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    result[i][j] = addNumbers(result[i][j], multiplyNumbers(a[i][k], b[k][j]));
                }
            }
        }

        return result;
    }

    public double[][] add(double[][] a, double[][] b) {
        if (!canAddOrSubtract(a, b)) {
            throw new IllegalArgumentException("Matrices cannot be added.");
        }

        int rows = a.length;
        int columns = a[0].length;
        double[][] result = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = addNumbers(a[i][j], b[i][j]);
            }
        }

        return result;
    }

    public double[][] subtract(double[][] a, double[][] b) {
        if (!canAddOrSubtract(a, b)) {
            throw new IllegalArgumentException("Matrices cannot be subtracted.");
        }

        int rows = a.length;
        int columns = a[0].length;
        double[][] result = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = subtractNumbers(a[i][j], b[i][j]);
            }
        }

        return result;
    }
}
