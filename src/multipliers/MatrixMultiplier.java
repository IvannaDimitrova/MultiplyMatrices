package src.multipliers;

import java.util.concurrent.Callable;
import src.exceptions.MatrixSizeMultiplicationException;
import src.matrix.Matrix;

public abstract class MatrixMultiplier implements Callable<Matrix> {

    // Using single letter names for readability
    protected final Matrix A; // left matrix
    protected final Matrix B; // right matrix

    public MatrixMultiplier(Matrix A, Matrix B) {
    	if(A.width() != B.height())
            throw new MatrixSizeMultiplicationException();

        this.A = A;
        this.B = B;
	}

	public final Matrix getLeft() {
        return A;
    }

    public final Matrix getRight() {
        return B;
    }
    
    public int[] setSequenceOf(int m) {
		int n = A.height();
		int [] subseq = new int[n];
		int d = n/m;
		
		int i;
		for(i = 0; i < m; i++) {
			subseq[i] = d;
		}
		
		int sumN = m*subseq[0];
		
		while(sumN != n && i < m) {
			subseq[i] += 1;
			sumN += 1;
			++i;
		}
				
		return subseq;
	}
}

/*package src.multipliers;

public class MatrixMultiplier implements Runnable {
	
	int from;
	int to;
	private double A[][];
	private double B[][];
	private double C[][];
	
	
	MatrixMultiplier(int from, int to, double A[][], double B[][], double C[][]) {
		
		this.from = from;
        this.to = to;
        this.A = A;
        this.B = B;
        this.C = C;
	}
	
	@Override
	public void run() {
		if(A.length >= B[0].length)
		{ int rowsInA = to;
	     int columnsInA = A[0].length; // same as rows in B
	     int columnsInB = B[0].length;
	  
	     for (int i = from; i < rowsInA; i++) {
	         for (int j = 0; j < columnsInB; j++) {
	              for (int k = 0; k < columnsInA; k++) {
	                   C[i][j] = C[i][j] + A[i][k] * B[k][j];
	               }
	           }
	       }
		}
		else {
			int rowsInA = A.length;
		     int columnsInA = A[0].length; // same as rows in B
		     int columnsInB = to;
		  
		     for (int i = from; i < rowsInA; i++) {
		         for (int j = from; j < columnsInB; j++) {
		              for (int k = 0; k < columnsInA; k++) {
		                   C[i][j] = C[i][j] + A[i][k] * B[k][j];
		               }
		           }
		       }
		}
	}
}
*/