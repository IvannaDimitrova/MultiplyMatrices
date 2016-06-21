package src.exceptions;

public class MatrixSizeMultiplicationException extends IllegalArgumentException implements MsgException {

    private static final long serialVersionUID = 1L;

    public MatrixSizeMultiplicationException() {super();}

    @Override
    public String getErrorMsg() { return "Can not perform multiplication on matrices of that size."; }

}
