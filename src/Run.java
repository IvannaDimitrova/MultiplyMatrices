package src;

import src.exceptions.ArgsParseException;
import src.io.MatrixReader;
import src.matrix.Matrix;
import src.multipliers.MatrixMultiplier;
import src.multipliers.MultiplierFactory;
import src.matrix.MatrixOperations;

import static src.RunSettings.parseArgs;

import java.io.*;

import static src.Messages.*;

public class Run {
	
	 private static final String MANUAL_PATH = "/MatricesMultiplication/src/manual.txt";
	
		private RunSettings mSettings;
        private MatrixMultiplier mMultiplier;
        private MatrixOperations mOperations = new MatrixOperations();
        
        private Matrix A;
        private Matrix B;
        
        public static void main(String[] args) {
        	
		    System.out.println("Program Arguments:");
	        for (String arg : args) {
	            System.out.println("\t" + arg);
	        }
	        
	        try {
	        	Run run = new Run(args);
	        	run.execute();
	        } catch (ArgsParseException e) {
	        	System.out.println(e.getMessage());
	        }
        }
        
        private Run(String[] args) throws ArgsParseException {
            mSettings = parseArgs(args);
        }

        private void execute() throws ArgsParseException {
            if (mSettings.shouldPrintManual()){
                printManual();
                return;
            }
            if(mSettings.shouldUseTextFile()){
	            try {
	            	loadSourceMatrices();
	            } catch (IOException e) {
	                System.out.println(E_LOADING_MATRICES);
	                return;
	            }
            } else {
            	int m = mSettings.getFirstD();
            	int n = mSettings.getSecondD();
            	int k = mSettings.getThirdD();
            	 A = new Matrix(m,n);
                 B = new Matrix(n,k);
                 mOperations.fillMatrix(A);
                 mOperations.fillMatrix(B);
                 
            }
           
            if(A.width() != B.height())
                throw new ArgsParseException(E_MATRIX_SIZES);
            
            if(mSettings.shouldUseGeneral()){
            	 mMultiplier = MultiplierFactory.createMultiplierGeneral(A, B);
            } else if (mSettings.shouldUseParallel()) {
            	int [] seq = mMultiplier.setSequenceOf(mSettings.getThreadCount());
            	int from = 0;
            	int to = seq[0];
            	for (int s = 0; s < seq.length; s++) {
            		mMultiplier = MultiplierFactory.createMultiplierParallel(from, to, A, B);
            		from += seq[s];
            		to = from + seq[s+1];
            	}
            }
           
            
            
            
        }
        private void loadSourceMatrices() throws IOException {
            System.out.println(M_LOADING_MATRICES);
            MatrixReader sourceFiles = new MatrixReader(mSettings.getSourceMatrix());
                A = sourceFiles.readFirstMatrix();
                B = sourceFiles.readSecondMatrix();
                sourceFiles.close();
        }
        
        private static void printManual() {
            try (InputStream is = Run.class.getClassLoader().getResourceAsStream(MANUAL_PATH);
                InputStreamReader ir = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ir)){

                String line;
                while((line = br.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (IOException e) {
                System.out.println(E_MANUAL);
            }
        }
        
}





