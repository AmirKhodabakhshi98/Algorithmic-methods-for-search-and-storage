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


    public Trie(){

    };

    private ArrayList<Integer> searchTwoWords(ArrayList<String> searchList){
        String s1 = searchList.get(0);
        String s2 = searchList.get(1);
        ArrayList<Integer> s1Results = get(s1);
        ArrayList<Integer> s2Results = get(s2);
        ArrayList<Integer> matches = new ArrayList<>();

        int s1Index=0;
        int s2Index=0;

        while (s1Index < s1Results.size() && s2Index < s2Results.size()){

            if (s1Results.get(s1Index) < s2Results.get(s2Index)){

                s1Index = skipTo(s1Results, s2Results.get(s2Index));

            }else {

                s2Index = skipTo(s2Results, s1Results.get(s1Index));
            }

            //finished searching smaller array, avoid out of bounds
            if (s1Index== -1 || s2Index == -1){
                break;
            }

            if (s1Results.get(s1Index).equals(s2Results.get(s2Index))){

                matches.add(s1Results.get(s1Index));
                s1Index++;
            }
        }

        return matches;

    }


    private ArrayList<Integer> searchMultipleWords(ArrayList<String> searchList) {
        int k = searchList.size();

        ArrayList<Integer> t0 = get(searchList.get(0));

        ArrayList<Integer> results = new ArrayList<>();

        ArrayList<ArrayList<Integer>> lw = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<searchList.size();i++){
            lw.add(get(searchList.get(i))); //funkar, hämtar alla instancer för varje ord.
        }

        int d= get(searchList.get(0)).get(0);
        int matches = 0;

        int count = 0;
        for (int j=0; j<t0.size(); j++){
            matches = 0;
            d = t0.get(j);
//öka d på nåt sätt.
            for (int w=0; w<lw.size();w++){

                int skipTo = skipTo(lw.get(w),d);
                if (skipTo == -1){
                    continue;
                }
                int f = get(searchList.get(w)).get(skipTo);

                if (f==d){
                    matches++;
                }else {
                    d = f;
                    matches = 0;
                }
            }
            if (matches==k){
                results.add(d);
            }
            count++;

        }

        return results;
    }





    private int skipTo(ArrayList<Integer> list, int searchValue){

        for (int i=0; i<list.size(); i++){
            if (list.get(i)==searchValue){
                return i;
            }
            if (list.get(i)>searchValue){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        String filelocation = "files/oldhouse.txt";
     //      String filelocation = "files/bible-washed.txt";
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
            String key = JOptionPane.showInputDialog("write words pls & thanks");


            if (key==null){
                break;
            }

            String[] keyWords = key.split(" ");

            ArrayList<String> searchWords = new ArrayList<>(Arrays.asList(keyWords));

            ArrayList<Integer> results = trie.searchMultipleWords(searchWords);

            StringBuilder sb1 =  new StringBuilder();

            for (int j=0; j<results.size(); j++){
                sb1.append(results.get(j)).append(", ");
            }

            System.out.println(sb1.toString());

        }

    }



}
