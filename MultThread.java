package src;

public class MultThread implements Runnable {
	
	int from;
	int to;
	private double A[][];
	private double B[][];
	private double C[][];
	
	
	MultThread(int from, int to, double A[][], double B[][], double C[][]) {
		
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
