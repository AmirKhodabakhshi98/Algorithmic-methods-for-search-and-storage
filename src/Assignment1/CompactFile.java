
package Assignment1;

import java.io.*;
import java.nio.charset.StandardCharsets;

//Amir Khodabakhshi
public class CompactFile {

    StringBuilder output;

    public CompactFile(String filename, int B, int lo, int hi) throws IOException {

        String input = readFile(filename);
        setValues(input, B, lo, hi);
        writeFile();

    }


    private void setValues(String input, int B, int lo, int hi) throws IOException {

        char[] chars = input.toCharArray();
        String[] binaryString = new String[chars.length];

        int minbits = (int) Math.ceil (Math.log(hi-lo+1) / Math.log(2)); //minimum nbr of bits required is calculated as 2-log of hi-lo-1 rounded up

        int nbrOfBits = minbits * chars.length;
        int nearestByteMultiple =  8 * (int) (Math.ceil((nbrOfBits)/8.0));

        output = new StringBuilder(nearestByteMultiple);

        for (int i = 0; i< chars.length; i++){

            binaryString[i] = Integer.toBinaryString (((int) chars[i]) - lo); //subtracts low from char value and transforms it to binary

            //if nbr of digits is less than min required bits, add zeroes to its left to fix it
            if (binaryString[i].length() < minbits){

                StringBuilder intToBinary = new StringBuilder(minbits);

                //loop to add enough zeroes to the left of a binary string so that it reaches min required digits. eg 1 --> 001 for 3 bits
                for (int j = 0; j<(minbits-binaryString[i].length()); j++){
                    intToBinary.append(0);

                    output.append(0);
                }
                intToBinary.append(binaryString[i]);
                output.append(binaryString[i]);
                binaryString[i] = intToBinary.toString(); //saves the binary value of an input integer
            }
        }


        //adds 0s to the end of the output so that it reaches the nearest multiple of 8
        //append by a new string that is formed by a new char array. the size of char array is the difference between
        //nearest byte and current nbr of bits. \0 is the default char and is replaced by 0s
        output.append(new String(new char[nearestByteMultiple-nbrOfBits]).replace("\0", "0"));
        String str = output.toString();
        


        System.out.println(output.toString());



    }

    private void writeFile() throws IOException {
        FileWriter fileWriter = new FileWriter("E:\\backup ssd\\downloads\\MAU HT 20\\algo search n storage\\test");

     //   int x = new Integer(128);
        byte aByte = (byte) 0b10000110;
        int b = aByte & 0xFF;
        byte[] arr = {aByte};

        fileWriter.write(arr[0]);
        fileWriter.close();
    }

    private String readFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line = "";

        while ((line = br.readLine()) != null){
            sb.append(line);
        }
        br.close();

        line = sb.toString();
        return line;

    }


    public static void main(String[] args) throws IOException {
        new CompactFile("files/abba.txt",1,97,101);
    }

}
