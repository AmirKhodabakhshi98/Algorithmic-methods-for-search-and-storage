package Assignment2;

import java.util.ArrayList;
import java.util.Arrays;

public class A2 {


    int[] testValue = {1,5,8,0};

    public A2(){




    }

    static <T> T[] LsdRadixSort(T[]input, GetDigitHandle<T> gbh, int maxLengthInDigits, int bitsInDigit){

        int countSize = 0;


        if (gbh == arrayIntGetter){
            countSize=10;
        }
        else if (gbh== arrayCharGetter){
            countSize=256;
        }

        String[] aux = new String[input.length];
        ArrayList<T> aux2 = new ArrayList<>();

        for (int i = maxLengthInDigits-1; i>=0; i--){
            System.out.println(i);
            int[] count = new int[countSize+1];

            // count frequencies
          for (int j=0; j<input.length; j++){
              int digit = gbh.getDigit(input[j], i);
              count[digit+1]+=1;
            //  count[digit+1]++;
          }

          //compute cumulates
          for (int n=0; n<countSize; n++){
                count[n+1] += count[n];
          }


          for (int x=0; x<input.length; x++){
              //aux[count[a[i]]++] =
              //aux[count[a[i]]+=1]
              int digit = gbh.getDigit(input[x], i); //hämtar värdet för digit
              int position = count[digit];  //hämtar nuvarande position som ska skrivas till
              aux[position] = String.valueOf(input[x]); //sätter till positionen
              count[digit]++;

          }

          for (int y=0; y<input.length; y++){

                  input[y] = (T) aux[y] ;
                  aux2.add((T)aux[y]);

          }


        }


        return input;
    }

    static <T> T[] countSort(T[] input, int place, GetDigitHandle<T> gbh){

        //räkna hur många ggr varje tal förekommer


        int[] countOccurences = new int[2];

        for (int i=0; i<input.length; i++){
            System.out.println(gbh.getDigit(input[i], place));
        }

        //gå igenom array igen sätt de på d stället de börjar på

        return input;

    }

    interface GetDigitHandle<T> {
        int getDigit(T elem, int i);
    }


    static GetDigitHandle<Integer> arrayIntGetter = new GetDigitHandle<Integer>() {
        public int getDigit(Integer elem, int i) {
            String str = elem.toString();

            if (i < str.length()) {
                return Integer.parseInt(String.valueOf(str.charAt(i))); }
            else                 {
                return 0; }
        }
    };


    static GetDigitHandle<String> arrayCharGetter = new GetDigitHandle<String>() {
        public int getDigit(String elem, int i) {

            if (i < elem.length()) {
                return  elem.charAt(i); }
            else                 {
                return 0; }
        }

    };









    public static void main(String[] args) {
     //   Integer[] arr = {1,6,9,2};

    //   LsdRadixSort(arr,arrayIntGetter,1,0);

       String[] arr = {"bbc", "xyz","abc","soulykun", "POMEGRANATE", "Dab", "aaage", "aaabc"};
        arr = LsdRadixSort(arr,arrayCharGetter,11,0);
        System.out.println(Arrays.toString(arr));
    }





}
