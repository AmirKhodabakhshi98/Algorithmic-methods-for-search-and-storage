package Assignment2;

import java.io.*;
import java.util.ArrayList;
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

        lsd.LsdRadixSort(arr,lsd.getChar,1000,256);
      //  msd.MsdRadixSort(arr,msd.getChar,1000,256);











     //   String fileName = "files/LSDBible.txt";
     //   BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));


     //   for (int i=0; i<arr.length-1; i++){
        //    if (arr[i]>arr[i+1]){
        //        System.out.println("unsorted");
       //         System.out.println(i);
      //          break;
        //    String str1 = arr[i];
        //    writer.write(str1 + "\n");

      //  }
    //    writer.close();


    }



}








