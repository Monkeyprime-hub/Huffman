import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        HuffmaaanLetsGo huffmaaanLetsGo = new HuffmaaanLetsGo();
//        huffmaaanLetsGo.compress("C:\\Users\\User\\Desktop\\primer.txt");
        huffmaaanLetsGo.extract("C:\\Users\\User\\Desktop\\primer.txt.hf","C:\\Users\\User\\Desktop\\primer.txt.table" );

    }
}