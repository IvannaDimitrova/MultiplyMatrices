
public class WorkerThread implements Runnable{

    private int from;
    private int to;
    private double A[][];
    private double B[][];
    private double C[][];
    private int threadNumber;
    
    public WorkerThread(int from, int to, double A[][], double B[][], double C[][], int threadNumber )
    {
        this.from = from;
        this.to = to;
        this.A = A;
        this.B = B;
        this.C = C;
        this.threadNumber = threadNumber;
    }
    
    @Override
    public void run()
    {
    	
    	System.out.println("Thread " + threadNumber + " started.");
    	
    	if(A.length >= B[0].length) {
						
			for (int i = from; i < to; i++) {
		         for (int j = 0; j < B[0].length; j++) {
		               C[i][j] =  multiplyRowCol(i,j);
		         }
			}
			
		} else if (A.length < B[0].length) {
			
			for (int i = 0; i < A.length; i++) {
		         for (int j = from; j < to; j++) {
		               C[i][j] =  multiplyRowCol(i,j);
		         }
			}
		}
    	
        System.out.println("Thread " + threadNumber + " stopped.");
       
    }
    private double multiplyRowCol(int row, int col) {
        double result = 0d;

        for (int i = 0; i < A[0].length; ++i)
            result += A[row][i] * B[i][col];

        return result;
	}
    
}