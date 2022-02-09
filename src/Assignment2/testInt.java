package Assignment2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;

public class testInt {

    public static void main(String[] args) throws IOException {

        String fileLocation = "files/ints.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
        StringBuilder sb = new StringBuilder();
        String str = "";


        LinkedList<Integer> list = new LinkedList<Integer>();
        //read file content
        while ((str = br.readLine()) !=null){
            list.add(Integer.parseInt(str));
        }

        Integer[]arr=  new Integer[list.size()];
        list.toArray(arr);

        long start = System.currentTimeMillis();
    //    lsd.LsdRadixSort(arr,lsd.getInt,4,256);
        msd.MsdRadixSort(arr,msd.getInt,4,256);
      //  Arrays.sort(arr);
        long end = System.currentTimeMillis();
        long duration = end-start;
        System.out.println("duration ms: " + duration);
        System.out.println("duration s: " + duration/1000);

        for (int i=0; i<arr.length-1; i++){
            if (arr[i]>arr[i+1]){
                System.out.println("unsorted");
                System.out.println(arr[i] + " - " + arr[i+1]);
                break;
            }
        }





       // arr= lsd.LsdRadixSort(arr,lsd.getChar,1000,256);

        //  msd.MsdRadixSort(arr,msd.getChar,1000,256);

     //   String fileName = "files/ArraySort-INT.txt";
    //    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

//
    //   for (int i=0; i<arr.length-1; i++){

          //      int str1 = arr[i];
        //      writer.write(str1 + "\n");

   //     }
    //       writer.close();



    }


}
