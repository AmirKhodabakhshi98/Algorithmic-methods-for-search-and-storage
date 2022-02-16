package Assignment2;

import java.util.Arrays;

public class msd {



    interface compare<T> {
        boolean isPrevBiggerThanNext(T elem, T elem2, int startPos);
    }


    static compare<String> getStringCompare = new compare<String>() {
        public boolean isPrevBiggerThanNext(String prev, String next, int startPos) {

            try {


           //     if (prev.substring(startPos - 1).compareTo(next.substring(startPos - 1)) > 0) {
                if (prev.substring(startPos).compareTo(next.substring(startPos)) > 0) {
                    //  if (prev.compareTo(next)>0){
                    return true;
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(prev);
                System.out.println(next);
                System.out.println(startPos);
                System.exit(0);
            }
            return false;
        }
    };

    static compare<Integer> getIntCompare = new compare<Integer>() {
        public boolean isPrevBiggerThanNext(Integer prev, Integer next, int startPos) {

            if (prev>next){
                return true;
            }
             return false;

        }
    };


    static <T> void insertionSort(T[]input, GetDigitHandle<T> gbh, int d, int arrStart, int arrEndInclusive){
     //   compare comp = null;
     //   if (gbh==getChar){
     //       comp = getStringCompare;
     //   }else if (gbh==getInt){
     //       comp = getIntCompare;
     //   }

        for (int i = arrStart+1; i<=arrEndInclusive; i++){

            int j=i;

           while (j>arrStart &&
                   isPrevBiggerThanNextNew(input[j-1],input[j],gbh,d)){

                T temp = input[j-1];
                input[j-1] = input[j];
                input[j] = temp;
                j--;
            }
       }
    }
    

    static <T> boolean isPrevBiggerThanNextNew(T prev, T next, GetDigitHandle<T> gbh, int d){

       // int d = d;

        while (gbh.getDigit(prev, d) == gbh.getDigit(next, d)) {

            d++;

            if (gbh.getDigit(prev,d) == -1 &&
                    gbh.getDigit(next,d) == -1){

                return false;
            }
        }

        return gbh.getDigit(prev, d) > gbh.getDigit(next, d);
    }


    //d = den position i sträng vi e i
    static  <T> void  Sort(T[]input, GetDigitHandle<T>gbh, int maxElemLength, int alphabetSize, T[] aux, int lo, int hi, int d){

      //  if (hi <= lo){
     //       return;
     //   }
        if (hi-lo <21){
            insertionSort(input,gbh,d,lo,hi);
            return;
        }

        int[] count = new int[alphabetSize+2]; //+2 för o ha plats för -1 i getdigit

        //frequency
        for (int i=lo; i <= hi; i++){
            int digit = gbh.getDigit(input[i],d);
            count[digit+2] += 1;
        }

        //cumulatives
        for (int r=0; r<alphabetSize+1;r++){
            count[r+1] += count[r];
        }

        //
        for (int i = lo; i<=hi; i++){
            int digit = gbh.getDigit(input[i],d)+1; //hämtar värdet för digit
            int position = count[digit]; //hämtar nuvarande position som ska skrivas till
            aux[position] = input[i];
            count[digit]++;
        }


        for (int i=lo; i<=hi; i++){
            input[i] = aux[i-lo];
        }

        //inkl hi
        for (int r=0; r<alphabetSize; r++){
            Sort(input,gbh,maxElemLength,alphabetSize,aux,lo+count[r],lo+count[r+1]-1,d+1);
        }




    }


    static <T> void MsdRadixSort(T[]input, GetDigitHandle<T>gbh, int maxElemLength, int alphabetSize) {

        T[] aux = input.clone();
        Sort(input,gbh,maxElemLength,alphabetSize,aux,0,input.length-1,0);

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


       // String[] arr = {"seashells", "she","sells","sea","she","shore", "shells", "surely" };
        String[] arr = {"b", "c", "e", "d", "f", "g", "ba", "bca" };
         MsdRadixSort(arr, getChar,11,256);

        System.out.println(Arrays.toString(arr));
    }
}
