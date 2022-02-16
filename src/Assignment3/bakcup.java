/*
package Assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Trie {


    private static final int R=256;
    private Node root = new Node();


    public int get(String key){
        Node node = get(root,key,0);
        if (node==null){
            return 0;
        }
        return node.frequency;
    }

    public Node put(Node node, String key, int pos){
        if (node==null){
            node = new Node();
        }
        if (pos==key.length()){
            node.frequency++;
            return node;
        }
        char c = key.charAt(pos);
        node.next[c] = put(node.next[c], key, pos+1 );
        return node;
    }


    private Node get(Node node, String key, int pos){
        if (node==null){
            return null;
        }
        if (pos==key.length()){
            return node;
        }
        char c = key.charAt(pos);
        return get(node.next[c],key,pos+1);
    }


    private static class Node
    {

        private int frequency = 0;
        private Node[] next = new Node[R];
    }




    public void put(String key){
        root = put(root,key,0); //dvs lägger till nya barn till root när den skickar tbx.
    }



    public static void main(String[] args) throws IOException {
        String filelocation = "files/A1ExampleText";
        BufferedReader br = new BufferedReader(new FileReader(filelocation));
        StringBuilder sb = new StringBuilder();
        String str = "";

        while ((str=br.readLine()) != null){
            sb.append(str).append(" ");
        }
        br.close();

        str=sb.toString();
        str.toLowerCase();
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




    }



}
*/