package Assignment3.Search;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

   //     private int index;
        private ArrayList<Integer> indexList = new ArrayList<>();
        private Node[] next = new Node[alphabetSize];

        private void addIndex(int index){
            boolean exists = false;
            for (int i=0; i<indexList.size(); i++){
                if (index == indexList.get(i)){
                    exists=true;
                    break;
                }
            }
            if (exists == false){
                indexList.add(index);
            }
        }
    }


    public void put(String key, int index)
    {
        root = put(root,key,0, index); //dvs lägger till nya barn till root när den skickar tbx.
    }

    public Node put(Node node, String key, int pos, int index){
        if (node==null){
            node = new Node();
        }
        if (pos==key.length()){
            node.addIndex(index);
            return node;
        }
        int c = key.charAt(pos) - lo;
        node.next[c] = put(node.next[c], key, pos+1, index );
        return node;
    }

    public ArrayList<Integer> get(String key){
        Node node = get(root,key,0);
        if (node==null){
            return null;
        }
        return node.indexList;
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





    public static void main(String[] args) throws IOException {
   //     String filelocation = "files/oldhouse.txt";
           String filelocation = "files/bible-washed.txt";
        BufferedReader br = new BufferedReader(new FileReader(filelocation));
        StringBuilder sb = new StringBuilder();
        String str = "";

        Trie trie = new Trie();
        long start = System.currentTimeMillis();
        int index = 1;
        while ((str=br.readLine()) != null){
            sb = new StringBuilder();
            sb.append(str);
            str = sb.toString();
            String[] arr = str.split(" ");
            for (int i = 0; i< arr.length; i++){
                trie.put(arr[i],index);
            }
            index++;
        }
        br.close();
        long end = System.currentTimeMillis();
        long duration = end-start;
        System.out.println("build duration: " + duration);



        while (true){
            String key = JOptionPane.showInputDialog("searchword");
            if (key==null){
                break;
            }
            ArrayList<Integer> results = trie.get(key);

            StringBuilder sb1 =  new StringBuilder();
            sb1.append(key).append(": ");
            for (int j=0; j<results.size(); j++){
                sb1.append(results.get(j)).append(", ");
            }
            end = System.currentTimeMillis();

            System.out.println(sb1.toString() + "duration: " );

        }

    }



}
