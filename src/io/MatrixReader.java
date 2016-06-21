package src.io;

import src.matrix.Matrix;
import java.io.*;
import java.nio.file.Path;

public class MatrixReader implements Closeable {
	
		private final Path mMatrixFile;
	    private InputStream mInputStream;
	    private DataInputStream mDataStream;
	    public int m, n, k;
	    public Matrix firstMatrix;
        public Matrix secondMatrix;
	    
	    public MatrixReader(Path path) throws FileNotFoundException {
	        mMatrixFile = path;
	        openMatrixFile();
	    }
	    public void readDimensions() throws IOException {
	    	 m = mDataStream.readInt();
	    	 n = mDataStream.readInt();
	    	 k = mDataStream.readInt();
	    }
	   
	    public Matrix readFirstMatrix() throws IOException {
	       		
	        firstMatrix = new Matrix(m,n); 
	        for (int i = 0; i < m; ++i) {
	            for (int j = 0; j < n; ++j) {
	                firstMatrix.setElement(i, j, mDataStream.readDouble());
	            }
	        }

	        return firstMatrix;
	        } 
	    
	    public Matrix readSecondMatrix() throws IOException {
	    	
       		mDataStream.skip(m*n + 5);
	    	secondMatrix = new Matrix(n,k);
	    	for (int i = 0; i < n; ++i) {
	    		for (int j = 0; j < k; ++j) {
	    			secondMatrix.setElement(i, j, mDataStream.readDouble());
            }	
        }

        return secondMatrix;
    }
	   
	    @Override
	    public void close() throws IOException {
	        mDataStream.close();
	        mInputStream.close();
	    }

	    private void openMatrixFile() throws FileNotFoundException {
	        mInputStream = new FileInputStream(mMatrixFile.toString());
	        mDataStream = new DataInputStream(mInputStream);
	    }

}
