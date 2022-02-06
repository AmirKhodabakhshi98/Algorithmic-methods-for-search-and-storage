package Assignment2;

public class Example {
    interface GetDigitHandle<T> {
        int getDigit(T elem, int i);
    }

    static <T> int maxLength(T[] arr, GetDigitHandle<T> gbh) {
        int i, j, maxl = -1;

        for (i = 0; i < arr.length; i++) {
            j = 0;
            while (gbh.getDigit(arr[i], j) >= 0) {
                j++; }
                if (maxl < j) {
                        maxl = j; }
        }
        return maxl;
    }

    static GetDigitHandle<byte[]> arrayByteGetter = new GetDigitHandle<byte[]>() {
        public int getDigit(byte[] elem, int i) {
            if (i < elem.length) {
                return elem[i]; }
            else                 {
                return -1; }
        }
    };

    // Example use:
    public static void main(String[] args) {
        byte[][] a = {
                { 55, 44, (byte) 200 },
                { 3, (byte) 255 },
                { 1, 2, 1, 2, 1},   // the longest element, 5 bytes
                { 100, 101, 102, 103}
        };

        int m = maxLength(a, arrayByteGetter);
        System.out.println(m);  // outputs 5
    }
}