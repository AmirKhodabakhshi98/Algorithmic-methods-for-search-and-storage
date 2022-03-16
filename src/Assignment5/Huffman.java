package Assignment5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

    static PriorityQueue<Node> queue ;

    private static class Node implements Comparable<Node> {
        char c ;
        int freq = 0;

        public Node(char c, int freq ){
            this.c = c;
            this.freq = freq;
        }

        public Node(){

        }

        public int compareTo(Node node){
            // return Integer.compare(freq, node.freq);
            return freq - node.freq;
        }
    }


    //reads a file and creates a priority queue for it's char based on their freq
    static void createPriorityQueue(String filename) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        StringBuilder sb = new StringBuilder();

        Map<Character,Integer> freqMap = new HashMap<Character, Integer>();

        queue = new PriorityQueue<>();

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

        for (Map.Entry<Character,Integer> entry: freqMap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            queue.add(node);
        }



        int size = queue.size();

        for (int i=0; i < size; i++){
            Node n = queue.poll();
            System.out.println(n.c + " - " + n.freq);
        }


    }


    static void sortMap(){



    }

    public static void main(String[] args) throws IOException {
        String filename = "files/huffAbra.txt";
        //  String filename = "files/bible-en.txt";
        createPriorityQueue(filename);
    }
}
