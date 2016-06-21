package src;

import static src.Messages.*;

import java.nio.file.Path;
import java.nio.file.Paths;


import src.exceptions.ArgsParseException;

public class RunSettings {
	
	//Options
	private static final String FIRSTMATRIXROWS   =  "-m";
	private static final String FIRSTMATRIXCOLS   =  "-n";
	private static final String SECONDMATRIXCOLS  =  "-k";
	
	private static final String THREADSNUMBER     =  "-t";
	private static final String TEXTFILE          =  "-i";
	private static final String SAVENEWMATRIX     =  "-o";
	private static final String HELP              =  "-h";
	private static final String QUIET_REGIME      =  "-q";
	
	private boolean mQuietRegime = false;
	private boolean mPrintManual = false;
	private boolean mUseParallel = false;
	private boolean mUseGeneral = false;
	private boolean mUseTextFile = false;
	private boolean mFirstMatrixRows = false;
	private boolean mFirstMatrixCols = false;
	private boolean mSecondMatrixRows = false;
	private boolean mSaveFile = false;
	
	private int threadcount = 1;
	private int firstDimensionArg;
	private int secondDimensionArg;
	private int thirdDimensionArg;
	 
	public static final int CORE_COUNT = Runtime.getRuntime().availableProcessors();
	
	public int mPoolSize = CORE_COUNT;
	
	private Path mSourceMatrix;
	private Path mDestination;
	
	public static RunSettings parseArgs(String[] args) throws ArgsParseException {
        
		//if (args.length < 5)
          //  throw new ArgsParseException(E_MISSING_PATHS);
		
		RunSettings settings = new RunSettings();
        
        //Check if first argument is for help
        if (args.length == 0 || (args.length == 1 && args[0].equals(HELP)) || args[1].equals(HELP)) {
            settings.mPrintManual = true;
            return settings;
        }
        
        if(args[args.length-1] == QUIET_REGIME)
        	settings.mQuietRegime = true;
        
        //Check if it is text file with matrices or a set of dimensions
        switch (args[1]) {

        case FIRSTMATRIXROWS :
        	settings.mFirstMatrixRows = true;
        	break;
        case TEXTFILE :
        	settings.mUseTextFile = true;
        	break;
        default :
        	System.out.println(E_INCORRECT_OPTION );
        	
         }

        //Check if dimensions are set correctly
        if(settings.mFirstMatrixRows && isInt(args[2])){
        	
        	settings.setFirstD(Integer.parseInt(args[2]));
        	switch (args[3]) {
        	case  FIRSTMATRIXCOLS :
        		settings.mFirstMatrixCols = true;
        		break;
        	default :
        		System.out.println(E_INCORRECT_OPTION );
        	}
        }
        
        //Check if dimensions are set correctly
        if(settings.mFirstMatrixCols && isInt(args[4])){
        	
        	settings.setSecondD(Integer.parseInt(args[4]));
        	switch (args[5]) {
        	case SECONDMATRIXCOLS :
        		settings.mSecondMatrixRows = true;
        		break;
        	default :
        		System.out.println(E_INCORRECT_OPTION);
        	}
        }
        
        //Check if dimensions are set correctly and if threads are set
        if(settings.mSecondMatrixRows && isInt(args[6])) {
        	
        	settings.setThirdD(Integer.parseInt(args[6]));
        	switch (args[7]) {
        	case THREADSNUMBER :
        		if(Integer.parseInt(args[8]) == 1) {
        			settings.mUseGeneral = true;
        		} else {
        			settings.mUseParallel = true;
        			if (isInt(args[8]))
                        settings.setThreadCount(Integer.parseInt(args[8]));
        		}
        		break;
        	default :
        		System.out.println(E_INCORRECT_OPTION);
        	}
        }
         //Check if file name is correct and if threads are set
        if(settings.mUseTextFile && args[2].contains(".txt")) {
        	settings.setSource(args[2]);
        	switch (args[3]) {
        	case SAVENEWMATRIX :
        		settings.mSaveFile = true;
        		settings.setNewFile(args[4]);
        		break;
        	default :
        		System.out.println(E_INCORRECT_OPTION);
        	}
        if(settings.mSaveFile){
        	switch (args[5]) {
        	case THREADSNUMBER :
        		if(Integer.parseInt(args[6]) == 1) {
        			settings.mUseGeneral = true;
        		} else {
        			settings.mUseParallel = true;
        			if (isInt(args[6]))
        				settings.setThreadCount(Integer.parseInt(args[6]));
        		}
        		break;
        	default :
        		System.out.println(E_INCORRECT_OPTION);
        	}
        }
        }
        if(args[args.length - 1] == QUIET_REGIME) {
        	settings.mQuietRegime = true;
        }
        
        return settings;
	} //End of parseArgs
	
	
	public boolean shouldUseQuietRegime() {
		return mQuietRegime;
	}


	private void setThreadCount(int size) throws ArgsParseException {
        if ((size <= 0 || size > CORE_COUNT * 2) && size <= 32) {
            throw new ArgsParseException(size + E_INVALID_NUMBER_THREADS);
        }
        threadcount = size;
    }
	public boolean shouldUseParallel() {
        return mUseParallel;
    }

    public boolean shouldUseGeneral() {
        return mUseGeneral;
    }
    public boolean shouldUseTextFile() {
    	return mUseTextFile;
    }
	public int getThreadCount() {
		return threadcount;
	}
	private void setFirstD(int size) {
		firstDimensionArg = size;
	}
	private void setSecondD(int size) {
		secondDimensionArg = size;
	}
	private void setThirdD(int size) {
		thirdDimensionArg = size;
	}
	public int getFirstD() {
		return firstDimensionArg;
	}
	public int getSecondD() {
		return secondDimensionArg;
	}
	public int getThirdD() {
		return thirdDimensionArg;
	}
	
	private void setSource(String source) throws ArgsParseException {
	        mSourceMatrix = Paths.get(source);
	    }
	private void setNewFile(String source) throws ArgsParseException {
        mSourceMatrix = Paths.get(source);
    }
	public Path getSourceMatrix() {
        return mSourceMatrix;
    }

    public Path getDestination() {
        return mDestination;
    }
	private static boolean isInt(String str){
    	 if (str == null) {
    	        return false;
    	    }
    	    int length = str.length();
    	    if (length == 0) {
    	        return false;
    	    }
    	    int i = 0;
    	    if (str.charAt(0) == '-') {
    	        if (length == 1) {
    	            return false;
    	        }
    	        i = 1;
    	    }
    	    for (; i < length; i++) {
    	        char c = str.charAt(i);
    	        if (c < '0' || c > '9') {
    	            return false;
    	        }
    	    }
    	    return true;
    }   
	
	public boolean shouldPrintManual() {
        return mPrintManual;
    }
	
}
