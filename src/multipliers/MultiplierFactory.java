package src.multipliers;

import src.matrix.Matrix;

public class MultiplierFactory {
	public static MatrixMultiplier createMultiplierGeneral(Matrix A, Matrix B) {
			GeneralMatrixMultiplier result = new GeneralMatrixMultiplier(A, B);
			return result;
		}
	
	public static MatrixMultiplier createMultiplierParallel(int from, int to, Matrix A, Matrix B) {
			ParallelMatrixMultiplication result = new ParallelMatrixMultiplication(from, to, A, B);
			return result;
		}
	
}
