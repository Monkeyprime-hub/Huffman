import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        HuffmaaanLetsGo huffmaaanLetsGo = new HuffmaaanLetsGo();
//        huffmaaanLetsGo.compress("C:\\Users\\User\\Desktop\\primerrr.txt");
        huffmaaanLetsGo.extract("C:\\Users\\User\\Desktop\\primerrr.txt.hf","C:\\Users\\User\\Desktop\\primerrr.txt.table.txt" );

    }
}