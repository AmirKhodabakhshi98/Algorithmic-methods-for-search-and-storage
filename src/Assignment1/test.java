package Assignment1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class test {

    public static void main(String[] args) throws IOException {

        int length = 5;
        String unary = String.format("%" + length + "s","").replace(' ','1');

        //       String bits = String.format("%" + minBits + "s",  Integer.toBinaryString((input[i]))) //left pad with spaces to reach minbit
        //              .replace(' ', '0'); //replace spaces with 0
        //       sb.append(bits);

        System.out.println(unary);
    }
}


