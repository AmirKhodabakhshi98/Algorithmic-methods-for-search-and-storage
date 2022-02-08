package Assignment2;

import java.util.Arrays;

public class msd {





    static  <T> void  Sort(T[]input, GetDigitHandle<T>gbh, int maxElemLength, int bitsInDigit, T[] aux, int lo, int hi, int d){

        if (hi <= lo){
            return;
        }

        int[] count = new int[bitsInDigit+2]; //+2 för o ha plats för -1 i getdigit

        for (int i=lo; i <= hi; i++){
            int digit = gbh.getDigit(input[i],d);
            count[digit+2] += 1;
        }

        for (int r=0; r<bitsInDigit+1;r++){
            count[r+1] += count[r];
        }


        for (int i = lo; i<=hi; i++){
            int digit = gbh.getDigit(input[i],d)+1;
            int position = count[digit];
            aux[position] = input[i];
            count[digit]++;
        }


        for (int i=lo; i<=hi; i++){
            input[i] = aux[i-lo];
        }


        for (int r=0; r<bitsInDigit; r++){
            Sort(input,gbh,maxElemLength,bitsInDigit,aux,lo+count[r],lo+count[r+1]-1,d+1);
        }




    }


    static <T> void MsdRadixSort(T[]input, GetDigitHandle<T>gbh, int maxElemLength, int bitsInDigit) {


        T[] aux = input.clone();
        Sort(input,gbh,maxElemLength,bitsInDigit,aux,0,input.length-1,0);

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
                return elem.charAt(i);
            } else {
                return -1;
            }
        }
    };



    interface GetDigitHandle<T> {
        int getDigit(T elem, int i);
    }


    public static void main(String[] args) {
        //      Integer[] arr = {1,6,9,2,13,11};
        //    Integer[] arr = {170, 45, 75, 90, 2, 802, 2, 66};
        //    Integer[] arr = {33,1,0,3,13};

        //   LsdRadixSort(arr,arrayIntGetter,1,0);
        //     arr = LsdRadixSort(arr, getInt,5,0);


     //   String[] arr = {"bbc", "xyz","abc","shells","she","soulykun", "POMEGRANATE", "Dab", "aaage","abdeee", "aaabce", "ab", };
       // String[] arr = {"seashells", "she","sells","sea","she","shore", "shells", "surely" };
        String[] arr = {"b", "c", "e", "d", "f", "g", "ba", "bca" };
         MsdRadixSort(arr, getChar,11,256);

        System.out.println(Arrays.toString(arr));
    }
}
