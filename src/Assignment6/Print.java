package Assignment6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Map;

class Print {




    protected static void printFile(OutputStream writer, byte[] input, int I, Map<Byte,String> huffmanCodesMap, byte[]mtfArr) throws IOException {

        printSize(writer,input);
        printI(writer,I);
        printBytes(writer, huffmanCodesMap, mtfArr);

    }



    private static void printSize(OutputStream writer, byte[]input) throws IOException {
        byte[] sizeArray = ByteBuffer.allocate(4).putInt(input.length).array();

        for (byte b:
                sizeArray) {
            writer.write(b);
        }
    }

    private static void printI(OutputStream writer, int I) throws IOException {
        byte[] array = ByteBuffer.allocate(4).putInt(I).array();

        for (byte b:
                array) {
            writer.write(b);
        }
    }



    protected static void printBytes(OutputStream writer, Map<Byte,String> huffmanCodesMap, byte[]mtfArr) throws IOException {


        byte b = 0;
        int bitPos = 7;


        for (int c = 0; c<256; c++){

            if (!huffmanCodesMap.containsKey((byte)c)){
                bitPos = decreaseBitPos(bitPos);
                b = checkForWrite(b,bitPos, writer);
            }

            if (huffmanCodesMap.containsKey((byte)c)){

                String code = huffmanCodesMap.get((byte)c);

                //unary
                for (int i=0; i<code.length();i++){
                    b = setBit(b,bitPos);
                    bitPos = decreaseBitPos(bitPos);
                    b = checkForWrite(b,bitPos, writer);
                }

                bitPos = decreaseBitPos(bitPos);//0-bit after unary

                b = checkForWrite(b,bitPos, writer);

                //print symbol codes
                for (int i = 0; i<code.length(); i++){
                    int bit = Character.getNumericValue(code.charAt(i));

                    if (bit==1){
                        b = setBit(b,bitPos);
                        bitPos = decreaseBitPos(bitPos);

                    }else if (bit == 0){
                        bitPos = decreaseBitPos(bitPos);
                    }

                    b = checkForWrite(b,bitPos, writer);
                }
            }

        }


        //print input
        for (int i=0; i<mtfArr.length; i++){

            String code = huffmanCodesMap.get(mtfArr[i]);


            for (int j=0; j<code.length(); j++){
                int bit = Character.getNumericValue(code.charAt(j));
                if (bit==0){
                    bitPos = decreaseBitPos(bitPos);

                }else if (bit==1){
                    b = setBit(b,bitPos);
                    bitPos = decreaseBitPos(bitPos);
                }

                b = checkForWrite(b,bitPos, writer);
            }

        }

        //zero padding
        if (bitPos != 7){
            checkForWrite(b,7,writer);
        }

        writer.close();


    }



    static byte checkForWrite(byte b, int bitPos, OutputStream writer) throws IOException {
        if (bitPos==7){
            writer.write(b);
            writer.flush();
            return 0;
        }
        return b;
    }
    static int decreaseBitPos(int bitPos){
        if (bitPos == 0){
            return 7;
        }
        return bitPos-1;
    }


    static byte setBit(byte b, int pos){

        b |= 1 << pos;

        return b;
    }





}
