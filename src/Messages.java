package src;

public class Messages {

	public static final String E_MANUAL                 = "Manual might not be shown correctly.";
	public static final String E_HELP_USED_NOT_ALONE    = "-h can only be used alone.";
	public static final String E_OPTION_REPETITION      = "Repeated options are not allowed.";
	public static final String E_UNKNOWN_OPTION         = "Not a valid option.";
	public static final String E_INCORRECT_OPTION       = "Incorrect option";
    public static final String E_MATRIX_SIZES           = "Incompatible matrices.";
    public static final String E_LOADING_MATRICES       = "Failed to load source matrices.";
    public static final String E_SAVING_RESULT          = "Failed to save result.";
    public static final String E_INVALID_NUMBER_THREADS = "Invalid number of threads";
    public static final String E_INVALID_POOL_SIZE      = " is an invalid threads to use argument.";
    
    public static final String E_MISSING_PATHS    = "Specify at least 3 Paths.";
    public static final String M_LOADING_MATRICES = "Loading matrices...";
    public static final String M_SAVING_RESULT    = "Saving result...";
    public static final String M_AFTER_PASS       = "Pass %d finished in %d %s\n";
    public static final String M_TOTAL_RUNTIME    = "Benchmark with %d passes finished in %d %s\n";
    public static final String M_NOT_RUNNING      = "Task is not running.";
    public static final String M_THREAD_USED      = "The multiplication task is currently using 0 threads.";
    public static final String M_CALCULATING      = "Calculating...";
    public static final String M_MATRIX_INFO      = "%s matrix is with width %d and height %d.\n";

    public Messages() {}
	
}
