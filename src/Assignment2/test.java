package Assignment2;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class test {

    public static void main(String[] args) {
        byte[][] a = {
                {55, 44, (byte) 200},
                {3, (byte) 255},
                {1, 2, 1, 2, 1},   // the longest element, 5 bytes
                {100, 101, 102, 103}
        };


    //    System.out.println(a[1][0]);
    //    System.out.println(a[1][1]);

        String str = "abc";

   //     System.out.println((int) str.charAt(1));
        int b = 1543252514;
        b = b>>24;
        System.out.println(b);

    }
}
