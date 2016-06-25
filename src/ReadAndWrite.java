import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class ReadAndWrite {
	public static double A[][];
	public static double B[][];
	public double C[][];
	public static int m;
	public static int n;
	public static int k;
	public static int threadcount;
	public static String path = "\\MultMatrices\\m3x-data.in";
	public static String pathOut = "\\MultMatrices\\m3x-data.in";
	
	public ReadAndWrite(String[] args) throws FileNotFoundException {
		File file = new File(path);
		Scanner in = new Scanner(file);
		m = in.nextInt();
		n = in.nextInt();
		k = in.nextInt();
		in.close();
		if("-o" == args[1]){
	     	threadcount = Integer.parseInt(args[2]);
	     } else {
	     	threadcount = Integer.parseInt(args[1]);	        	
	     }
		
	}
	
	public static double[][] readFirstMatrix(String[] args) throws FileNotFoundException{
		File file = new File(path);
		Scanner in = new Scanner(file);
		
		m = in.nextInt();
		n = in.nextInt();
		
		A = new double[m][n];
		
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				A[i][j] = in.nextDouble();
			}
		}
		in.close();
		return A;
	}
	
	public static double[][] readSecondMatrix(String[] args) throws FileNotFoundException{
		File file = new File(path);
		Scanner in = new Scanner(file);
		
		m = in.nextInt();
		n = in.nextInt();
		k = in.nextInt();
		
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				 in.nextDouble();
			}
		}
		
		B = new double[n][k];
		
		for (int i1 = 0; i1 < m; ++i1) {
			for (int j = 0; j < n; ++j) {
				B[i1][j] = in.nextDouble();
			}
		}
		in.close();
		return B;
	}
	
	public static void writeMatrix(double[][] C) throws IOException {
		 
		 	OutputStream mOutputStream = new FileOutputStream(pathOut);
		    DataOutputStream mDataStream = new DataOutputStream(mOutputStream);

	        // Writing out sizes
	        mDataStream.writeInt(m);
	        mDataStream.writeInt(k);

	        // Writing out data
	        for (int i = 0; i < m; ++i) {
	            for (int j = 0; j < k; ++j) {
	                mDataStream.writeDouble(C[i][j]);
	            }
	        }

	        mDataStream.flush();
	        mDataStream.close();
	    }
	
	public int getM() {
		return m;
	}
	
	public int getN() {
		return n;
	}
	
	public int getK() {
		return k;
	}
	
	public int getT() {
		return threadcount;
	}
	
	 public static void setM(int m) {
		ReadAndWrite.m = m;
	}

	public static void setN(int n) {
		ReadAndWrite.n = n;
	}

	public static void setK(int k) {
		ReadAndWrite.k = k;
	}

	
	
}
