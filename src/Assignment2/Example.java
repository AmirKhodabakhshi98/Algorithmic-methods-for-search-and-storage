package Assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Example {
    interface GetDigitHandle<T> {
        int getDigit(T elem, int i);
    }

    static <T> int maxLength(T[] arr, GetDigitHandle<T> gbh) {
        int i, j, maxl = -1;

        for (i = 0; i < arr.length; i++) {
            j = 0;
            while (gbh.getDigit(arr[i], j) >= 0) {
                j++; }
                if (maxl < j) {
                        maxl = j; }
        }
        return maxl;
    }

    static GetDigitHandle<String> arrayByteGetter = new GetDigitHandle<String>() {
        public int getDigit(String elem, int i) {
            if (i < elem.length()) {
                return elem.charAt(i); }
            else                 {
                return -1; }
        }
    };

    // Example use:
    public static void main(String[] args) throws IOException {

        String fileLocation = "files/bible-lines.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
        String str = "";
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();

        //read file content
        while ((str = br.readLine()) !=null){
            list.add(str);
        }


        String[] arr = new String[list.size()];
        list.toArray(arr);



        int m = maxLength(arr, arrayByteGetter);
        System.out.println(m);  // outputs 5
    }
}