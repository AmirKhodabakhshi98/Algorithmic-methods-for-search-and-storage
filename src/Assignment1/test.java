package Assignment1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class test {

    public static void main(String[] args) throws IOException {
        String file = "E:\\backup ssd\\downloads\\MAU HT 20\\algo search n storage\\abba-1-97-101";
    //   String file = "E:\\backup ssd\\downloads\\MAU HT 20\\algo search n storage\\abba-4-1630000000-1689999999";
     //  String file = "E:\\backup ssd\\downloads\\MAU HT 20\\algo search n storage\\test";


        InputStream is = new FileInputStream(file);
        int byteread = -1;

        byte[] data = Files.readAllBytes(Paths.get(file));
        System.out.println(data.length);


        while ((byteread = is.read()) != -1){ //läser in den som positiv int. vkt angående hur
            //d kmr säg att d skriva "signed" o ser negativt ut vid skrivning men läses in positivt.
            System.out.print(byteread + " ");
        }



      //  BufferedReader br = new BufferedReader(new FileReader(file));
      //  String line ="";
        //       while ((line=br.readLine()) != null){
        //        System.out.println(line);
        //     }



    }
}


