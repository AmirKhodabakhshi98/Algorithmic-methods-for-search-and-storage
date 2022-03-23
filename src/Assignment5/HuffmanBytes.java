package Assignment5;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
            Node node = new Node(queue.poll(), queue.poll());
            queue.add(node);
        }

        root = new Node(queue.poll(), queue.poll());

    }


    static void encodeTree(){
        huffmanCodesMap = new HashMap<>();

        StringBuilder code = new StringBuilder();

        preOrderTraversal(root, code, 0);

      //  concatTest(root,"");

        System.out.println("Huffman codes: " + huffmanCodesMap.entrySet());

    }


    static void concatTest(Node node, String code){

        if (node==null){
            return;
        }

        if (node.isLeaf){
            huffmanCodesMap.put(node.b, code);
        }

        concatTest(node.left, code.concat("0"));

        concatTest(node.right, code.concat("1"));

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





    static void printBytes(String outpath) throws IOException {

      //  String outFile = "files/huff/abracomp.huff";
      //  BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));


        File file = new File(outpath);
        OutputStream writer = new FileOutputStream(file);

        printSize(writer);

        byte b = 0;
        int bitPos = 7;


        for (int c = 0; c<256; c++){

            if (!huffmanCodesMap.containsKey((byte)c)){
                    bitPos = decreaseBitPos(bitPos);
                    b = checkForWrite(b,bitPos, writer);
            }

            if (huffmanCodesMap.containsKey((byte)c)){

                String code = huffmanCodesMap.get((byte)c);

                //unary
                for (int i=0; i<code.length();i++){
                    b = setBit(b,bitPos);
                    bitPos = decreaseBitPos(bitPos);
                    b = checkForWrite(b,bitPos, writer);
                }

                bitPos = decreaseBitPos(bitPos);//0-bit after unary

                b = checkForWrite(b,bitPos, writer);

                //print symbol codes
                for (int i = 0; i<code.length(); i++){
                    int bit = Character.getNumericValue(code.charAt(i));

                    if (bit==1){
                        b = setBit(b,bitPos);
                        bitPos = decreaseBitPos(bitPos);

                    }else if (bit == 0){
                        bitPos = decreaseBitPos(bitPos);
                    }

                    b = checkForWrite(b,bitPos, writer);
                }
            }

        }


        //print input
        for (int i=0; i<input.length; i++){

            String code = huffmanCodesMap.get(input[i]);

            for (int j=0; j<code.length(); j++){
                int bit = Character.getNumericValue(code.charAt(j));
                if (bit==0){
                    bitPos = decreaseBitPos(bitPos);

                }else if (bit==1){
                    b = setBit(b,bitPos);
                    bitPos = decreaseBitPos(bitPos);
                }

                b = checkForWrite(b,bitPos, writer);
            }

        }

        //zero padding
        if (bitPos != 7){
            checkForWrite(b,7,writer);
        }

        writer.close();


    }


    static void debugRead(String str) throws IOException{
        Path path = Paths.get(str);
        byte[] arr = Files.readAllBytes(path);
        System.out.println("längd: " + arr.length);
    }

    static byte checkForWrite(byte b, int bitPos, OutputStream writer) throws IOException {
        if (bitPos==7){
            writer.write(b);
            writer.flush();
            return 0;
        }
        return b;
    }
    static int decreaseBitPos(int bitPos){
        if (bitPos == 0){
            return 7;
        }
        return bitPos-1;
    }


    static byte setBit(byte b, int pos){

        b |= 1 << pos;

        return b;
    }



    static void printSize(OutputStream writer) throws IOException {
        byte[] sizeArray = ByteBuffer.allocate(4).putInt(input.length).array();

        for (byte b:
                sizeArray) {
            writer.write(b);
        }
    }





    public static void main(String[] args) throws IOException {
     //   String filename = "files/huffAbra.txt";
          String filename = "files/bible-en.txt";
      //  String filename = "files/huff/easyinput.txt";
        readInputAndCreatePriorityQueue(filename);
        long start = System.currentTimeMillis();

        createHuffmanTree();
        encodeTree();


   //     print();
        printBytes("files/huff/bibcomp.huff");

        long end = System.currentTimeMillis();
        System.out.println(end-start);


    }
}
