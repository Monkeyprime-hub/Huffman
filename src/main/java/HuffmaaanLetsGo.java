import fileUtils.FileInput;
import fileUtils.FileOutput;

import java.io.File;
import java.nio.charset.MalformedInputException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.io.EOFException;

public class HuffmaaanLetsGo {
    private static final byte ENCODING_TABLE_SIZE = 127;

    public  void compress(String stringPath) throws IOException {
        List<String> stringList;
        File inputFile = new File(stringPath);
        String s = "";
        File compressedFile, table;

        try {
            stringList = Files.readAllLines(Paths.get(inputFile.getAbsolutePath()));
        } catch (NoSuchFileException e) {
            System.out.println("Неверный путь, или такого файла неТУ!");
            return;
        }

        for (String item : stringList) {
            s += item;
            s += '\n';
        }

        HuffmanOperator operator = new HuffmanOperator(new HuffmanTree(s));

        compressedFile = new File(inputFile.getAbsolutePath() + ".hf");
        compressedFile.createNewFile();
        try (FileOutput fileOutput = new FileOutput(compressedFile)) {
            fileOutput.writeBytes(operator.getByted());
        }

        //создаем файлик

        table = new File(inputFile.getAbsolutePath() + ".table.txt");
        table.createNewFile();
        try (FileOutput fo = new FileOutput(table)) {
            fo.writeString(operator.getEncodingTable());
        }

        System.out.println("Путь файлу мечты: " + compressedFile.getAbsolutePath());
        System.out.println("Путь к таблице " + table.getAbsolutePath());

    }

    public  void extract(String filePath, String tablePath) throws FileNotFoundException, IOException {
        HuffmanOperator operator = new HuffmanOperator();
        File compressedFile = new File(filePath),
                tableFile = new File(tablePath),
                extractedFile = new File(filePath + ".hf");
        String compressed = "";
        String[] encodingArray = new String[ENCODING_TABLE_SIZE];

        try (FileInput fileInput = new FileInput(compressedFile)) {
            byte b;
            while (true) {
                b = fileInput.readByte();
                compressed += String.format("%8s", Integer.toBinaryString(b & 0xff)).replace(" ", "0");
            }
        } catch (EOFException e) {

        }



        // Туть мы читаем таблицу
        try (FileInput fi = new FileInput(tableFile)) {
            fi.readLine();
            encodingArray[(byte)'\n'] = fi.readLine();
            while (true) {
                String s = fi.readLine();
                if (s == null)
                    throw new EOFException();
                encodingArray[(byte)s.charAt(0)] = s.substring(1, s.length());
            }
        } catch (EOFException ignore) {}

        extractedFile.createNewFile();
        //extract:
        try (FileOutput fo = new FileOutput(extractedFile)) {
            fo.writeString(operator.extract(compressed, encodingArray));
        }

        System.out.println("Путь к файлу " + extractedFile.getAbsolutePath());
    }
}