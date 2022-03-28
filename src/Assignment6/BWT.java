package Assignment6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

public class BWT {


    private static int I;


    static int[] suffixArrayMaker(byte[] input) throws IOException {

        int[] suffixArray = new int[input.length+1];

        for (int i=0; i< suffixArray.length; i++){
            suffixArray[i] = i;
        }
        byteSort.MsdRadixSort(suffixArray,256, input);


        return suffixArray;
    }


    static byte[] bwt(byte[]input, int[] suffixArray){

        byte[] transform = new byte[suffixArray.length-1]; //behöver inte plats för $

        int j = 0;
        for (int i=0; i<suffixArray.length; i++){

            if (suffixArray[i]!=0){
                transform[j] = input[suffixArray[i] - 1];
          //      System.out.println(j + ": " + (char) transform[j]);
                j++;
            }
            else {I = i;}
        }

        return transform;
    }


    static byte[] moveToFront(byte[] bwt){
       // byte[] mtfList = new byte[256];
        byte[] output = new byte[bwt.length];

        MtfList mtfList = new MtfList();
        for (int i=0; i<256; i++){
            mtfList.add((byte)i);
        }

        for (int i=0; i<bwt.length; i++){

            int pos = mtfList.get(bwt[i]);
            output[i] = (byte) pos;
         //   System.out.println(output[i]);
        }



        return output;
    }








    public static void main(String[] args) throws IOException {
       // String file1 = "files/bwt/abra.txt";
      //  String file2 = "files/bwt/banana.txt";
        String file3 = "files/bible-oneline.txt";

        long start = System.currentTimeMillis();

        byte[] input = Files.readAllBytes(Path.of(file3));
        int[] suffixarray = suffixArrayMaker(input);

        byte[] bwtArr = bwt(input,suffixarray);

        byte[] mtfArr = moveToFront(bwtArr);

        System.out.println("mtf: " + Arrays.toString(mtfArr));

        Map<Byte,String> huffManCodesMap = HuffmanCode.getHuffmanCode(mtfArr);
        System.out.println("huffmancodes: " + huffManCodesMap.toString());

        long end = System.currentTimeMillis();
        long duration = end-start;
        System.out.println("durrrrr: " + duration);


        File outFile = new File("files/bwt/bibCOMP");
        OutputStream writer = new FileOutputStream(outFile);

        Print.printFile(writer,input,I, huffManCodesMap,mtfArr);







    }

}
