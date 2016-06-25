public class MainThread {
   
    public static final int NUM_OF_THREADS = 32;
    public static final String FIRSTMATRIXROWS   =  "-m";
	public static final String FIRSTMATRIXCOLS   =  "-n";
	public static final String SECONDMATRIXCOLS  =  "-k";
	public static final String THREADSNUMBER     =  "-t";
	public static final String E_INCORRECT_OPTION = "Incorrect Option";
	
    public static void main(String args[]) throws InterruptedException
    {
    	long startTime = System.currentTimeMillis();
		 System.out.println("Program Arguments:");
		 for (int i = 0; i < args.length; i++) {   	
             System.out.println("\t" + args[i]+ " " + i);
         }
         
		int m = 0, n = 0, k = 0, threadcount = 0;
		
		boolean mUseParallel = false;
	    boolean mUseSerial = false;
	    
	    m = Integer.parseInt(args[1]);
	    n = Integer.parseInt(args[3]);
        k = Integer.parseInt(args[5]);
        
        if(Integer.parseInt(args[7]) == 1) {
        		mUseSerial = true;
        	} else if (Integer.parseInt(args[7]) > 1 && Integer.parseInt(args[7]) <= 32) {
        		mUseParallel = true;
        		threadcount = Integer.parseInt(args[7]); 
        	}
		
        double A[][] = new double[m][n];
        double B[][] = new double[n][k];
        double C[][] = new double[m][k];
        
        // fill matrix A with random values
        for (int row = 0; row < m; row++) {
	    	for(int col = 0; col < n; col++) {
	    		A[row][col] =  Math.random();
	    	}
        }   
     
        System.out.println(" A Matrix : ");
                for(int row = 0 ; row < m; row++)
                {
                        for (int col = 0 ; col < n; col++ )
                        {
                            System.out.print("  " + A[row][col]);
                        }
                        System.out.println();
                 }
         //fill matrix B with random values       
        for (int row = 0; row < n; row++) {
	    	for(int col = 0; col < k; col++) {
	    		B[row][col] =  Math.random();
	    	}
        }
        System.out.println(" B Matrix : ");
        for(int row = 0 ; row < n; row++){
        	for (int col = 0 ; col < k; col++ ) {
        		System.out.print("  " + B[row][col]);
        		}
        	System.out.println();
        	}
        
        Thread[] thrd = new Thread[NUM_OF_THREADS];

        if(mUseSerial){
        	System.out.println("The only one thread started.");
        	long startTimeSerial = System.currentTimeMillis();
        	
        	for (int u = 0; u < m; u++) {
        		for (int j = 0; j < k; j++) {
        			for (int p = 0; p < n; ++p){
        				C[u][j] += A[u][p] * B[p][j];
       	         }
                }
        	}
        	
        	long timeSerial = System.currentTimeMillis() - startTimeSerial;
        	System.out.println("Thread  execution time was: " + timeSerial + " ms");
        	System.out.println("The only thread stopped.");
        	System.out.println();
        	} 
        
        else if(mUseParallel) {
        	long startTimeThead = System.currentTimeMillis();
            int d = m/threadcount;
            int [] seq = new int[threadcount];
            int h;
            for(h = 0; h < threadcount; h++) {
            	seq[h] = d;
            }    		
            int sumN = threadcount*seq[0];
            h = 0;
            while(sumN != m && h < threadcount) {
            	seq[h] += 1;
            	sumN += 1;
            	++h;
           	}
            		  
            int from = 0;
            int to = seq[0];
            for (int s = 0; s < seq.length; s++) {
            	System.out.println("in for of parallel multiplication");
            	thrd[s] = new Thread(new WorkerThread(from, to, A, B, C, s));
                from += seq[s];
                if(s < seq.length - 1)
                  	to = from + seq[s+1];
                 thrd[s].start(); //thread start
                   		
                 long timeThread = System.currentTimeMillis() - startTimeThead;
                 System.out.println("Total execution time for current run (millis): " + timeThread );
             	System.out.println();

                }
                               
             }       // end of use Parallel    
        // printing resulting matrix C after multiplication
        System.out.println(" Resulting C Matrix : ");
        for(int row = 0 ; row < m; row++) {
        	for (int col = 0 ; col < k; col++ ) {
        		System.out.print("  " + C[row][col]);
        		}
        	System.out.println();
        	}
        long time = System.currentTimeMillis() - startTime;
        System.out.println("Total execution time (millis): " + time );
    	System.out.println();

        System.exit(0);   
    }// end of main
     
}//end of class MainThread


