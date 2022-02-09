package Assignment2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class testString {



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

        long startTime = System.currentTimeMillis();
        lsd.LsdRadixSort(arr,lsd.getChar,528,256);
    //    msd.MsdRadixSort(arr,msd.getChar,528,256);
      //  Arrays.sort(arr);
        long endTime = System.currentTimeMillis();

        long duration = (endTime-startTime);
        System.out.println("duration ms: " + duration);
        System.out.println("duration s: " + duration / 1000);

        for (int i=0; i<arr.length-1; i++){
            if (arr[i].compareTo(arr[i+1]) > 0 ){
                System.out.println("unsorted@ " + i);
                System.out.println(arr[i] + " - " + arr[i+1]);
                break;
            }

        }





     //   String fileName = "files/.txt";
     //  BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));


     //   for (int i=0; i<arr.length-1; i++){
     //       String str1 = arr[i];
     //      writer.write(str1 + "\n");
    //    }
   //     writer.close();


    }



}








