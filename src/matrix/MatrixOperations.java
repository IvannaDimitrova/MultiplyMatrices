package src.matrix;

public class MatrixOperations {

	//get the entire matrix
	public Matrix getMatrix(Matrix A) {
		return A;
	}
	
	//get matrix from a specific row to a specific row
	public Matrix getFromRowToRowMatrix(int fromRow, int toRow, Matrix A) {
		
		Matrix fromRowToRowA = new Matrix(toRow - fromRow, A.width());
		for (int i = fromRow; i <= toRow; i++) {
			for (int j = 0; j < A.width(); j++) {
				fromRowToRowA.setElement(i, j, A.getElement(i, j));
				}
		}
		return fromRowToRowA;
		
	}
	//get matrix from a specific column to a specific column
	public Matrix getFromColToColMatrix(int fromCol, int toCol, Matrix A) {
		
		Matrix fromColToColA = new Matrix(A.height(), toCol - fromCol);
		for (int i = 0; i <= A.height(); i++) {
			for (int j = fromCol; j < toCol; j++) {
				fromColToColA.setElement(i, j, A.getElement(i, j));
				}
		}
		return fromColToColA;
		
	}
	 public static Matrix transpose(Matrix A) {
	        Matrix C = new Matrix(A.height(), A.width());

	        for (int i = 0; i < A.height(); i++)
	            for (int j = 0; j < A.width(); j++)
	                C.setElement(j, i, A.getElement(i, j));

	        return C;
	    }
	
	//filling matrix with random values
	public void fillMatrix(Matrix A) {
	
	    for (int row = 0; row < A.height(); row++) {
	    	for(int col = 0; col < A.width(); col++) {
	    		A.setElement(row, col, Math.random());
	    	}
	    }
	}
	
	//print matrix
	public void printMatrix(Matrix A) {
		
		System.out.println(" A Matrix : ");
	    for(int row = 0 ; row < A.height(); row++)
	     {
	             for (int col = 0 ; col < A.width(); col++ )
	             {
	                 System.out.print("  " + A.getElement(row,col));
	             }
	             System.out.println();
	      }
	}
	public MatrixOperations() {};
	
}
