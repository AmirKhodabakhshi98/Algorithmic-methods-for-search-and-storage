package Assignment5;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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





    static void printBytes() throws IOException {
        String outFile = "files/test.huff";
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

        printSize(writer);

        byte b = 0;
        int bitPos = 0;

        for (int c = 0; c<256; c++){

            if (!huffmanCodesMap.containsKey((byte)c)){
                while (!huffmanCodesMap.containsKey((byte)c) && c<256){
                 //   bitPos++; //print single 0-bit
                    c++;
                    bitPos = increaseBitPos(bitPos);
                    if (bitPos== 0){
                        writer.write(b);
                        b = 0;
                    }
                }
            }

            if (huffmanCodesMap.containsKey((byte)c)){

                String code = huffmanCodesMap.get((byte)c);

                //unary
                for (int i=0; i<code.length();i++){
                    b = setBit(b,bitPos);
                    bitPos = increaseBitPos(bitPos);
                    if (bitPos==0){
                        writer.write(b);
                        b=0;
                    }
                }

                bitPos = increaseBitPos(bitPos);
                if (bitPos==0){ //0-bit after unary
                    writer.write(b);
                    b=0;
                }

                //print huffcode
                for (int i = 0; i<code.length(); i++){
                    int bit = Character.getNumericValue(code.charAt(i));

                    if (bit==1){
                        b = setBit(b,bitPos);
                        bitPos = increaseBitPos(bitPos);

                    }else if (bit == 0){
                        bitPos = increaseBitPos(bitPos);
                    }

                    if (bitPos == 0 ){
                        writer.write(b);
                        b=0;
                    }

                }



            //    sb.append((huffmanCodesMap.get((byte)c)));

            }

        }

        writer.close();

    }



    static int increaseBitPos(int bitPos){
        return (bitPos+1) % 8;
    }


    static byte setBit(byte b, int pos){

        b |= 1 << pos;

        return b;
    }






    static void printSize(BufferedWriter writer) throws IOException {
        byte[] sizeArray = ByteBuffer.allocate(4).putInt(input.length).array();

        for (byte b:
                sizeArray) {
            writer.write(b);
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
      //  String filename = "files/huffAbra.txt";
        //  String filename = "files/bible-en.txt";
        String filename = "files/abra.txt";
        readInputAndCreatePriorityQueue(filename);
        createHuffmanTree();
        encodeTree();
   //     print();
        printBytes();




    }
}
