package Assignment6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BWT {


    private static int I;


    static int[] suffixArrayMaker(byte[] input) throws IOException {

        int[] suffixarray = byteSuffixArray.makeSuffArray(input);

        /*
        for (int start:
             suffixarray) {

            for (int i = start; i<input.length; i++){
                System.out.print((char)input[i]);
            }
            System.out.println();

        }
        */

        return suffixarray;
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

    static void moveToFront(byte[] bwt){
       // byte[] mtfList = new byte[256];
        byte[] output = new byte[bwt.length];

        MtfList mtfList = new MtfList();
        for (int i=0; i<256; i++){
            mtfList.add((byte)i);
        }

        for (int i=0; i<bwt.length; i++){

            int pos = mtfList.get(bwt[i]);
            output[i] = (byte) pos;
            System.out.println(output[i]);
        }



      /*  int pos;
        int outPos = 0;

        for (byte item: bwt) {
            pos = findItem(item,mtfList); // hittar var den finns i listan
            output[outPos++] = (byte) pos; // skriver ut värdet.

            move(mtfList, pos);


        }
*/
    }

    /*
    static byte[] move(byte[] unsorted, int pos){


        return null;
    }
    static int findItem(byte item, byte[] mtfList){

        for (int i=0; i<mtfList.length;i++){
            if (item==mtfList[i]){
                return i;
            }
        }

        return -1;
    }


*/







    public static void main(String[] args) throws IOException {
        String file1 = "files/bwt/abra.txt";
        String file2 = "files/bwt/banana.txt";
        byte[] input = Files.readAllBytes(Path.of(file1));
        int[] suffixarray = suffixArrayMaker(input);
        byte[] bwtArr = bwt(input,suffixarray);
        moveToFront(bwtArr);
    }

}
