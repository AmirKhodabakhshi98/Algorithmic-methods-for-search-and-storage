package Assignment2;

import java.util.Arrays;

public class insertionPractice {




    static void insertion(int[] input, int arrStart, int arrEnd){
        for (int i = arrStart+1; i<=arrEnd; i++){
            int j=i;
            while (j>0 && input[j-1]>input[j]){

                int temp = input[j-1];
                input[j-1] = input[j];
                input[j]=temp;
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,12,9,4,0,11,15,99,22,500,501,499,2,23};
        insertion(arr,1,2);
        System.out.println(Arrays.toString(arr));

        String s1 = "aa";
        String s2 = "a";

        System.out.println(s1.compareTo(s2));



    }

/*
    static <T> void insertionSort(T[]input, GetDigitHandle<T> gbh, int d, int arrStart, int arrEnd){
        for (int i = arrStart+1; i<=arrEnd; i++){
            int j=i;
            while (j>0 && input[j-1]>input[j]){

                j--;

 */
}
