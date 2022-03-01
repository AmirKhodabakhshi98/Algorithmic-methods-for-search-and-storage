package Assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class suffixArrayMaker {



    static int[] makeSuffArray() throws IOException {
        String filename = "files/bible-oneline.txt";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str = br.readLine();

        suffixClass[] suffArray = new suffixClass[str.length()];



        for (int i=0; i<str.length(); i++){
            suffArray[i] = new suffixClass(i,str.substring(i));
        }

        suffixRadixMsd.MsdRadixSort(suffArray, suffixRadixMsd.getChar,256);

       // System.out.println(Arrays.toString(suffArray));

        int[] indexArr = new int[str.length()];
        for (int i=0; i<suffArray.length; i++){
            indexArr[i] = suffArray[i].getIndex();

        }

        return indexArr;
    }









    public static void main(String[] args) throws IOException {


        suffixArrayMaker.makeSuffArray();



    }

}
