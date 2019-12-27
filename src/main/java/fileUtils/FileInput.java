package fileUtils;

import java.io.FileInputStream;
import java.io.EOFException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;

public class FileInput implements Closeable {
    private FileInputStream fileInputStream;
    private BufferedReader fileBufferedReader;

    public FileInput(File file) throws IOException {
        fileInputStream = new FileInputStream(file);
        fileBufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
    }


    public byte readByte() throws IOException {
        int currentByte = fileInputStream.read();
        if (currentByte == -1)
            throw new EOFException();
        return (byte) currentByte;
    }

    public String readLine() throws IOException {
        return fileBufferedReader.readLine();
    }

    @Override
    public void close() throws IOException {
        fileInputStream.close();
    }
}
