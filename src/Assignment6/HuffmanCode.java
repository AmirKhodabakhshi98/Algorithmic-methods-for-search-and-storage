package Assignment6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanCode {

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


        //leaf constructor
        public Node(Byte b, int freq ){
            this.b = b;
            this.freq = freq;
            isLeaf = true;
        }

        //parent constructor
        public Node(Node left, Node right){
            this.left = left;
            this.right = right;
            freq = left.freq + right.freq;
        }

        public int compareTo(Node node){
            return freq - node.freq;
        }
    }


    static Map<Byte, String> getHuffmanCode(byte[] arr) throws IOException {
        readInputAndCreatePriorityQueue(arr);
        createHuffmanTree();

        return encodeTree();
    }

    //reads a file and creates a priority queue for it's char based on their freq
    static void readInputAndCreatePriorityQueue(byte[] in) throws IOException {

        Map<Byte,Integer> freqMap = new HashMap<Byte, Integer>();
        queue = new PriorityQueue<>();

        input = in;

        for (int i=0; i<input.length; i++){

            if (!freqMap.containsKey(input[i])){

                freqMap.put(input[i],1);
            }
            else freqMap.put(input[i], freqMap.get(input[i])+1);
        }

        System.out.println("frequency map: " + freqMap.toString());

        for (Map.Entry<Byte,Integer> entry: freqMap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            queue.add(node);
        }

    }


    static void createHuffmanTree(){

        while (queue.size()>2){
            Node node = new Node(queue.poll(), queue.poll());
            queue.add(node);
        }

        root = new Node(queue.poll(), queue.poll());

    }


    static Map<Byte,String> encodeTree(){
        huffmanCodesMap = new HashMap<>();

        StringBuilder code = new StringBuilder();

        preOrderTraversal(root, code, 0);

        return huffmanCodesMap;

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






}
