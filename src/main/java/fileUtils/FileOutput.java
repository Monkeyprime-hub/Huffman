package fileUtils;

import java.io.*;

public class FileOutput implements Closeable {

    private File outputFile;
    private FileOutputStream fileOutputStream;

    public FileOutput(File file) throws FileNotFoundException {
        outputFile = file;
        fileOutputStream = new FileOutputStream(outputFile);
    }

    public void writeByte(byte myByte) throws IOException {
        fileOutputStream.write(myByte);
    }

    public void writeBytes(byte[] bytes) throws IOException {
        fileOutputStream.write(bytes);
    }

    public void writeString(String string) {
        try (PrintWriter pw = new PrintWriter(outputFile)) {
            pw.write(string);
        } catch (FileNotFoundException e) {
            System.out.println("Неверный путь, или такого файла НЕту!");
        }
    }

    @Override
    public void close() throws IOException {
        fileOutputStream.close();
    }

    public void finalize() throws IOException {
        close();
    }
}