package Assignment5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class test {

    static int increaseBitPos2(int bitPos){
        return (bitPos+1) % 8;
    }



    static int increaseBitPos(int bitPos){
        return (bitPos+1) % 4;
    }


    static byte setBit(byte b, int pos){

        b |= 1 << pos;

        return b;
    }
    public static void main(String[] args) throws IOException {

        String code = "11001100";
        byte b = 0;
        int bitpos = 0;

        for (int i=0; i<code.length(); i++){
            int bit = Character.getNumericValue(code.charAt(i));

            if (bit==1){
                b = setBit(b,bit);
                bitpos = increaseBitPos(bitpos);
            }
            else if (bit == 0){
                bitpos = increaseBitPos(bitpos);


            }
            if (bitpos == 0 ){
                System.out.println(String.valueOf(b));
                //b=0;
            }
        }

      //  System.out.println(b);










       // byte b = 100;

     //   int pos = 3;

   //     System.out.println( b |= 1 << pos);

       // int x = 5;
//
    //    if (increaseBitPos2(x)==9){
    //        System.out.println("a");
   //     }

   //     increaseBitPos2(x);
   //     System.out.println(x);


        /*
        int bitPos = 0;
        byte b = 0;
        for (int i=0; i<3;i++){

            b = setBit(b,bitPos);
            System.out.println(b);
            bitPos = increaseBitPos(bitPos);
            if (bitPos==0){
                System.out.println(b);
                b=0;
            }
        }
        bitPos = increaseBitPos(bitPos);
        if (bitPos==0){
            System.out.println(b);
        }



 */


/*
        int counter = 0;
        int bit = -1;

        while (counter<256){
            bit++;
            System.out.println(bit%8);
            counter++;
        }


        String outfile = "files/test.huff";
        BufferedWriter writer = new BufferedWriter(new FileWriter(outfile));

        writer.write(0x01);
        writer.write(0x01);
        writer.write(0x01);
        writer.write(0x01);
        writer.write(0x01);
        writer.write(0 & 0x01);
        writer.write(0 & 0x01);
        writer.write(0 & 0x01);

        byte c = 12;
      //  writer.write(c);;
        writer.close();

        byte[]data = Files.readAllBytes(Paths.get(outfile));
        for (byte b:
             data) {
            System.out.println(b);

        }

 */
    }



}
