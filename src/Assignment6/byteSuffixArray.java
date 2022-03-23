package Assignment6;

import java.io.IOException;

public class byteSuffixArray {


    private static int[]suffixArray;

    static int[] makeSuffArray(byte[] input) throws IOException {


        suffixArray = new int[input.length+1];

        for (int i=0; i< suffixArray.length; i++){
            suffixArray[i] = i;
        }
        byteSort.insertionSort(suffixArray,0, input);

        return suffixArray;

    }




}

