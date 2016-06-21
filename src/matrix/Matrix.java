package src.matrix;

public class Matrix {
	

	private int mWidth;
	private int mHeight;
	private double[][] mElements;
	
	public Matrix()  {}
	public Matrix(int height, int width) {
		mWidth = width;
        mHeight = height;

        mElements = new double[height][width];
	}

	public int width() {
		return mWidth;
	}
	
	public int height() {
		return mHeight;
	}
	
	public void setElement(int row, int col, double value) {
	       mElements[row][col] = value;
	   }
	
	public double getElement(int row, int col) {
        return mElements[row][col];
    }
	
	public static enum Sector {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
	
	public void insert(Matrix B, Sector sector) {
        int rowStartA = 0, colStartA = 0;
        int rowStartB = 0, colStartB = 0;

        int rows = Math.min(this.mHeight, B.mHeight);
        int cols = Math.min(this.mWidth, B.mWidth);

        switch (sector) {

            case UP:
                break;

            case DOWN:
                colStartA = mWidth - cols;
                colStartB = B.mWidth - cols;
                break;

            case LEFT:
                rowStartA = mHeight - rows;
                rowStartB = B.mHeight - rows;
                break;

            case RIGHT:
                rowStartA = mHeight - rows; colStartA = mWidth - cols;
                rowStartB = B.mHeight - rows; colStartB = B.mWidth - cols;
                break;

        }

        for (int i = 0; i < rows; ++i)
            System.arraycopy(B.mElements[rowStartB + i], colStartB, mElements[rowStartA + i], colStartA, cols);
    }

	
	
	
}
