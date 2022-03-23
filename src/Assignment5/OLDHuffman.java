package Assignment5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class OLDHuffman {

    static PriorityQueue<Node> queue;
    static Map<Character, String> huffmanCodesMap;
    static Node root;
    static String input;

    private static class Node implements Comparable<Node> {
        char c;
        int freq = 0;
        Node left;
        Node right;

        public Node(char c, int freq ){
            this.c = c;
            this.freq = freq;

        }

        public Node(Node left, Node right){
            this.left = left;
            this.right = right;
            freq = left.freq + right.freq;

        }

        public int compareTo(Node node){
            // return Integer.compare(freq, node.freq);
            return freq - node.freq;
        }
    }


    //reads a file and creates a priority queue for it's char based on their freq
    static void createPriorityQueue(String filename) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader(filename));

        StringBuilder sb = new StringBuilder();

        Map<Character,Integer> freqMap = new HashMap<Character, Integer>();

        queue = new PriorityQueue<>();

        while ((input = br.readLine()) != null){
            sb.append(input);
        }

        input = sb.toString();

        for (int i=0; i<input.length(); i++){

            char c = input.charAt(i);

            if (!freqMap.containsKey(c)){
                freqMap.put(c,1);
            }
            else freqMap.put(c, freqMap.get(c)+1);
        }

        for (Map.Entry<Character,Integer> entry: freqMap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            queue.add(node);
        }

        System.out.println("Frequency map:" +  freqMap.entrySet());


    }


    static void createHuffmanTree(){


        while (queue.size()>2){
            Node left = queue.poll();
            Node right = queue.poll();
            Node node = new Node(left, right);
            queue.add(node);
        }

        root = new Node(queue.poll(), queue.poll());

    }


    static void encodeTree(){
        huffmanCodesMap = new HashMap<>();

        StringBuilder code = new StringBuilder();
        preOrderTraversal(root, code, 0);
        System.out.println("Huffman codes: " + huffmanCodesMap.entrySet());

    }


    static void preOrderTraversal(Node node, StringBuilder code, int codeLength){

        if (node==null){
            return;
        }

        if (node.c != '\0'){
            huffmanCodesMap.put(node.c, code.toString());

        }

        code.append("0");
        preOrderTraversal(node.left, code, code.length());

        code.setLength(codeLength);
        code.append("1");

        preOrderTraversal(node.right, code, code.length());

    }


    static void print(){
        int length = input.length();



    }


    public static void main(String[] args) throws IOException {
      //  String filename = "files/huffAbra.txt";
        //  String filename = "files/bible-en.txt";
        String filename = "files/abra.txt";
        createPriorityQueue(filename);
        createHuffmanTree();
        encodeTree();


    }
}
