package Assignment5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Huffman {


    private class node{
        int freq;
        char c;
    }


    static void readInput() throws IOException {
       // String filename = "files/huffAbra.txt";
        String filename = "files/bible-en.txt";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        StringBuilder sb = new StringBuilder();

        Map<Character,Integer> freqMap = new HashMap<Character, Integer>();

        while ((str = br.readLine()) != null){
            sb.append(str);
        }


        str = sb.toString();

        for (int i=0; i<str.length(); i++){

            char c = str.charAt(i);

            if (!freqMap.containsKey(c)){
                freqMap.put(c,1);
            }
            else freqMap.put(c, freqMap.get(c)+1);
        }






    }


    static void sortMap(){



    }

    public static void main(String[] args) throws IOException {
        readInput();
    }
}
