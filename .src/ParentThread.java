package src;
//import java.util.Random;

public class ParentThread {

	public static void main(String[] args) {

		System.out.println("Program Arguments:");
        for (String arg : args) {
            System.out.println("\t" + arg);
        }
        
        //set by cmd line
        int m = Integer.parseInt(args[1]); 
        int n = Integer.parseInt(args[2]);
        int k = Integer.parseInt(args[3]);
        int numOfThreads = Integer.parseInt(args[4]);
        		
        double A[][] = new double[m][n];
        double B[][] = new double[n][k];
        double result[][] = new double[m][k];
        
        int threadcount = 0;
      
        //filling the first matrix with random values
        for (int i = 0; i < m; i++)
        	{for(int j = 0; j < n; j++)
        		
        	    {A[i][j] = Math.random();
        	    } 
        	}
       
        //filling the second matrix with random values
        for (int i = 0; i < n; i++)
        	{for(int j = 0; j < k; j++)
        		{B[i][j] =  Math.random();
        		}
        	}
        
        Thread[] thrd = new Thread[32];
        
        
        
        try {
        	for(int row = 0; row < m; row++)
        	{
        		for(int col = 0; col < k; col++)
        		{		
        			System.out.println("Thread " + threadcount + " started.“");
        				thrd[threadcount] = new Thread(new MultThread(row, col, A, B, result));
	        			thrd[threadcount].start();
	        			thrd[threadcount].join();
	        			threadcount++;
	        		System.out.println("Thread " + (threadcount-1) + " stopped.“");
	        			
	        			
        		}
        			
        	}
        }
        catch(InterruptedException ie){}
	
        System.out.println(" A Matrix : ");
         for(int row = 0 ; row < m; row++)
          {
                  for (int col = 0 ; col < n; col++ )
                  {
                      System.out.print("  "+A[row][col]);
                  }
                  System.out.println();
           }
          
          // printing matrix B
          System.out.println(" B Matrix : ");
          for(int row = 0 ; row < n; row++)
          {
                  for (int col = 0 ; col < k; col++ )
                  {
                      System.out.print("  "+B[row][col]);
                  }
                  System.out.println();
           }
          
          // printing resulting matrix C after multiplication
          System.out.println(" Resulting C Matrix : ");
          for(int row = 0 ; row < m; row++)
          {
                  for (int col = 0 ; col < k; col++ )
                  {
                      System.out.print("  "+result[row][col]);
                  }
                  System.out.println();
           }
          System.out.println("threadcount: " + threadcount);
	}

}





