package Assignment4;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class suffixArrayMaker {

    static int length;
    static String input;
    static int[]suffixArray;

    static void makeSuffArray(String filename) throws IOException {


        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        input = br.readLine();

        length = input.length();

        suffixArray = new int[length];

        for (int i=0; i<length; i++){
            suffixArray[i] = i;
        }

        suffixRadixMsd.MsdRadixSort(suffixArray,256, input);

        long end = System.currentTimeMillis();

        long duration = end-start;
        System.out.println("duration: " + duration);
    }


    static int binarySearch(String key){

        int low = 0;
        int high = length-1;
        int mid;
        int cmp = 0;

        while (low <= high){
            mid = Math.floorDiv((low+high),2);
            cmp = compare(key, suffixArray[mid]);
            if (cmp<0){
                high = mid-1;
            }
            else if (cmp>0){
                low = mid+1;
            }
            else return mid;

        }

        return low;
    }

    static int compare(String key, int pos){
        int cmp = 0;
        int index =0;

        while (true){
            if (index >= key.length()){ //nått slutet på sökterm
                break;
            }
            if (index+pos >= length){ //nått slutet på input sträng
                cmp = 1;
                break;
            }

            cmp = key.charAt(index) - input.charAt(index + pos);

            if (cmp != 0 ){
                break;
            }
            index++;
        }

        return cmp;
    }


    static ArrayList<Integer> findNeighbours(String key, int suffixArrayPos){
        ArrayList<Integer> hits = new ArrayList<>();
        hits.add(suffixArray[suffixArrayPos]);

        int cmp;
        int forward = 1;
        int backward = -1;

        while (true){

            if (suffixArrayPos+forward >= suffixArray.length){
                break;
            }

            cmp = compare(key,suffixArray[suffixArrayPos+forward]);

            if (cmp == 0){

                hits.add(suffixArray[suffixArrayPos+forward]);
                forward++;

            }else break;

        }

        while (true){
            if (suffixArrayPos+backward < 0){
                break;
            }
            cmp = compare(key,suffixArray[suffixArrayPos + backward]);
            if (cmp==0){
                hits.add(suffixArray[suffixArrayPos + backward]);
                backward--;
            }else break;
        }


        return hits;
    }


    static void print(int substringStart, String key){


        String output;

        if (substringStart+key.length()+10 < input.length()){

            output = input.substring(substringStart,substringStart+key.length()+10);

        }else {
            output = input.substring(substringStart);
        }

        System.out.println(substringStart +": " + output);


    }



    static void userInterface(){
        while (true) {
            String key = JOptionPane.showInputDialog(null, "search suffix");
            if (key == null) {
                break;
            }

            int suffixArrayPos = binarySearch(key);



            ArrayList<Integer> hits = findNeighbours(key,suffixArrayPos);

            for (int i=0; i<hits.size(); i++) {
                print(hits.get(i), key);
            }

        }

    }


    public static void main(String[] args) throws IOException {
        //  String filename = "files/bible-oneline.txt";
        String filename = "files/suffixEasy.txt";

        makeSuffArray(filename);
        userInterface();




    }

}


