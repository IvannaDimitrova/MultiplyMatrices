package src.multipliers;

import src.matrix.Matrix;

public class ParallelMatrixMultiplication extends MatrixMultiplier {
	
	public Matrix result;
	int from, to;
			
	public ParallelMatrixMultiplication(int from, int to, Matrix A, Matrix B) {
		super(A,B);
		result = new Matrix(A.height(), B.width());
		this.from = from;
		this.to = to;
	}

	@Override
	public Matrix call() throws Exception {
							
		return MultMiniMatrix(from, to, getLeft(), getRight());
	}
	
	private Matrix MultMiniMatrix(int from, int to, Matrix A, Matrix B) {
		
		if(A.height() >= B.width()) {
			
			int curHeightA = to;
			int curWidthB = B.width();
						
			double value;
			for (int i = from; i < curHeightA; i++) {
		         for (int j = 0; j < curWidthB; j++) {
		               value =  multiplyRowCol(i,j);
	                   result.setElement(i, j, value);
		         }
			}
			
		} else if (A.height() < B.width()) {
			int curHeightA = A.height();
			int curWidthB = to;
			
			double value;
			for (int i = 0; i < curHeightA; i++) {
		         for (int j = from; j < curWidthB; j++) {
		               value =  multiplyRowCol(i,j);
	                   result.setElement(i, j, value);
		         }
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
