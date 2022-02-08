package Assignment2;

import java.util.Arrays;

public class lsd {



    static <T> void LsdRadixSort(T[]input, GetDigitHandle<T> gbh, int maxElemLength, int bitsPerDigit){



       T[] aux = input.clone();


        for (int i = maxElemLength-1; i>=0; i--){
            int[] count = new int[bitsPerDigit+2];

            // count frequencies
            for (int j=0; j<input.length; j++){
                int digit = gbh.getDigit(input[j], i);
               count[digit+2]+=1;
            }


            //compute cumulates
            for (int n=0; n<bitsPerDigit; n++){
                count[n+1] += count[n];
            }


            for (int x=0; x<input.length; x++){
                int digit = gbh.getDigit(input[x], i)+1; //hämtar värdet för digit
                int position = count[digit];  //hämtar nuvarande position som ska skrivas till
                aux[position] = input[x]; //sätter till positionen
                count[digit]++;
            }


            for (int y = 0; y<input.length; y++){
                input[y] = aux[y];
            }

        }


    }




    interface GetDigitHandle<T> {
        int getDigit(T elem, int i);
    }


    static GetDigitHandle<Integer> getInt = new GetDigitHandle<Integer>() {
        public int getDigit(Integer elem, int i) {

            if (i<4 && i>-1){
                return elem>>((3-i)*8)&255;
            }
            else                 {
                return -1; }
        }


    };


    static GetDigitHandle<String> getChar = new GetDigitHandle<String>() {
        public int getDigit(String elem, int i) {

            if (i < elem.length()) {
                return  elem.charAt(i); }

            else                 {
                return -1; }
        }

    };









    public static void main(String[] args) {
     //      Integer[] arr = {1,6,9,2,13,11};
       //    Integer[] arr = {170, 45, 75, 90, 2, 802, 2, 66};
       //    Integer[] arr = {33,1,0,3,13};

        //   LsdRadixSort(arr,arrayIntGetter,1,0);
     //     arr = LsdRadixSort(arr, getInt,5,256;

    //    String[] arr = {"b", "c", "e", "d", "f", "g", "ba" };

        //String[] arr = {"bbc", "xyz","abc","soulykun", "POMEGRANATE", "Dab", "aaage","abdeee", "aaabce", "ab", };

    //    System.out.println(Arrays.toString(arr));
    }





}
