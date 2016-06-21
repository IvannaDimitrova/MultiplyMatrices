package src.io;

import src.matrix.Matrix;
import java.io.*;
import java.nio.file.Path;

public class MatrixWriter implements Closeable {
	
	private final Path mMatrixFile;
    private OutputStream mOutputStream;
    private DataOutputStream mDataStream;

    public MatrixWriter(Path path) throws FileNotFoundException {
        mMatrixFile = path;
        openMatrixFile();
    }

    public void writeMatrix(Matrix C) throws IOException {
        // Writing out sizes
        mDataStream.writeInt(C.height());
        mDataStream.writeInt(C.width());

        // Writing out data
        for (int i = 0; i < C.height(); ++i) {
            for (int j = 0; j < C.width(); ++j) {
                mDataStream.writeDouble(C.getElement(i, j));
            }
        }

        mDataStream.flush();
    }

    @Override
    public void close() throws IOException {
        mDataStream.close();
        mOutputStream.close();
    }

    private void openMatrixFile() throws FileNotFoundException {
        mOutputStream = new FileOutputStream(mMatrixFile.toString());
        mDataStream = new DataOutputStream(mOutputStream);
    }

}
