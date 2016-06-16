package src;

import static src.Messages.*;

public class RunSettings {
	
	//Options
	private static final String FIRSTMATRIXROWS   =  "-m";
	private static final String FIRSTMATRIXCOLS   =  "-n";
	private static final String SECONDMATRIXCOLS  =  "-k";
	
	private static final String THREADSNUMBER     =  "-t";
	private static final String TEXTFILE          =  "-i";
	private static final String HELP              =  "-h";
	
	private boolean mPrintManual = false;
	private boolean mUseParallel = false;
	private boolean mUseGeneral = false;
	private boolean mUseTextFile = false;
	private boolean mFirstMatrixRows = false;
	private boolean mFirstMatrixCols = false;
	private boolean mSecondMatrixRows = false;

	private int THREADCOUNT = 1;
	
	
	
	public static RunSettings parseArgs(String[] args) throws ArgsParseException {
        
		if (args.length < 5)
            throw new ArgsParseException(E_MISSING_PATHS);
		
		RunSettings settings = new RunSettings();
        
        //Check if first argument is for help
        if (args.length == 0 || (args.length == 1 && args[0].equals(HELP)) || args[1].equals(HELP)) {
            settings.mPrintManual = true;
            return settings;
        }
        
        //Check if it is text file with matrices or set of dimensions
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
        	switch (args[3]) {
        	case  FIRSTMATRIXCOLS :
        		settings.mFirstMatrixCols = true;
        		break;
        	default :
        		System.out.println(E_INCORRECT_OPTION );
        	}
        }
        
        //Check if dimensions are set correctly
        if(settings.mFirstMatrixCols && isInt(args[3])){
        	switch (args[4]) {
        	case SECONDMATRIXCOLS :
        		settings.mSecondMatrixRows = true;
        		break;
        	default :
        		System.out.println(E_INCORRECT_OPTION);
        	}
        }
        
        //Check if dimensions are set correctly and if threads are set
        if(settings.mSecondMatrixRows && isInt(args[5])) {
        	switch (args[6]) {
        	case THREADSNUMBER :
        		if(Integer.parseInt(args[4]) == 1) {
        			settings.mUseGeneral = true;
        		} else {
        			settings.mUseParallel = true;
        		}
        		break;
        	default :
        		System.out.println(E_INCORRECT_OPTION);
        	}
        }
         //Check if file name is correct and if threads are set
        if(settings.mUseTextFile && args[2].contains(".txt")) {
        	switch (args[3]) {
        	case THREADSNUMBER :
        		if(Integer.parseInt(args[4]) == 1) {
        			settings.mUseGeneral = true;
        		} else {
        			settings.mUseParallel = true;
        		}
        		break;
        	default :
        		System.out.println(E_INCORRECT_OPTION);
        	}
        }
        return settings;
	} //End of 

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
