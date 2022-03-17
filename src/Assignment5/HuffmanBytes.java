package Assignment5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanBytes {

    static PriorityQueue<Node> queue;
    static Map<Byte, String> huffmanCodesMap;
    static Node root;
    static byte[] input;

    private static class Node implements Comparable<Node> {

        byte b;
        int freq;
        Node left;
        Node right;
        boolean isLeaf = false;


        public Node(Byte b, int freq ){
            this.b = b;
            this.freq = freq;
            isLeaf = true;

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
    static void readInputAndCreatePriorityQueue(String filename) throws IOException {

        Map<Byte,Integer> freqMap = new HashMap<Byte, Integer>();
        queue = new PriorityQueue<>();

         input = Files.readAllBytes(Path.of(filename));

        for (int i=0; i<input.length; i++){

            if (!freqMap.containsKey(input[i])){

                freqMap.put(input[i],1);
            }
            else freqMap.put(input[i], freqMap.get(input[i])+1);
        }


        for (Map.Entry<Byte,Integer> entry: freqMap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            queue.add(node);
        }

       // System.out.println("Frequency map:" +  freqMap.entrySet());

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

        if (node.isLeaf){
            huffmanCodesMap.put(node.b, code.toString());

        }

        code.append("0");
        preOrderTraversal(node.left, code, code.length());

        code.setLength(codeLength);
        code.append("1");

        preOrderTraversal(node.right, code, code.length());

    }



    static void print(){
        StringBuilder sb = new StringBuilder();
        int length = input.length;
        System.out.println(Integer.toBinaryString(length));

    }


    public static void main(String[] args) throws IOException {
      //  String filename = "files/huffAbra.txt";
        //  String filename = "files/bible-en.txt";
        String filename = "files/abra.txt";
        readInputAndCreatePriorityQueue(filename);
        createHuffmanTree();
        encodeTree();
        print();





    }
}
