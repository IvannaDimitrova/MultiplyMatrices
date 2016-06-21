package src.multipliers;

import src.matrix.Matrix;

public class GeneralMatrixMultiplier extends MatrixMultiplier {

	public GeneralMatrixMultiplier(Matrix A, Matrix B) {
		super(A, B);
	}
	
	@Override
	public Matrix call() throws Exception {
		int colsCount = B.width();
		int rowsCount = A.height();
	
		Matrix result = new Matrix(rowsCount, colsCount);
		
		double value;
		for (int i = 0; i < rowsCount; i++) {
	         for (int j = 0; j < colsCount; j++) {
	                   value =  multiplyRowCol(i, j);
	                   result.setElement(i, j, value);
		      	
	        }
		}
		return result;
	}
	private double multiplyRowCol(int row, int col) {
        double result = 0d;

        for (int i = 0; i < A.width(); ++i)
            result += A.getElement(row, i) * B.getElement(i, col);

        return result;
    }
}
