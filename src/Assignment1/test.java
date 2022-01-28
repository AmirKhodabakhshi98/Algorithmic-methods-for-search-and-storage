package Assignment1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class test {

    public static void main(String[] args) throws IOException {
     //   String file = "E:\\backup ssd\\downloads\\MAU HT 20\\algo search n storage\\abba-1-97-101";
    //   String file = "E:\\backup ssd\\downloads\\MAU HT 20\\algo search n storage\\abba-4-1630000000-1689999999";
        String file = "E:\\backup ssd\\downloads\\MAU HT 20\\algo search n storage\\test";

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line ="";

        InputStream is = new FileInputStream(file);
        int byteread = -1;


 //       while ((line=br.readLine()) != null){
    //        System.out.println(line);
   //     }

        while ((byteread = is.read()) != -1){
            System.out.print(byteread + " ");
        }

     //   for (int i=0; i<data.length; i++){
        //    System.out.println(data[i]);
       //     System.out.println(Arrays.toString(data));
       // }

    }
}


