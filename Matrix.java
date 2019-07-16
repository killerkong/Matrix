
class Matrix {
    public double[][] matrix;
    private double[][] inversion;
    public int row;
    public int col;

    public Matrix() {
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        row = matrix.length;
        col = matrix[0].length;
    }

    public Matrix multiply(double t) {
        double[][] result = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = t * matrix[i][j];
            }
        }
        return new Matrix(result);
    }

    public Matrix multiply(Matrix matrix2) {
        if (col != matrix2.row) {
            System.out.println("Multiplication does not apply.");
            return null;
        } else {
            double[][] result = new double[row][matrix2.col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    result[i][j] = 0;
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < matrix2.col; j++) {
                    for (int k = 0; k < col; k++) {
                        result[i][j] += matrix[i][k] * matrix2.matrix[k][j];
                    }
                }
            }
            return new Matrix(result);
        }
    }

    public void REF() {
        for (int i = 0, j = 0; i < row; i++, j++) {
            format(i, j);
            if (matrix[i][j] != 0) {
                pivot(i, j);
            } else {
                continue;
            }
        }
    }

    public void format(int i, int j) {
        if (matrix[i][j] != 0) {
            double num = matrix[i][j];
            for (int k = j; k < col; k++) {
                matrix[i][k] /= num;
            }
        } else {
            int unzero = i;
            for (int k = i + 1; k < row; k++) {
                if (matrix[k][j] != 0) {
                    unzero = k;
                    break;
                }
            }
            if (unzero != i) {
                swapRow(i, unzero);
                format(i, j);
            }
        }
    }

    public void pivot(int i, int j) {
        for (int k = i + 1; k < row; k++) {
            if (matrix[k][j] != 0) {
                double num = matrix[k][j] / matrix[i][j];
                for (int z = j; z < col; z++) {
                    matrix[k][z] -= matrix[i][j] * num;
                }
            }
        }
    }

    public void swapRow(int row1, int row2) {
        for (int i = 0; i < col; i++) {
            double temp = matrix[row1][i];
            matrix[row1][i] = matrix[row2][i];
            matrix[row2][i] = temp;
        }
    }

    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%3.2f ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isSquare() {
        if (row == col)
            return true;
        return false;
    }

    public static void main(String[] args) {
        double[][] array2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 7, 9 } };
        double[][] array1 = { { 1, 1, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
        Matrix matrix1 = new Matrix(array1);
        Matrix matrix2 = new Matrix(array2);
        Matrix result = matrix1.multiply(matrix2);
        matrix2.print();
        matrix2.REF();
        matrix2.print();
    }
}