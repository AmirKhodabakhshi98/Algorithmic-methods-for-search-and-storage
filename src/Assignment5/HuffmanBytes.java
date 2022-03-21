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

        root = new Node(queue.poll(), queue.poll()); //FIXA ISSUE DÄR D BA FINNS 1 TECKEN.

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



    static void print() throws IOException {
        StringBuilder sb = new StringBuilder();

        String lengthOut = String.format("%016d",Integer.parseInt(Integer.toBinaryString(input.length)));
//        System.out.println(lengthOut);
        sb.append(lengthOut).append("\n");


        for (int c = 0; c<256; c++){

            if (!huffmanCodesMap.containsKey((byte)c)){
                while (!huffmanCodesMap.containsKey((byte)c) && c<256){
                    sb.append("0");
                    c++;
                }
                sb.append("\n");
            }

             if (huffmanCodesMap.containsKey((byte)c)){
                 String code = huffmanCodesMap.get((byte)c);

                 String unary = String.format("%" + code.length() + "s","").replace(' ','1');
                 sb.append(unary).append('0');
                 sb.append("\n");
                 sb.append((huffmanCodesMap.get((byte)c)));
                 sb.append("\n");
            }

        }

        sb = inputToHuffman(sb);


        String outFile = "files/abracompd.huff";
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));




        writer.write(Byte.parseByte(sb.toString()));
        writer.close();
    }





/*
    static void printBytesOLD() throws IOException {
        ArrayList<Integer> list = new ArrayList<>();

        String outFile = "files/huff/abracomp.huff";
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

        printSize(writer);

        byte b = 0;
        int bitPos = 7;

        int counter = 0;
        for (int c = 0; c<256; c++){

            if (!huffmanCodesMap.containsKey((byte)c)){ //CHECKED
                while (!huffmanCodesMap.containsKey((byte)c) && c<256){ //CHECKED
                    list.add(0);
                    c++;
                    bitPos = decreaseBitPos(bitPos);
                    if (bitPos== 7){

                        printer(writer,b);
                        b = 0;
                    }
                }
            }

            if (huffmanCodesMap.containsKey((byte)c)){ //CHECKED

                String code = huffmanCodesMap.get((byte)c); //CHECKED

                //unary
                for (int i=0; i<code.length();i++){ //CHECKED
                    list.add(1);
                    b = setBit(b,bitPos);
                    bitPos = decreaseBitPos(bitPos);

                    if (bitPos==7){
                        printer(writer,b);
                        b=0;
                    }
                }

                bitPos = decreaseBitPos(bitPos);//0-bit after unary
                list.add(0);
                if (bitPos==7){
                    printer(writer,b);
                    b=0;
                }

                //print huffcode
                for (int i = 0; i<code.length(); i++){
                    int bit = Character.getNumericValue(code.charAt(i));

                    if (bit==1){
                        b = setBit(b,bitPos);
                        bitPos = decreaseBitPos(bitPos);
                        list.add(1);

                    }else if (bit == 0){
                        list.add(0);
                        bitPos = decreaseBitPos(bitPos);
                    }

                    if (bitPos == 7 ){
                        printer(writer,b);
                        b=0;
                    }
                }
            }

        }


        for (int i=0; i<input.length; i++){

            String code = huffmanCodesMap.get(input[i]);
            System.out.println(code);
            for (int j=0; j<code.length(); j++){
                int bit = Character.getNumericValue(code.charAt(j));
                    if (bit==0){
                        list.add(0);
                        bitPos = decreaseBitPos(bitPos);

                    }else if (bit==1){
                        list.add(1);
                        b = setBit(b,bitPos);
                        bitPos = decreaseBitPos(bitPos);
                    }

                    if (bitPos==7){
                        printer(writer,b);
                        b=0;
                    }
            }

        }

      //  System.out.println(counter);

        writer.close();


        for (int e:
             list) {
            System.out.print(e);

        }


    }

*/

    static void printBytes() throws IOException {
        ArrayList<Integer> list = new ArrayList<>();

        String outFile = "files/huff/abracomp.huff";
          BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));


        File file = new File("files/huff/abracomp.huff");
       // OutputStream writer = new FileOutputStream(file);

        printSize(writer);
        debugRead();

        byte b = 0;
        int bitPos = 7;

        int counter = 0;
        for (int c = 0; c<256; c++){

            if (!huffmanCodesMap.containsKey((byte)c)){ //CHECKED
                    list.add(0);
                    bitPos = decreaseBitPos(bitPos);
                    if (bitPos== 7){
                        counter++;
                        writer.write(b);
                        writer.flush();
                        b = 0;
                        debugRead();
                    }
            }

            if (huffmanCodesMap.containsKey((byte)c)){ //CHECKED

                String code = huffmanCodesMap.get((byte)c); //CHECKED

                //unary
                for (int i=0; i<code.length();i++){ //CHECKED
                    list.add(1);
                    b = setBit(b,bitPos);
                    bitPos = decreaseBitPos(bitPos);

                    if (bitPos==7){
                        counter++;
                        writer.write(b);
                        writer.flush();
                        b=0;
                        debugRead();
                    }
                }

                bitPos = decreaseBitPos(bitPos);//0-bit after unary
                list.add(0);
                if (bitPos==7){
                    counter++;
                    writer.write(b);
                    writer.flush();
                    b=0;
                    debugRead();
                }

                //print huffcode
                for (int i = 0; i<code.length(); i++){
                    int bit = Character.getNumericValue(code.charAt(i));

                    if (bit==1){
                        b = setBit(b,bitPos);
                        bitPos = decreaseBitPos(bitPos);
                        list.add(1);

                    }else if (bit == 0){
                        list.add(0);
                        bitPos = decreaseBitPos(bitPos);
                    }

                    if (bitPos == 7 ){
                        counter++;
                        writer.write(b);
                        writer.flush();
                        b=0;
                        debugRead();
                    }
                }
            }

        }


        for (int i=0; i<input.length; i++){

            String code = huffmanCodesMap.get(input[i]);

            for (int j=0; j<code.length(); j++){
                int bit = Character.getNumericValue(code.charAt(j));
                if (bit==0){
                    list.add(0);
                    bitPos = decreaseBitPos(bitPos);

                }else if (bit==1){
                    list.add(1);
                    b = setBit(b,bitPos);
                    bitPos = decreaseBitPos(bitPos);
                }

                if (bitPos==7){
                    counter++;
                    writer.write(b);
                    writer.flush();
                    debugRead();
                    b=0;
                }
            }

        }

          System.out.println("counter: " + counter);

        writer.close();
        debugRead();
/*

        for (int e:
                list) {
            System.out.print(e);

        }


 */

    }
/*
    static void printer(BufferedWriter writer, byte b) throws IOException {
        writer.write(b);
    }


    static void printer(OutputStream writer, byte b) throws IOException {
        writer.write(b);
    }


 */

    static void debugRead() throws IOException {
        Path path = Paths.get("files/huff/abracomp.huff");
        byte[] arr = Files.readAllBytes(path);
        System.out.println(arr.length);

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

    static void printSize(BufferedWriter writer) throws IOException {
        byte[] sizeArray = ByteBuffer.allocate(4).putInt(input.length).array();

        for (byte b:
                sizeArray) {
            writer.write(b);
            writer.flush();
        }
    }


    static StringBuilder inputToHuffman(StringBuilder sb){

        for (int i=0; i<input.length; i++){
            sb.append(huffmanCodesMap.get(input[i]));
            sb.append("\n");
        }

        return sb;
    }



    public static void main(String[] args) throws IOException {
        String filename = "files/huffAbra.txt";
       //   String filename = "files/bible-en.txt";
    //    String filename = "files/huff/easyinput.txt";
        readInputAndCreatePriorityQueue(filename);
        createHuffmanTree();
        encodeTree();
   //     print();
        printBytes();




    }
}
