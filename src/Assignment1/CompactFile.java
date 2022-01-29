
package Assignment1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

//Amir Khodabakhshi
public class CompactFile {

    StringBuilder output;

    public CompactFile(String filename, int b, int lo, int hi) throws IOException {

        String input = readFile(filename,b);
        setValues(input, b, lo, hi);
        writeFile();

    }


    //A*256^3+(B*256^2+(C*256^1+D)

    private void setValues(String input, int b, int lo, int hi) throws IOException {

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



     //   System.out.println(output.toString());



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

    private String[] readFile(String filename, int b) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line = "";

        //   while ((line = br.readLine()) != null){
        //        sb.append(line);
        //    }
        //    br.close();

        Path path = Paths.get(filename);
        byte[] data = Files.readAllBytes(path);

        String[] array = new String[(int) Math.ceil(((double) data.length / b))]; // funkar d alltid?

        int j=0;
        switch (b) {
            case 1:
                for (int i = 0; i < array.length; i++) {
                    array[i] = String.valueOf(data[i]);
                }

                break;

            case 2:
                for (int i=0; i<data.length; i+=b){

                    int result16 = ((data[i] & 0xff) << 8) | (data[i + 1] & 0xff);
                    array[j] = String.valueOf(result16);
                    j++;
                }

                break;

            case 3:
                for (int i=0; i<data.length; i+=b){

                    int result24 = ((data[i] & 0xff) << 16) | ((data[i+1] & 0xff) << 8) | (data[i+2] & 0xff);
                    array[j] = String.valueOf(result24);
                    j++;
                }
                break;

            case 4:
                for (int i=0; i<data.length; i+=b){

                    int result32 = ((data[i] & 0xff) << 24) | ((data[i+1] & 0xff) << 16) | ((data[i+2] & 0xff) << 8) | (data[i+3] & 0xff);
                    array[j] = String.valueOf(result32);
                    j++;
                }
                break;
        }

         System.out.println(Arrays.toString(array));

        return array;

    }






    public static void main(String[] args) throws IOException {
        new CompactFile("files/abba.txt",4,97,101);
    }

}
