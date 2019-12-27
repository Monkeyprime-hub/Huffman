public class HuffmanOperator {
    private final byte ENCODING_TABLE_SIZE = 127;
    private HuffmanTree mainHuffmanTree;
    private String myString;
    private int[] freqArray;
    private String[] encodingArray;
    private double ratio;


    public HuffmanOperator(HuffmanTree MainHuffmanTree) {
        this.mainHuffmanTree = MainHuffmanTree;

        myString = mainHuffmanTree.getOriginalString();

        encodingArray = mainHuffmanTree.getEncodingArray();

        freqArray = mainHuffmanTree.getFrequenceArray();
    }

    public HuffmanOperator() {
    }


    private String getCompressedString() {
        String compressed = "";
        String intermidiate = "";

        //displayEncodingArray();
        for (int i = 0; i < myString.length(); i++) {
            intermidiate += encodingArray[myString.charAt(i)];
        }

        byte counter = 0; //количество добавленных нулей
        for (int length = intermidiate.length(), kolZero = 8 - length % 8;
             counter < kolZero; counter++) {
            intermidiate += "0";
        }


        compressed = String.format("%8s", Integer.toBinaryString(counter & 0xff)).replace(" ", "0") + intermidiate;


        setCompressionRatio();

        return compressed;
    }

    private void setCompressionRatio() {    // считаем кеф
        double sumA = 0, sumB = 0;
        for (int i = 0; i < ENCODING_TABLE_SIZE; i++) {
            if (freqArray[i] != 0) {
                sumA += 8 * freqArray[i];
                sumB += encodingArray[i].length() * freqArray[i];
            }
        }
        ratio = sumA / sumB;
    }

    public byte[] getBytedMsg() {
        StringBuilder compressedString = new StringBuilder(getCompressedString());
        byte[] compressedBytes = new byte[compressedString.length() / 8];
        for (int i = 0; i < compressedBytes.length; i++) {
            compressedBytes[i] = (byte) Integer.parseInt(compressedString.substring(i * 8, (i + 1) * 8), 2);
        }
        return compressedBytes;
    }

    public String extract(String compressed, String[] newEncodingArray) {
        String decompressed = "";
        String current = "";
        String delta = "";
        encodingArray = newEncodingArray;


        for (int i = 0; i < 8; i++)
            delta += compressed.charAt(i);
        int ADDED_ZEROES = Integer.parseInt(delta, 2);

        for (int i = 8, l = compressed.length() - ADDED_ZEROES; i < l; i++) {

            current += compressed.charAt(i);
            for (int j = 0; j < ENCODING_TABLE_SIZE; j++) {
                if (current.equals(encodingArray[j])) {
                    decompressed += (char) j;
                    current = "";
                }
            }
        }

        return decompressed;
    }

    public String getEncodingTable() {
        String enc = "";
        for (int i = 0; i < encodingArray.length; i++) {
            if (freqArray[i] != 0)
                enc += (char) i + encodingArray[i] + '\n';
        }
        return enc;
    }

    public double getCompressionRatio() {
        return ratio;
    }


    public void displayEncodingArray() {
        System.out.println("Encoding table");
        for (int i = 0; i < ENCODING_TABLE_SIZE; i++) {
            //if (freqArray[i] != 0) {
            System.out.print((char) i + " ");
            System.out.println(encodingArray[i]);
            //}
        }
        System.out.println("******************************************");
    }
}
