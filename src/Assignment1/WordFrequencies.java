
package Assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//Amir Khodabakhshi
public class WordFrequencies {


    private String fileLocation = "files/A1ExampleText";

    public WordFrequencies() throws IOException {
        countWordFrequency(cleanInput(readFile()));
    }

    private String readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
        StringBuilder sb = new StringBuilder();
        String str = "";

        //read file content
        while ((str = br.readLine()) !=null){
            sb.append(str).append(" ");
        }

        br.close();

        str = sb.toString();

        return str;
    }


    private String[] cleanInput(String str){
        str = str.toLowerCase();

        String[] wordArray = str.split(" ");

        for (int i=0; i<wordArray.length; i++){
            wordArray[i] = wordArray[i].replaceAll("[^a-z]", "");
        }

        return wordArray;
    }

    private void countWordFrequency(String[] wordArray){
        Map<String, Integer> map = new HashMap<>();

        for(String s:wordArray){
            if (!map.containsKey(s)){
                map.put(s,1);
            }else map.put(s, map.get(s) +1);

        }

        for(String key : map.keySet()){
            System.out.println(key + ": " + map.get(key));
        }

     //   return wordArray;
    }


     public static void main(String[] args) {

         try {
             new WordFrequencies();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}
