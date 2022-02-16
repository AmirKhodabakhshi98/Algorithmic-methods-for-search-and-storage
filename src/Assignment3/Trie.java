package Assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Trie {


    private static int alphabetSize = 26;
    private int lo = 97;
    private int hi = 122;
    private Node root = new Node();

    public Trie(int lo, int hi){
        this.lo=lo;
        alphabetSize = hi-lo+1;
    }

    public Trie(){

    };


    private static class Node
    {

        private int frequency = 0;
        private Node[] next = new Node[alphabetSize];
    }


    public void put(String key){
        root = put(root,key,0); //dvs lägger till nya barn till root när den skickar tbx.
    }

    public Node put(Node node, String key, int pos){
        if (node==null){
            node = new Node();
        }
        if (pos==key.length()){
            node.frequency++;
            return node;
        }
        int c = key.charAt(pos) - lo;
        node.next[c] = put(node.next[c], key, pos+1 );
        return node;
    }

    public int get(String key){
        Node node = get(root,key,0);
        if (node==null){
            return 0;
        }
        return node.frequency;
    }

    private Node get(Node node, String key, int pos){
        if (node==null){
            return null;
        }
        if (pos==key.length()){
            return node;
        }
        int c = key.charAt(pos)- lo;
        return get(node.next[c],key,pos+1);
    }





/*
    public void getAll(){
        StringBuilder sb = new StringBuilder();
        getAll(root, sb);
    }

    public char getAll(Node node, StringBuilder sb){

        if (node.frequency != 0){
            System.out.println(sb.toString() + " " + node.frequency);
        }
        for (int i=0; i< alphabetSize;i++ ){
            if (node.next[i] != null){
                sb.append((char)(i+lo));
                getAll(node.next[i],sb);
            }
        }

        return ' ';
    }
 */
    public static void main(String[] args) throws IOException {
        String filelocation = "files/A1ExampleText";
        BufferedReader br = new BufferedReader(new FileReader(filelocation));
        StringBuilder sb = new StringBuilder();
        String str = "";

        while ((str=br.readLine()) != null){
            sb.append(str).append(" ");
        }
        br.close();

        str = sb.toString();
        str = str.toLowerCase();
        String[] wordArray = str.split(" ");
        for (int i=0; i< wordArray.length;i++){
            wordArray[i] = wordArray[i].replaceAll("[^a-z]","");
        }

        Trie t = new Trie();
        for (String s:wordArray){
            t.put(s);
        }

        for(String key: wordArray){
            System.out.println(key + ": " + t.get(key));
        }

        System.out.println(Arrays.toString(wordArray));



    }



}
