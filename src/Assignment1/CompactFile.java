
package Assignment1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

//Amir Khodabakhshi
public class CompactFile {

    StringBuilder output;

    public CompactFile(String filename, int b, int lo, int hi) throws IOException {

        int[] input = readCompactWrite(filename, b, lo, hi);

    }


    private int[] readCompactWrite(String filename, int b, int lo, int hi) throws IOException {

        Path path = Paths.get(filename);
        byte[] data = Files.readAllBytes(path);
        int[] input = new int[(int) Math.ceil(((double) data.length / b))]; //input array based on byta data and b size

        int j=0; //to traverse the input array for b>1

        //for loops traverse input array and based on b put together bits to a single value.
        switch (b) {
            case 1:
                for (int i = 0; i < input.length; i++) {
                    input[i] = data[i]-lo;
                }
                break;

            case 2:
                for (int i=0; i<data.length; i+=b){

                    int result16 = ((data[i]) << 8) | (data[i + 1]);
                    input[j] = result16-lo;
                    j++;
                }
                break;

            case 3:
                for (int i=0; i<data.length; i+=b){

                    int result24 = ((data[i]) << 16) | ((data[i+1]) << 8) | (data[i+2]);
                    input[j] = result24 - lo;
                    j++;
                }
                break;

            case 4:
                for (int i=0; i<data.length; i+=b){

                    int result32 = ((data[i]) << 24) | ((data[i+1] ) << 16) | ((data[i+2] ) << 8) | (data[i+3] );
                    input[j] = result32-lo;
                    j++;
                }
                break;
        }

        System.out.println(Arrays.toString(input));


        int minBits = (int) Math.ceil (Math.log(hi-lo+1) / Math.log(2)); //minimum nbr of bits required is calculated as 2-log of hi-lo-1 rounded up
        int nbrOfBits =  input.length * minBits; //total nbr of bits required to represent the trimmed input
        int nearest8Multiple =  8 * (int) (Math.ceil((nbrOfBits)/8.0)); //closest multiple of 8, to know how many 0s to pad

        StringBuilder sb = new StringBuilder(nbrOfBits);

        for (int i=0; i<input.length; i++){

            //left pad the binary nbrs with 0s to reach minimum nbr of required bits.
            String bits = String.format("%" + minBits + "s",  Integer.toBinaryString((input[i]))) //left pad with spaces to reach minbit
                    .replace(' ', '0'); //replace spaces with 0
            sb.append(bits);
        }


        //pad output with 0s to reach nearest multiple of 8
        if (nbrOfBits != nearest8Multiple){
            sb.append("0".repeat( nearest8Multiple - nbrOfBits));
        }

        String str = sb.toString();

        System.out.println(str);

        byte[] output = new byte[nearest8Multiple/8];

        int index = 0;

        //split up the string into 8-bit pieces to be stored in a byte array
        for (int i=0; i<nearest8Multiple; i +=8){

            output[index] = (byte) Integer.parseInt(str.substring(i,i+8),2);
            index++;
        }

        System.out.println(Arrays.toString(output));

        Files.write(Paths.get("E:\\backup ssd\\downloads\\MAU HT 20\\algo search n storage\\test"), output);

        return input;

    }





    public static void main(String[] args) throws IOException {
        new CompactFile("files/abba.txt",4,1630000000,1689999999);
    }



}
